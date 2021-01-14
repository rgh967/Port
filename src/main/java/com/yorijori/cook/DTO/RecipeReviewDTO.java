package com.yorijori.cook.DTO;

public class RecipeReviewDTO implements Comparable<RecipeReviewDTO>{
	private int REVIEW_ID;
	private int RECIPE_ID;
	private String MEMBER_ID;
	private String REVIEW_CONTENT;
	private int REVIEW_STAR;
	private String REVIEW_DATE;
	
	private MemberDTO member;
	
	public MemberDTO getMember() {
		return member;
	}
	public void setMember(MemberDTO member) {
		this.member = member;
	}
	public int getREVIEW_ID() {
		return REVIEW_ID;
	}
	public void setREVIEW_ID(int REVIEW_ID) {
		this.REVIEW_ID = REVIEW_ID;
	}
	public int getRECIPE_ID() {
		return RECIPE_ID;
	}
	public void setRECIPE_ID(int RECIPE_ID) {
		this.RECIPE_ID = RECIPE_ID;
	}
	public String getMEMBER_ID() {
		return MEMBER_ID;
	}
	public void setMEMBER_ID(String MEMBER_ID) {
		this.MEMBER_ID = MEMBER_ID;
	}
	public String getREVIEW_CONTENT() {
		return REVIEW_CONTENT;
	}
	public void setREVIEW_CONTENT(String REVIEW_CONTENT) {
		this.REVIEW_CONTENT = REVIEW_CONTENT;
	}
	public int getREVIEW_STAR() {
		return REVIEW_STAR;
	}
	public void setREVIEW_STAR(int REVIEW_STAR) {
		this.REVIEW_STAR = REVIEW_STAR;
	}
	public String getREVIEW_DATE() {
		return REVIEW_DATE;
	}
	public void setREVIEW_DATE(String REVIEW_DATE) {
		this.REVIEW_DATE = REVIEW_DATE;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RecipeReview [RECIPE_ID=").append(RECIPE_ID).append(", REVIEW_STAR=").append(REVIEW_STAR)
				.append("]");
		return builder.toString();
	}
	@Override
	public int compareTo(RecipeReviewDTO o) {
		return this.REVIEW_STAR - o.REVIEW_STAR;
	}
	
}
