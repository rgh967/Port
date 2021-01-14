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
		'���Ŀ� ��, û�� ����, ������ ��� ����ּ���. <br>�������� 2cm �������� �߶��ּ���. ',
		'regi_1/step1.jpg');
insert into RECIPE_STEP
values(1, 2,
		'�ޱ��� �Ķ����ҿ� ���߱⸧ 4ū���� �ְ� ���� ������ �־� �ߺҿ� �����ֻ���. <br>������ �븩�ϰ� ������ ���� ä�ҿ� �������� �ְ� �����ּ���. <br> ���İ� ���������� ���ֿ� ���ҽ� 2ū���� �ְ� �����ּ���. ',
		'regi_1/step2.jpg');
insert into RECIPE_STEP
values(1, 3,
		'���ҽ��� ��ü ��ῡ ���� ���̸� ������ 2������, �ұ� 2����, ���� 1�������� �־��ּ���. <br>�� �������� �Ŀ� ���߸� �־ �������մϴ�.',
		'regi_1/step3.jpg');

insert into RECIPE_STEP
values(2, 1,
		'�ް�⿡ �ұ�, ���߷� �ذ��� �մϴ�. ',
		'');
insert into RECIPE_STEP
values(2, 2,
		'�ذ��� �ް�⿡ �а��� ���� �����ְ� ������� �����ݴϴ�. <br>���������� �����縦 ������ �ڿ� �⸧�� �ٻ��ϰ� Ƣ���� �ſ���~ ',
		'regi_2/step2.jpg');
insert into RECIPE_STEP
values(2, 3,
		'�ι�° Ƣ�ܳ� �� ��ø� �� �ٻ�ٻ��� ���������� ��� �� �־��! ',
		'regi_2/step3.jpg');

insert into RECIPE_STEP
values(3, 1,
		'�Ķ����ҿ� �⸧�� ��¦ �θ��� ���� ��� ���ּ���. (������ó�� �ٻ��ϰ� �ؾ� �μ����� �ʰ� ���� ���ƿ�) <br>�̶� �信 �ް��� Ǯ�� ���� ����� �����ּ���. ',
		'');
insert into RECIPE_STEP
values(3, 2,
		'���Ĵ� �߰�, �ĸ��� ���׶���, ���� ���� ��� �����ּ���. ',
		'regi_3/step2.jpg');
insert into RECIPE_STEP
values(3, 3,
		'�� ���� ������ ���, �� ���� ���� ġ� ���� �ѷ��ּ���. ',
		'');
insert into RECIPE_STEP
values(3, 4,
		'ġ� �� ���� ������ 10�а��� ���� �ҿ� �÷������� ��~! ',
		'regi_3/step4.jpg');

insert into RECIPE_STEP
values(4, 1,
		'����� �߰� ���, ��α�� 1x1cm ���� ũ��� ����ּ���. ',
		'regi_4/step1.jpg');
insert into RECIPE_STEP
values(4, 2,
		'�ޱ� �ҿ� ���͸� �ְ� ��α⸦ �����ּ���. <br> tip. ��Ŀ�� �־� ���� ���� ���Ͱ� Ÿ�� �ʰ� �⸧�� ���� �־� ���� �����ּ���. �� ���� ��α�� ����� �� ���ִ� �������� �ȴ�ϴ�. ',
		'regi_4/step2.jpg');
insert into RECIPE_STEP
values(4, 3,
		'���Ⱑ �������� ������ ��α� ������ �ְ�, ���� �����ϸ� ���� �־� �����ּ���. <br>���� �����鼭 ���ҽ��� ������ �־��ּ���. ',
		'regi_4/step3.jpg');
insert into RECIPE_STEP
values(4, 4,
		'������ ���� ��� �Ķ��̸� �÷��ָ� �� ���ִ�ϴ�! ',
		'regi_4/step4.jpg');
		
insert into RECIPE_STEP
values(5, 1,
		'����, ����� ����, ��� ������ī, ��, ��ġ�� �Ա� ���� ����ּ���. ',
		'');
insert into RECIPE_STEP
values(5, 2,
		'�ޱ��� �ҿ� ���͸� �쿩�ֽð�, ��ġ�� ���ĸ� ���� �ְ� �����ּ���. <br>�� ���� ���� �ְ� ���ٰ� ���������� ����� ������ ������ī�� �ְ� �����ݴϴ�. ',
		'regi_5/step2.jpg');
insert into RECIPE_STEP
values(5, 3,
		'������ ��� ���� ������ ���� �ְ� �丶�� �ҽ��� 7������ �־��ּ���. <br>�ҽ��� ���⿡ ���� �������൵ ���ƿ�! ',
		'regi_5/step3.jpg');
