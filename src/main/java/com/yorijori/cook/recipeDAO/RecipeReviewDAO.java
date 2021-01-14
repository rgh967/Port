package com.yorijori.cook.recipeDAO;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yorijori.cook.DTO.RecipeReviewDTO;

@Repository
public class RecipeReviewDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;

	public int selectReviewCount(int RECIPE_ID) {
		return sqlSession.selectOne("Reviews.selectReviewCount", RECIPE_ID);
	}
	
	public List<RecipeReviewDTO> selectReviewList(Map<String, Integer> map) {
		return sqlSession.selectList("Reviews.selectReviewList", map);
	}

	public int insertReview(RecipeReviewDTO recipeReview) {
		return sqlSession.insert("Reviews.insertReview", recipeReview);
	}

	public int updateReview(RecipeReviewDTO recipeReview) {
		return sqlSession.update("Reviews.updateReview", recipeReview);
	}

	public int deleteReview(int REVIEW_ID) {
		return sqlSession.delete("Reviews.deleteReview", REVIEW_ID);
	}
	
	public List<RecipeReviewDTO> selectReviewStar() {
		return sqlSession.selectList("Reviews.selectReviewStar");
	}

}
