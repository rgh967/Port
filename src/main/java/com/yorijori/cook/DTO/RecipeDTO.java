package com.yorijori.cook.DTO;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/*
	@Data : getter, setter, toString, 기본 생성자 생성해주는 lombok 명령
*/
@Data
public class RecipeDTO {
	private int RECIPE_ID;
	private String MEMBER_ID;
	private String RECIPE_TITLE;
	private String RECIPE_MAIN_IMG;
	private String RECIPE_INTRO;
	private String RECIPE_URL;
	private int RECIPE_PEOPLE;
	private int RECIPE_TIME;
	private String RECIPE_DEGREE;
	private int RECIPE_READCOUNT;
	// Recipe 테이블 Member 테이블 조인해서 사용하기 위해 Member VO 필드로 추가
	// getter, setter 추가
	private MemberDTO member;
	private RecipeReviewDTO recipeReview;
		
	// recipe_regi.jsp에서 name 속성 확인 
	private MultipartFile uploadFile;
	private String originalFile;	// 첨부될 파일 이름
		
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getOriginalFile() {
		return originalFile;
	}
	public void setOriginalFile(String originalFile) {
		this.originalFile = originalFile;
	}
	public RecipeReviewDTO getRecipeReview() {
		return recipeReview;
	}
	public void setRecipeReview(RecipeReviewDTO recipeReview) {
		this.recipeReview = recipeReview;
	}
	public MemberDTO getMember() {
		return member;
	}
	public void setMember(MemberDTO member) {
		this.member = member;
	}
	public int getRECIPE_ID() {
		return RECIPE_ID;
	}
	public void setRECIPE_ID(int rECIPE_ID) {
		RECIPE_ID = rECIPE_ID;
	}
	public String getMEMBER_ID() {
		return MEMBER_ID;
	}
	public void setMEMBER_ID(String mEMBER_ID) {
		MEMBER_ID = mEMBER_ID;
	}
	public String getRECIPE_TITLE() {
		return RECIPE_TITLE;
	}
	public void setRECIPE_TITLE(String rECIPE_TITLE) {
		RECIPE_TITLE = rECIPE_TITLE;
	}
	public String getRECIPE_MAIN_IMG() {
		return RECIPE_MAIN_IMG;
	}
	public void setRECIPE_MAIN_IMG(String rECIPE_MAIN_IMG) {
		RECIPE_MAIN_IMG = rECIPE_MAIN_IMG;
	}
	public String getRECIPE_INTRO() {
		return RECIPE_INTRO;
	}
	public void setRECIPE_INTRO(String rECIPE_INTRO) {
		RECIPE_INTRO = rECIPE_INTRO;
	}
	public String getRECIPE_URL() {
		return RECIPE_URL;
	}
	public void setRECIPE_URL(String rECIPE_URL) {
		RECIPE_URL = rECIPE_URL;
	}
	public int getRECIPE_PEOPLE() {
		return RECIPE_PEOPLE;
	}
	public void setRECIPE_PEOPLE(int rECIPE_PEOPLE) {
		RECIPE_PEOPLE = rECIPE_PEOPLE;
	}
	public int getRECIPE_TIME() {
		return RECIPE_TIME;
	}
	public void setRECIPE_TIME(int rECIPE_TIME) {
		RECIPE_TIME = rECIPE_TIME;
	}
	public String getRECIPE_DEGREE() {
		return RECIPE_DEGREE;
	}
	public void setRECIPE_DEGREE(String rECIPE_DEGREE) {
		RECIPE_DEGREE = rECIPE_DEGREE;
	}
	public int getRECIPE_READCOUNT() {
		return RECIPE_READCOUNT;
	}
	public void setRECIPE_READCOUNT(int rECIPE_READCOUNT) {
		RECIPE_READCOUNT = rECIPE_READCOUNT;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RecipeDTO [RECIPE_ID=").append(RECIPE_ID).append(", MEMBER_ID=").append(MEMBER_ID)
				.append(", RECIPE_TITLE=").append(RECIPE_TITLE).append(", RECIPE_MAIN_IMG=").append(RECIPE_MAIN_IMG)
				.append(", RECIPE_INTRO=").append(RECIPE_INTRO).append(", RECIPE_URL=").append(RECIPE_URL)
				.append(", RECIPE_PEOPLE=").append(RECIPE_PEOPLE).append(", RECIPE_TIME=").append(RECIPE_TIME)
				.append(", RECIPE_DEGREE=").append(RECIPE_DEGREE).append(", RECIPE_READCOUNT=").append(RECIPE_READCOUNT)
				.append(", recipeReview=").append(recipeReview).append("]");
		return builder.toString();
	}
	
}
