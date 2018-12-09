/*
 * This file is generated by jOOQ.
 */
package crawler.jra.dao.tables.records;


import crawler.jra.dao.tables.RaceOddsFuku;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record4;
import org.jooq.Record9;
import org.jooq.Row9;
import org.jooq.impl.UpdatableRecordImpl;
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
public class RaceOddsFukuRecord extends UpdatableRecordImpl<RaceOddsFukuRecord> implements Record9<String, UByte, UByte, UByte, UByte, BigDecimal, BigDecimal, Timestamp, Timestamp> {

    private static final long serialVersionUID = -430327105;

    /**
     * Setter for <code>appdb01.race_odds_fuku.KAISAI_CD</code>.
     */
    public void setKaisaiCd(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>appdb01.race_odds_fuku.KAISAI_CD</code>.
     */
    public String getKaisaiCd() {
        return (String) get(0);
    }

    /**
     * Setter for <code>appdb01.race_odds_fuku.RACE_NO</code>.
     */
    public void setRaceNo(UByte value) {
        set(1, value);
    }

    /**
     * Getter for <code>appdb01.race_odds_fuku.RACE_NO</code>.
     */
    public UByte getRaceNo() {
        return (UByte) get(1);
    }

    /**
     * Setter for <code>appdb01.race_odds_fuku.ODDS_TIME_NO</code>.
     */
    public void setOddsTimeNo(UByte value) {
        set(2, value);
    }

    /**
     * Getter for <code>appdb01.race_odds_fuku.ODDS_TIME_NO</code>.
     */
    public UByte getOddsTimeNo() {
        return (UByte) get(2);
    }

    /**
     * Setter for <code>appdb01.race_odds_fuku.UMA_NO</code>.
     */
    public void setUmaNo(UByte value) {
        set(3, value);
    }

    /**
     * Getter for <code>appdb01.race_odds_fuku.UMA_NO</code>.
     */
    public UByte getUmaNo() {
        return (UByte) get(3);
    }

    /**
     * Setter for <code>appdb01.race_odds_fuku.NINKI_NO</code>.
     */
    public void setNinkiNo(UByte value) {
        set(4, value);
    }

    /**
     * Getter for <code>appdb01.race_odds_fuku.NINKI_NO</code>.
     */
    public UByte getNinkiNo() {
        return (UByte) get(4);
    }

    /**
     * Setter for <code>appdb01.race_odds_fuku.FUKU_ODDS_MIN</code>.
     */
    public void setFukuOddsMin(BigDecimal value) {
        set(5, value);
    }

    /**
     * Getter for <code>appdb01.race_odds_fuku.FUKU_ODDS_MIN</code>.
     */
    public BigDecimal getFukuOddsMin() {
        return (BigDecimal) get(5);
    }

    /**
     * Setter for <code>appdb01.race_odds_fuku.FUKU_ODDS_MAX</code>.
     */
    public void setFukuOddsMax(BigDecimal value) {
        set(6, value);
    }

    /**
     * Getter for <code>appdb01.race_odds_fuku.FUKU_ODDS_MAX</code>.
     */
    public BigDecimal getFukuOddsMax() {
        return (BigDecimal) get(6);
    }

    /**
     * Setter for <code>appdb01.race_odds_fuku.INSERT_DTTM</code>.
     */
    public void setInsertDttm(Timestamp value) {
        set(7, value);
    }

    /**
     * Getter for <code>appdb01.race_odds_fuku.INSERT_DTTM</code>.
     */
    public Timestamp getInsertDttm() {
        return (Timestamp) get(7);
    }

    /**
     * Setter for <code>appdb01.race_odds_fuku.UPDATE_DTTM</code>.
     */
    public void setUpdateDttm(Timestamp value) {
        set(8, value);
    }

    /**
     * Getter for <code>appdb01.race_odds_fuku.UPDATE_DTTM</code>.
     */
    public Timestamp getUpdateDttm() {
        return (Timestamp) get(8);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record4<String, UByte, UByte, UByte> key() {
        return (Record4) super.key();
    }

    // -------------------------------------------------------------------------
    // Record9 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row9<String, UByte, UByte, UByte, UByte, BigDecimal, BigDecimal, Timestamp, Timestamp> fieldsRow() {
        return (Row9) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row9<String, UByte, UByte, UByte, UByte, BigDecimal, BigDecimal, Timestamp, Timestamp> valuesRow() {
        return (Row9) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return RaceOddsFuku.RACE_ODDS_FUKU.KAISAI_CD;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UByte> field2() {
        return RaceOddsFuku.RACE_ODDS_FUKU.RACE_NO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UByte> field3() {
        return RaceOddsFuku.RACE_ODDS_FUKU.ODDS_TIME_NO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UByte> field4() {
        return RaceOddsFuku.RACE_ODDS_FUKU.UMA_NO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UByte> field5() {
        return RaceOddsFuku.RACE_ODDS_FUKU.NINKI_NO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<BigDecimal> field6() {
        return RaceOddsFuku.RACE_ODDS_FUKU.FUKU_ODDS_MIN;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<BigDecimal> field7() {
        return RaceOddsFuku.RACE_ODDS_FUKU.FUKU_ODDS_MAX;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field8() {
        return RaceOddsFuku.RACE_ODDS_FUKU.INSERT_DTTM;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field9() {
        return RaceOddsFuku.RACE_ODDS_FUKU.UPDATE_DTTM;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component1() {
        return getKaisaiCd();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UByte component2() {
        return getRaceNo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UByte component3() {
        return getOddsTimeNo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UByte component4() {
        return getUmaNo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UByte component5() {
        return getNinkiNo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal component6() {
        return getFukuOddsMin();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal component7() {
        return getFukuOddsMax();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component8() {
        return getInsertDttm();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component9() {
        return getUpdateDttm();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value1() {
        return getKaisaiCd();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UByte value2() {
        return getRaceNo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UByte value3() {
        return getOddsTimeNo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UByte value4() {
        return getUmaNo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UByte value5() {
        return getNinkiNo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal value6() {
        return getFukuOddsMin();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal value7() {
        return getFukuOddsMax();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value8() {
        return getInsertDttm();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value9() {
        return getUpdateDttm();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RaceOddsFukuRecord value1(String value) {
        setKaisaiCd(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RaceOddsFukuRecord value2(UByte value) {
        setRaceNo(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RaceOddsFukuRecord value3(UByte value) {
        setOddsTimeNo(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RaceOddsFukuRecord value4(UByte value) {
        setUmaNo(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RaceOddsFukuRecord value5(UByte value) {
        setNinkiNo(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RaceOddsFukuRecord value6(BigDecimal value) {
        setFukuOddsMin(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RaceOddsFukuRecord value7(BigDecimal value) {
        setFukuOddsMax(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RaceOddsFukuRecord value8(Timestamp value) {
        setInsertDttm(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RaceOddsFukuRecord value9(Timestamp value) {
        setUpdateDttm(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RaceOddsFukuRecord values(String value1, UByte value2, UByte value3, UByte value4, UByte value5, BigDecimal value6, BigDecimal value7, Timestamp value8, Timestamp value9) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached RaceOddsFukuRecord
     */
    public RaceOddsFukuRecord() {
        super(RaceOddsFuku.RACE_ODDS_FUKU);
    }

    /**
     * Create a detached, initialised RaceOddsFukuRecord
     */
    public RaceOddsFukuRecord(String kaisaiCd, UByte raceNo, UByte oddsTimeNo, UByte umaNo, UByte ninkiNo, BigDecimal fukuOddsMin, BigDecimal fukuOddsMax, Timestamp insertDttm, Timestamp updateDttm) {
        super(RaceOddsFuku.RACE_ODDS_FUKU);

        set(0, kaisaiCd);
        set(1, raceNo);
        set(2, oddsTimeNo);
        set(3, umaNo);
        set(4, ninkiNo);
        set(5, fukuOddsMin);
        set(6, fukuOddsMax);
        set(7, insertDttm);
        set(8, updateDttm);
    }
}