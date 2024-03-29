package crawler.jra.main;

import static crawler.jra.dao.Tables.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.jooq.types.UByte;

import crawler.jra.dao.tables.records.RaceOddsFukuRecord;
import crawler.jra.dao.tables.records.RaceOddsTanRecord;
import crawler.jra.dao.tables.records.RaceOddsUmrnRecord;
import crawler.jra.dao.tables.records.RaceUmaListRecord;
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
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author kilim
 *
 */
@Slf4j
public class JraOddsCrawler {

	/** databbase.properties */
	private DatabaseProperties dbProperties;

	/** parameter.properties */
	private ParameterProperties parameterProperties;

	/**
	 * database.properties のデータ構造を表すクラスです。
	 */
	@Value
	private static class DatabaseProperties {
		private String database;
		private String username;
		private String password;

		private DatabaseProperties() {
			// database.properties を読み込んで変数へ設定する
			try {
				Properties properties = new Properties();
				properties.load(this.getClass().getClassLoader().getResourceAsStream("database.properties"));
				this.database = properties.getProperty("database");
				this.username = properties.getProperty("username");
				this.password = properties.getProperty("password");
			} catch (IOException e) {
				throw new RuntimeException("プロパティファイルの読み込み時にエラーが発生しました。", e);
			}
		}
	}

	/**
	 * parameter.properties のデータ構造を表すクラスです。
	 */
	@Value
	private static class ParameterProperties {
		private LocalDate kaisaiDate;
		private int oddsTimeNo;
		private boolean preDelete;

		private ParameterProperties() {
			// parameter.properties を読み込んで変数へ設定する
			try {
				Properties properties = new Properties();
				properties.load(this.getClass().getClassLoader().getResourceAsStream("parameter.properties"));
				this.kaisaiDate = LocalDate.parse(Optional.ofNullable(System.getProperty("kaisai.date"))
						.orElse(properties.getProperty("kaisai.date")));
				this.oddsTimeNo = Integer.valueOf(Optional.ofNullable(System.getProperty("odds.time.no"))
						.orElse(properties.getProperty("odds.time.no")));
				this.preDelete = Boolean.valueOf(Optional.ofNullable(System.getProperty("pre.delete"))
						.orElse(properties.getProperty("pre.delete")));
			} catch (IOException e) {
				throw new RuntimeException("プロパティファイルの読み込み時にエラーが発生しました。", e);
			}
		}
	}

	/**
	 * コンストラクタ.
	 */
	public JraOddsCrawler() {
		// database.properties を読み込む
		this.dbProperties = new DatabaseProperties();
		// parameter.properties を読み込む
		this.parameterProperties = new ParameterProperties();

		// Jdk 11、且つ JawsDB 接続の場合、JRA ページアクセス時に javax.net.ssl.SSLException:
		// Received fatal alert: internal_error が発生するため、あえて TLS 1.2 を指定する
		System.setProperty("jdk.tls.client.protocols", "TLSv1.2");
	}

