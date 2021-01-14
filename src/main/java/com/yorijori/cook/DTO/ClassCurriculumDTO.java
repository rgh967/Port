package com.yorijori.cook.DTO;

import lombok.Data;

@Data
public class ClassCurriculumDTO {
	private int CLASS_ID;
	private int CUR_DAY;
	private String CUR_CONTENT;
	
	private String[] ARR_CUR_DAY;
	private String[] ARR_CUR_CONTENT;
}