insert into RECIPE_STEP
values(5, 4,
		'�������� �� �����ָ� �����ּ���. ',
		'');
insert into RECIPE_STEP
values(5, 5,
		'����� �׸��� ���� ��� �������� ġ��� ü��ġ� �÷��ݴϴ�. ',
		'regi_5/step5.jpg');
insert into RECIPE_STEP
values(5, 6,
		'180���� ������ ���쿡 �ְ� 10�а� �����ָ� ��~! <br>���쿡 ���� ���� ���°� �޶��� �� ������ �߰��߰� �� üũ���ּ���. ',
		'regi_5/step6.jpg');

insert into RECIPE_STEP
values(6, 1,
		'���ĸ� Į���� ũ�� �־ ŭ���ϰ� ����ݴϴ�. <br>����ߴ� �����̽��� �ϰ� ŭ���ϰ� ����ݴϴ�. <br>���Ĵ� �ۼ۽��� ����ݴϴ�. ',
		'');
insert into RECIPE_STEP
values(6, 2,
		'���ڲ����� �ʷ��� ���� �� ���̽� ������� �߶��ݴϴ�. <br> ���� ���̴� ä ����ݴϴ�. ',
		'');
insert into RECIPE_STEP
values(6, 3,
		'��������� ����߿� ���� ũ�⿡ �°� �߶��ݴϴ�. ',
		'');
insert into RECIPE_STEP
values(6, 4,
		'�ߺҿ� �Ŀ����� 2�� ���� �װ� �ޱ��� �ҿ� ������ 10�а� �����ݴϴ�. (���� ���̻��� ������ ����� �ߴ�) ',
		'regi_6/step4.jpg');
insert into RECIPE_STEP
values(6, 5,
		'���� ���� ���� �⸧�� ���ĸ� �־� �ߺҷ� �����ݴϴ�. <br>����, ����, �������, ����, ����߸� �ְ� ����߿� ���İ� ���� ������ ���� �����ݴϴ�. <br>���� 1ū�� ���� �ְ� �����ݴϴ�. ���� 400ml�� �ְ� ���嵵 ���� �־��ݴϴ�. ',
		'regi_6/step5.jpg');
insert into RECIPE_STEP
values(6, 6,
		'���� ���� �޾� ������ ���� ����ݴϴ�. ',
		'');
insert into RECIPE_STEP
values(6, 7,
		'��ȭ�鿡 ¥�� �ҽ��� �װ� ����� ���̸� �÷��ָ� �ϼ�! ',
		'regi_6/step7.jpg');

insert into RECIPE_STEP
values(7, 1,
		'���� ���� ����� Į�� ���� �غ��Ѵ�. ',
		'regi_7/step1.jpg');
insert into RECIPE_STEP
values(7, 2,
		'���� �������츦 �ְ� ���ڿ� ��������, �ұ�, ���߸� �־� ��������� �����. ',
		'regi_7/step2.jpg');
insert into RECIPE_STEP
values(7, 3,
		'�Ļ��� �׵θ��� �ڸ��� 4��� �Ѵ�. ',
		'regi_7/step3.jpg');
insert into RECIPE_STEP
values(7, 4,
		'step 3�� �Ļ��� step 2�� ��������� �ִ´�. ',
		'regi_7/step4.jpg');
insert into RECIPE_STEP
values(7, 5,
		'�� 70���� �⸧�������� ������ �븩�ϰ� Ƣ�ܳ���. ',
		'regi_7/step5.jpg');

insert into RECIPE_STEP
values(8, 1,
		'���� ������ �����, ����ũ��� ����� �� ���� 20������ ��� ���б� ���ݴϴ�. ',
		'');
insert into RECIPE_STEP
values(8, 2,
		'���������̾��� �µ��� 190������ 5�а� ������ �ݴϴ�. ',
		'');
insert into RECIPE_STEP
values(8, 3,
		'������ ���� ŰģŸ�÷� ���� ���⸦ �۾��ݴϴ�. <br>�ͽ̺��� ������ �ְ� �Ŀ���1��Ǭ�� �־� ���� ���� �ݴϴ�. ',
		'regi_8/step3.jpg');
insert into RECIPE_STEP
values(8, 4,
		'190������ 15�� �����ݴϴ�. �ѹ� ������ �� 190������ 5~10�� �� �����ݴϴ�. ',
		'');
insert into RECIPE_STEP
values(8, 5,
		'�ҿ� ����2��Ǭ, �ø����3��Ǭ, �Ŀ��� �ݽ�Ǭ�� �־� �ݴϴ�. ������ Ÿ���ʰ� ���� �����ָ鼭 �̿� �ݴϴ�. ',
		'regi_8/step5.jpg');
