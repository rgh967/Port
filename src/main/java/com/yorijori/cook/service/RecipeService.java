package com.yorijori.cook.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.yorijori.cook.DTO.RecipeDTO;

public interface RecipeService {

	int mainProcess();
	
	Map<String, Object> recipeChkListProcess(List<String> checkbox, String filter, int page, int limit);

	Map<String, Object> recipePagingProcess(int page, int limit, String filter);

	Map<String, Object> recipeInitListProcess(int page, int limit, int cHEF_ID, int nATION_ID, int pART_ID, String filter);
	
	// 레시피 등록 
	int recipeRegiProcess(String saveFolder, RecipeDTO recipe, 
				String[] CHEF_ID, String[] NATION_ID, String[] PART_ID, 
				String[] ING_NAME, String[] ING_AMOUNT, 
				String[] STEP_NUM, String[] STEP_CONTENT, MultipartFile[] STEP_IMAGE);
	
	// 레시피 상세정보 뷰
	Map<String, Object> recipeDetailProcess(int rECIPE_ID);
		
}
