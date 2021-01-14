drop table SUBSCRIBE CASCADE CONSTRAINTS;

create table SUBSCRIBE(
	MEMBER_ID_REG      VARCHAR2(10),
	MEMBER_ID_SUBS     VARCHAR2(10),
	PRIMARY KEY(MEMBER_ID_REG, MEMBER_ID_SUBS),
	FOREIGN KEY (MEMBER_ID_REG) REFERENCES MEMBER(MEMBER_ID),
	FOREIGN KEY (MEMBER_ID_SUBS) REFERENCES MEMBER(MEMBER_ID)
);
		
select * from SUBSCRIBE;