insert into RECIPE_STEP
values(8, 6,
		'�ҽ��� ������ ������ �ְ� ���� ���� �Բ� ���� ������ �ݴϴ�. ',
		'regi_8/step6.jpg');

insert into RECIPE_STEP
values(9, 1,
		'������ �� �غ��� ����, �ľ ���⸦ ������ ���쿡 �ұ� 1/2 ���� ���� ������ ���� 1 ���� ���� �ְ� ������ �ּ���. ',
		'regi_9/step1.jpg');
insert into RECIPE_STEP
values(9, 2,
		'�ߺҿ��� �ø����� �� ���� �־��ּ���. �⸧�� ���� ������ ������ ������ �־��ּ���.',
		'');
insert into RECIPE_STEP
values(9, 3,
		'������ �븩�븩 ������ ���츦 �־��ּ���. �����ġ�뵵 �־��ּ���. <br>���� �ſ� ���� �����Ͻø� �����ġ�븦 ������ �߶� �־��ּ���. ',
		'regi_9/step3.jpg');
insert into RECIPE_STEP
values(9, 4,
		'�Ľ��� ���縦 �ѷ��� ������ ���ּ���. <br>�ϼ��� �丮�� ���� ��鿩 ������ ���� ���ֽ��ϴ� ^^',
		'regi_9/step4.jpg');
		
insert into RECIPE_STEP
values(10, 1,
		'�ȳ��ϼ���~ �ٻ��Դϴ�^^ <br>���� ��ö ���� �������� �̿��� ��ŭ�� ����Ʈ�� �������ؿ�! <br>�ʱ��ʱ� �İ��� �ŷ����� �� �Դ� �� ������ �˷��帱����! ',
		'');
insert into RECIPE_STEP
values(10, 2,
		'���� �� ����ƾ�� �ְ� ���� �㰡 10~15�� ���� �ξ� �����ϰ� ���麸���ϰ� �ҷ��� �غ��ϱ�! ',
		'regi_10/step2.jpg');
insert into RECIPE_STEP
values(10, 3,
		'�ͼ��� ��纣���� ü���� ������ �Ӿ��� ������ �־��. �õ� ������ ����Ѵٸ� ���� ������ �н�~~ <br>������(2ū��)�� �ְ� ���� �����~',
		'regi_10/step3.jpg');
insert into RECIPE_STEP
values(10, 4,
		'���� �� �����ֽ��� ��(3ū��)�� �ְ� �߾� �ҿ� �÷���. <br>�����ڸ����� ��������ϸ� �Ҹ� ����ƾ�� ���⸦ �� ¥ �ְ� ���� �� ���·� ����� �쿩��.',
		'regi_10/step4.jpg');
insert into RECIPE_STEP
values(10, 5,
		'���� �ſ� ���緮�� �װ� ����ǿ��� 4~5�ð� ������ ������ �ϼ��ϼ���! ',
		'regi_10/step5.jpg');

insert into RECIPE_STEP
values(11, 1,
		'���ĸ� �ۼ� ����ּ���. û����ߵ� �߰� �����ּ���. ',
		'');
insert into RECIPE_STEP
values(11, 2,
		'���� ����+���� 1������+���尡�� 1ū��+�������� 1/2ū�� �־��ּ���. ���Ŀ� ���� �ְ� ������� ����� �ּ���. ',
		'regi_11/step2.jpg');
insert into RECIPE_STEP
values(11, 3,
		'�� 800ml�� �ع��ٽ����� �ְ� �����ּ���. ',
		'');
insert into RECIPE_STEP
values(11, 4,
		'������ �췯���� �ٽ����� ��������, ������ 1ū��, �ұ� 1/2������, �������� 1ū���� �־��ּ���. ',
		'regi_11/step4.jpg');
insert into RECIPE_STEP
values(11, 5,
		'������ ��������� ���κθ� �ְ� ������ ũ��� �ɰ� �ּ���. Ǯ��� ����� ä�� �ɷ� �־��ּ���. <br>���ĸ� ��� �־� ������ ���ֽø� �ϼ�! ',
		'');

insert into RECIPE_STEP
values(12, 1,
		'���� ��� 4���� ��Ʈ�� �ұ� �ణ�� �ְ� �����ּ���.  ',
		'regi_12/step1.jpg');
insert into RECIPE_STEP
values(12, 2,
		'�Ķ����ҿ� �⸧�� �θ��� ������� ���� �� ü��ġ� �÷��ּ���.  ',
		'regi_12/step2.jpg');
insert into RECIPE_STEP
values(12, 3,
		'����� ���� ���� ����� ����ָ� �ϼ�! ',
		'regi_12/step3.jpg');
