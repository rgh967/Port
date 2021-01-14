drop table RECIPE_ING CASCADE CONSTRAINTS;

create table RECIPE_ING(
	RECIPE_ID      NUMBER,
	ING_NAME     VARCHAR2(50),
	ING_AMOUNT      VARCHAR2(50) NOT NULL,
	PRIMARY KEY(RECIPE_ID, ING_NAME),
	FOREIGN KEY (RECIPE_ID) REFERENCES RECIPE(RECIPE_ID)
);

insert into RECIPE_ING
values(1, '팽이버섯', '1/3개');
insert into RECIPE_ING
values(1, '마늘', '8알');
insert into RECIPE_ING
values(1, '양파', '1/2개');
insert into RECIPE_ING
values(1, '베이컨', '120g');
insert into RECIPE_ING
values(1, '숙주', '200g');

insert into RECIPE_ING
values(2, '달고기 포', '1조각');
insert into RECIPE_ING
values(2, '소금', '1ts');
insert into RECIPE_ING
values(2, '후추', '1ts');
insert into RECIPE_ING
values(2, '밀가루', '1컵');
insert into RECIPE_ING
values(2, '계란', '1개');
insert into RECIPE_ING
values(2, '빵가루', '1컵');
insert into RECIPE_ING
values(2, '타르타르소스', '한종지');

insert into RECIPE_ING
values(3, '쌀밥', '1그릇');
insert into RECIPE_ING
values(3, '청피망', '1개');
insert into RECIPE_ING
values(3, '모짜렐라치즈', '2줌');
insert into RECIPE_ING
values(3, '불고기햄', '40g');
insert into RECIPE_ING
values(3, '양파', '1/3개');
insert into RECIPE_ING
values(3, '파자소스', '1국자');
insert into RECIPE_ING
values(3, '계란', '1개');

insert into RECIPE_ING
values(4, '쌀밥', '2그릇');
insert into RECIPE_ING
values(4, '깍두기', '2/3접시');
insert into RECIPE_ING
values(4, '소고기', '50g');
insert into RECIPE_ING
values(4, '버터', '1큰술');
insert into RECIPE_ING
values(4, '다진 마늘', '1큰술');
insert into RECIPE_ING
values(4, '굴소스', '1작은 술');
insert into RECIPE_ING
values(4, '깨소금', '약간');

insert into RECIPE_ING
values(5, '쌀밥', '2그릇');
insert into RECIPE_ING
values(5, '양파', '1개(소)');
insert into RECIPE_ING
values(5, '노란 파프리카', '1/2개');
insert into RECIPE_ING
values(5, '양송이 버섯', '2개');
insert into RECIPE_ING
values(5, '배추김치', '1/2줌');
insert into RECIPE_ING
values(5, '햄', '50g');
insert into RECIPE_ING
values(5, '토마토 소스', '7숫가락');
insert into RECIPE_ING
values(5, '모짜렐라 치즈', '한줌');
insert into RECIPE_ING
values(5, '체다 치즈', '1장');
insert into RECIPE_ING
values(5, '다진 마늘', '1/2숫가락');
insert into RECIPE_ING
values(5, '버터', '1숫가락');

insert into RECIPE_ING
values(6, '중화면', '1');
insert into RECIPE_ING
values(6, '양파', '1');
insert into RECIPE_ING
values(6, '감자', '2');
insert into RECIPE_ING
values(6, '대파', '1');
insert into RECIPE_ING
values(6, '양배추', '1/4');
insert into RECIPE_ING
values(6, '춘장', '1봉');
insert into RECIPE_ING
values(6, '오이', '1');
insert into RECIPE_ING
values(6, '돼지목살', '1');
insert into RECIPE_ING
values(6, '계란', '1');
insert into RECIPE_ING
values(6, '전분', '약간');

insert into RECIPE_ING
values(7, '새우', '20마리');
insert into RECIPE_ING
values(7, '식빵', '4장');
insert into RECIPE_ING
values(7, '달걀흰자', '1개');
insert into RECIPE_ING
values(7, '소금', '1/2작은술');
insert into RECIPE_ING
values(7, '후추', '약간');
insert into RECIPE_ING
values(7, '감자전분', '1.5큰술');
insert into RECIPE_ING
values(7, '식용유', '약간');

insert into RECIPE_ING
values(8, '고구마', '2개');
insert into RECIPE_ING
values(8, '식용유', '1스푼');
insert into RECIPE_ING
values(8, '올리고당', '3스푼');
insert into RECIPE_ING
values(8, '설탕', '2스푼');

insert into RECIPE_ING
values(9, '생 칵테일 새우(대)', '8마리');
insert into RECIPE_ING
values(9, '마늘', '8알');
insert into RECIPE_ING
values(9, '올리브유', '1컵');
insert into RECIPE_ING
values(9, '페페론치노', '10개');
insert into RECIPE_ING
values(9, '소금', '조금');
insert into RECIPE_ING
values(9, '통후추 가루', '조금');

insert into RECIPE_ING
values(10, '블루베리', '2컵');
insert into RECIPE_ING
values(10, '체리', '2컵');
insert into RECIPE_ING
values(10, '꿀', '3큰술');
insert into RECIPE_ING
values(10, '판젤라틴', '4장');
insert into RECIPE_ING
values(10, '레몬즙', '2큰술');

insert into RECIPE_ING
values(11, '순두부', '1모');
insert into RECIPE_ING
values(11, '계란', '2알');
insert into RECIPE_ING
values(11, '쪽파', '한줌');
insert into RECIPE_ING
values(11, '국간장', '1T');
insert into RECIPE_ING
values(11, '소금', '1/3t');
insert into RECIPE_ING
values(11, '다진마늘', '1T');
insert into RECIPE_ING
values(11, '해물다시팩', '1개');
insert into RECIPE_ING
values(11, '고춧가루', '1T');
insert into RECIPE_ING
values(11, '설탕', '1t');
insert into RECIPE_ING
values(11, '깨', '1t');
insert into RECIPE_ING
values(11, '청양고추', '1개');

insert into RECIPE_ING
values(12, '계란', '4알');
insert into RECIPE_ING
values(12, '체다치즈', '1장');
insert into RECIPE_ING
values(12, '소금', '약간');

