drop table RECIPE_STEP CASCADE CONSTRAINTS;

create table RECIPE_STEP(
	RECIPE_ID      NUMBER,
	STEP_NUM         NUMBER,
	STEP_CONTENT      LONG NOT NULL,
	STEP_IMAGE      VARCHAR2(100),
	PRIMARY KEY(RECIPE_ID, STEP_NUM),
	FOREIGN KEY (RECIPE_ID) REFERENCES RECIPE(RECIPE_ID)
);

insert into RECIPE_STEP
values(1, 1,
		'양파와 파, 청양 고추, 마늘을 얇게 썰어주세요. <br>베이컨은 2cm 간격으로 잘라주세요. ',
		'regi_1/step1.jpg');
insert into RECIPE_STEP
values(1, 2,
		'달궈진 후라이팬에 고추기름 4큰술을 넣고 썰어둔 마늘을 넣어 중불에 볶아주새요. <br>마늘이 노릇하게 익으면 썰어둔 채소와 베이컨을 넣고 볶아주세요. <br> 양파가 투명해지면 숙주와 굴소스 2큰술을 넣고 볶아주세요. ',
		'regi_1/step2.jpg');
insert into RECIPE_STEP
values(1, 3,
		'굴소스가 전체 재료에 골고루 섞이면 통후추 2작은술, 소금 2꼬집, 설탕 1작은술을 넣어주세요. <br>다 볶아지면 파와 고추를 넣어서 마무리합니다.',
		'regi_1/step3.jpg');

insert into RECIPE_STEP
values(2, 1,
		'달고기에 소금, 후추로 밑간을 합니다. ',
		'');
insert into RECIPE_STEP
values(2, 2,
		'밑간한 달고기에 밀가루 옷을 입혀주고 계란옷을 입혀줍니다. <br>마지막으로 빵가루를 입혀준 뒤에 기름에 바삭하게 튀겨줄 거예요~ ',
		'regi_2/step2.jpg');
insert into RECIPE_STEP
values(2, 3,
		'두번째 튀겨낸 후 드시면 더 바삭바삭한 생선가스를 즐길 수 있어요! ',
		'regi_2/step3.jpg');

insert into RECIPE_STEP
values(3, 1,
		'후라이팬에 기름을 살짝 두르고 밥을 얇게 펴주세요. (누룽지처럼 바삭하게 해야 부서지지 않고 맛에 좋아요) <br>이때 밥에 달걀을 풀어 같이 섞어가며 부쳐주세요. ',
		'');
insert into RECIPE_STEP
values(3, 2,
		'양파는 잘게, 파망은 동그랗게, 햄은 굵게 썰어 놓아주세요. ',
		'regi_3/step2.jpg');
insert into RECIPE_STEP
values(3, 3,
		'밥 위에 토핑을 얹고, 그 위에 피자 치즈를 골고루 뿌려주세요. ',
		'');
insert into RECIPE_STEP
values(3, 4,
		'치즈가 다 녹을 때까지 10분가량 약한 불에 올려놓으면 끝~! ',
		'regi_3/step4.jpg');

insert into RECIPE_STEP
values(4, 1,
		'쇠고기는 잘게 썰고, 깍두기는 1x1cm 정도 크기로 썰어주세요. ',
		'regi_4/step1.jpg');
insert into RECIPE_STEP
values(4, 2,
		'달군 팬에 버터를 넣고 깍두기를 볶아주세요. <br> tip. 버커를 넣어 볶을 때는 버터가 타지 않게 기름을 조금 넣어 같이 볶아주세요. 잘 익은 깍두기로 만들면 더 맛있는 볶음밥이 된답니다. ',
		'regi_4/step2.jpg');
insert into RECIPE_STEP
values(4, 3,
		'쇠고기가 갈색으로 익으면 깍두기 국물을 넣고, 끌기 시작하면 밥을 넣어 볶아주세요. <br>간을 봐가면서 굴소스는 적당히 넣어주세요. ',
		'regi_4/step3.jpg');
insert into RECIPE_STEP
values(4, 4,
		'볶음밥 위에 계란 후라이를 올려주면 더 밋있답니다! ',
		'regi_4/step4.jpg');
		
insert into RECIPE_STEP
values(5, 1,
		'양파, 양송이 버섯, 노란 파프리카, 햄, 김치를 먹기 좋게 썰어주세요. ',
		'');
insert into RECIPE_STEP
values(5, 2,
		'달궈진 팬에 버터를 녹여주시고, 김치와 양파를 먼저 넣고 볶아주세요. <br>그 다음 햄을 넣고 볶다가 마지막으로 양송이 버섯과 파프라카를 넣고 볶아줍니다. ',
		'regi_5/step2.jpg');
