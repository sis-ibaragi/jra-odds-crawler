package crawler.jra.main;

import static crawler.jra.dao.Tables.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.jooq.types.UByte;

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

			Settings settings = new Settings();
			settings.setExecuteLogging(true);
			settings.withRenderSchema(false);
			DSLContext create = DSL.using(conn, SQLDialect.MARIADB, settings);

			deleteOddsData(create);
			parseOddsPage(create);
			conn.commit();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * .
	 * @param create DSLContext
	 */
	private void parseOddsPage(DSLContext create) {

		// JRA トップページ -> 開催選択ページに遷移
		KaisaiSelectPage kaisaiSelectPage = new TopPage().goKaisaiSelectPage();

		// 開催一覧をループ
		kaisaiSelectPage.parse().getKaisaiCnameMap().keySet().forEach(kaisaiName -> {
			// レース選択ページに遷移
			RaceSelectPage raceSelectPage = kaisaiSelectPage.goRaceSelectPage(kaisaiName);
			// 開催日を指定
			raceSelectPage.setFilterKaisaiDt(this.parameterProperties.getProperty("kaisai.date"));

			// KAISAI テーブルへデータを登録する
			saveKaisaiData(create, raceSelectPage.parse().getKaisaiDto());

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
						saveRaceData(create, tnpkPage.getRaceDto(), tnpkPage.getRaceTnpkNinList());

						// 馬連オッズ（馬番順）ページに遷移
						RaceUmrnUmaPage umrnUmaPage = raceSelectPage.goRaceUmrnUmaPage(raceNo);
						// 馬連オッズ（人気順）ページに遷移
						RaceUmrnNinPage umrnPage = umrnUmaPage.goRaceUmrnNinPage();
						umrnPage.parse();
						log.debug(umrnPage.getRaceDto().toString());
						log.debug(umrnPage.getRaceUmrnNinList().toString());

						// RACE_ODDS テーブルとその配下のテーブルへデータを登録する
						saveOddsData(
								create,
								tnpkPage.getRaceDto(),
								tnpkPage.getRaceTnpkNinList(),
								umrnPage.getRaceDto(),
								umrnPage.getRaceUmrnNinList());
					});
		});
	}

	/**
	 * .
	 * @param create DSLContext
	 */
	private void deleteOddsData(DSLContext create) {
		// 当日の初回実行の場合のみデータを削除する
		if (!parameterProperties.getProperty("odds.time.no").equals("1")) {
			return;
		}
		create.deleteFrom(RACE_ODDS_UMRN).execute();
		create.deleteFrom(RACE_ODDS_FUKU).execute();
		create.deleteFrom(RACE_ODDS_TAN).execute();
		create.deleteFrom(RACE_ODDS).execute();
		create.deleteFrom(RACE_UMA_LIST).execute();
		create.deleteFrom(RACE).execute();
		create.deleteFrom(KAISAI).execute();
	}

	/**
	 * KAISAI テーブルへデータを登録します。
	 * @param create DSLContext
	 * @param kaisaiDto .
	 */
	private void saveKaisaiData(DSLContext create, KaisaiDto kaisaiDto) {
		if (kaisaiDto == null) {
			return;
		}
		// 当日の初回実行の場合のみ開催情報を登録する
		if (!this.parameterProperties.getProperty("odds.time.no").equals("1")) {
			return;
		}
		// KAISAI テーブルへデータを登録する
		create.insertInto(KAISAI)
				.columns(KAISAI.KAISAI_CD,
						KAISAI.KAISAI_NM,
						KAISAI.KAISAI_DT)
				.values(kaisaiDto.getKaisaiCd(),
						kaisaiDto.getKaisaiNm(),
						Optional.ofNullable(kaisaiDto.getKaisaiDt()).map(m -> Date.valueOf(m)).orElse(null))
				.execute();
	}

	/**
	 * RACE、RACE_UMA_LIST テーブルへデータを登録します。
	 * @param create DSLContext
	 * @param raceTnpkDto .
	 * @param raceTnpkNinList .
	 */
	private void saveRaceData(
			DSLContext create,
			RaceDto raceTnpkDto,
			List<RaceTnpkNinDto> raceTnpkNinList) {

		// 当日の初回実行の場合のみレース情報を登録する
		if (!this.parameterProperties.getProperty("odds.time.no").equals("1")) {
			return;
		}

		// RACE へデータを登録する
		create.insertInto(RACE)
				.columns(RACE.KAISAI_CD, RACE.RACE_NO)
				.values(raceTnpkDto.getKaisaiCd(), UByte.valueOf(raceTnpkDto.getRaceNo()))
				.execute();

		// RACE_UMA_LIST へデータを登録する
		raceTnpkNinList.forEach(dto -> {
			create.insertInto(RACE_UMA_LIST)
					.columns(RACE_UMA_LIST.KAISAI_CD,
							RACE_UMA_LIST.RACE_NO,
							RACE_UMA_LIST.UMA_NO,
							RACE_UMA_LIST.WAKU_NO,
							RACE_UMA_LIST.UMA_NM,
							RACE_UMA_LIST.JOCKEY_NM)
					.values(raceTnpkDto.getKaisaiCd(),
							UByte.valueOf(raceTnpkDto.getRaceNo()),
							UByte.valueOf(dto.getUmaNo()),
							UByte.valueOf(dto.getWakuNo()),
							dto.getUmaNm(),
							dto.getJockeyNm())
					.execute();
		});
	}

	/**
	 * RACE_ODDS テーブルとその配下のテーブルへデータを登録します。
	 * @param create DSLContext
	 * @param raceTnpkDto .
	 * @param raceTnpkNinList .
	 * @param raceUmrnDto .
	 * @param raceUmrnNinList .
	 */
	private void saveOddsData(
			DSLContext create,
			RaceDto raceTnpkDto,
			List<RaceTnpkNinDto> raceTnpkNinList,
			RaceDto raceUmrnDto,
			List<RaceUmrnNinDto> raceUmrnNinList) {

		// ODDS_TIME_NO を取得
		String oddsTimeNo = this.parameterProperties.getProperty("odds.time.no");

		// RACE_ODDS テーブルへデータを登録する
		create.insertInto(RACE_ODDS)
				.columns(RACE_ODDS.KAISAI_CD,
						RACE_ODDS.RACE_NO,
						RACE_ODDS.ODDS_TIME_NO,
						RACE_ODDS.TNPK_ODDS_TIME,
						RACE_ODDS.UMRN_ODDS_TIME)
				.values(raceTnpkDto.getKaisaiCd(),
						UByte.valueOf(raceTnpkDto.getRaceNo()),
						UByte.valueOf(oddsTimeNo),
						Optional.ofNullable(raceTnpkDto.getOddsTm()).map(m -> Time.valueOf(m)).orElse(null),
						Optional.ofNullable(raceUmrnDto.getOddsTm()).map(m -> Time.valueOf(m)).orElse(null))
				.execute();

		// RACE_ODDS_TAN テーブルへデータを登録する
		raceTnpkNinList.forEach(dto -> {
			create.insertInto(RACE_ODDS_TAN)
					.columns(RACE_ODDS_TAN.KAISAI_CD,
							RACE_ODDS_TAN.RACE_NO,
							RACE_ODDS_TAN.ODDS_TIME_NO,
							RACE_ODDS_TAN.UMA_NO,
							RACE_ODDS_TAN.NINKI_NO,
							RACE_ODDS_TAN.TAN_ODDS)
					.values(raceTnpkDto.getKaisaiCd(),
							UByte.valueOf(raceTnpkDto.getRaceNo()),
							UByte.valueOf(oddsTimeNo),
							UByte.valueOf(dto.getUmaNo()),
							UByte.valueOf(dto.getNinkiNo()),
							dto.getTanOdds())
					.execute();
		});

		// RACE_ODDS_FUKU テーブルへデータを登録する
		raceTnpkNinList.forEach(dto -> {
			create.insertInto(RACE_ODDS_FUKU)
					.columns(RACE_ODDS_FUKU.KAISAI_CD,
							RACE_ODDS_FUKU.RACE_NO,
							RACE_ODDS_FUKU.ODDS_TIME_NO,
							RACE_ODDS_FUKU.UMA_NO,
							RACE_ODDS_FUKU.NINKI_NO,
							RACE_ODDS_FUKU.FUKU_ODDS_MIN,
							RACE_ODDS_FUKU.FUKU_ODDS_MAX)
					.values(raceTnpkDto.getKaisaiCd(),
							UByte.valueOf(raceTnpkDto.getRaceNo()),
							UByte.valueOf(oddsTimeNo),
							UByte.valueOf(dto.getUmaNo()),
							UByte.valueOf(dto.getNinkiNo()),
							dto.getFukuOddsMin(),
							dto.getFukuOddsMax())
					.execute();
		});

		// RACE_ODDS_UMRN テーブルへデータを登録する
		raceUmrnNinList.forEach(dto -> {
			create.insertInto(RACE_ODDS_UMRN)
					.columns(RACE_ODDS_UMRN.KAISAI_CD,
							RACE_ODDS_UMRN.RACE_NO,
							RACE_ODDS_UMRN.ODDS_TIME_NO,
							RACE_ODDS_UMRN.UMA_NO_1,
							RACE_ODDS_UMRN.UMA_NO_2,
							RACE_ODDS_UMRN.NINKI_NO,
							RACE_ODDS_UMRN.UMRN_ODDS)
					.values(raceUmrnDto.getKaisaiCd(),
							UByte.valueOf(raceUmrnDto.getRaceNo()),
							UByte.valueOf(oddsTimeNo),
							UByte.valueOf(dto.getUmaNo1()),
							UByte.valueOf(dto.getUmaNo2()),
							UByte.valueOf(dto.getNinkiNo()),
							dto.getOdds())
					.execute();
		});
	}
}
