drop table RECIPE CASCADE CONSTRAINTS;
drop sequence RECIPE_SEQ;

create table RECIPE(
	RECIPE_ID         NUMBER,
	MEMBER_ID      VARCHAR2(10) NOT NULL,
	RECIPE_TITLE         VARCHAR2(50) NOT NULL,
	RECIPE_MAIN_IMG      VARCHAR2(100),
	RECIPE_INTRO      VARCHAR2(300) NOT NULL,
	RECIPE_URL         VARCHAR2(1000),
	RECIPE_PEOPLE		NUMBER NOT NULL,
	RECIPE_TIME			NUMBER NOT NULL,
	RECIPE_DEGREE		VARCHAR2(10) NOT NULL,
	RECIPE_READCOUNT	NUMBER default 0,
	PRIMARY KEY(RECIPE_ID),
	FOREIGN KEY (MEMBER_ID) REFERENCES MEMBER(MEMBER_ID)
);
create sequence RECIPE_SEQ;

insert into RECIPE
values(RECIPE_SEQ.nextval,'A1111','������ ���� ����','regi_1/rep_food.jpg','����� �Ұ� ���� ��� �����ϴ� ���� ������ �����Դϴ�.',
		'https://www.youtube.com/watch?v=EQahweVvZcc',
		2, 60, '��', 0);

insert into RECIPE
values(RECIPE_SEQ.nextval,'A1111','�����','regi_2/rep_food.jpg','����� �Ұ� ���� ��� �����ϴ� ������Դϴ�. Ÿ��Ÿ�� �ҽ��� �Բ� ������ ���� ���־��!',
		'https://www.youtube.com/watch?v=8TL4ke15ttQ',
		1, 50, '��', 0);

insert into RECIPE
values(RECIPE_SEQ.nextval,'A2222','������','regi_3/rep_food.jpg','����� �Ұ� ���� ��� �����ϴ� ������ �Դϴ�. ���� ġ� �ּ�~~',
		'https://www.youtube.com/watch?v=yLhgFhaoySU',
		1, 20, '��', 0);

insert into RECIPE
values(RECIPE_SEQ.nextval,'A1111','��α� ������','regi_4/rep_food.jpg','��ġ��������� �� �ٸ� �ŷ�, ��α� ������!',
		'https://www.youtube.com/watch?v=BjliMh3xios',
		2, 20, '��', 0);

insert into RECIPE
values(RECIPE_SEQ.nextval,'A1111','���� ��ġ �׶���','regi_5/rep_food.jpg','���� Ȱ�� ����~~���̵��� �����ϴ� �������� �ְ�!',
		'https://www.youtube.com/watch?v=3UvtRQSVS2o',
		2, 25, '��', 0);

insert into RECIPE
values(RECIPE_SEQ.nextval,'A2222','¥���','regi_6/rep_food.jpg','�达 ǥ���� ¥��� ���� ������ �� �ִ�! ',
		'https://www.youtube.com/watch?v=lV2RBHwzYxk',
		2, 40, '��', 0);

insert into RECIPE
values(RECIPE_SEQ.nextval,'A2222','�ຸ��','regi_7/rep_food.jpg','�ٻ��� �Ļ��� ������ ������� ����! ��� ������, ���̵� ����~ ',
		'https://www.youtube.com/watch?v=i44wkqKlj9E',
		2, 30, '��', 0);

insert into RECIPE
values(RECIPE_SEQ.nextval,'A1111','���� ����','regi_8/rep_food.jpg','���������̾�� ����� ���� ���� ',
		'https://www.youtube.com/watch?v=Qrl0u3jVwE0',
		4, 20, '��', 0);

insert into RECIPE
values(RECIPE_SEQ.nextval,'A2222','���ٽ� �� ������','regi_9/rep_food.jpg','Ȩ��Ƽ ������ ���ٽ� �� ������ ',
		'https://www.youtube.com/watch?v=81KTu3Si4KU',
		2, 29, '��', 0);

insert into RECIPE
values(RECIPE_SEQ.nextval,'A1111','���� �� ����','regi_10/rep_food.jpg','�ʱ��ʱ� ����Ʈ ���� �� ���� ',
		'https://www.youtube.com/watch?v=X_8gpCUiJIM',
		4, 10, '��', 0);

insert into RECIPE
values(RECIPE_SEQ.nextval,'A2222','���κ� �����','regi_11/rep_food.jpg','�ʰ��� ���� ������',
		'https://www.youtube.com/watch?v=X_8gpCUiJIM',
		2, 5, '��', 0);

insert into RECIPE
values(RECIPE_SEQ.nextval,'A2222','ġ�� �������','regi_12/rep_food.jpg','ġ� ���� �þ�� ���ִ� �������~! ',
		'https://www.youtube.com/watch?v=X_8gpCUiJIM',
		2, 10, '��', 0);

select * from RECIPE;

select * 
		from
			( select rownum rnum, b.*
			  from
				  ( select r.RECIPE_ID, r.RECIPE_TITLE, r.RECIPE_MAIN_IMG, 
				  		   SUBSTR(r.RECIPE_INTRO, 1, 25) as RECIPE_INTRO,
				  	 	   r.RECIPE_URL, r.RECIPE_PEOPLE, r.RECIPE_TIME, r.RECIPE_DEGREE,
				  	 	   r.RECIPE_READCOUNT, m.MEMBER_RANK, m.MEMBER_NAME 
				  	from recipe r, member m
				  	where r.MEMBER_ID = m.MEMBER_ID) b
			)
		where rnum >= 1 and rnum <= 12
