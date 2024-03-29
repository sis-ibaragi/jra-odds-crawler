alter table RACE add constraint RACE_FK1 foreign key if not exists (KAISAI_CD) references KAISAI(KAISAI_CD);
alter table RACE_UMA_LIST add constraint RACE_UMA_LIST_FK1 foreign key if not exists (KAISAI_CD, RACE_NO) references RACE(KAISAI_CD, RACE_NO);
alter table RACE_ODDS add constraint RACE_ODDS_FK1 foreign key if not exists (KAISAI_CD, RACE_NO) references RACE(KAISAI_CD, RACE_NO);
alter table RACE_ODDS_TAN add constraint RACE_ODDS_TAN_FK1 foreign key if not exists (KAISAI_CD, RACE_NO, ODDS_TIME_NO) references RACE_ODDS(KAISAI_CD, RACE_NO, ODDS_TIME_NO);
alter table RACE_ODDS_FUKU add constraint RACE_ODDS_FUKU_FK1 foreign key if not exists (KAISAI_CD, RACE_NO, ODDS_TIME_NO) references RACE_ODDS(KAISAI_CD, RACE_NO, ODDS_TIME_NO);
alter table RACE_ODDS_UMRN add constraint RACE_ODDS_UMRN_FK1 foreign key if not exists (KAISAI_CD, RACE_NO, ODDS_TIME_NO) references RACE_ODDS(KAISAI_CD, RACE_NO, ODDS_TIME_NO);
alter table RACE_UMA_MARK add constraint RACE_UMA_MARK_FK1 foreign key if not exists (KAISAI_CD, RACE_NO, UMA_NO) references RACE_UMA_LIST(KAISAI_CD, RACE_NO, UMA_NO);
alter table RACE_RSLT add constraint RACE_RSLT_FK1 foreign key if not exists (KAISAI_CD, RACE_NO, UMA_NO) references RACE_UMA_LIST(KAISAI_CD, RACE_NO, UMA_NO);
alter table RACE_RSLT_DIVIDEND_TAN add constraint RACE_RSLT_DIVIDEND_TAN_FK1 foreign key if not exists (KAISAI_CD, RACE_NO, UMA_NO) references RACE_UMA_LIST(KAISAI_CD, RACE_NO, UMA_NO);
alter table RACE_RSLT_DIVIDEND_FUKU add constraint RACE_RSLT_DIVIDEND_FUKU_FK1 foreign key if not exists (KAISAI_CD, RACE_NO, UMA_NO) references RACE_UMA_LIST(KAISAI_CD, RACE_NO, UMA_NO);
alter table RACE_RSLT_DIVIDEND_UMRN add constraint RACE_RSLT_DIVIDEND_UMRN_FK1 foreign key if not exists (KAISAI_CD, RACE_NO, UMA_NO_1) references RACE_UMA_LIST(KAISAI_CD, RACE_NO, UMA_NO);
alter table RACE_RSLT_DIVIDEND_UMRN add constraint RACE_RSLT_DIVIDEND_UMRN_FK2 foreign key if not exists (KAISAI_CD, RACE_NO, UMA_NO_2) references RACE_UMA_LIST(KAISAI_CD, RACE_NO, UMA_NO);
alter table RACE_RSLT_DIVIDEND_UMTN add constraint RACE_RSLT_DIVIDEND_UMTN_FK1 foreign key if not exists (KAISAI_CD, RACE_NO, UMA_NO_1) references RACE_UMA_LIST(KAISAI_CD, RACE_NO, UMA_NO);
alter table RACE_RSLT_DIVIDEND_UMTN add constraint RACE_RSLT_DIVIDEND_UMTN_FK2 foreign key if not exists (KAISAI_CD, RACE_NO, UMA_NO_2) references RACE_UMA_LIST(KAISAI_CD, RACE_NO, UMA_NO);
alter table RACE_RSLT_DIVIDEND_WIDE add constraint RACE_RSLT_DIVIDEND_WIDE_FK1 foreign key if not exists (KAISAI_CD, RACE_NO, UMA_NO_1) references RACE_UMA_LIST(KAISAI_CD, RACE_NO, UMA_NO);
alter table RACE_RSLT_DIVIDEND_WIDE add constraint RACE_RSLT_DIVIDEND_WIDE_FK2 foreign key if not exists (KAISAI_CD, RACE_NO, UMA_NO_2) references RACE_UMA_LIST(KAISAI_CD, RACE_NO, UMA_NO);
alter table RACE_RSLT_DIVIDEND_TRIO add constraint RACE_RSLT_DIVIDEND_TRIO_FK1 foreign key if not exists (KAISAI_CD, RACE_NO, UMA_NO_1) references RACE_UMA_LIST(KAISAI_CD, RACE_NO, UMA_NO);
alter table RACE_RSLT_DIVIDEND_TRIO add constraint RACE_RSLT_DIVIDEND_TRIO_FK2 foreign key if not exists (KAISAI_CD, RACE_NO, UMA_NO_2) references RACE_UMA_LIST(KAISAI_CD, RACE_NO, UMA_NO);
alter table RACE_RSLT_DIVIDEND_TRIO add constraint RACE_RSLT_DIVIDEND_TRIO_FK3 foreign key if not exists (KAISAI_CD, RACE_NO, UMA_NO_3) references RACE_UMA_LIST(KAISAI_CD, RACE_NO, UMA_NO);
alter table RACE_RSLT_DIVIDEND_TRIFECTA add constraint RACE_RSLT_DIVIDEND_TRIFECTA_FK1 foreign key if not exists (KAISAI_CD, RACE_NO, UMA_NO_1) references RACE_UMA_LIST(KAISAI_CD, RACE_NO, UMA_NO);
alter table RACE_RSLT_DIVIDEND_TRIFECTA add constraint RACE_RSLT_DIVIDEND_TRIFECTA_FK2 foreign key if not exists (KAISAI_CD, RACE_NO, UMA_NO_2) references RACE_UMA_LIST(KAISAI_CD, RACE_NO, UMA_NO);
alter table RACE_RSLT_DIVIDEND_TRIFECTA add constraint RACE_RSLT_DIVIDEND_TRIFECTA_FK3 foreign key if not exists (KAISAI_CD, RACE_NO, UMA_NO_3) references RACE_UMA_LIST(KAISAI_CD, RACE_NO, UMA_NO);
