package com.yorijori.cook.service;

import java.util.Map;

import com.yorijori.cook.DTO.ClassReviewDTO;
import com.yorijori.cook.DTO.RecipeReviewDTO;

public interface ReviewService {
	
	// ������ ���� ���
	Map<String, Object> recipeReviewProcess(int RECIPE_ID, int page);
	
	// ������ ���� ���
	int recipeReviewRegiProcess(RecipeReviewDTO recipeReview);
	
	// ������ ���� ����
	int recipeReviewModifyProcess(RecipeReviewDTO recipeReview);

	// ������ ���� ����
	int recipeReviewDelProcess(int REVIEW_ID);

	// Ŭ���� ���� ���
	Map<String, Object> classReviewProcess(int CLASS_ID, int page);

	// Ŭ���� ���� ���
	int classReviewRegiProcess(ClassReviewDTO classReview);

	// Ŭ���� ���� ����
	int classReviewModifyProcess(ClassReviewDTO classReview);

	// Ŭ���� ���� ����
	int classReviewDelProcess(int REVIEW_ID);

	// Ŭ���� ���� ��� �������� Ȯ��
	int selectReview(ClassReviewDTO classReview);
	
}
