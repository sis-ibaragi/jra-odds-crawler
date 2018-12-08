package crawler.jra.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import crawler.jra.dto.KaisaiDto;
import crawler.jra.dto.RaceDto;
import crawler.jra.dto.RaceTnpkNinDto;
import crawler.jra.dto.RaceUmrnNinDto;
import crawler.jra.page.KaisaiSelectPage;
import crawler.jra.page.RaceSelectPage;
import crawler.jra.page.RaceTnpkNinPage;
import crawler.jra.page.RaceTnpkUmaPage;
import crawler.jra.page.RaceUmrnNinPage;
import crawler.jra.page.RaceUmrnUmaPage;
import crawler.jra.page.TopPage;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author kilim
 *
 */
@Slf4j
public class JraOddsCrawler {

	/** db.properties */
	private Properties dbProperties;

	/** sql.properties */
	private Properties sqlProperties;

	/** parameter.properties */
	private Properties parameterProperties;

	/**
	 * .
	 */
	public JraOddsCrawler() {
		this.dbProperties = new Properties();
		try {
			this.dbProperties.load(this.getClass().getClassLoader().getResourceAsStream("database.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		this.sqlProperties = new Properties();
		try {
			this.sqlProperties.load(this.getClass().getClassLoader().getResourceAsStream("sql.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		this.parameterProperties = new Properties();
		try {
			this.parameterProperties.load(this.getClass().getClassLoader().getResourceAsStream("parameter.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		log.info("処理を開始します。");
		JraOddsCrawler main = new JraOddsCrawler();
		try {
			main.execute();
		} catch (Exception e) {
			log.error("実行中にエラーが発生しました。", e);
		} finally {
			log.info("処理が終了しました。");
		}
	}

	/**
	 * .
	 */
	private void execute() {

		try (Connection conn = DriverManager.getConnection(
				this.dbProperties.getProperty("database"),
				this.dbProperties.getProperty("username"),
				this.dbProperties.getProperty("password"))) {
			conn.setAutoCommit(false);

			deleteOddsData(conn);
			parseOddsPage(conn);
			conn.commit();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * .
	 * @param conn DB コネクション
	 */
	private void parseOddsPage(Connection conn) {

		// JRA トップページ -> 開催選択ページに遷移
		KaisaiSelectPage kaisaiSelectPage = new TopPage().goKaisaiSelectPage();

		// 開催一覧をループ
		kaisaiSelectPage.parse().getKaisaiCnameMap().keySet().forEach(kaisaiName -> {
			// レース選択ページに遷移
			RaceSelectPage raceSelectPage = kaisaiSelectPage.goRaceSelectPage(kaisaiName);
			// 開催日を指定
			raceSelectPage.setFilterKaisaiDt(this.parameterProperties.getProperty("kaisai.date"));

			// KAISAI テーブルへデータを登録する
			saveKaisaiData(conn, raceSelectPage.parse().getKaisaiDto());

			// レース一覧をループ
			Optional.ofNullable(raceSelectPage.parse().getRacePageCnameMap())
					.orElse(Collections.emptyMap()).keySet().forEach(raceNo -> {
						// 単複オッズ（馬番順）ページに遷移
						RaceTnpkUmaPage tnpkUmaPage = raceSelectPage.goRaceTnpkUmaPage(raceNo);
						// 単複オッズ（人気順）ページに遷移
						RaceTnpkNinPage tnpkPage = tnpkUmaPage.goRaceTnpkNinPage();
						tnpkPage.parse();
						log.debug(tnpkPage.getRaceDto().toString());
						log.debug(tnpkPage.getRaceTnpkNinList().toString());

						// RACE、RACE_UMA_LIST テーブルへデータを登録する
						saveRaceData(conn, tnpkPage.getRaceDto(), tnpkPage.getRaceTnpkNinList());

						// 馬連オッズ（馬番順）ページに遷移
						RaceUmrnUmaPage umrnUmaPage = raceSelectPage.goRaceUmrnUmaPage(raceNo);
						// 馬連オッズ（人気順）ページに遷移
						RaceUmrnNinPage umrnPage = umrnUmaPage.goRaceUmrnNinPage();
						umrnPage.parse();
						log.debug(umrnPage.getRaceDto().toString());
						log.debug(umrnPage.getRaceUmrnNinList().toString());

						// RACE_ODDS テーブルとその配下のテーブルへデータを登録する
						saveOddsData(conn, tnpkPage.getRaceDto(), tnpkPage.getRaceTnpkNinList(), umrnPage.getRaceDto(),
								umrnPage.getRaceUmrnNinList());
					});
		});
	}

	/**
	 * .
	 * @param conn DB コネクション
	 */
	private void deleteOddsData(Connection conn) {
		// 当日の初回実行の場合のみデータを削除する
		if (!parameterProperties.getProperty("odds.time.no").equals("1")) {
			return;
		}
		try (Statement stmt = conn.createStatement()) {

			stmt.executeUpdate(this.sqlProperties.getProperty("sql.raceOddsUmrn.deleteAll"));
			stmt.executeUpdate(this.sqlProperties.getProperty("sql.raceOddsFuku.deleteAll"));
			stmt.executeUpdate(this.sqlProperties.getProperty("sql.raceOddsTan.deleteAll"));
			stmt.executeUpdate(this.sqlProperties.getProperty("sql.raceOdds.deleteAll"));
			stmt.executeUpdate(this.sqlProperties.getProperty("sql.raceUmaList.deleteAll"));
			stmt.executeUpdate(this.sqlProperties.getProperty("sql.race.deleteAll"));
			stmt.executeUpdate(this.sqlProperties.getProperty("sql.kaisai.deleteAll"));

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * KAISAI テーブルへデータを登録します。
	 * @param conn DB コネクション
	 * @param kaisaiDto .
	 */
	private void saveKaisaiData(Connection conn, KaisaiDto kaisaiDto) {
		if (kaisaiDto == null) {
			return;
		}
		// 当日の初回実行の場合のみ開催情報を登録する
		if (!this.parameterProperties.getProperty("odds.time.no").equals("1")) {
			return;
		}
		// KAISAI テーブルへデータを登録する
		try (PreparedStatement ps = conn.prepareStatement(this.sqlProperties.getProperty("sql.kaisai.insert"))) {

			ps.setString(1, kaisaiDto.getKaisaiCd());
			ps.setString(2, kaisaiDto.getKaisaiNm());
			ps.setDate(3, kaisaiDto.getKaisaiDt() == null ? null : Date.valueOf(kaisaiDto.getKaisaiDt()));
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * RACE、RACE_UMA_LIST テーブルへデータを登録します。
	 * @param conn DB コネクション
	 * @param raceTnpkDto .
	 * @param raceTnpkNinList .
	 */
	private void saveRaceData(
			Connection conn,
			RaceDto raceTnpkDto,
			List<RaceTnpkNinDto> raceTnpkNinList) {

		// 当日の初回実行の場合のみレース情報を登録する
		if (!this.parameterProperties.getProperty("odds.time.no").equals("1")) {
			return;
		}

		// RACE へデータを登録する
		try (PreparedStatement ps = conn.prepareStatement(this.sqlProperties.getProperty("sql.race.insert"))) {

			ps.setString(1, raceTnpkDto.getKaisaiCd());
			ps.setInt(2, Integer.valueOf(raceTnpkDto.getRaceNo()));
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		// RACE_UMA_LIST へデータを登録する
		try (PreparedStatement ps = conn
				.prepareStatement(this.sqlProperties.getProperty("sql.raceUmaList.insert"))) {

			raceTnpkNinList.forEach(dto -> {
				try {
					ps.setString(1, raceTnpkDto.getKaisaiCd());
					ps.setInt(2, Integer.valueOf(raceTnpkDto.getRaceNo()));
					ps.setInt(3, Integer.valueOf(dto.getUmaNo()));
					ps.setInt(4, Integer.valueOf(dto.getWakuNo()));
					ps.setString(5, dto.getUmaNm());
					ps.setString(6, dto.getJockeyNm());
					ps.executeUpdate();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			});
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * RACE_ODDS テーブルとその配下のテーブルへデータを登録します。
	 * @param conn DB コネクション
	 * @param raceTnpkDto .
	 * @param raceTnpkNinList .
	 * @param raceUmrnDto .
	 * @param raceUmrnNinList .
	 */
	private void saveOddsData(
			Connection conn,
			RaceDto raceTnpkDto,
			List<RaceTnpkNinDto> raceTnpkNinList,
			RaceDto raceUmrnDto,
			List<RaceUmrnNinDto> raceUmrnNinList) {

		// RACE_ODDS テーブルへデータを登録する
		try (PreparedStatement ps = conn
				.prepareStatement(this.sqlProperties.getProperty("sql.raceOdds.insert"))) {

			ps.setString(1, raceTnpkDto.getKaisaiCd());
			ps.setInt(2, Integer.valueOf(raceTnpkDto.getRaceNo()));
			ps.setInt(3, Integer.valueOf(parameterProperties.getProperty("odds.time.no")));
			ps.setTime(4, raceTnpkDto.getOddsTm() == null ? null : Time.valueOf(raceTnpkDto.getOddsTm()));
			ps.setTime(5, raceUmrnDto.getOddsTm() == null ? null : Time.valueOf(raceUmrnDto.getOddsTm()));
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		// RACE_ODDS_TAN テーブルへデータを登録する
		try (PreparedStatement ps = conn.prepareStatement(this.sqlProperties.getProperty("sql.raceOddsTan.insert"))) {

			raceTnpkNinList.forEach(dto -> {
				try {
					ps.setString(1, raceTnpkDto.getKaisaiCd());
					ps.setInt(2, Integer.valueOf(raceTnpkDto.getRaceNo()));
					ps.setInt(3, Integer.valueOf(parameterProperties.getProperty("odds.time.no")));
					ps.setInt(4, Integer.valueOf(dto.getUmaNo()));
					ps.setInt(5, Integer.valueOf(dto.getNinkiNo()));
					ps.setBigDecimal(6, dto.getTanOdds());
					ps.executeUpdate();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			});

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		// RACE_ODDS_FUKU テーブルへデータを登録する
		try (PreparedStatement ps = conn.prepareStatement(this.sqlProperties.getProperty("sql.raceOddsFuku.insert"))) {

			raceTnpkNinList.forEach(dto -> {
				try {
					ps.setString(1, raceTnpkDto.getKaisaiCd());
					ps.setInt(2, Integer.valueOf(raceTnpkDto.getRaceNo()));
					ps.setInt(3, Integer.valueOf(parameterProperties.getProperty("odds.time.no")));
					ps.setInt(4, Integer.valueOf(dto.getUmaNo()));
					ps.setInt(5, Integer.valueOf(dto.getNinkiNo()));
					ps.setBigDecimal(6, dto.getFukuOddsMin());
					ps.setBigDecimal(7, dto.getFukuOddsMax());
					ps.executeUpdate();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			});

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		// RACE_ODDS_UMRN テーブルへデータを登録する
		try (PreparedStatement ps = conn.prepareStatement(this.sqlProperties.getProperty("sql.raceOddsUmrn.insert"))) {

			raceUmrnNinList.forEach(dto -> {
				try {
					ps.setString(1, raceUmrnDto.getKaisaiCd());
					ps.setInt(2, Integer.valueOf(raceUmrnDto.getRaceNo()));
					ps.setInt(3, Integer.valueOf(parameterProperties.getProperty("odds.time.no")));
					ps.setInt(4, Integer.valueOf(dto.getUmaNo1()));
					ps.setInt(5, Integer.valueOf(dto.getUmaNo2()));
					ps.setInt(6, Integer.valueOf(dto.getNinkiNo()));
					ps.setBigDecimal(7, dto.getOdds());
					ps.executeUpdate();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			});

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
