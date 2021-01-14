drop table CLASS_CURRICULUM CASCADE CONSTRAINTS;

create table CLASS_CURRICULUM(
	CLASS_ID  	       	NUMBER,
	CUR_DAY				NUMBER,
	CUR_CONTENT			VARCHAR2(200) NOT NULL,
	PRIMARY KEY(CLASS_ID, CUR_DAY),
	FOREIGN KEY (CLASS_ID) REFERENCES CLASS(CLASS_ID)
);

insert into CLASS_CURRICULUM
values(1, 1, '�⺻ ���� �� ��ī�� �̷� ����');
insert into CLASS_CURRICULUM
values(1, 2, '��Ű���� �⺻! �ӷ� �ø��� ���� ��ī�γ��� �ǽ�');
insert into CLASS_CURRICULUM
values(1, 3, '���� ��ī�� & ���� ��ī�� �����~');

insert into CLASS_CURRICULUM
values(2, 1, '�����ġ�� ������ ���Բ� ���ӵ� ����� �Դ� ������ Ŭ����');

insert into CLASS_CURRICULUM
values(3, 1, '������ ��� �丮 ����');
insert into CLASS_CURRICULUM
values(3, 2, '�������� �� ���� �丮 �ǽ�');
insert into CLASS_CURRICULUM
values(3, 3, '���ö� ���� ����');
insert into CLASS_CURRICULUM
values(3, 4, '��ٸ� �ִ� �ѻ� ����! ');

insert into CLASS_CURRICULUM
values(4, 1, '�پ��� �а��� Ȱ�� �丮 �̷�');
insert into CLASS_CURRICULUM
values(4, 2, '�پ��� �а��� Ȱ�� �丮 �ǽ�');
insert into CLASS_CURRICULUM
values(4, 3, '������ ���� �̷�');
insert into CLASS_CURRICULUM
values(4, 4, '������ ���� �ǽ�');
insert into CLASS_CURRICULUM
values(4, 5, '�� Į���� �����');

insert into CLASS_CURRICULUM
values(5, 1, 'Ŀ���͵� Ÿ��Ʈ');
insert into CLASS_CURRICULUM
values(5, 2, '��ĭ Ÿ��Ʈ');
insert into CLASS_CURRICULUM
values(5, 3, '��ī�շ���ӷ� Ÿ��Ʈ');

insert into CLASS_CURRICULUM
values(6, 1, '������ �����, �κ�����');

insert into CLASS_CURRICULUM
values(7, 1, '���η�ġ��, �ް�����');

insert into CLASS_CURRICULUM
values(8, 1, '�����ֿ� �������');
insert into CLASS_CURRICULUM
values(8, 2, '�����ֿ� �������');
insert into CLASS_CURRICULUM
values(8, 3, '�����ֿ� �������');

insert into CLASS_CURRICULUM
values(9, 1, 'ǻ�� ������ �簻');

insert into CLASS_CURRICULUM
values(10, 1, 'ī���� ����, ī�� ����, �ƺ�ī�� ���� ���� ������ġ, ���� ����ġ �佺Ʈ, �������� �� ����ũ');

insert into CLASS_CURRICULUM
values(11, 1, '�������� ���� �丮 2������ ����Ʈ�� ���鷻');

insert into CLASS_CURRICULUM
values(12, 1, '���� ��ũ�� ����ũ');