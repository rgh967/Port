drop table QUESTION CASCADE CONSTRAINTS;

create table QUESTION(
NOTICE_NUM		  NUMBER,
NOTICE_ID         VARCHAR2(10),
NOTICE_TITLE      VARCHAR2(100) NOT NULL,
NOTICE_CONTENT    VARCHAR2(1000) NOT NULL,
NOTICE_DATE       DATE NOT NULL,
NOTICE_READCOUNT  NUMBER
);

insert into QUESTION(NOTICE_NUM, NOTICE_ID, NOTICE_TITLE, NOTICE_CONTENT, NOTICE_DATE, NOTICE_READCOUNT )
values ('1','admin','**����ã������ �Դϴ�.**','�丮���� �������� ���� ���� �е��� �ñ��� �Ͻô� �����Դϴ�.',sysdate,'1');

insert into QUESTION(NOTICE_NUM, NOTICE_ID, NOTICE_TITLE, NOTICE_CONTENT, NOTICE_DATE, NOTICE_READCOUNT )
values ('2','admin','**��й�ȣ �нǾȳ�**',
'�α��� ȭ�� �ϴ� ȸ������ ���� ��й�ȣ ã�� �޴��� �̿��� �ֽñ� �ٶ��ϴ�.
���̵�н��� ��� 1��1 ���Ǹ� �̿��� �ּ���. �����մϴ�.',sysdate,'1');

select * from QUESTION;
