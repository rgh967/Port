package com.yorijori.cook.DTO;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ClassDetailDTO {
	private int CLASS_ID;
	private int DETAIL_STEP;
	private String DETAIL_CONTENT;
	private String DETAIL_IMAGE;
	
	private String[] ARR_DETAIL_STEP;
	private String[] ARR_DETAIL_CONTENT;
	private String[] ARR_DETAIL_IMAGE;
	private MultipartFile[] ARR_DETAIL_UPLOADFILE;
}
