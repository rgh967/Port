package com.yorijori.cook.DTO;

import lombok.Data;

@Data
public class ClassReviewDTO {
	private int REVIEW_ID;
	private int CLASS_ID;
	private String MEMBER_ID;
	private String REVIEW_CONTENT;
	private int REVIEW_STAR;
	private String REVIEW_DATE;
	
	private MemberDTO member;
}
