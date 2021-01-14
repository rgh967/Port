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
values(RECIPE_SEQ.nextval,'A1111','베이컨 숙주 볶음','regi_1/rep_food.jpg','어른아이 할거 없이 모두 좋아하는 숙주 베이컨 볶음입니다.',
		'https://www.youtube.com/watch?v=EQahweVvZcc',
		2, 60, '상', 0);

insert into RECIPE
values(RECIPE_SEQ.nextval,'A1111','생선까스','regi_2/rep_food.jpg','어른아이 할거 없이 모두 좋아하는 생선까스입니다. 타르타르 소스와 함께 먹으면 더욱 맛있어요!',
		'https://www.youtube.com/watch?v=8TL4ke15ttQ',
		1, 50, '상', 0);

insert into RECIPE
values(RECIPE_SEQ.nextval,'A2222','밥피자','regi_3/rep_food.jpg','어른아이 할거 없이 모두 좋아하는 밥피자 입니다. 피자 치즈를 솔솔~~',
		'https://www.youtube.com/watch?v=yLhgFhaoySU',
		1, 20, '하', 0);

insert into RECIPE
values(RECIPE_SEQ.nextval,'A1111','깍두기 볶음밥','regi_4/rep_food.jpg','김치볶음밥과는 또 다른 매력, 깍두기 볶음밥!',
		'https://www.youtube.com/watch?v=BjliMh3xios',
		2, 20, '하', 0);

insert into RECIPE
values(RECIPE_SEQ.nextval,'A1111','오븐 김치 그라탕','regi_5/rep_food.jpg','찬밥 활용 만점~~아이들이 좋아하는 간식으로 최고!',
		'https://www.youtube.com/watch?v=3UvtRQSVS2o',
		2, 25, '중', 0);

insert into RECIPE
values(RECIPE_SEQ.nextval,'A2222','짜장면','regi_6/rep_food.jpg','김씨 표류기 짜장면 나도 따라할 수 있다! ',
		'https://www.youtube.com/watch?v=lV2RBHwzYxk',
		2, 40, '상', 0);

insert into RECIPE
values(RECIPE_SEQ.nextval,'A2222','멘보샤','regi_7/rep_food.jpg','바삭한 식빵과 촉촉한 새우살의 만남! 어른들 술안주, 아이들 간식~ ',
		'https://www.youtube.com/watch?v=i44wkqKlj9E',
		2, 30, '중', 0);

insert into RECIPE
values(RECIPE_SEQ.nextval,'A1111','고구마 맛탕','regi_8/rep_food.jpg','에어프라이어로 만드는 고구마 맛탕 ',
		'https://www.youtube.com/watch?v=Qrl0u3jVwE0',
		4, 20, '하', 0);

insert into RECIPE
values(RECIPE_SEQ.nextval,'A2222','감바스 알 아히요','regi_9/rep_food.jpg','홈파티 레시피 감바스 알 아히요 ',
		'https://www.youtube.com/watch?v=81KTu3Si4KU',
		2, 29, '하', 0);

insert into RECIPE
values(RECIPE_SEQ.nextval,'A1111','베리 컵 젤리','regi_10/rep_food.jpg','탱글탱글 디저트 베리 컵 젤리 ',
		'https://www.youtube.com/watch?v=X_8gpCUiJIM',
		4, 10, '하', 0);

insert into RECIPE
values(RECIPE_SEQ.nextval,'A2222','순두부 계란국','regi_11/rep_food.jpg','초간단 집밥 레시피',
		'https://www.youtube.com/watch?v=X_8gpCUiJIM',
		2, 5, '중', 0);

insert into RECIPE
values(RECIPE_SEQ.nextval,'A2222','치즈 계란말이','regi_12/rep_food.jpg','치즈가 쭉쭉 늘어나는 맛있는 계란말이~! ',
		'https://www.youtube.com/watch?v=X_8gpCUiJIM',
		2, 10, '하', 0);

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
