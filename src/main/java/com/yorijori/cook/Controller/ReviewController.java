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
	
	/* 레시피 후기 */
	// 후기 출력
	@ResponseBody
	@PostMapping(value="/recipeReviewProcess.net")
	public Map<String, Object> recipeReviewProcess(int RECIPE_ID, @RequestParam(value="page", defaultValue="1", required=false) int page){
		return reviewService.recipeReviewProcess(RECIPE_ID, page);
	}
	
	// 후기 등록
	@ResponseBody
	@PostMapping(value="/recipeReviewRegiProcess.net")
	public void recipeReviewRegiProcess(RecipeReviewDTO recipeReview){
		int result = reviewService.recipeReviewRegiProcess(recipeReview);
		
		if(result == 1) {
			System.out.println("후기 등록 성공");
		}else {
			System.out.println("후기 등록 실패");
		}
		
	}
	
	// 후기 수정
	@ResponseBody
	@PostMapping(value="/recipeReviewModifyProcess.net")
	public void recipeReviewModifyProcess(RecipeReviewDTO recipeReview){
		int result = reviewService.recipeReviewModifyProcess(recipeReview);
		
		if(result == 1) {
			System.out.println("후기 수정 성공");
		}else {
			System.out.println("후기 수정 실패");
		}
	}
	
	// 후기 삭제
	@ResponseBody
	@PostMapping(value="/recipeReviewDelProcess.net")
	public void recipeReviewDelProcess(int REVIEW_ID){
		int result = reviewService.recipeReviewDelProcess(REVIEW_ID);
		
		if(result == 1) {
			System.out.println("후기 삭제 성공");
		}else {
			System.out.println("후기 삭제 실패");
		}
	}
	
	/* 클래스 후기 */
	// 후기 출력
	@ResponseBody
	@PostMapping(value="/classReviewProcess.net")
	public Map<String, Object> classReviewProcess(int CLASS_ID, @RequestParam(value="page", defaultValue="1", required=false) int page){
		return reviewService.classReviewProcess(CLASS_ID, page);
	}
		
	// 후기 등록
	@ResponseBody
	@PostMapping(value="/classReviewRegiProcess.net")
	public Map<String, Object> classReviewRegiProcess(ClassReviewDTO classReview){
		int result = reviewService.classReviewRegiProcess(classReview);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(result == 1) {
			System.out.println("후기 등록 성공");
			resultMap.put("result", "0");
		}else if(result == 2){
			System.out.println("후기 등록 실패");
			resultMap.put("result", "0");
		}else {
			System.out.println("후기 등록 불가능");
			resultMap.put("result", "1");
		}
		
		return resultMap;
	}
		
	// 후기 수정
	@ResponseBody
	@PostMapping(value="/classReviewModifyProcess.net")
	public void classReviewModifyProcess(ClassReviewDTO classReview){
		int result = reviewService.classReviewModifyProcess(classReview);
			
		if(result == 1) {
			System.out.println("후기 수정 성공");
		}else {
			System.out.println("후기 수정 실패");
		}
	}
		
	// 후기 삭제
	@ResponseBody
	@PostMapping(value="/classReviewDelProcess.net")
	public void classReviewDelProcess(int REVIEW_ID){
		int result = reviewService.classReviewDelProcess(REVIEW_ID);
			
		if(result == 1) {
			System.out.println("후기 삭제 성공");
		}else {
			System.out.println("후기 삭제 실패");
		}
	}
	
}
