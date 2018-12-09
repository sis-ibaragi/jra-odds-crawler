/*
 * This file is generated by jOOQ.
 */
package crawler.jra.dao;


import crawler.jra.dao.tables.Kaisai;
import crawler.jra.dao.tables.Race;
import crawler.jra.dao.tables.RaceOdds;
import crawler.jra.dao.tables.RaceOddsFuku;
import crawler.jra.dao.tables.RaceOddsTan;
import crawler.jra.dao.tables.RaceOddsUmrn;
import crawler.jra.dao.tables.RaceUmaList;
import crawler.jra.dao.tables.records.KaisaiRecord;
import crawler.jra.dao.tables.records.RaceOddsFukuRecord;
import crawler.jra.dao.tables.records.RaceOddsRecord;
import crawler.jra.dao.tables.records.RaceOddsTanRecord;
import crawler.jra.dao.tables.records.RaceOddsUmrnRecord;
import crawler.jra.dao.tables.records.RaceRecord;
import crawler.jra.dao.tables.records.RaceUmaListRecord;

import javax.annotation.Generated;

import org.jooq.ForeignKey;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>appdb01</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.7"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<KaisaiRecord> KEY_KAISAI_PRIMARY = UniqueKeys0.KEY_KAISAI_PRIMARY;
    public static final UniqueKey<RaceRecord> KEY_RACE_PRIMARY = UniqueKeys0.KEY_RACE_PRIMARY;
    public static final UniqueKey<RaceOddsRecord> KEY_RACE_ODDS_PRIMARY = UniqueKeys0.KEY_RACE_ODDS_PRIMARY;
    public static final UniqueKey<RaceOddsFukuRecord> KEY_RACE_ODDS_FUKU_PRIMARY = UniqueKeys0.KEY_RACE_ODDS_FUKU_PRIMARY;
    public static final UniqueKey<RaceOddsTanRecord> KEY_RACE_ODDS_TAN_PRIMARY = UniqueKeys0.KEY_RACE_ODDS_TAN_PRIMARY;
    public static final UniqueKey<RaceOddsUmrnRecord> KEY_RACE_ODDS_UMRN_PRIMARY = UniqueKeys0.KEY_RACE_ODDS_UMRN_PRIMARY;
    public static final UniqueKey<RaceUmaListRecord> KEY_RACE_UMA_LIST_PRIMARY = UniqueKeys0.KEY_RACE_UMA_LIST_PRIMARY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<RaceRecord, KaisaiRecord> RACE_IBFK_1 = ForeignKeys0.RACE_IBFK_1;
    public static final ForeignKey<RaceOddsRecord, RaceRecord> RACE_ODDS_IBFK_1 = ForeignKeys0.RACE_ODDS_IBFK_1;
    public static final ForeignKey<RaceOddsFukuRecord, RaceOddsRecord> RACE_ODDS_FUKU_IBFK_1 = ForeignKeys0.RACE_ODDS_FUKU_IBFK_1;
    public static final ForeignKey<RaceOddsTanRecord, RaceOddsRecord> RACE_ODDS_TAN_IBFK_1 = ForeignKeys0.RACE_ODDS_TAN_IBFK_1;
    public static final ForeignKey<RaceOddsUmrnRecord, RaceOddsRecord> RACE_ODDS_UMRN_IBFK_1 = ForeignKeys0.RACE_ODDS_UMRN_IBFK_1;
    public static final ForeignKey<RaceUmaListRecord, RaceRecord> RACE_UMA_LIST_IBFK_1 = ForeignKeys0.RACE_UMA_LIST_IBFK_1;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class UniqueKeys0 {
        public static final UniqueKey<KaisaiRecord> KEY_KAISAI_PRIMARY = Internal.createUniqueKey(Kaisai.KAISAI, "KEY_kaisai_PRIMARY", Kaisai.KAISAI.KAISAI_CD);
        public static final UniqueKey<RaceRecord> KEY_RACE_PRIMARY = Internal.createUniqueKey(Race.RACE, "KEY_race_PRIMARY", Race.RACE.KAISAI_CD, Race.RACE.RACE_NO);
        public static final UniqueKey<RaceOddsRecord> KEY_RACE_ODDS_PRIMARY = Internal.createUniqueKey(RaceOdds.RACE_ODDS, "KEY_race_odds_PRIMARY", RaceOdds.RACE_ODDS.KAISAI_CD, RaceOdds.RACE_ODDS.RACE_NO, RaceOdds.RACE_ODDS.ODDS_TIME_NO);
        public static final UniqueKey<RaceOddsFukuRecord> KEY_RACE_ODDS_FUKU_PRIMARY = Internal.createUniqueKey(RaceOddsFuku.RACE_ODDS_FUKU, "KEY_race_odds_fuku_PRIMARY", RaceOddsFuku.RACE_ODDS_FUKU.KAISAI_CD, RaceOddsFuku.RACE_ODDS_FUKU.RACE_NO, RaceOddsFuku.RACE_ODDS_FUKU.ODDS_TIME_NO, RaceOddsFuku.RACE_ODDS_FUKU.UMA_NO);
        public static final UniqueKey<RaceOddsTanRecord> KEY_RACE_ODDS_TAN_PRIMARY = Internal.createUniqueKey(RaceOddsTan.RACE_ODDS_TAN, "KEY_race_odds_tan_PRIMARY", RaceOddsTan.RACE_ODDS_TAN.KAISAI_CD, RaceOddsTan.RACE_ODDS_TAN.RACE_NO, RaceOddsTan.RACE_ODDS_TAN.ODDS_TIME_NO, RaceOddsTan.RACE_ODDS_TAN.UMA_NO);
        public static final UniqueKey<RaceOddsUmrnRecord> KEY_RACE_ODDS_UMRN_PRIMARY = Internal.createUniqueKey(RaceOddsUmrn.RACE_ODDS_UMRN, "KEY_race_odds_umrn_PRIMARY", RaceOddsUmrn.RACE_ODDS_UMRN.KAISAI_CD, RaceOddsUmrn.RACE_ODDS_UMRN.RACE_NO, RaceOddsUmrn.RACE_ODDS_UMRN.ODDS_TIME_NO, RaceOddsUmrn.RACE_ODDS_UMRN.UMA_NO_1, RaceOddsUmrn.RACE_ODDS_UMRN.UMA_NO_2);
        public static final UniqueKey<RaceUmaListRecord> KEY_RACE_UMA_LIST_PRIMARY = Internal.createUniqueKey(RaceUmaList.RACE_UMA_LIST, "KEY_race_uma_list_PRIMARY", RaceUmaList.RACE_UMA_LIST.KAISAI_CD, RaceUmaList.RACE_UMA_LIST.RACE_NO, RaceUmaList.RACE_UMA_LIST.UMA_NO);
    }

    private static class ForeignKeys0 {
        public static final ForeignKey<RaceRecord, KaisaiRecord> RACE_IBFK_1 = Internal.createForeignKey(crawler.jra.dao.Keys.KEY_KAISAI_PRIMARY, Race.RACE, "race_ibfk_1", Race.RACE.KAISAI_CD);
        public static final ForeignKey<RaceOddsRecord, RaceRecord> RACE_ODDS_IBFK_1 = Internal.createForeignKey(crawler.jra.dao.Keys.KEY_RACE_PRIMARY, RaceOdds.RACE_ODDS, "race_odds_ibfk_1", RaceOdds.RACE_ODDS.KAISAI_CD, RaceOdds.RACE_ODDS.RACE_NO);
        public static final ForeignKey<RaceOddsFukuRecord, RaceOddsRecord> RACE_ODDS_FUKU_IBFK_1 = Internal.createForeignKey(crawler.jra.dao.Keys.KEY_RACE_ODDS_PRIMARY, RaceOddsFuku.RACE_ODDS_FUKU, "race_odds_fuku_ibfk_1", RaceOddsFuku.RACE_ODDS_FUKU.KAISAI_CD, RaceOddsFuku.RACE_ODDS_FUKU.RACE_NO, RaceOddsFuku.RACE_ODDS_FUKU.ODDS_TIME_NO);
        public static final ForeignKey<RaceOddsTanRecord, RaceOddsRecord> RACE_ODDS_TAN_IBFK_1 = Internal.createForeignKey(crawler.jra.dao.Keys.KEY_RACE_ODDS_PRIMARY, RaceOddsTan.RACE_ODDS_TAN, "race_odds_tan_ibfk_1", RaceOddsTan.RACE_ODDS_TAN.KAISAI_CD, RaceOddsTan.RACE_ODDS_TAN.RACE_NO, RaceOddsTan.RACE_ODDS_TAN.ODDS_TIME_NO);
        public static final ForeignKey<RaceOddsUmrnRecord, RaceOddsRecord> RACE_ODDS_UMRN_IBFK_1 = Internal.createForeignKey(crawler.jra.dao.Keys.KEY_RACE_ODDS_PRIMARY, RaceOddsUmrn.RACE_ODDS_UMRN, "race_odds_umrn_ibfk_1", RaceOddsUmrn.RACE_ODDS_UMRN.KAISAI_CD, RaceOddsUmrn.RACE_ODDS_UMRN.RACE_NO, RaceOddsUmrn.RACE_ODDS_UMRN.ODDS_TIME_NO);
        public static final ForeignKey<RaceUmaListRecord, RaceRecord> RACE_UMA_LIST_IBFK_1 = Internal.createForeignKey(crawler.jra.dao.Keys.KEY_RACE_PRIMARY, RaceUmaList.RACE_UMA_LIST, "race_uma_list_ibfk_1", RaceUmaList.RACE_UMA_LIST.KAISAI_CD, RaceUmaList.RACE_UMA_LIST.RACE_NO);
    }
}
