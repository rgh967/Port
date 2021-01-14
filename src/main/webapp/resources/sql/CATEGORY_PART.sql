drop table CATEGORY_PART CASCADE CONSTRAINTS;

create table CATEGORY_PART(
	PART_ID		NUMBER,
	PART_NAME		VARCHAR2(200) NOT NULL,
	PRIMARY KEY(PART_ID)
);

insert into CATEGORY_PART
values(1, '돼지고기');
insert into CATEGORY_PART
values(2, '쇠고기');
insert into CATEGORY_PART
values(3, '닭고기');
insert into CATEGORY_PART
values(4, '햄/소시지');
insert into CATEGORY_PART
values(5, '해산물');
insert into CATEGORY_PART
values(6, '채소류');
insert into CATEGORY_PART
values(7, '콩/두부');
insert into CATEGORY_PART
values(8, '달걀/유제품');
insert into CATEGORY_PART
values(9, '과일');
insert into CATEGORY_PART
values(10, '면류');
insert into CATEGORY_PART
values(11, '김치');
insert into CATEGORY_PART
values(12, '만두');
insert into CATEGORY_PART
values(13, '가공식품');

select * from CATEGORY_PART;