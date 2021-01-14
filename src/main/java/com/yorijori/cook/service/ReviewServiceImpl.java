package com.yorijori.cook.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yorijori.cook.DTO.ClassReviewDTO;
import com.yorijori.cook.DTO.RecipeReviewDTO;
import com.yorijori.cook.classDAO.ClassReviewDAO;
import com.yorijori.cook.recipeDAO.RecipeReviewDAO;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private RecipeReviewDAO recipeReviewDao;

	@Autowired
	private ClassReviewDAO classReviewDao;
	
	/* 레시피 후기 */
	@Override
	public Map<String, Object> recipeReviewProcess(int RECIPE_ID, int page) {
		int startrow = 1;
		int endrow = page * 3;
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("RECIPE_ID", RECIPE_ID);
		map.put("start", startrow);
		map.put("end", endrow);
		
		List<RecipeReviewDTO> list = recipeReviewDao.selectReviewList(map);  // 레시피 리뷰 가져오기
		
		int listcount = recipeReviewDao.selectReviewCount(RECIPE_ID);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", list);
		resultMap.put("listcount", listcount);
		
		return resultMap;
	}

	@Override
	public int recipeReviewRegiProcess(RecipeReviewDTO recipeReview) {
		return recipeReviewDao.insertReview(recipeReview);
	}
	
	@Override
	public int recipeReviewModifyProcess(RecipeReviewDTO recipeReview) {
		return recipeReviewDao.updateReview(recipeReview);
	}

	@Override
	public int recipeReviewDelProcess(int REVIEW_ID) {
		return recipeReviewDao.deleteReview(REVIEW_ID);
	}

	/* 클래스 후기 */
	@Override
	public Map<String, Object> classReviewProcess(int CLASS_ID, int page) {
		int startrow = 1;
		int endrow = page * 3;
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("CLASS_ID", CLASS_ID);
		map.put("start", startrow);
		map.put("end", endrow);
		
		List<ClassReviewDTO> list = classReviewDao.selectReviewList(map);  // 레시피 리뷰 가져오기
		
		int listcount = classReviewDao.selectReviewCount(CLASS_ID);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", list);
		resultMap.put("listcount", listcount);
		
		return resultMap;
	}

	@Override
	public int classReviewRegiProcess(ClassReviewDTO classReview) {
		int result = 1;  // 후기 등록이 완료되었는지 확인하는 값(후기 등록 불가: 0, 후기 등록 성공: 1, 후기 등록 실패: 2)
		
		int preResult = classReviewDao.selectReview(classReview);  // 수강 신청했고 수강일이 지났는지 확인(1: 후기 등록 가능, 0: 후기 등록 불가)
		
		if(preResult == 0) {
			result = 0;  // 후기 등록 불가 표시
		}else {  // 후기 등록 가능
			result = classReviewDao.insertReview(classReview);  // 후기 등록(성공: 1, 실패: 0)
			if(result == 0) {
				result = 2;
			}
		}
		
		return result;
	}

	@Override
	public int classReviewModifyProcess(ClassReviewDTO classReview) {
		System.out.println(classReview.getMEMBER_ID() + ", " + classReview.getREVIEW_ID() + ", " + classReview.getREVIEW_CONTENT());
		return classReviewDao.updateReview(classReview);
	}

	@Override
	public int classReviewDelProcess(int REVIEW_ID) {
		return classReviewDao.deleteReview(REVIEW_ID);
	}

	@Override
	public int selectReview(ClassReviewDTO classReview) {
		return classReviewDao.selectReview(classReview);
	}

}
