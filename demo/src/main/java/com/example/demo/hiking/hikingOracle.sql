
/* Drop Triggers */

DROP TRIGGER TRI_hiking_mtid;



/* Drop Tables */

DROP TABLE hiking CASCADE CONSTRAINTS;



/* Drop Sequences */

DROP SEQUENCE SEQ_hiking_mtid;




/* Create Sequences */

CREATE SEQUENCE SEQ_hiking_mtid INCREMENT BY 1 START WITH 1;



/* Create Tables */

CREATE TABLE hiking
(
	mtid number NOT NULL,
	mtName varchar2(20) NOT NULL,
	location varchar2(40),
	altitude number NOT NULL,
	viewCount number DEFAULT 0 NOT NULL,
	modTime timestamp DEFAULT CURRENT_TIMESTAMP,
	isDeleted number DEFAULT 0,
	PRIMARY KEY (mtid)
);



/* Create Triggers */

CREATE OR REPLACE TRIGGER TRI_hiking_mtid BEFORE INSERT ON hiking
FOR EACH ROW
BEGIN
	SELECT SEQ_hiking_mtid.nextval
	INTO :new.mtid
	FROM dual;
END;

/




