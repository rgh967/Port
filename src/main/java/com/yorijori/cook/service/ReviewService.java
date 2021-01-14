package com.yorijori.cook.service;

import java.util.Map;

import com.yorijori.cook.DTO.ClassReviewDTO;
import com.yorijori.cook.DTO.RecipeReviewDTO;

public interface ReviewService {
	
	// 레시피 리뷰 출력
	Map<String, Object> recipeReviewProcess(int RECIPE_ID, int page);
	
	// 레시피 리뷰 등록
	int recipeReviewRegiProcess(RecipeReviewDTO recipeReview);
	
	// 레시피 리뷰 수정
	int recipeReviewModifyProcess(RecipeReviewDTO recipeReview);

	// 레시피 리뷰 삭제
	int recipeReviewDelProcess(int REVIEW_ID);

	// 클래스 리뷰 출력
	Map<String, Object> classReviewProcess(int CLASS_ID, int page);

	// 클래스 리뷰 등록
	int classReviewRegiProcess(ClassReviewDTO classReview);

	// 클래스 리뷰 수정
	int classReviewModifyProcess(ClassReviewDTO classReview);

	// 클래스 리뷰 삭제
	int classReviewDelProcess(int REVIEW_ID);

	// 클래스 리뷰 등록 가능한지 확인
	int selectReview(ClassReviewDTO classReview);
	
}