	/**
	 * このクラスのメインメソッドです。
	 * 
	 * @param args コマンドライン引数
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

		try (Connection conn = DriverManager.getConnection(this.dbProperties.getDatabase(),
				this.dbProperties.getUsername(), this.dbProperties.getPassword())) {
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
	 * 
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
			raceSelectPage.setFilterKaisaiDt(this.parameterProperties.getKaisaiDate());

			// KAISAI テーブルへデータを登録する
			saveKaisaiData(create, raceSelectPage.parse().getKaisaiDto());

			// レース一覧をループ
			Optional.ofNullable(raceSelectPage.parse().getRacePageCnameMap()).orElse(Collections.emptyMap()).keySet()
					.forEach(raceNo -> {
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
						saveOddsData(create, tnpkPage.getRaceDto(), tnpkPage.getRaceTnpkNinList(),
								umrnPage.getRaceDto(), umrnPage.getRaceUmrnNinList());
					});
		});
	}

	/**
	 * .
	 * 
	 * @param create DSLContext
	 */
	private void deleteOddsData(DSLContext create) {
		// 事前削除のフラグが指定された場合はデータを全削除する
		if (this.parameterProperties.isPreDelete()) {
			create.deleteFrom(RACE_ODDS_UMRN).execute();
			create.deleteFrom(RACE_ODDS_FUKU).execute();
			create.deleteFrom(RACE_ODDS_TAN).execute();
			create.deleteFrom(RACE_ODDS).execute();
			create.deleteFrom(RACE_UMA_LIST).execute();
			create.deleteFrom(RACE).execute();
			create.deleteFrom(KAISAI).execute();
			return;
		}

		// 当日の初回実行の場合は当日の KAISAI とその配下のテーブルを削除する
		if (this.parameterProperties.getOddsTimeNo() == 0) {
			Result<Record1<String>> result = create.select(KAISAI.KAISAI_CD).from(KAISAI)
					.where(KAISAI.KAISAI_DT.eq(Date.valueOf(this.parameterProperties.kaisaiDate))).fetch();
			result.forEach(r -> {
				create.deleteFrom(RACE_ODDS_UMRN)
						.where(RACE_ODDS_UMRN.KAISAI_CD.eq(r.getValue(RACE_ODDS_UMRN.KAISAI_CD))).execute();
				create.deleteFrom(RACE_ODDS_FUKU)
						.where(RACE_ODDS_FUKU.KAISAI_CD.eq(r.getValue(RACE_ODDS_FUKU.KAISAI_CD))).execute();
				create.deleteFrom(RACE_ODDS_TAN).where(RACE_ODDS_TAN.KAISAI_CD.eq(r.getValue(RACE_ODDS_TAN.KAISAI_CD)))
						.execute();
				create.deleteFrom(RACE_ODDS).where(RACE_ODDS.KAISAI_CD.eq(r.getValue(RACE_ODDS.KAISAI_CD))).execute();
				create.deleteFrom(RACE_UMA_LIST).where(RACE_UMA_LIST.KAISAI_CD.eq(r.getValue(KAISAI.KAISAI_CD)))
						.execute();
				create.deleteFrom(RACE).where(RACE.KAISAI_CD.eq(r.getValue(RACE.KAISAI_CD))).execute();
				create.deleteFrom(KAISAI).where(KAISAI.KAISAI_CD.eq(r.getValue(KAISAI.KAISAI_CD))).execute();
			});
			return;
		}

		// それ以外の場合は ODDS_TIME_NO を条件に RACE_ODDS とその配下のテーブルを削除する
		Result<Record1<String>> result = create.select(KAISAI.KAISAI_CD).from(KAISAI)
				.where(KAISAI.KAISAI_DT.eq(Date.valueOf(this.parameterProperties.kaisaiDate))).fetch();
		result.forEach(r -> {
			create.deleteFrom(RACE_ODDS_UMRN).where(RACE_ODDS_UMRN.KAISAI_CD.eq(r.getValue(RACE_ODDS_UMRN.KAISAI_CD)))
					.and(RACE_ODDS_UMRN.ODDS_TIME_NO.eq(UByte.valueOf(this.parameterProperties.oddsTimeNo))).execute();
			create.deleteFrom(RACE_ODDS_FUKU).where(RACE_ODDS_FUKU.KAISAI_CD.eq(r.getValue(RACE_ODDS_FUKU.KAISAI_CD)))
					.and(RACE_ODDS_FUKU.ODDS_TIME_NO.eq(UByte.valueOf(this.parameterProperties.oddsTimeNo))).execute();
			create.deleteFrom(RACE_ODDS_TAN).where(RACE_ODDS_TAN.KAISAI_CD.eq(r.getValue(RACE_ODDS_TAN.KAISAI_CD)))
					.and(RACE_ODDS_TAN.ODDS_TIME_NO.eq(UByte.valueOf(this.parameterProperties.oddsTimeNo))).execute();
			create.deleteFrom(RACE_ODDS).where(RACE_ODDS.KAISAI_CD.eq(r.getValue(RACE_ODDS.KAISAI_CD)))
					.and(RACE_ODDS.ODDS_TIME_NO.eq(UByte.valueOf(this.parameterProperties.oddsTimeNo))).execute();
		});
	}

