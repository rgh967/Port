drop table CLASS_DETAIL CASCADE CONSTRAINTS;

create table CLASS_DETAIL(
	CLASS_ID  	       	NUMBER,
	DETAIL_STEP				NUMBER,
	DETAIL_CONTENT			LONG NOT NULL,
	DETAIL_IMAGE 		VARCHAR2(100),
	PRIMARY KEY(CLASS_ID, DETAIL_STEP),
	FOREIGN KEY (CLASS_ID) REFERENCES CLASS(CLASS_ID)
);

insert into CLASS_DETAIL
values(1, 1, 
		'기본 도구부터 마카롱에 이론 설명을 배울거예요~ 갈라지고 속이 빈 마카롱을 많이 만들어 보신 분들! 이론 부터 차근차근 실패없는 마카롱을 만들 수 있어요~', 
		'');
insert into CLASS_DETAIL
values(1, 2, 
		'마키롱의 기본! 머랭 올리는 법과 마카로나쥬 실습 <br> 마카롱의 식감을 결정하고 잘 마르게 하는 마카로나쥬 작업 꼼꼼히 알려드립니다. ', 
		'regi_1/detail2.jpg');
insert into CLASS_DETAIL
values(1, 3, 
		'유자 마카롱 & 초코 마카롱 만들기~ <br>보기에도 예쁘고 맛도 있지만 굽기 상당히 까다로운 마카롱! 꽤 많은 양의 프랜치 머랭을 만들어가실 거예요~ ', 
		'regi_1/detail3.jpg');

insert into CLASS_DETAIL
values(2, 1, 
		'포기김치와 백김치, 총각김치를 만들어 볼거예요! 김장재료를 고르는 법부터 절이는 법, 양념의 배합뿐 아니라 어떻게 하면 김치 맛을 잘 유지하면서 오랫동안 먹을 수 있는지에 대한 모든 노하우를 전수합니다. ', 
		'regi_2/detail1.jpg');
		insert into CLASS_DETAIL
values(2, 2, 
		'보쌈용 고기를 삶아서 예쁘게 플레이팅! 만들어 둔 김치와 함께 저녁 식사를 함께 합니다~', 
		'');

insert into CLASS_DETAIL
values(3, 1, 
		'첫날에는 계란 장조림, 모둠 전, 맥반석 달걀, 계란 볶음밥 등! 간단하지만 다양하고 활용도 높은 계란 요리들을 만들어볼거예요. <br>더 맛있게 즐길 수 있는 꿀팁까지! ', 
		'regi_3/detail1.png');
insert into CLASS_DETAIL
values(3, 2, 
		'둘쨋 날에는 생선의 손질법! 특히 요리 초보에게 어려운 비린내 제거 방법까지 쏙쏙 이해되는 수업을 진행합니다. <br>고등어 구이, 가자미 무우 조림을 함께 만들어보아요~ ', 
		'regi_3/detail2.png');
insert into CLASS_DETAIL
values(3, 3, 
		'셋째 날에는 꿀떨어지는 도시락 메뉴 팁을 전수합니다! <br> 데이트, 아이들 소풍, 남편 점심 도시락에 사랑을 담아보아요~^^ ', 
		'regi_3/detail3.png');
insert into CLASS_DETAIL
values(3, 4, 
		'마지막 날! 가족들을 위한 푸짐한 한상을 실습합니다. <br>(메뉴 구성은 매 회 달라짐)', 
		'regi_3/detail4.png');

insert into CLASS_DETAIL
values(4, 1, 
		'알면 알수록 재미있는 요리의 세계! 요리 뿐만 아니라 다양한 용도로 활용될 수 있는 밀가루  <br>그 다양한 활용법과 특성을 이론과 실습을 통해 배웁니다. ', 
		'regi_4/detail1.jpg');
insert into CLASS_DETAIL
values(4, 2, 
		'5가지 육수(다시마 육수, 채소 육수, 가쓰오 육수, 닭고기 육수, 소고기 육수)에 대해 배웁니다. <br>각 육수가 어떤 요리에 어울리는지 직접 실습하며 배우는 의미있는 시간!', 
		'regi_4/detail2.jpg');
insert into CLASS_DETAIL
values(4, 3, 
		'수제비와 칼국수! 감자 분이 포슬포슬 오르고 호박이 달큼하게 익는 계절, 칼국수나 수제비 한 그릇 맛있게 끓여 먹고 싶은 때 <br>육수 내고 밀가루 반죽해 누구나 후루룩 끓이기 쉬운 메뉴지만, 평범한 음식일수록 맛 내기는 더 어려운 법! <br>손맛과 정성으로 많은 사람의 마음까지 뜨끈하게 데워주는 수제비집와 칼국수집을 찾아 며느리도 모르는 맛 내기 비법을 살짝 엿봅시다! ', 
		'regi_4/detail3.jpg');

insert into CLASS_DETAIL
values(5, 1, 
		'프랑스 대표디저트 커스터드 타르트! 바로 첫째날 수업의 주제입니다. 타르트에 대한 이론부터 실습까지 빠짐없이 배우는 알찬 시간이 될거예요. ', 
		'regi_5/detail1.jpg');
insert into CLASS_DETAIL
values(5, 2, 
		'타르트에는 여러가지 종류가 있죠~. 견과류 타르트도 뺴놓을 수 없습니다. 둘째 날에는 그중에서 피칸 타르트를 실습해보겠습니다. ', 
		'regi_5/detail2.jpg');
insert into CLASS_DETAIL
values(5, 3, 
		'셋째 날! 화려한 과일 타르크는 어떤가요? 상클하고 달달한 마카롱레몬 머랭 타르트를 함께 즐겨보아요!', 
		'regi_5/detail3.jpg');

