--drop SEQUENCE SEQ_TABLE;
--CREATE SEQUENCE SEQ_TABLE;

--drop table user;
CREATE TABLE SEC_USER (
--	id BIGINT DEFAULT (NEXT VALUE FOR SEQ_TABLE) PRIMARY KEY NOT NULL,
	id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	created_By VARCHAR NOT NULL,
	created_Date TIMESTAMP NOT NULL,
	last_Modified_By VARCHAR NOT NULL,
	last_Modified_Date TIMESTAMP NOT NULL,
	version_No BIGINT,
	status VARCHAR NOT NULL,
	user_code VARCHAR NOT NULL,
	user_name VARCHAR NOT NULL,
	user_type VARCHAR NOT NULL,
	OFFICE VARCHAR NOT NULL
);

CREATE TABLE JOURNAL_DATA (
--	id BIGINT DEFAULT (NEXT VALUE FOR SEQ_TABLE) PRIMARY KEY NOT NULL,
	id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	created_By VARCHAR NOT NULL,
	created_Date TIMESTAMP NOT NULL,
	last_Modified_By VARCHAR NOT NULL,
	last_Modified_Date TIMESTAMP NOT NULL,
	version_No BIGINT,
	status VARCHAR NOT NULL,
	AUTHOR_CH_NAME VARCHAR NOT NULL,
	AUTHOR_EN_NAME VARCHAR NOT NULL,
	PAPER_CH_TITLE VARCHAR NOT NULL,
	PAPER_EN_TITLE VARCHAR NOT NULL,
	PROFESSOR_CH_NAME VARCHAR NOT NULL,
	PROFESSOR_EN_NAME VARCHAR NOT NULL,
	GRADUATE_LEVEL VARCHAR NOT NULL,
	COLLEGE VARCHAR NOT NULL,
	DEPARTMENT VARCHAR NOT NULL,
	PUBLISH_YR INT NOT NULL,
	GRADUATE_YR INT NOT NULL,
	CH_KEY_WORDS VARCHAR NOT NULL,
	EN_KEY_WORDS VARCHAR NOT NULL,
	CH_ABSTRACT VARCHAR NOT NULL,
	EN_ABSTRACT VARCHAR NOT NULL,
	SITE VARCHAR NOT NULL
);