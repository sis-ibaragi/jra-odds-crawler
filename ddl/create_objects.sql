create table if not exists KAISAI (
	KAISAI_CD		CHAR(10)		NOT NULL,
	KAISAI_NM		VARCHAR(32)		NOT NULL,
	KAISAI_DT		DATE			NOT NULL,
	INSERT_DTTM		TIMESTAMP		NOT NULL DEFAULT CURRENT_TIMESTAMP,
	UPDATE_DTTM		TIMESTAMP		NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (KAISAI_CD)
);


create table if not exists RACE (
	KAISAI_CD			CHAR(10)			NOT NULL,
	RACE_NO				TINYINT UNSIGNED	NOT NULL,
	RACE_RSLT_DONE_FLG	BOOLEAN				NOT NULL DEFAULT 0,
	RACE_CANCEL_FLG 	BOOLEAN				NOT NULL DEFAULT 0,
	INSERT_DTTM		TIMESTAMP				NOT NULL DEFAULT CURRENT_TIMESTAMP,
	UPDATE_DTTM		TIMESTAMP				NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (KAISAI_CD, RACE_NO)
);

create table if not exists RACE_UMA_LIST (
	KAISAI_CD		CHAR(10)			NOT NULL,
	RACE_NO			TINYINT UNSIGNED	NOT NULL,
	UMA_NO			TINYINT UNSIGNED	NOT NULL,
	WAKU_NO			TINYINT UNSIGNED	NOT NULL,
	UMA_NM			VARCHAR(64)			NOT NULL,
	JOCKEY_NM		VARCHAR(64)			NOT NULL,
	INSERT_DTTM		TIMESTAMP			NOT NULL DEFAULT CURRENT_TIMESTAMP,
	UPDATE_DTTM		TIMESTAMP			NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (KAISAI_CD, RACE_NO, UMA_NO)
);

create table if not exists RACE_ODDS (
	KAISAI_CD		CHAR(10)			NOT NULL,
	RACE_NO			TINYINT UNSIGNED	NOT NULL,
	ODDS_TIME_NO	TINYINT UNSIGNED	NOT NULL,
	TNPK_ODDS_TIME	TIME,
	UMRN_ODDS_TIME	TIME,
	INSERT_DTTM		TIMESTAMP			NOT NULL DEFAULT CURRENT_TIMESTAMP,
	UPDATE_DTTM		TIMESTAMP			NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (KAISAI_CD, RACE_NO, ODDS_TIME_NO)
);


create table if not exists RACE_ODDS_TAN (
	KAISAI_CD		CHAR(10)			NOT NULL,
	RACE_NO			TINYINT UNSIGNED	NOT NULL,
	ODDS_TIME_NO	TINYINT UNSIGNED	NOT NULL,
	UMA_NO			TINYINT UNSIGNED	NOT NULL,
	NINKI_NO		TINYINT UNSIGNED,
	SORT_NO			TINYINT UNSIGNED,
	TAN_ODDS		DECIMAL(6, 1),
	INSERT_DTTM		TIMESTAMP			NOT NULL DEFAULT CURRENT_TIMESTAMP,
	UPDATE_DTTM		TIMESTAMP			NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (KAISAI_CD, RACE_NO, ODDS_TIME_NO, UMA_NO)
);

create table if not exists RACE_ODDS_FUKU (
	KAISAI_CD		CHAR(10)			NOT NULL,
	RACE_NO			TINYINT UNSIGNED	NOT NULL,
	ODDS_TIME_NO	TINYINT UNSIGNED	NOT NULL,
	UMA_NO			TINYINT UNSIGNED	NOT NULL,
	NINKI_NO		TINYINT UNSIGNED,
	SORT_NO			TINYINT UNSIGNED,
	FUKU_ODDS_MIN	DECIMAL(6, 1),
	FUKU_ODDS_MAX	DECIMAL(6, 1),
	INSERT_DTTM		TIMESTAMP			NOT NULL DEFAULT CURRENT_TIMESTAMP,
	UPDATE_DTTM		TIMESTAMP			NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (KAISAI_CD, RACE_NO, ODDS_TIME_NO, UMA_NO)
);

create table if not exists RACE_ODDS_UMRN (
	KAISAI_CD		CHAR(10)			NOT NULL,
	RACE_NO			TINYINT UNSIGNED	NOT NULL,
	ODDS_TIME_NO	TINYINT UNSIGNED	NOT NULL,
	UMA_NO_1		TINYINT UNSIGNED	NOT NULL,
	UMA_NO_2		TINYINT UNSIGNED	NOT NULL,
	NINKI_NO		TINYINT UNSIGNED,
	SORT_NO			TINYINT UNSIGNED,
	UMRN_ODDS		DECIMAL(6, 1),
	DIFF_RT			DECIMAL(4,2),
	INSERT_DTTM		TIMESTAMP		NOT NULL DEFAULT CURRENT_TIMESTAMP,
	UPDATE_DTTM		TIMESTAMP		NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (KAISAI_CD, RACE_NO, ODDS_TIME_NO, UMA_NO_1, UMA_NO_2)
);

create table if not exists RACE_UMA_MARK (
	KAISAI_CD		CHAR(10)			NOT NULL,
	RACE_NO			TINYINT UNSIGNED	NOT NULL,
	UMA_NO			TINYINT UNSIGNED	NOT NULL,
	MARK_CD			CHAR(2),
	INSERT_DTTM		TIMESTAMP			NOT NULL DEFAULT CURRENT_TIMESTAMP,
	UPDATE_DTTM		TIMESTAMP			NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (KAISAI_CD, RACE_NO, UMA_NO)
);

