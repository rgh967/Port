drop table CLASS CASCADE CONSTRAINTS;
drop sequence CLASS_SEQ;

create table CLASS(
	CLASS_ID  	       	NUMBER,
	MEMBER_ID   		VARCHAR2(10) NOT NULL,
	CLASS_TITLE         VARCHAR2(100) NOT NULL,
	CLASS_MAIN_IMG      VARCHAR2(100) NOT NULL,
	CLASS_APP_STARTDATE 	DATE NOT NULL,
	CLASS_APP_ENDDATE 		DATE NOT NULL,
	CLASS_STARTDATE 	DATE NOT NULL,
	CLASS_ENDDATE 		DATE NOT NULL,
	CLASS_STARTTIME		DATE NOT NULL,
	CLASS_ENDTIME		DATE NOT NULL,
	CLASS_PEOPLE 		NUMBER NOT NULL,
	CLASS_REG_PEOPLE		NUMBER default 0,
	CLASS_COST 			NUMBER NOT NULL,
	CLASS_ADDRESS		LONG NOT NULL,
	CLASS_LECTURER		VARCHAR2(1000) NOT NULL,
	PRIMARY KEY(CLASS_ID),
	FOREIGN KEY (MEMBER_ID) REFERENCES MEMBER(MEMBER_ID)
);

create sequence CLASS_SEQ;

insert into CLASS
values(CLASS_SEQ.nextval,'A1111','�丮����) ���� ��ī�� Ŭ����','regi_1/rep_class.jpg',
		'2020-11-01', '2020-11-30', '2020-12-20', '2020-12-22', TO_DATE('10:00','HH24:MI'), TO_DATE('13:00','HH24:MI'),
		10, 0, 30000, '����� ���ʱ�', 'ȣ�� ����Ʈ �ǹ� ������ A111�Դϴ�!');
		
insert into CLASS
values(CLASS_SEQ.nextval,'A2222','�ܿ����!! ���� & ����','regi_2/rep_class.png',
		'2020-10-10', '2020-11-11', '2020-11-29', '2020-11-29', TO_DATE('12:00','HH24:MI'), TO_DATE('18:00','HH24:MI'),
		15, 0, 80000, '��û���� ������', '�ҸӴ� �ո� �״��~! �ð� ���� A222�Դϴ�.');
		
insert into CLASS
values(CLASS_SEQ.nextval,'A2222','�پ��� �α� �ع���','regi_3/rep_class.png',
		'2020-12-01', '2020-12-20', '2021-01-21', '2021-01-24', TO_DATE('10:00','HH24:MI'), TO_DATE('12:00','HH24:MI'),
		15, 0, 80000, '��û���� ������', '�ҸӴ� �ո� �״��~! �ð� ���� A222�Դϴ�.');
		
insert into CLASS
values(CLASS_SEQ.nextval,'A2222','�а��� �丮�� ���� ���','regi_4/rep_class.png',
		'2020-12-21', '2021-01-11', '2021-02-01', '2021-02-05', TO_DATE('10:00','HH24:MI'), TO_DATE('12:00','HH24:MI'),
		15, 0, 80000, '��û���� ������', '�ҸӴ� �ո� �״��~! �ð� ���� A222�Դϴ�.');

insert into CLASS
values(CLASS_SEQ.nextval,'A1111','Ÿ��Ʈ ������ų�','regi_5/rep_class.png',
		'2021-01-10', '2021-01-30', '2021-02-03', '2021-02-05', TO_DATE('14:00','HH24:MI'), TO_DATE('17:00','HH24:MI'),
		10, 0, 200000, '����� ���ʱ�', 'ȣ�� ����Ʈ �ǹ� ������ A111�Դϴ�!');
		
