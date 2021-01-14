drop table CLASS_CURRICULUM CASCADE CONSTRAINTS;

create table CLASS_CURRICULUM(
	CLASS_ID  	       	NUMBER,
	CUR_DAY				NUMBER,
	CUR_CONTENT			VARCHAR2(200) NOT NULL,
	PRIMARY KEY(CLASS_ID, CUR_DAY),
	FOREIGN KEY (CLASS_ID) REFERENCES CLASS(CLASS_ID)
);

insert into CLASS_CURRICULUM
values(1, 1, '기본 도구 및 마카롱 이론 설명');
insert into CLASS_CURRICULUM
values(1, 2, '마키롱의 기본! 머랭 올리는 법과 마카로나쥬 실습');
insert into CLASS_CURRICULUM
values(1, 3, '유자 마카롱 & 초코 마카롱 만들기~');

insert into CLASS_CURRICULUM
values(2, 1, '김장김치도 만들어가고 다함께 보쌈도 만들어 먹는 원데이 클래스');

insert into CLASS_CURRICULUM
values(3, 1, '간단한 계란 요리 배우기');
insert into CLASS_CURRICULUM
values(3, 2, '생선손질 및 생선 요리 실습');
insert into CLASS_CURRICULUM
values(3, 3, '도시락 반찬 꿀팁');
insert into CLASS_CURRICULUM
values(3, 4, '상다리 휘는 한상 차림! ');

insert into CLASS_CURRICULUM
values(4, 1, '다양한 밀가루 활용 요리 이론');
insert into CLASS_CURRICULUM
values(4, 2, '다양한 밀가루 활용 요리 실습');
insert into CLASS_CURRICULUM
values(4, 3, '육수의 종류 이론');
insert into CLASS_CURRICULUM
values(4, 4, '육수의 종류 실습');
insert into CLASS_CURRICULUM
values(4, 5, '손 칼국수 만들기');

insert into CLASS_CURRICULUM
values(5, 1, '커스터드 타르트');
insert into CLASS_CURRICULUM
values(5, 2, '피칸 타르트');
insert into CLASS_CURRICULUM
values(5, 3, '마카롱레몬머랭 타르트');

insert into CLASS_CURRICULUM
values(6, 1, '고추장 고등어갈비, 두부조림');

insert into CLASS_CURRICULUM
values(7, 1, '목살두루치기, 달걀말이');

insert into CLASS_CURRICULUM
values(8, 1, '전통주와 전통안주');
insert into CLASS_CURRICULUM
values(8, 2, '전통주와 전통안주');
insert into CLASS_CURRICULUM
values(8, 3, '전통주와 전통안주');

insert into CLASS_CURRICULUM
values(9, 1, '퓨전 디자인 양갱');

insert into CLASS_CURRICULUM
values(10, 1, '카마고 샌드, 카츠 샌드, 아보카도 수란 오픈 샌드위치, 두툼 프랜치 토스트, 계절과일 팬 케이크');

insert into CLASS_CURRICULUM
values(11, 1, '프랑스식 집밥 요리 2가지와 디져트인 마들렌');

insert into CLASS_CURRICULUM
values(12, 1, '딸기 생크림 케이크');