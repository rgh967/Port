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
values(CLASS_SEQ.nextval,'A1111','요리공방) 달콤 마카롱 클래스','regi_1/rep_class.jpg',
		'2020-11-01', '2020-11-30', '2020-12-20', '2020-12-22', TO_DATE('10:00','HH24:MI'), TO_DATE('13:00','HH24:MI'),
		10, 0, 30000, '서울시 서초구', '호텔 디저트 실무 전문가 A111입니다!');
		
insert into CLASS
values(CLASS_SEQ.nextval,'A2222','겨울맞이!! 김장 & 보쌈','regi_2/rep_class.png',
		'2020-10-10', '2020-11-11', '2020-11-29', '2020-11-29', TO_DATE('12:00','HH24:MI'), TO_DATE('18:00','HH24:MI'),
		15, 0, 80000, '충청남도 당진시', '할머니 손맛 그대로~! 시골 엄마 A222입니다.');
		
insert into CLASS
values(CLASS_SEQ.nextval,'A2222','다양한 인기 밑반찬','regi_3/rep_class.png',
		'2020-12-01', '2020-12-20', '2021-01-21', '2021-01-24', TO_DATE('10:00','HH24:MI'), TO_DATE('12:00','HH24:MI'),
		15, 0, 80000, '충청남도 당진시', '할머니 손맛 그대로~! 시골 엄마 A222입니다.');
		
insert into CLASS
values(CLASS_SEQ.nextval,'A2222','밀가루 요리와 육수 비법','regi_4/rep_class.png',
		'2020-12-21', '2021-01-11', '2021-02-01', '2021-02-05', TO_DATE('10:00','HH24:MI'), TO_DATE('12:00','HH24:MI'),
		15, 0, 80000, '충청남도 당진시', '할머니 손맛 그대로~! 시골 엄마 A222입니다.');

insert into CLASS
values(CLASS_SEQ.nextval,'A1111','타르트 프로페셔널','regi_5/rep_class.png',
		'2021-01-10', '2021-01-30', '2021-02-03', '2021-02-05', TO_DATE('14:00','HH24:MI'), TO_DATE('17:00','HH24:MI'),
		10, 0, 200000, '서울시 서초구', '호텔 디저트 실무 전문가 A111입니다!');
		
insert into CLASS
values(CLASS_SEQ.nextval,'A2222','원데이 클래스) 고추장 고등어갈비, 두부조림 만들기','regi_6/rep_class.png',
		'2020-12-01', '2020-12-31', '2021-01-08', '2021-01-08', TO_DATE('13:10','HH24:MI'), TO_DATE('15:10','HH24:MI'),
		15, 0, 15000, '충청남도 당진시', '할머니 손맛 그대로~! 시골 엄마 A222입니다.');

insert into CLASS
values(CLASS_SEQ.nextval,'A2222','원데이클래스) 목살두루치기, 달걀말이 만들기','regi_7/rep_class.png',
		'2020-12-01', '2020-12-31', '2021-01-14', '2021-01-14', TO_DATE('19:30','HH24:MI'), TO_DATE('21:30','HH24:MI'),
		15, 0, 15000, '충청남도 당진시', '할머니 손맛 그대로~! 시골 엄마 A222입니다.');

insert into CLASS
values(CLASS_SEQ.nextval,'A2222','나만의 막걸리와 맛있는 전통안주 만들기 (대한민국/서울)','regi_8/rep_class.png',
		'2021-09-10', '2021-10-01', '2021-10-11', '2021-10-13', TO_DATE('12:00','HH24:MI'), TO_DATE('18:00','HH24:MI'),
		20, 0, 60000, '충청남도 당진시', '할머니 손맛 그대로~! 시골 엄마 A222입니다.');

insert into CLASS
values(CLASS_SEQ.nextval,'A1111','한국전통 문양을 접합한 퓨전 디자인 양갱','regi_9/rep_class.png',
		'2021-09-10', '2021-10-01', '2021-10-14', '2021-10-14', TO_DATE('12:00','HH24:MI'), TO_DATE('18:00','HH24:MI'),
		20, 0, 130000, '서울시 서초구', '호텔 디저트 실무 전문가 A111입니다!');

insert into CLASS
values(CLASS_SEQ.nextval,'A1111','직접 만드는 우아한 한끼 카페 브런치','regi_10/rep_class.jpg',
				'2021-09-10', '2021-10-01', '2021-10-14', '2021-10-14', TO_DATE('12:00','HH24:MI'), TO_DATE('18:00','HH24:MI'),
		15, 0, 80000, '서울시 서초구', '호텔 디저트 실무 전문가 A111입니다!');

insert into CLASS
values(CLASS_SEQ.nextval,'A2222','프랑스 쉐프 니코에게 배우는 맛있는 "프랑스 집밥" 클래스!','regi_11/rep_class.png',
		'2021-10-10', '2021-11-11', '2021-11-29', '2021-11-29', TO_DATE('15:00','HH24:MI'), TO_DATE('18:00','HH24:MI'),
		5, 0, 26000, '서울시 용산', '현) 프랑스 요리 쉐프<br>*쉐프 경력 :<br>- 프라이빗 소규모 프랑스 홈 쿠킹 클래스 운영<br>- "윌리엄소노마" 고객 쿠킹 클래스 진행<br>- "서울 디저트 페어" 디저트 분야 참여 (1회-22회)<br>- "월드 디저트 페스티벌" 초청 참여<br>- TV조선 주최 서울 푸드페스티벌 “피크닉온더 브릿지” 디저트 분야 초청 참여<br>- Anispoon, 집밥트레블러 (프랑스 집밥 체험 호스트)');

insert into CLASS
values(CLASS_SEQ.nextval,'A1111','나만의 취미, 베이킹 쿠킹 클래스!','regi_12/rep_class.png',
		'2020-10-10', '2020-11-11', '2020-11-29', '2020-11-29', TO_DATE('15:00','HH24:MI'), TO_DATE('18:00','HH24:MI'),
		15, 0, 80000, '서울시 서초구', '호텔 디저트 실무 전문가 A111입니다!');

select * from CLASS;