insert into RECIPE_STEP
values(5, 3,
		'재료들이 어느 정도 익으면 밥을 넣고 토마토 소스를 7숫가락 넣어주세요. <br>소스는 취향에 따라 조절해줘도 좋아요! ',
		'regi_5/step3.jpg');
insert into RECIPE_STEP
values(5, 4,
		'뒤적뒤적 잘 섞어주며 볶아주세요. ',
		'');
insert into RECIPE_STEP
values(5, 5,
		'오븐용 그릇에 밥을 담고 모차렐라 치즈와 체다치즈를 올려줍니다. ',
		'regi_5/step5.jpg');
insert into RECIPE_STEP
values(5, 6,
		'180도로 예열된 오븐에 넣고 10분간 돌려주면 끝~! <br>오븐에 따라 조리 상태가 달라질 수 있으니 중간중간 꼭 체크해주세요. ',
		'regi_5/step6.jpg');

insert into RECIPE_STEP
values(6, 1,
		'양파를 칼집을 크게 넣어서 큼직하게 썰어줍니다. <br>양배추는 슬라이스를 하고 큼직하게 썰어줍니다. <br>대파는 송송썰기로 썰어줍니다. ',
		'');
insert into RECIPE_STEP
values(6, 2,
		'감자껍질을 필러로 벗긴 뒤 다이스 모양으로 잘라줍니다. <br> 고명용 오이는 채 썰어줍니다. ',
		'');
insert into RECIPE_STEP
values(6, 3,
		'돼지목살을 양배추와 양파 크기에 맞게 잘라줍니다. ',
		'');
insert into RECIPE_STEP
values(6, 4,
		'중불에 식용유를 2컵 정도 붓고 달궈진 팬에 춘장을 10분간 볶아줍니다. (춘장 사이사이 기포가 생기면 중단) ',
		'regi_6/step4.jpg');
insert into RECIPE_STEP
values(6, 5,
		'장을 볶고 남은 기름에 대파를 넣어 중불로 볶아줍니다. <br>설탕, 감자, 돼지고기, 양파, 양배추를 넣고 양배추와 양파가 숨이 죽을때 까지 볶아줍니다. <br>춘장 1큰술 반을 넣고 볶아줍니다. 물을 400ml를 넣고 간장도 같이 넣어줍니다. ',
		'regi_6/step5.jpg');
insert into RECIPE_STEP
values(6, 6,
		'냄비에 물을 받아 끓으면 면을 삶아줍니다. ',
		'');
insert into RECIPE_STEP
values(6, 7,
		'중화면에 짜장 소스를 붓고 계란과 오이를 올려주면 완성! ',
		'regi_6/step7.jpg');

insert into RECIPE_STEP
values(7, 1,
		'껍질 벗긴 새우는 칼로 다져 준비한다. ',
		'regi_7/step1.jpg');
insert into RECIPE_STEP
values(7, 2,
		'볼에 다진새우를 넣고 흰자와 감자전분, 소금, 후추를 넣어 새우반죽을 만든다. ',
		'regi_7/step2.jpg');
insert into RECIPE_STEP
values(7, 3,
		'식빵은 테두리를 자르고 4등분 한다. ',
		'regi_7/step3.jpg');
insert into RECIPE_STEP
values(7, 4,
		'step 3의 식빵에 step 2의 새우반죽을 넣는다. ',
		'regi_7/step4.jpg');
insert into RECIPE_STEP
values(7, 5,
		'약 70도의 기름에서부터 서서히 노릇하게 튀겨낸다. ',
		'regi_7/step5.jpg');

insert into RECIPE_STEP
values(8, 1,
		'고구마 껍질을 벗기고, 한입크기로 썰어준 뒤 물에 20분정도 담궈 전분기 빼줍니다. ',
		'');
insert into RECIPE_STEP
values(8, 2,
		'에어프라이어의 온도를 190도에서 5분간 예열해 줍니다. ',
		'');
insert into RECIPE_STEP
values(8, 3,
		'고구마를 건져 키친타올로 남은 물기를 닦아줍니다. <br>믹싱볼에 고구마를 넣고 식용유1스푼을 넣어 골고루 섞어 줍니다. ',
		'regi_8/step3.jpg');
insert into RECIPE_STEP
values(8, 4,
		'190도에서 15분 구워줍니다. 한번 뒤집은 후 190도에서 5~10분 더 구워줍니다. ',
		'');
