drop table CATEGORY_NATION CASCADE CONSTRAINTS;

create table CATEGORY_NATION(
	NATION_ID		NUMBER,
	NATION_NAME		VARCHAR2(100) NOT NULL,
	PRIMARY KEY(NATION_ID)
);

insert into CATEGORY_NATION
values(1, '한식');
insert into CATEGORY_NATION
values(2, '일식');
insert into CATEGORY_NATION
values(3, '중식');
insert into CATEGORY_NATION
values(4, '양식');
