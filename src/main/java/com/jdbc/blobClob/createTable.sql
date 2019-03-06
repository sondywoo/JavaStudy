-----------------------------------------------------------------
--	For MySQL
-----------------------------------------------------------------

DROP TABLE T_JDBC_BLOB;
CREATE TABLE T_JDBC_BLOB(
	`ob_id` NUMBER(20) unsigned NOT NULL,
	`ob_name` VARCHAR2(200) DEFAULT NULL,
	`ob_blob` BLOB(2000) DEFAULT NULL,
	PRIMARY KEY (`ob_id`)
) DEFAULT CHARSET=utf8;

-----------------------------------------------------------------
--	For Oracle
-----------------------------------------------------------------

DROP TABLE T_JDBC_BLOB;
CREATE TABLE T_JDBC_BLOB(
	ob_id NUMBER(20) NOT NULL,
	ob_name VARCHAR2(200) DEFAULT NULL,
	ob_blob Blob DEFAULT NULL,
	PRIMARY KEY (ob_id)
);

DROP TABLE T_JDBC_CLOB;
CREATE TABLE T_JDBC_CLOB(
	ob_id NUMBER(20) NOT NULL,
	ob_name VARCHAR2(200) DEFAULT NULL,
	ob_clob Clob DEFAULT NULL,
	PRIMARY KEY (ob_id)
);