drop table CATEGORY_CHEF CASCADE CONSTRAINTS;

create table CATEGORY_CHEF(
	CHEF_ID		NUMBER,
	CHEF_NAME		VARCHAR2(100) NOT NULL,
	PRIMARY KEY(CHEF_ID)
);

insert into CATEGORY_CHEF
values(1, '������');
insert into CATEGORY_CHEF
values(2, '�����');
insert into CATEGORY_CHEF
values(3, '�̿���');
insert into CATEGORY_CHEF
values(4, '������');
