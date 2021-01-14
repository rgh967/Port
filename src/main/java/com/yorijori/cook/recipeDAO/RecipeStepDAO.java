package com.yorijori.cook.recipeDAO;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yorijori.cook.DTO.RecipeStepDTO;

@Repository
public class RecipeStepDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public int insertStep(RecipeStepDTO step) {
		return sqlSession.insert("RecipeSteps.insertStep", step);
	}
}
