drop table CATEGORY_NATION CASCADE CONSTRAINTS;

create table CATEGORY_NATION(
	NATION_ID		NUMBER,
	NATION_NAME		VARCHAR2(100) NOT NULL,
	PRIMARY KEY(NATION_ID)
);

insert into CATEGORY_NATION
values(1, '�ѽ�');
insert into CATEGORY_NATION
values(2, '�Ͻ�');
insert into CATEGORY_NATION
values(3, '�߽�');
insert into CATEGORY_NATION
values(4, '���');
