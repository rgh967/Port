package com.yorijori.cook.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yorijori.cook.DTO.ClassDTO;
import com.yorijori.cook.DTO.RecipeDTO;
import com.yorijori.cook.DTO.RecipeReviewDTO;
import com.yorijori.cook.classDAO.ClassSearchDAO;
import com.yorijori.cook.recipeDAO.RecipeReviewDAO;
import com.yorijori.cook.recipeDAO.RecipeSearchDAO;

@Service
public class SearchServiceImpl implements SearchService{
	
	@Autowired
	private RecipeSearchDAO recipeSearchDao;
	
	@Autowired
	private ClassSearchDAO classSearchDao;
	
	@Autowired
	private RecipeReviewDAO reviewDao;

	@Override
	public Map<String, Object> searchProcess(String srhType, String srhText) {
		int recipeCount = 0;
		int classCount = 0;
		List<RecipeDTO> recipeList = null;
		List<ClassDTO> classList = null;
		
		List<RecipeReviewDTO> reviewlist = reviewDao.selectReviewStar();
		starProcess(reviewlist);
		
		if(srhType.equals("none")) {  // 전체(제목, 닉네임) 검색
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("TITLE", srhText);
			map.put("MEMBER_NAME", srhText);
			recipeCount = recipeSearchDao.selectRecipeAllCount(map);
			recipeList = recipeSearchDao.selectRecipeAllList(map);
			for(int i = 0; i < recipeList.size(); i++) {
				for(int j = 0; j < reviewlist.size(); j++) {
					if(recipeList.get(i).getRECIPE_ID() == reviewlist.get(j).getRECIPE_ID() && reviewlist.get(j).getREVIEW_STAR() != 0) {
						recipeList.get(i).setRecipeReview(reviewlist.get(j));
					}
				}
			}
			// 람다식 이용해서 recipelist 정렬(별점순)
			Collections.sort(recipeList, (r1, r2) -> r2.getRecipeReview().compareTo(r1.getRecipeReview()));
			
			classCount = classSearchDao.selectClassAllCount(map);
			classList = classSearchDao.selectClassAllList(map);
		}else if(srhType.equals("title")) {  // 제목 검색
			recipeCount = recipeSearchDao.selectRecipeTitleCount(srhText);
			recipeList = recipeSearchDao.selectRecipeTitleList(srhText);
			for(int i = 0; i < recipeList.size(); i++) {
				for(int j = 0; j < reviewlist.size(); j++) {
					if(recipeList.get(i).getRECIPE_ID() == reviewlist.get(j).getRECIPE_ID() && reviewlist.get(j).getREVIEW_STAR() != 0) {
						recipeList.get(i).setRecipeReview(reviewlist.get(j));
					}
				}
			}
			// 람다식 이용해서 recipelist 정렬(별점순)
			Collections.sort(recipeList, (r1, r2) -> r2.getRecipeReview().compareTo(r1.getRecipeReview()));
			
			classCount = classSearchDao.selectClassTitleCount(srhText);
			classList = classSearchDao.selectClassTitleList(srhText);
		}else {  // 닉네임 검색
			recipeCount = recipeSearchDao.selectRecipeNameCount(srhText);
			recipeList = recipeSearchDao.selectRecipeNameList(srhText);
			for(int i = 0; i < recipeList.size(); i++) {
				for(int j = 0; j < reviewlist.size(); j++) {
					if(recipeList.get(i).getRECIPE_ID() == reviewlist.get(j).getRECIPE_ID() && reviewlist.get(j).getREVIEW_STAR() != 0) {
						recipeList.get(i).setRecipeReview(reviewlist.get(j));
					}
				}
			}
			// 람다식 이용해서 recipelist 정렬(별점순)
			Collections.sort(recipeList, (r1, r2) -> r2.getRecipeReview().compareTo(r1.getRecipeReview()));
			
			classCount = classSearchDao.selectClassNameCount(srhText);
			classList = classSearchDao.selectClassNameList(srhText);
		}
		
		for(RecipeDTO r : recipeList) {
			System.out.println(r.toString());
		}
		for(ClassDTO r : classList) {
			System.out.println(r.toString());
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("recipeCount", recipeCount);
		resultMap.put("classCount", classCount);
		resultMap.put("recipeList", recipeList);
		resultMap.put("classList", classList);
		return resultMap;
	}
	
	public void starProcess(List<RecipeReviewDTO> reviewlist) {
		int sum = 0;
		int divine = 1;
		for (int i = 0; i < reviewlist.size(); i++) {
			divine = 1;
			for (int j = 1; j < reviewlist.size(); j++) {
				if(i == j) continue;
				else if(i < j) {
					if (reviewlist.get(i).getRECIPE_ID() == reviewlist.get(j).getRECIPE_ID()) {
						if (divine == 1)
							sum = reviewlist.get(i).getREVIEW_STAR();
						sum += reviewlist.get(j).getREVIEW_STAR();
						divine++;
						reviewlist.get(i).setREVIEW_STAR(sum / divine);
						reviewlist.get(j).setREVIEW_STAR(0);
					}
				}
			}
		}
	}

}