create table if not exists RACE_RSLT (
	KAISAI_CD		CHAR(10)			NOT NULL,
	RACE_NO			TINYINT UNSIGNED	NOT NULL,
	UMA_NO			TINYINT UNSIGNED	NOT NULL,
	ORDER_NO		TINYINT UNSIGNED,
	RACE_RSLT_DIV	TINYINT(1) UNSIGNED	NOT NULL DEFAULT 0,
	INSERT_DTTM		TIMESTAMP			NOT NULL DEFAULT CURRENT_TIMESTAMP,
	UPDATE_DTTM		TIMESTAMP			NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (KAISAI_CD, RACE_NO, UMA_NO)
);

create table if not exists RACE_RSLT_DIVIDEND_TAN (
	KAISAI_CD		CHAR(10)			NOT NULL,
	RACE_NO			TINYINT UNSIGNED	NOT NULL,
	UMA_NO			TINYINT UNSIGNED	NOT NULL,
	DIVIDEND_YEN	INT UNSIGNED		NOT NULL,
	INSERT_DTTM		TIMESTAMP			NOT NULL DEFAULT CURRENT_TIMESTAMP,
	UPDATE_DTTM		TIMESTAMP			NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (KAISAI_CD, RACE_NO, UMA_NO)
);

create table if not exists RACE_RSLT_DIVIDEND_FUKU (
	KAISAI_CD		CHAR(10)			NOT NULL,
	RACE_NO			TINYINT UNSIGNED	NOT NULL,
	UMA_NO			TINYINT UNSIGNED	NOT NULL,
	DIVIDEND_YEN	INT UNSIGNED		NOT NULL,
	INSERT_DTTM		TIMESTAMP			NOT NULL DEFAULT CURRENT_TIMESTAMP,
	UPDATE_DTTM		TIMESTAMP			NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (KAISAI_CD, RACE_NO, UMA_NO)
);

create table if not exists RACE_RSLT_DIVIDEND_UMRN (
	KAISAI_CD		CHAR(10)			NOT NULL,
	RACE_NO			TINYINT UNSIGNED	NOT NULL,
	UMA_NO_1		TINYINT UNSIGNED	NOT NULL,
	UMA_NO_2		TINYINT UNSIGNED	NOT NULL,
	DIVIDEND_YEN	INT UNSIGNED		NOT NULL,
	INSERT_DTTM		TIMESTAMP			NOT NULL DEFAULT CURRENT_TIMESTAMP,
	UPDATE_DTTM		TIMESTAMP			NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (KAISAI_CD, RACE_NO, UMA_NO_1, UMA_NO_2)
);

create table if not exists RACE_RSLT_DIVIDEND_UMTN (
	KAISAI_CD		CHAR(10)			NOT NULL,
	RACE_NO			TINYINT UNSIGNED	NOT NULL,
	UMA_NO_1		TINYINT UNSIGNED	NOT NULL,
	UMA_NO_2		TINYINT UNSIGNED	NOT NULL,
	DIVIDEND_YEN	INT UNSIGNED		NOT NULL,
	INSERT_DTTM		TIMESTAMP			NOT NULL DEFAULT CURRENT_TIMESTAMP,
	UPDATE_DTTM		TIMESTAMP			NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (KAISAI_CD, RACE_NO, UMA_NO_1, UMA_NO_2)
);

create table if not exists RACE_RSLT_DIVIDEND_WIDE (
	KAISAI_CD		CHAR(10)			NOT NULL,
	RACE_NO			TINYINT UNSIGNED	NOT NULL,
	UMA_NO_1		TINYINT UNSIGNED	NOT NULL,
	UMA_NO_2		TINYINT UNSIGNED	NOT NULL,
	DIVIDEND_YEN	INT UNSIGNED		NOT NULL,
	INSERT_DTTM		TIMESTAMP			NOT NULL DEFAULT CURRENT_TIMESTAMP,
	UPDATE_DTTM		TIMESTAMP			NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (KAISAI_CD, RACE_NO, UMA_NO_1, UMA_NO_2)
);

create table if not exists RACE_RSLT_DIVIDEND_TRIO (
	KAISAI_CD		CHAR(10)			NOT NULL,
	RACE_NO			TINYINT UNSIGNED	NOT NULL,
	UMA_NO_1		TINYINT UNSIGNED	NOT NULL,
	UMA_NO_2		TINYINT UNSIGNED	NOT NULL,
	UMA_NO_3		TINYINT UNSIGNED	NOT NULL,
	DIVIDEND_YEN	INT UNSIGNED		NOT NULL,
	INSERT_DTTM		TIMESTAMP			NOT NULL DEFAULT CURRENT_TIMESTAMP,
	UPDATE_DTTM		TIMESTAMP			NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (KAISAI_CD, RACE_NO, UMA_NO_1, UMA_NO_2, UMA_NO_3)
);

create table if not exists RACE_RSLT_DIVIDEND_TRIFECTA (
	KAISAI_CD		CHAR(10)			NOT NULL,
	RACE_NO			TINYINT UNSIGNED	NOT NULL,
	UMA_NO_1		TINYINT UNSIGNED	NOT NULL,
	UMA_NO_2		TINYINT UNSIGNED	NOT NULL,
	UMA_NO_3		TINYINT UNSIGNED	NOT NULL,
	DIVIDEND_YEN	INT UNSIGNED		NOT NULL,
	INSERT_DTTM		TIMESTAMP			NOT NULL DEFAULT CURRENT_TIMESTAMP,
	UPDATE_DTTM		TIMESTAMP			NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (KAISAI_CD, RACE_NO, UMA_NO_1, UMA_NO_2, UMA_NO_3)
);
