drop table RECIPE_ING CASCADE CONSTRAINTS;

create table RECIPE_ING(
	RECIPE_ID      NUMBER,
	ING_NAME     VARCHAR2(50),
	ING_AMOUNT      VARCHAR2(50) NOT NULL,
	PRIMARY KEY(RECIPE_ID, ING_NAME),
	FOREIGN KEY (RECIPE_ID) REFERENCES RECIPE(RECIPE_ID)
);

insert into RECIPE_ING
values(1, '���̹���', '1/3��');
insert into RECIPE_ING
values(1, '����', '8��');
insert into RECIPE_ING
values(1, '����', '1/2��');
insert into RECIPE_ING
values(1, '������', '120g');
insert into RECIPE_ING
values(1, '����', '200g');

insert into RECIPE_ING
values(2, '�ް�� ��', '1����');
insert into RECIPE_ING
values(2, '�ұ�', '1ts');
insert into RECIPE_ING
values(2, '����', '1ts');
insert into RECIPE_ING
values(2, '�а���', '1��');
insert into RECIPE_ING
values(2, '���', '1��');
insert into RECIPE_ING
values(2, '������', '1��');
insert into RECIPE_ING
values(2, 'Ÿ��Ÿ���ҽ�', '������');

insert into RECIPE_ING
values(3, '�ҹ�', '1�׸�');
insert into RECIPE_ING
values(3, 'û�Ǹ�', '1��');
insert into RECIPE_ING
values(3, '��¥����ġ��', '2��');
insert into RECIPE_ING
values(3, '�Ұ����', '40g');
insert into RECIPE_ING
values(3, '����', '1/3��');
insert into RECIPE_ING
values(3, '���ڼҽ�', '1����');
insert into RECIPE_ING
values(3, '���', '1��');

insert into RECIPE_ING
values(4, '�ҹ�', '2�׸�');
insert into RECIPE_ING
values(4, '��α�', '2/3����');
insert into RECIPE_ING
values(4, '�Ұ��', '50g');
insert into RECIPE_ING
values(4, '����', '1ū��');
insert into RECIPE_ING
values(4, '���� ����', '1ū��');
insert into RECIPE_ING
values(4, '���ҽ�', '1���� ��');
insert into RECIPE_ING
values(4, '���ұ�', '�ణ');

insert into RECIPE_ING
values(5, '�ҹ�', '2�׸�');
insert into RECIPE_ING
values(5, '����', '1��(��)');
insert into RECIPE_ING
values(5, '��� ������ī', '1/2��');
insert into RECIPE_ING
values(5, '����� ����', '2��');
insert into RECIPE_ING
values(5, '���߱�ġ', '1/2��');
insert into RECIPE_ING
values(5, '��', '50g');
insert into RECIPE_ING
values(5, '�丶�� �ҽ�', '7������');
insert into RECIPE_ING
values(5, '��¥���� ġ��', '����');
insert into RECIPE_ING
values(5, 'ü�� ġ��', '1��');
insert into RECIPE_ING
values(5, '���� ����', '1/2������');
insert into RECIPE_ING
values(5, '����', '1������');

insert into RECIPE_ING
values(6, '��ȭ��', '1');
insert into RECIPE_ING
values(6, '����', '1');
insert into RECIPE_ING
values(6, '����', '2');
insert into RECIPE_ING
values(6, '����', '1');
insert into RECIPE_ING
values(6, '�����', '1/4');
insert into RECIPE_ING
values(6, '����', '1��');
insert into RECIPE_ING
values(6, '����', '1');
insert into RECIPE_ING
values(6, '�������', '1');
insert into RECIPE_ING
values(6, '���', '1');
insert into RECIPE_ING
values(6, '����', '�ణ');

insert into RECIPE_ING
values(7, '����', '20����');
insert into RECIPE_ING
values(7, '�Ļ�', '4��');
insert into RECIPE_ING
values(7, '�ް�����', '1��');
insert into RECIPE_ING
values(7, '�ұ�', '1/2������');
insert into RECIPE_ING
values(7, '����', '�ణ');
insert into RECIPE_ING
values(7, '��������', '1.5ū��');
insert into RECIPE_ING
values(7, '�Ŀ���', '�ణ');

insert into RECIPE_ING
values(8, '����', '2��');
insert into RECIPE_ING
values(8, '�Ŀ���', '1��Ǭ');
insert into RECIPE_ING
values(8, '�ø����', '3��Ǭ');
insert into RECIPE_ING
values(8, '����', '2��Ǭ');

insert into RECIPE_ING
values(9, '�� Ĭ���� ����(��)', '8����');
insert into RECIPE_ING
values(9, '����', '8��');
insert into RECIPE_ING
values(9, '�ø�����', '1��');
insert into RECIPE_ING
values(9, '�����ġ��', '10��');
insert into RECIPE_ING
values(9, '�ұ�', '����');
insert into RECIPE_ING
values(9, '������ ����', '����');

insert into RECIPE_ING
values(10, '��纣��', '2��');
insert into RECIPE_ING
values(10, 'ü��', '2��');
insert into RECIPE_ING
values(10, '��', '3ū��');
insert into RECIPE_ING
values(10, '������ƾ', '4��');
insert into RECIPE_ING
values(10, '������', '2ū��');

insert into RECIPE_ING
values(11, '���κ�', '1��');
insert into RECIPE_ING
values(11, '���', '2��');
insert into RECIPE_ING
values(11, '����', '����');
insert into RECIPE_ING
values(11, '������', '1T');
insert into RECIPE_ING
values(11, '�ұ�', '1/3t');
insert into RECIPE_ING
values(11, '��������', '1T');
insert into RECIPE_ING
values(11, '�ع��ٽ���', '1��');
insert into RECIPE_ING
values(11, '���尡��', '1T');
insert into RECIPE_ING
values(11, '����', '1t');
insert into RECIPE_ING
values(11, '��', '1t');
insert into RECIPE_ING
values(11, 'û�����', '1��');

insert into RECIPE_ING
values(12, '���', '4��');
insert into RECIPE_ING
values(12, 'ü��ġ��', '1��');
insert into RECIPE_ING
values(12, '�ұ�', '�ణ');