	/**
	 * KAISAI テーブルへデータを登録します。
	 * 
	 * @param create    DSLContext
	 * @param kaisaiDto .
	 */
	private void saveKaisaiData(DSLContext create, KaisaiDto kaisaiDto) {
		if (kaisaiDto == null) {
			return;
		}
		// 当日の初回実行の場合のみ開催情報を登録する
		if (this.parameterProperties.getOddsTimeNo() != 0) {
			return;
		}

		// KAISAI テーブルへデータを登録する
		create.insertInto(KAISAI).set(KAISAI.KAISAI_CD, kaisaiDto.getKaisaiCd())
				.set(KAISAI.KAISAI_NM, kaisaiDto.getKaisaiNm()).set(KAISAI.KAISAI_DT,
						Optional.ofNullable(kaisaiDto.getKaisaiDt()).map(m -> Date.valueOf(m)).orElse(null))
				.execute();
	}

	/**
	 * RACE、RACE_UMA_LIST テーブルへデータを登録します。
	 * 
	 * @param create          DSLContext
	 * @param raceTnpkDto     .
	 * @param raceTnpkNinList .
	 */
	private void saveRaceData(DSLContext create, RaceDto raceTnpkDto, List<RaceTnpkNinDto> raceTnpkNinList) {

		// 当日の初回実行の場合のみレース情報を登録する
		if (this.parameterProperties.getOddsTimeNo() != 0) {
			return;
		}

		// RACE へデータを登録する
		create.insertInto(RACE).set(RACE.KAISAI_CD, raceTnpkDto.getKaisaiCd())
				.set(RACE.RACE_NO, UByte.valueOf(raceTnpkDto.getRaceNo())).execute();

		// RACE_UMA_LIST へデータを登録する
		List<RaceUmaListRecord> records = new ArrayList<>();
		raceTnpkNinList.forEach(dto -> {
			RaceUmaListRecord record = new RaceUmaListRecord();
			record.setKaisaiCd(raceTnpkDto.getKaisaiCd());
			record.setRaceNo(UByte.valueOf(raceTnpkDto.getRaceNo()));
			record.setUmaNo(UByte.valueOf(dto.getUmaNo()));
			record.setWakuNo(UByte.valueOf(dto.getWakuNo()));
			record.setUmaNm(dto.getUmaNm());
			record.setJockeyNm(dto.getJockeyNm());
			records.add(record);
		});
		create.batchInsert(records).execute();

	}

