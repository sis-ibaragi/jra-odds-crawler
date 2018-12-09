/*
 * This file is generated by jOOQ.
 */
package crawler.jra.dao.tables;


import crawler.jra.dao.Appdb01;
import crawler.jra.dao.Indexes;
import crawler.jra.dao.Keys;
import crawler.jra.dao.tables.records.RaceOddsTanRecord;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;
import org.jooq.types.UByte;


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
public class RaceOddsTan extends TableImpl<RaceOddsTanRecord> {

    private static final long serialVersionUID = 985488373;

    /**
     * The reference instance of <code>appdb01.race_odds_tan</code>
     */
    public static final RaceOddsTan RACE_ODDS_TAN = new RaceOddsTan();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RaceOddsTanRecord> getRecordType() {
        return RaceOddsTanRecord.class;
    }

    /**
     * The column <code>appdb01.race_odds_tan.KAISAI_CD</code>.
     */
    public final TableField<RaceOddsTanRecord, String> KAISAI_CD = createField("KAISAI_CD", org.jooq.impl.SQLDataType.CHAR(10).nullable(false), this, "");

    /**
     * The column <code>appdb01.race_odds_tan.RACE_NO</code>.
     */
    public final TableField<RaceOddsTanRecord, UByte> RACE_NO = createField("RACE_NO", org.jooq.impl.SQLDataType.TINYINTUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>appdb01.race_odds_tan.ODDS_TIME_NO</code>.
     */
    public final TableField<RaceOddsTanRecord, UByte> ODDS_TIME_NO = createField("ODDS_TIME_NO", org.jooq.impl.SQLDataType.TINYINTUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>appdb01.race_odds_tan.UMA_NO</code>.
     */
    public final TableField<RaceOddsTanRecord, UByte> UMA_NO = createField("UMA_NO", org.jooq.impl.SQLDataType.TINYINTUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>appdb01.race_odds_tan.NINKI_NO</code>.
     */
    public final TableField<RaceOddsTanRecord, UByte> NINKI_NO = createField("NINKI_NO", org.jooq.impl.SQLDataType.TINYINTUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>appdb01.race_odds_tan.TAN_ODDS</code>.
     */
    public final TableField<RaceOddsTanRecord, BigDecimal> TAN_ODDS = createField("TAN_ODDS", org.jooq.impl.SQLDataType.DECIMAL(6, 1).defaultValue(org.jooq.impl.DSL.field("NULL", org.jooq.impl.SQLDataType.DECIMAL)), this, "");

    /**
     * The column <code>appdb01.race_odds_tan.INSERT_DTTM</code>.
     */
    public final TableField<RaceOddsTanRecord, Timestamp> INSERT_DTTM = createField("INSERT_DTTM", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.field("current_timestamp()", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * The column <code>appdb01.race_odds_tan.UPDATE_DTTM</code>.
     */
    public final TableField<RaceOddsTanRecord, Timestamp> UPDATE_DTTM = createField("UPDATE_DTTM", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.field("current_timestamp()", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * Create a <code>appdb01.race_odds_tan</code> table reference
     */
    public RaceOddsTan() {
        this(DSL.name("race_odds_tan"), null);
    }

    /**
     * Create an aliased <code>appdb01.race_odds_tan</code> table reference
     */
    public RaceOddsTan(String alias) {
        this(DSL.name(alias), RACE_ODDS_TAN);
    }

    /**
     * Create an aliased <code>appdb01.race_odds_tan</code> table reference
     */
    public RaceOddsTan(Name alias) {
        this(alias, RACE_ODDS_TAN);
    }

    private RaceOddsTan(Name alias, Table<RaceOddsTanRecord> aliased) {
        this(alias, aliased, null);
    }

    private RaceOddsTan(Name alias, Table<RaceOddsTanRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> RaceOddsTan(Table<O> child, ForeignKey<O, RaceOddsTanRecord> key) {
        super(child, key, RACE_ODDS_TAN);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Appdb01.APPDB01;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.RACE_ODDS_TAN_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<RaceOddsTanRecord> getPrimaryKey() {
        return Keys.KEY_RACE_ODDS_TAN_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<RaceOddsTanRecord>> getKeys() {
        return Arrays.<UniqueKey<RaceOddsTanRecord>>asList(Keys.KEY_RACE_ODDS_TAN_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<RaceOddsTanRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<RaceOddsTanRecord, ?>>asList(Keys.RACE_ODDS_TAN_IBFK_1);
    }

    public RaceOdds raceOdds() {
        return new RaceOdds(this, Keys.RACE_ODDS_TAN_IBFK_1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RaceOddsTan as(String alias) {
        return new RaceOddsTan(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RaceOddsTan as(Name alias) {
        return new RaceOddsTan(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public RaceOddsTan rename(String name) {
        return new RaceOddsTan(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public RaceOddsTan rename(Name name) {
        return new RaceOddsTan(name, null);
    }
}