insert into RECIPE_STEP
values(8, 5,
		'팬에 설탕2스푼, 올리고당3스푼, 식용유 반스푼을 넣어 줍니다. 설탕이 타지않게 팬을 돌려주면서 쫄여 줍니다. ',
		'regi_8/step5.jpg');
insert into RECIPE_STEP
values(8, 6,
		'소스가 끓으면 고구마를 넣고 검은 깨와 함께 골고루 버무려 줍니다. ',
		'regi_8/step6.jpg');

insert into RECIPE_STEP
values(9, 1,
		'마늘을 썰어서 준비해 놓고, 씻어서 물기를 제거한 새우에 소금 1/2 작은 술과 통후추 가루 1 작은 술을 넣고 버무려 주세요. ',
		'regi_9/step1.jpg');
insert into RECIPE_STEP
values(9, 2,
		'중불에서 올리브유 한 컵을 넣어주세요. 기름에 열이 오르면 썰어놓은 마늘을 넣어주세요.',
		'');
insert into RECIPE_STEP
values(9, 3,
		'마늘이 노릇노릇 해지면 새우를 넣어주세요. 페페론치노도 넣어주세요. <br>만약 매운 맛을 좋아하시면 페페론치노를 반으로 잘라 넣어주세요. ',
		'regi_9/step3.jpg');
insert into RECIPE_STEP
values(9, 4,
		'파슬리 가루를 뿌려서 마무리 해주세요. <br>완성된 요리에 빵을 곁들여 먹으면 더욱 맛있습니다 ^^',
		'regi_9/step4.jpg');
		
insert into RECIPE_STEP
values(10, 1,
		'안녕하세요~ 앨샘입니다^^ <br>여름 제철 과일 베리류를 이용해 상큼한 디저트를 만들어볼까해요! <br>탱글탱글 식감이 매력적인 떠 먹는 컵 젤리를 알려드릴께요! ',
		'');
insert into RECIPE_STEP
values(10, 2,
		'볼에 판 젤라틴을 넣고 물에 담가 10~15분 정도 두어 투명하고 보들보들하게 불려서 준비하기! ',
		'regi_10/step2.jpg');
insert into RECIPE_STEP
values(10, 3,
		'믹서에 블루베리와 체리는 꼭지와 속씨를 제거해 넣어요. 냉동 과육을 사용한다면 손질 과정은 패스~~ <br>레몬즙(2큰술)을 넣고 곱게 갈고요~',
		'regi_10/step3.jpg');
insert into RECIPE_STEP
values(10, 4,
		'냄비에 간 베리주스와 꿀(3큰술)을 넣고 중약 불에 올려요. <br>가장자리부터 끓기시작하면 불린 젤라틴의 물기를 꼭 짜 넣고 불을 끈 상태로 저어가며 녹여요.',
		'regi_10/step4.jpg');
insert into RECIPE_STEP
values(10, 5,
		'예쁜 컵에 적당량씩 붓고 냉장실에서 4~5시간 굳혀서 젤리를 완성하세요! ',
		'regi_10/step5.jpg');

insert into RECIPE_STEP
values(11, 1,
		'쪽파를 송송 썰어주세요. 청양고추도 잘게 다져주세요. ',
		'');
insert into RECIPE_STEP
values(11, 2,
		'간장 반컵+설탕 1작은술+고춧가루 1큰술+다진마늘 1/2큰술 넣어주세요. 쪽파와 깨도 넣고 양념장을 만들어 주세요. ',
		'regi_11/step2.jpg');
insert into RECIPE_STEP
values(11, 3,
		'물 800ml에 해물다시팩을 넣고 끓여주세요. ',
		'');
insert into RECIPE_STEP
values(11, 4,
		'육수가 우러나면 다시팩을 건져내고, 국간장 1큰술, 소금 1/2작은술, 다진마늘 1큰술을 넣어주세요. ',
		'regi_11/step4.jpg');
insert into RECIPE_STEP
values(11, 5,
		'국물이 끓어오르면 순두부를 넣고 적당한 크기로 쪼개 주세요. 풀어둔 계란은 채에 걸러 넣어주세요. <br>쪽파를 듬뿍 넣어 마무리 해주시면 완성! ',
		'');

insert into RECIPE_STEP
values(12, 1,
		'볼에 계란 4개를 깨트려 소금 약간을 넣고 섞어주세요.  ',
		'regi_12/step1.jpg');
insert into RECIPE_STEP
values(12, 2,
		'후라이팬에 기름을 두르고 계란물을 부은 뒤 체다치즈를 올려주세요.  ',
		'regi_12/step2.jpg');
insert into RECIPE_STEP
values(12, 3,
		'계란을 돌돌 말아 모양을 잡아주면 완성! ',
		'regi_12/step3.jpg');
