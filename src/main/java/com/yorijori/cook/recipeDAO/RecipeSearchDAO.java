package com.yorijori.cook.recipeDAO;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yorijori.cook.DTO.RecipeDTO;

@Repository
public class RecipeSearchDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	/* ·¹½ÃÇÇ search */
	public int selectRecipeAllCount(HashMap<String, String> map) {
		return sqlSession.selectOne("Recipes.selectRecipeAllCount", map);
	}

	public List<RecipeDTO> selectRecipeAllList(HashMap<String, String> map) {
		return sqlSession.selectList("Recipes.selectRecipeAllList", map);
	}
	
	public int selectRecipeTitleCount(String srhText) {
		return sqlSession.selectOne("Recipes.selectRecipeTitleCount", srhText);
	}

	public List<RecipeDTO> selectRecipeTitleList(String srhText) {
		return sqlSession.selectList("Recipes.selectRecipeTitleList", srhText);
	}

	public int selectRecipeNameCount(String srhText) {
		return sqlSession.selectOne("Recipes.selectRecipeNameCount", srhText);
	}
	
	public List<RecipeDTO> selectRecipeNameList(String srhText) {
		return sqlSession.selectList("Recipes.selectRecipeNameList", srhText);
	}

}
