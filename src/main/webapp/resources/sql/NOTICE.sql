drop table NOTICE CASCADE CONSTRAINTS;

create table NOTICE(
NOTICE_NUM		  NUMBER,
NOTICE_ID         VARCHAR2(10),
NOTICE_TITLE      VARCHAR2(100) NOT NULL,
NOTICE_CONTENT    VARCHAR2(1000) NOT NULL,
NOTICE_DATE       DATE NOT NULL,
NOTICE_READCOUNT  NUMBER
);

insert into NOTICE(NOTICE_NUM, NOTICE_ID, NOTICE_TITLE, NOTICE_CONTENT, NOTICE_DATE, NOTICE_READCOUNT )
values ('1','admin','**���������Դϴ�.**','�丮���� �������� �̿��� �ּż� �����մϴ�. ���� Ȱ�� ��Ź�帳�ϴ�.',sysdate,'1');


select * from NOTICE;
