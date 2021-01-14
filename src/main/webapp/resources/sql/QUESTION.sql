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
values ('1','admin','**자주찾는질문 입니다.**','요리조리 페이지에 대해 많은 분들이 궁금해 하시는 내용입니다.',sysdate,'1');

insert into QUESTION(NOTICE_NUM, NOTICE_ID, NOTICE_TITLE, NOTICE_CONTENT, NOTICE_DATE, NOTICE_READCOUNT )
values ('2','admin','**비밀번호 분실안내**',
'로그인 화면 하단 회원가입 우측 비밀번호 찾기 메뉴를 이용해 주시기 바랍니다.
아이디분실의 경우 1대1 문의를 이용해 주세요. 감사합니다.',sysdate,'1');

select * from QUESTION;
