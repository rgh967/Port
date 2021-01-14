package com.yorijori.cook.classDAO;

import java.util.List;
import java.util.Map;

import com.yorijori.cook.DTO.ClassReviewDTO;

public interface ClassReviewDAO {
	
	public int selectReviewCount(int CLASS_ID);
	
	public List<ClassReviewDTO> selectReviewList(Map<String, Integer> map);

	public int insertReview(ClassReviewDTO classReview);

	public int updateReview(ClassReviewDTO classReview);

	public int deleteReview(int CLASS_ID);

	public int selectReview(ClassReviewDTO classReview);

}