insert into CLASS_DETAIL
values(6, 1, 
		'양념바른 구운고등어가 갈비처럼손으로 들고 먹을정도로 맛있다고 고갈비 라고 부른다고 하죠~<br> 비리지않고 바싹하게 고등어 굽는 방법과 집에 있는 양념재료로 매콤하고 맛있는 양념장만드는 방법을 배워봅니다. ', 
		'regi_6/detail1.jpg');
insert into CLASS_DETAIL
values(6, 2, 
		'양념장이 참 맛있는 두부조림 포슬포슬한 두부조림에 밥을 비벼보아요~ 명절음식이 질릴때~ 요 얼큰한 두부조림 어떠세요~ ', 
		'regi_6/detail2.jpg');

insert into CLASS_DETAIL
values(7, 1, 
		'밥반찬으로도 굿! 주말 술안주로도 굿! 매콤 짭조롬한 목살 두루치기 한판 맛있게 만들어봅시다. ', 
		'regi_7/detail1.jpg');
insert into CLASS_DETAIL
values(7, 2, 
		'비교적 쉬운 음식인 계란말이! 좀 더 특별하고 맛있는 맛으로 함께 만들어 보아요~ ', 
		'regi_7/detail2.jpg');
		
insert into CLASS_DETAIL
values(8, 1, 
		'정겨운 분위기 속에서 함께 막걸리를 만들어요. 오늘 빚은 술을 기대하며, 미리 만들어둔 막걸리를 맛보고, 또 함께 안주를 만들어 즐깁니다. ', 
		'');
insert into CLASS_DETAIL
values(8, 2, 
		'막걸리와 전통안주라는 공통된 주제가 있지만 늘 한 가지 종류로 만들어지는 것은 아니에요. 수업을 진행하는 당일의 날씨와 습도, 그리고 계절, 시장상황따라 가장 적합한 재료로 막걸리와 안주를 만듭니다. ', 
		'regi_8/detail2.jpg');
insert into CLASS_DETAIL
values(8, 3, 
		'제 수업에서는 꽃을 이용해 만드는 백화주, 포도를 이용해 만드는 포도 막걸리, 대추를 이용한 대추 막걸리 등 시중에서 만나보지 못하셨던 전통주를 만나실 수 있어요. 이런 특별한 막걸리가 여러분 손에서 직접 피어납니다. <br>메밀전병, 동래파전, 화전 등 계절에 따라 술과 함께 곁들일 전통안주도 함께 배워요. ', 
		'regi_8/detail3.jpg');

insert into CLASS_DETAIL
values(9, 1, 
		'1인당 12구 1Box 예쁘게 포장해 가실 수 있습니다. <br> 포함사항: 재료비, 포장비, 수강시간내 메트로디오빌 주차권 드립니다.', 
		'regi_9/detail1.jpg');
insert into CLASS_DETAIL
values(9, 2, 
		'코스소개: <br>1. 레시피에 들어 가는 재료에 관한 설명을 드립니다.<br>2. 레시피의 비율과 준비 과정에서 선 후 순서를 알려 드리고,<br>3. 디자인 양갱에 들어갈 모티브를 만드는 팁을 알려 드립니다.<br>4. 계절에 따라 들어갈 모티브 디자인을 공동으로 제작 합니다.<br>5. 인원이 많을 수록 많은 디자인을 제작 할 수 있습니다.<br>6. 모티브 제작이 준비 되면, 앙금과 한천을 끓여 준비 합니다.<br>7. 양갱을 제작 합니다.<br>8. 양갱이 굳는 동안 포장 박스를 준비 합니다.<br>9. 양갱을 분할하여 선물용으로 포장 합니다.', 
		'');
		
insert into CLASS_DETAIL
values(10, 1, 
		'타마고 샌드: 부드러운 달걀과 촉촉한 식빵의 조합! 매일먹어도 부담없는 hot 브런치 메뉴', 
		'regi_10/detail1.jpg');
insert into CLASS_DETAIL
values(10, 2, 
		'카츠샌드: 한끼를 든든하고 푸짐하게 즐길수 있는 브런치 메뉴!', 
		'regi_10/detail2.jpg');
insert into CLASS_DETAIL
values(10, 3, 
		'아보카도 수란 오픈 샌드위치: 건강과 맛 두마리 토끼를 동시에! 한단계 더 고급스러운 브런치 메뉴', 
		'regi_10/detail3.jpg');
insert into CLASS_DETAIL
values(10, 4, 
		'두툼 프렌치 토스트: 어른 아이 할것 없이 좋아하는 간단한 브런치 메뉴!', 
		'regi_10/detail4.jpg');
insert into CLASS_DETAIL
values(10, 5, 
		'계절 과일 팬 케이크: 계절 과일과 팬 케이크의 꿀조합! 대중적인 브런치 메뉴', 
		'regi_10/detail5.jpg');

insert into CLASS_DETAIL
values(11, 1, 
		'약 3시간동안 프랑스 요리에 대해 알아보고,프랑스식 집밥 요리 2가지와 디져트인 마들렌을 만들어보는 클래스입니다. <br>제 클래스는 정말 다양한 분들이 많이들 찾아주시곤 합니다!<br>레스토랑을 운영하고 계신 분, 예술가, 와인동호회 분들 등 프랑스와 요리를 좋아해서 더욱 더 잘 알고 즐기려고 오시는 분들이 많았어요!', 
		'regi_11/detail1.jpg');

insert into CLASS_DETAIL
values(12, 1, 
		'봄을 맞이하여 선물하기 좋은 딸기 케이크, 담백한 생크림과 새콤 달콤한 생딸기의 조화로 연인에게 선물하기 좋은 케이크를 만들어보세요! ', 
		'regi_12/detail1.jpg');
