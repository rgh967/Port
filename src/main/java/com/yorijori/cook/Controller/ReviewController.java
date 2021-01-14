package com.yorijori.cook.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yorijori.cook.DTO.ClassReviewDTO;
import com.yorijori.cook.DTO.RecipeReviewDTO;
import com.yorijori.cook.service.ReviewService;

@Controller
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	
	/* ������ �ı� */
	// �ı� ���
	@ResponseBody
	@PostMapping(value="/recipeReviewProcess.net")
	public Map<String, Object> recipeReviewProcess(int RECIPE_ID, @RequestParam(value="page", defaultValue="1", required=false) int page){
		return reviewService.recipeReviewProcess(RECIPE_ID, page);
	}
	
	// �ı� ���
	@ResponseBody
	@PostMapping(value="/recipeReviewRegiProcess.net")
	public void recipeReviewRegiProcess(RecipeReviewDTO recipeReview){
		int result = reviewService.recipeReviewRegiProcess(recipeReview);
		
		if(result == 1) {
			System.out.println("�ı� ��� ����");
		}else {
			System.out.println("�ı� ��� ����");
		}
		
	}
	
	// �ı� ����
	@ResponseBody
	@PostMapping(value="/recipeReviewModifyProcess.net")
	public void recipeReviewModifyProcess(RecipeReviewDTO recipeReview){
		int result = reviewService.recipeReviewModifyProcess(recipeReview);
		
		if(result == 1) {
			System.out.println("�ı� ���� ����");
		}else {
			System.out.println("�ı� ���� ����");
		}
	}
	
	// �ı� ����
	@ResponseBody
	@PostMapping(value="/recipeReviewDelProcess.net")
	public void recipeReviewDelProcess(int REVIEW_ID){
		int result = reviewService.recipeReviewDelProcess(REVIEW_ID);
		
		if(result == 1) {
			System.out.println("�ı� ���� ����");
		}else {
			System.out.println("�ı� ���� ����");
		}
	}
	
	/* Ŭ���� �ı� */
	// �ı� ���
	@ResponseBody
	@PostMapping(value="/classReviewProcess.net")
	public Map<String, Object> classReviewProcess(int CLASS_ID, @RequestParam(value="page", defaultValue="1", required=false) int page){
		return reviewService.classReviewProcess(CLASS_ID, page);
	}
		
	// �ı� ���
	@ResponseBody
	@PostMapping(value="/classReviewRegiProcess.net")
	public Map<String, Object> classReviewRegiProcess(ClassReviewDTO classReview){
		int result = reviewService.classReviewRegiProcess(classReview);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(result == 1) {
			System.out.println("�ı� ��� ����");
			resultMap.put("result", "0");
		}else if(result == 2){
			System.out.println("�ı� ��� ����");
			resultMap.put("result", "0");
		}else {
			System.out.println("�ı� ��� �Ұ���");
			resultMap.put("result", "1");
		}
		
		return resultMap;
	}
		
	// �ı� ����
	@ResponseBody
	@PostMapping(value="/classReviewModifyProcess.net")
	public void classReviewModifyProcess(ClassReviewDTO classReview){
		int result = reviewService.classReviewModifyProcess(classReview);
			
		if(result == 1) {
			System.out.println("�ı� ���� ����");
		}else {
			System.out.println("�ı� ���� ����");
		}
	}
		
	// �ı� ����
	@ResponseBody
	@PostMapping(value="/classReviewDelProcess.net")
	public void classReviewDelProcess(int REVIEW_ID){
		int result = reviewService.classReviewDelProcess(REVIEW_ID);
			
		if(result == 1) {
			System.out.println("�ı� ���� ����");
		}else {
			System.out.println("�ı� ���� ����");
		}
	}
	
}
