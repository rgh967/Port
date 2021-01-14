drop table CATEGORY_PART CASCADE CONSTRAINTS;

create table CATEGORY_PART(
	PART_ID		NUMBER,
	PART_NAME		VARCHAR2(200) NOT NULL,
	PRIMARY KEY(PART_ID)
);

insert into CATEGORY_PART
values(1, '�������');
insert into CATEGORY_PART
values(2, '����');
insert into CATEGORY_PART
values(3, '�߰��');
insert into CATEGORY_PART
values(4, '��/�ҽ���');
insert into CATEGORY_PART
values(5, '�ػ깰');
insert into CATEGORY_PART
values(6, 'ä�ҷ�');
insert into CATEGORY_PART
values(7, '��/�κ�');
insert into CATEGORY_PART
values(8, '�ް�/����ǰ');
insert into CATEGORY_PART
values(9, '����');
insert into CATEGORY_PART
values(10, '���');
insert into CATEGORY_PART
values(11, '��ġ');
insert into CATEGORY_PART
values(12, '����');
insert into CATEGORY_PART
values(13, '������ǰ');

select * from CATEGORY_PART;