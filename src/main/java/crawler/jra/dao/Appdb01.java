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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.7"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Appdb01 extends SchemaImpl {

    private static final long serialVersionUID = -1614833869;

    /**
     * The reference instance of <code>appdb01</code>
     */
    public static final Appdb01 APPDB01 = new Appdb01();

    /**
     * The table <code>appdb01.kaisai</code>.
     */
    public final Kaisai KAISAI = crawler.jra.dao.tables.Kaisai.KAISAI;

    /**
     * The table <code>appdb01.race</code>.
     */
    public final Race RACE = crawler.jra.dao.tables.Race.RACE;

    /**
     * The table <code>appdb01.race_odds</code>.
     */
    public final RaceOdds RACE_ODDS = crawler.jra.dao.tables.RaceOdds.RACE_ODDS;

    /**
     * The table <code>appdb01.race_odds_fuku</code>.
     */
    public final RaceOddsFuku RACE_ODDS_FUKU = crawler.jra.dao.tables.RaceOddsFuku.RACE_ODDS_FUKU;

    /**
     * The table <code>appdb01.race_odds_tan</code>.
     */
    public final RaceOddsTan RACE_ODDS_TAN = crawler.jra.dao.tables.RaceOddsTan.RACE_ODDS_TAN;

    /**
     * The table <code>appdb01.race_odds_umrn</code>.
     */
    public final RaceOddsUmrn RACE_ODDS_UMRN = crawler.jra.dao.tables.RaceOddsUmrn.RACE_ODDS_UMRN;

    /**
     * The table <code>appdb01.race_uma_list</code>.
     */
    public final RaceUmaList RACE_UMA_LIST = crawler.jra.dao.tables.RaceUmaList.RACE_UMA_LIST;

    /**
     * No further instances allowed
     */
    private Appdb01() {
        super("appdb01", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            Kaisai.KAISAI,
            Race.RACE,
            RaceOdds.RACE_ODDS,
            RaceOddsFuku.RACE_ODDS_FUKU,
            RaceOddsTan.RACE_ODDS_TAN,
            RaceOddsUmrn.RACE_ODDS_UMRN,
            RaceUmaList.RACE_UMA_LIST);
    }
}
