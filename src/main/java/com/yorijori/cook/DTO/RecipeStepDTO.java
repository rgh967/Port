package com.yorijori.cook.DTO;

public class RecipeStepDTO {
	private int RECIPE_ID;
	private int STEP_NUM;
	private String STEP_CONTENT;
	private String STEP_IMAGE;
	
	public int getRECIPE_ID() {
		return RECIPE_ID;
	}
	public void setRECIPE_ID(int RECIPE_ID) {
		this.RECIPE_ID = RECIPE_ID;
	}
	public int getSTEP_NUM() {
		return STEP_NUM;
	}
	public void setSTEP_NUM(int STEP_NUM) {
		this.STEP_NUM = STEP_NUM;
	}
	public String getSTEP_CONTENT() {
		return STEP_CONTENT;
	}
	public void setSTEP_CONTENT(String STEP_CONTENT) {
		this.STEP_CONTENT = STEP_CONTENT;
	}
	public String getSTEP_IMAGE() {
		return STEP_IMAGE;
	}
	public void setSTEP_IMAGE(String STEP_IMAGE) {
		this.STEP_IMAGE = STEP_IMAGE;
	}
	
}
