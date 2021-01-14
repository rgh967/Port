package com.yorijori.cook.recipeDAO;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yorijori.cook.DTO.RecipeIngDTO;

@Repository
public class RecipeIngDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	public int insertIng(RecipeIngDTO ing) {
		return sqlSession.insert("RecipeIngs.insertIng", ing);
	}

}
