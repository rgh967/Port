package com.yorijori.cook.recipeDAO;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yorijori.cook.DTO.RecipeDTO;
import com.yorijori.cook.DTO.RecipeIngDTO;
import com.yorijori.cook.DTO.RecipeReviewDTO;
import com.yorijori.cook.DTO.RecipeStepDTO;

@Repository
public class RecipeDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public RecipeDTO selectRecipe(int recipe_id) {
		return sqlSession.selectOne("Recipes.selectRecipe", recipe_id);
	}
	
	public int selectRecipeId() {
		return sqlSession.selectOne("Recipes.selectRecipeId");
	}
	
	public RecipeDTO selectRecipeMId(Object recipe_id) {
		return sqlSession.selectOne("Recipes.selectRecipeMId", recipe_id);
	}

	public List<RecipeStepDTO> selectRecipeStepList(int RECIPE_ID) {
		return sqlSession.selectList("Recipes.selectRecipeStepList", RECIPE_ID);
	}

	public List<RecipeIngDTO> selectRecipeIngList(int RECIPE_ID) {
		return sqlSession.selectList("Recipes.selectRecipeIngList", RECIPE_ID);
	}

	public int insertReview(RecipeReviewDTO recipeReview) {
		return sqlSession.insert("Recipes.insertReview", recipeReview);
	}
	
	public int insertRecipe(RecipeDTO recipe) {
		return sqlSession.insert("Recipes.insertRecipe", recipe);
	}
	
	public int selectRecipeCount() {
		return sqlSession.selectOne("Recipes.selectRecipeCount");
	}
	
	public int updateRecipeReadCount(int RECIPE_ID) {
		return sqlSession.update("Recipes.updateRecipeReadCount", RECIPE_ID);
	}
	
	public List<RecipeDTO> selectRecipeList(Map<String, Object> map) {
		return sqlSession.selectList("Recipes.selectRecipeList", map);
	}
	
	public List<RecipeDTO> selectRecipeListOrderId(Map<String, Object> map) {
		return sqlSession.selectList("Recipes.selectRecipeListOrderId", map);
	}
	
	public List<RecipeDTO> selectRecipeListOrderRank(Map<String, Object> map) {
		return sqlSession.selectList("Recipes.selectRecipeListOrderRank", map);
	}
}
