package com.yorijori.cook.DTO;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ClassDTO {
	// 클래스 ID
	private int CLASS_ID;
	// 회원 ID
	private String MEMBER_ID;
	// 클래스 제목
	private String CLASS_TITLE;
	// 클래스 대표사진
	private String CLASS_MAIN_IMG;
	// 클래스 신청 시작일
	private String CLASS_APP_STARTDATE;
	// 클래스 신청 마감일
	private String CLASS_APP_ENDDATE;
	// 클래스 수강 시작일
	private String CLASS_STARTDATE;
	// 클래스 수강 종료일
	private String CLASS_ENDDATE;
	// 클래스 수강 시작시간
	private String CLASS_STARTTIME;
	// 클래스 수강 종료시간
	private String CLASS_ENDTIME;
	// 클래스 수강 전체인원
	private int CLASS_PEOPLE;
	// 클래스 수강신청인원
	private int CLASS_REG_PEOPLE;
	// 클래스 수강 비용
	private int CLASS_COST;
	// 클래스 장소
	private String CLASS_ADDRESS;
	// 클래스 강사 소개
	private String CLASS_LECTURER;
	
	// class_regi.jsp에서 name 속성 확인 
	private MultipartFile uploadFile;
	private String originalFile;	// 첨부될 파일 이름
		
	// 주소처리
	private String postcode;
	private String address;
	private String address_etc;
	
	private MemberDTO member;
	private ClassReviewDTO classReview;

}
