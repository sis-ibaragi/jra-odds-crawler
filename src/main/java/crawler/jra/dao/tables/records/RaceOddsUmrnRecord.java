/*
 * This file is generated by jOOQ.
 */
package crawler.jra.dao.tables.records;


import crawler.jra.dao.tables.RaceOddsUmrn;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record11;
import org.jooq.Record5;
import org.jooq.Row11;
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
public class RaceOddsUmrnRecord extends UpdatableRecordImpl<RaceOddsUmrnRecord> implements Record11<String, UByte, UByte, UByte, UByte, UByte, UByte, BigDecimal, BigDecimal, Timestamp, Timestamp> {

    private static final long serialVersionUID = -1112393481;

    /**
     * Setter for <code>ihq2xbiptmm0mjtp.RACE_ODDS_UMRN.KAISAI_CD</code>.
     */
    public void setKaisaiCd(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>ihq2xbiptmm0mjtp.RACE_ODDS_UMRN.KAISAI_CD</code>.
     */
    public String getKaisaiCd() {
        return (String) get(0);
    }

    /**
     * Setter for <code>ihq2xbiptmm0mjtp.RACE_ODDS_UMRN.RACE_NO</code>.
     */
    public void setRaceNo(UByte value) {
        set(1, value);
    }

    /**
     * Getter for <code>ihq2xbiptmm0mjtp.RACE_ODDS_UMRN.RACE_NO</code>.
     */
    public UByte getRaceNo() {
        return (UByte) get(1);
    }

    /**
     * Setter for <code>ihq2xbiptmm0mjtp.RACE_ODDS_UMRN.ODDS_TIME_NO</code>.
     */
    public void setOddsTimeNo(UByte value) {
        set(2, value);
    }

    /**
     * Getter for <code>ihq2xbiptmm0mjtp.RACE_ODDS_UMRN.ODDS_TIME_NO</code>.
     */
    public UByte getOddsTimeNo() {
        return (UByte) get(2);
    }

    /**
     * Setter for <code>ihq2xbiptmm0mjtp.RACE_ODDS_UMRN.UMA_NO_1</code>.
     */
    public void setUmaNo_1(UByte value) {
        set(3, value);
    }

    /**
     * Getter for <code>ihq2xbiptmm0mjtp.RACE_ODDS_UMRN.UMA_NO_1</code>.
     */
    public UByte getUmaNo_1() {
        return (UByte) get(3);
    }

    /**
     * Setter for <code>ihq2xbiptmm0mjtp.RACE_ODDS_UMRN.UMA_NO_2</code>.
     */
    public void setUmaNo_2(UByte value) {
        set(4, value);
    }

    /**
     * Getter for <code>ihq2xbiptmm0mjtp.RACE_ODDS_UMRN.UMA_NO_2</code>.
     */
    public UByte getUmaNo_2() {
        return (UByte) get(4);
    }

    /**
     * Setter for <code>ihq2xbiptmm0mjtp.RACE_ODDS_UMRN.NINKI_NO</code>.
     */
    public void setNinkiNo(UByte value) {
        set(5, value);
    }

    /**
     * Getter for <code>ihq2xbiptmm0mjtp.RACE_ODDS_UMRN.NINKI_NO</code>.
     */
    public UByte getNinkiNo() {
        return (UByte) get(5);
    }

    /**
     * Setter for <code>ihq2xbiptmm0mjtp.RACE_ODDS_UMRN.SORT_NO</code>.
     */
    public void setSortNo(UByte value) {
        set(6, value);
    }

    /**
     * Getter for <code>ihq2xbiptmm0mjtp.RACE_ODDS_UMRN.SORT_NO</code>.
     */
    public UByte getSortNo() {
        return (UByte) get(6);
    }

    /**
     * Setter for <code>ihq2xbiptmm0mjtp.RACE_ODDS_UMRN.UMRN_ODDS</code>.
     */
    public void setUmrnOdds(BigDecimal value) {
        set(7, value);
    }

    /**
     * Getter for <code>ihq2xbiptmm0mjtp.RACE_ODDS_UMRN.UMRN_ODDS</code>.
     */
    public BigDecimal getUmrnOdds() {
        return (BigDecimal) get(7);
    }

    /**
     * Setter for <code>ihq2xbiptmm0mjtp.RACE_ODDS_UMRN.DIFF_RT</code>.
     */
    public void setDiffRt(BigDecimal value) {
        set(8, value);
    }

    /**
     * Getter for <code>ihq2xbiptmm0mjtp.RACE_ODDS_UMRN.DIFF_RT</code>.
     */
    public BigDecimal getDiffRt() {
        return (BigDecimal) get(8);
    }

    /**
     * Setter for <code>ihq2xbiptmm0mjtp.RACE_ODDS_UMRN.INSERT_DTTM</code>.
     */
    public void setInsertDttm(Timestamp value) {
        set(9, value);
    }

    /**
     * Getter for <code>ihq2xbiptmm0mjtp.RACE_ODDS_UMRN.INSERT_DTTM</code>.
     */
    public Timestamp getInsertDttm() {
        return (Timestamp) get(9);
    }

    /**
     * Setter for <code>ihq2xbiptmm0mjtp.RACE_ODDS_UMRN.UPDATE_DTTM</code>.
     */
    public void setUpdateDttm(Timestamp value) {
        set(10, value);
    }

    /**
     * Getter for <code>ihq2xbiptmm0mjtp.RACE_ODDS_UMRN.UPDATE_DTTM</code>.
     */
    public Timestamp getUpdateDttm() {
        return (Timestamp) get(10);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record5<String, UByte, UByte, UByte, UByte> key() {
        return (Record5) super.key();
    }

    // -------------------------------------------------------------------------
    // Record11 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row11<String, UByte, UByte, UByte, UByte, UByte, UByte, BigDecimal, BigDecimal, Timestamp, Timestamp> fieldsRow() {
        return (Row11) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row11<String, UByte, UByte, UByte, UByte, UByte, UByte, BigDecimal, BigDecimal, Timestamp, Timestamp> valuesRow() {
        return (Row11) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return RaceOddsUmrn.RACE_ODDS_UMRN.KAISAI_CD;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UByte> field2() {
        return RaceOddsUmrn.RACE_ODDS_UMRN.RACE_NO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UByte> field3() {
        return RaceOddsUmrn.RACE_ODDS_UMRN.ODDS_TIME_NO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UByte> field4() {
        return RaceOddsUmrn.RACE_ODDS_UMRN.UMA_NO_1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UByte> field5() {
        return RaceOddsUmrn.RACE_ODDS_UMRN.UMA_NO_2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UByte> field6() {
        return RaceOddsUmrn.RACE_ODDS_UMRN.NINKI_NO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UByte> field7() {
        return RaceOddsUmrn.RACE_ODDS_UMRN.SORT_NO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<BigDecimal> field8() {
        return RaceOddsUmrn.RACE_ODDS_UMRN.UMRN_ODDS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<BigDecimal> field9() {
        return RaceOddsUmrn.RACE_ODDS_UMRN.DIFF_RT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field10() {
        return RaceOddsUmrn.RACE_ODDS_UMRN.INSERT_DTTM;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field11() {
        return RaceOddsUmrn.RACE_ODDS_UMRN.UPDATE_DTTM;
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
        return getUmaNo_1();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UByte component5() {
        return getUmaNo_2();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UByte component6() {
        return getNinkiNo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UByte component7() {
        return getSortNo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal component8() {
        return getUmrnOdds();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal component9() {
        return getDiffRt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component10() {
        return getInsertDttm();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component11() {
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
        return getUmaNo_1();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UByte value5() {
        return getUmaNo_2();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UByte value6() {
        return getNinkiNo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UByte value7() {
        return getSortNo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal value8() {
        return getUmrnOdds();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal value9() {
        return getDiffRt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value10() {
        return getInsertDttm();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value11() {
        return getUpdateDttm();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RaceOddsUmrnRecord value1(String value) {
        setKaisaiCd(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RaceOddsUmrnRecord value2(UByte value) {
        setRaceNo(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RaceOddsUmrnRecord value3(UByte value) {
        setOddsTimeNo(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RaceOddsUmrnRecord value4(UByte value) {
        setUmaNo_1(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RaceOddsUmrnRecord value5(UByte value) {
        setUmaNo_2(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RaceOddsUmrnRecord value6(UByte value) {
        setNinkiNo(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RaceOddsUmrnRecord value7(UByte value) {
        setSortNo(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RaceOddsUmrnRecord value8(BigDecimal value) {
        setUmrnOdds(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RaceOddsUmrnRecord value9(BigDecimal value) {
        setDiffRt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RaceOddsUmrnRecord value10(Timestamp value) {
        setInsertDttm(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RaceOddsUmrnRecord value11(Timestamp value) {
        setUpdateDttm(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RaceOddsUmrnRecord values(String value1, UByte value2, UByte value3, UByte value4, UByte value5, UByte value6, UByte value7, BigDecimal value8, BigDecimal value9, Timestamp value10, Timestamp value11) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached RaceOddsUmrnRecord
     */
    public RaceOddsUmrnRecord() {
        super(RaceOddsUmrn.RACE_ODDS_UMRN);
    }

    /**
     * Create a detached, initialised RaceOddsUmrnRecord
     */
    public RaceOddsUmrnRecord(String kaisaiCd, UByte raceNo, UByte oddsTimeNo, UByte umaNo_1, UByte umaNo_2, UByte ninkiNo, UByte sortNo, BigDecimal umrnOdds, BigDecimal diffRt, Timestamp insertDttm, Timestamp updateDttm) {
        super(RaceOddsUmrn.RACE_ODDS_UMRN);

        set(0, kaisaiCd);
        set(1, raceNo);
        set(2, oddsTimeNo);
        set(3, umaNo_1);
        set(4, umaNo_2);
        set(5, ninkiNo);
        set(6, sortNo);
        set(7, umrnOdds);
        set(8, diffRt);
        set(9, insertDttm);
        set(10, updateDttm);
    }
}
