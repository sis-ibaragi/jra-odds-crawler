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

import javax.annotation.Generated;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables of the <code>appdb01</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.7"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index KAISAI_PRIMARY = Indexes0.KAISAI_PRIMARY;
    public static final Index RACE_PRIMARY = Indexes0.RACE_PRIMARY;
    public static final Index RACE_ODDS_PRIMARY = Indexes0.RACE_ODDS_PRIMARY;
    public static final Index RACE_ODDS_FUKU_PRIMARY = Indexes0.RACE_ODDS_FUKU_PRIMARY;
    public static final Index RACE_ODDS_TAN_PRIMARY = Indexes0.RACE_ODDS_TAN_PRIMARY;
    public static final Index RACE_ODDS_UMRN_PRIMARY = Indexes0.RACE_ODDS_UMRN_PRIMARY;
    public static final Index RACE_UMA_LIST_PRIMARY = Indexes0.RACE_UMA_LIST_PRIMARY;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index KAISAI_PRIMARY = Internal.createIndex("PRIMARY", Kaisai.KAISAI, new OrderField[] { Kaisai.KAISAI.KAISAI_CD }, true);
        public static Index RACE_PRIMARY = Internal.createIndex("PRIMARY", Race.RACE, new OrderField[] { Race.RACE.KAISAI_CD, Race.RACE.RACE_NO }, true);
        public static Index RACE_ODDS_PRIMARY = Internal.createIndex("PRIMARY", RaceOdds.RACE_ODDS, new OrderField[] { RaceOdds.RACE_ODDS.KAISAI_CD, RaceOdds.RACE_ODDS.RACE_NO, RaceOdds.RACE_ODDS.ODDS_TIME_NO }, true);
        public static Index RACE_ODDS_FUKU_PRIMARY = Internal.createIndex("PRIMARY", RaceOddsFuku.RACE_ODDS_FUKU, new OrderField[] { RaceOddsFuku.RACE_ODDS_FUKU.KAISAI_CD, RaceOddsFuku.RACE_ODDS_FUKU.RACE_NO, RaceOddsFuku.RACE_ODDS_FUKU.ODDS_TIME_NO, RaceOddsFuku.RACE_ODDS_FUKU.UMA_NO }, true);
        public static Index RACE_ODDS_TAN_PRIMARY = Internal.createIndex("PRIMARY", RaceOddsTan.RACE_ODDS_TAN, new OrderField[] { RaceOddsTan.RACE_ODDS_TAN.KAISAI_CD, RaceOddsTan.RACE_ODDS_TAN.RACE_NO, RaceOddsTan.RACE_ODDS_TAN.ODDS_TIME_NO, RaceOddsTan.RACE_ODDS_TAN.UMA_NO }, true);
        public static Index RACE_ODDS_UMRN_PRIMARY = Internal.createIndex("PRIMARY", RaceOddsUmrn.RACE_ODDS_UMRN, new OrderField[] { RaceOddsUmrn.RACE_ODDS_UMRN.KAISAI_CD, RaceOddsUmrn.RACE_ODDS_UMRN.RACE_NO, RaceOddsUmrn.RACE_ODDS_UMRN.ODDS_TIME_NO, RaceOddsUmrn.RACE_ODDS_UMRN.UMA_NO_1, RaceOddsUmrn.RACE_ODDS_UMRN.UMA_NO_2 }, true);
        public static Index RACE_UMA_LIST_PRIMARY = Internal.createIndex("PRIMARY", RaceUmaList.RACE_UMA_LIST, new OrderField[] { RaceUmaList.RACE_UMA_LIST.KAISAI_CD, RaceUmaList.RACE_UMA_LIST.RACE_NO, RaceUmaList.RACE_UMA_LIST.UMA_NO }, true);
    }
}