	/**
	 * RACE_ODDS テーブルとその配下のテーブルへデータを登録します。
	 * 
	 * @param create          DSLContext
	 * @param raceTnpkDto     .
	 * @param raceTnpkNinList .
	 * @param raceUmrnDto     .
	 * @param raceUmrnNinList .
	 */
	private void saveOddsData(DSLContext create, RaceDto raceTnpkDto, List<RaceTnpkNinDto> raceTnpkNinList,
			RaceDto raceUmrnDto, List<RaceUmrnNinDto> raceUmrnNinList) {

		// RACE_ODDS テーブルへデータを登録する
		create.insertInto(RACE_ODDS).set(RACE_ODDS.KAISAI_CD, raceTnpkDto.getKaisaiCd())
				.set(RACE_ODDS.RACE_NO, UByte.valueOf(raceTnpkDto.getRaceNo()))
				.set(RACE_ODDS.ODDS_TIME_NO, UByte.valueOf(this.parameterProperties.getOddsTimeNo()))
				.set(RACE_ODDS.TNPK_ODDS_TIME,
						Optional.ofNullable(raceTnpkDto.getOddsTm()).map(m -> Time.valueOf(m)).orElse(null))
				.set(RACE_ODDS.UMRN_ODDS_TIME,
						Optional.ofNullable(raceUmrnDto.getOddsTm()).map(m -> Time.valueOf(m)).orElse(null))
				.execute();

		// RACE_ODDS_TAN テーブルへデータを登録する
		{
			List<RaceOddsTanRecord> records = new ArrayList<>();
			AtomicInteger sortNo = new AtomicInteger(0);
			raceTnpkNinList.stream().forEach(dto -> {
				RaceOddsTanRecord record = new RaceOddsTanRecord();
				record.setKaisaiCd(raceTnpkDto.getKaisaiCd());
				record.setRaceNo(UByte.valueOf(raceTnpkDto.getRaceNo()));
				record.setOddsTimeNo(UByte.valueOf(this.parameterProperties.getOddsTimeNo()));
				record.setUmaNo(UByte.valueOf(dto.getUmaNo()));
				record.setNinkiNo(UByte.valueOf(dto.getNinkiNo()));
				record.setSortNo(UByte.valueOf(sortNo.incrementAndGet()));
				record.setTanOdds(dto.getTanOdds());
				records.add(record);
			});
			create.batchInsert(records).execute();
		}

		// RACE_ODDS_FUKU テーブルへデータを登録する
		{
			List<RaceOddsFukuRecord> records = new ArrayList<>();
			AtomicInteger sortNo = new AtomicInteger(0);
			raceTnpkNinList.stream()
					.sorted(Comparator.comparing(RaceTnpkNinDto::getFukuOddsMax4Sort)
							.thenComparing(RaceTnpkNinDto::getNinkiNo).thenComparing(RaceTnpkNinDto::getUmaNo))
					.forEach(dto -> {
						RaceOddsFukuRecord record = new RaceOddsFukuRecord();
						record.setKaisaiCd(raceTnpkDto.getKaisaiCd());
						record.setRaceNo(UByte.valueOf(raceTnpkDto.getRaceNo()));
						record.setOddsTimeNo(UByte.valueOf(this.parameterProperties.getOddsTimeNo()));
						record.setUmaNo(UByte.valueOf(dto.getUmaNo()));
						record.setNinkiNo((UByte) null);
						record.setSortNo(UByte.valueOf(sortNo.incrementAndGet()));
						record.setFukuOddsMin(dto.getFukuOddsMin());
						record.setFukuOddsMax(dto.getFukuOddsMax());
						records.add(record);
					});
			create.batchInsert(records).execute();
		}

		// RACE_ODDS_UMRN テーブルへデータを登録する
		{
			List<RaceOddsUmrnRecord> records = new ArrayList<>();
			AtomicInteger sortNo = new AtomicInteger(0);
			raceUmrnNinList.stream().forEach(dto -> {
				RaceOddsUmrnRecord record = new RaceOddsUmrnRecord();
				record.setKaisaiCd(raceUmrnDto.getKaisaiCd());
				record.setRaceNo(UByte.valueOf(raceUmrnDto.getRaceNo()));
				record.setOddsTimeNo(UByte.valueOf(this.parameterProperties.getOddsTimeNo()));
				record.setUmaNo_1(UByte.valueOf(dto.getUmaNo1()));
				record.setUmaNo_2(UByte.valueOf(dto.getUmaNo2()));
				record.setNinkiNo(UByte.valueOf(dto.getNinkiNo()));
				record.setSortNo(UByte.valueOf(sortNo.incrementAndGet()));
				record.setUmrnOdds(dto.getOdds());
				records.add(record);
			});
			create.batchInsert(records).execute();
		}
	}
}