insert into CLASS
values(CLASS_SEQ.nextval,'A2222','������ Ŭ����) ������ �����, �κ����� �����','regi_6/rep_class.png',
		'2020-12-01', '2020-12-31', '2021-01-08', '2021-01-08', TO_DATE('13:10','HH24:MI'), TO_DATE('15:10','HH24:MI'),
		15, 0, 15000, '��û���� ������', '�ҸӴ� �ո� �״��~! �ð� ���� A222�Դϴ�.');

insert into CLASS
values(CLASS_SEQ.nextval,'A2222','������Ŭ����) ���η�ġ��, �ް����� �����','regi_7/rep_class.png',
		'2020-12-01', '2020-12-31', '2021-01-14', '2021-01-14', TO_DATE('19:30','HH24:MI'), TO_DATE('21:30','HH24:MI'),
		15, 0, 15000, '��û���� ������', '�ҸӴ� �ո� �״��~! �ð� ���� A222�Դϴ�.');

insert into CLASS
values(CLASS_SEQ.nextval,'A2222','������ ���ɸ��� ���ִ� ������� ����� (���ѹα�/����)','regi_8/rep_class.png',
		'2021-09-10', '2021-10-01', '2021-10-11', '2021-10-13', TO_DATE('12:00','HH24:MI'), TO_DATE('18:00','HH24:MI'),
		20, 0, 60000, '��û���� ������', '�ҸӴ� �ո� �״��~! �ð� ���� A222�Դϴ�.');

insert into CLASS
values(CLASS_SEQ.nextval,'A1111','�ѱ����� ������ ������ ǻ�� ������ �簻','regi_9/rep_class.png',
		'2021-09-10', '2021-10-01', '2021-10-14', '2021-10-14', TO_DATE('12:00','HH24:MI'), TO_DATE('18:00','HH24:MI'),
		20, 0, 130000, '����� ���ʱ�', 'ȣ�� ����Ʈ �ǹ� ������ A111�Դϴ�!');

insert into CLASS
values(CLASS_SEQ.nextval,'A1111','���� ����� ����� �ѳ� ī�� �귱ġ','regi_10/rep_class.jpg',
				'2021-09-10', '2021-10-01', '2021-10-14', '2021-10-14', TO_DATE('12:00','HH24:MI'), TO_DATE('18:00','HH24:MI'),
		15, 0, 80000, '����� ���ʱ�', 'ȣ�� ����Ʈ �ǹ� ������ A111�Դϴ�!');

insert into CLASS
values(CLASS_SEQ.nextval,'A2222','������ ���� ���ڿ��� ���� ���ִ� "������ ����" Ŭ����!','regi_11/rep_class.png',
		'2021-10-10', '2021-11-11', '2021-11-29', '2021-11-29', TO_DATE('15:00','HH24:MI'), TO_DATE('18:00','HH24:MI'),
		5, 0, 26000, '����� ���', '��) ������ �丮 ����<br>*���� ��� :<br>- �����̺� �ұԸ� ������ Ȩ ��ŷ Ŭ���� �<br>- "�������ҳ븶" �� ��ŷ Ŭ���� ����<br>- "���� ����Ʈ ���" ����Ʈ �о� ���� (1ȸ-22ȸ)<br>- "���� ����Ʈ �佺Ƽ��" ��û ����<br>- TV���� ���� ���� Ǫ���佺Ƽ�� ����ũ�п´� �긴���� ����Ʈ �о� ��û ����<br>- Anispoon, ����Ʈ���� (������ ���� ü�� ȣ��Ʈ)');

insert into CLASS
values(CLASS_SEQ.nextval,'A1111','������ ���, ����ŷ ��ŷ Ŭ����!','regi_12/rep_class.png',
		'2020-10-10', '2020-11-11', '2020-11-29', '2020-11-29', TO_DATE('15:00','HH24:MI'), TO_DATE('18:00','HH24:MI'),
		15, 0, 80000, '����� ���ʱ�', 'ȣ�� ����Ʈ �ǹ� ������ A111�Դϴ�!');

select * from CLASS;
