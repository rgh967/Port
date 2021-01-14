package com.yorijori.cook.recipeDAO;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class RecipeCtgChefDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public List<Object> selectRecipeCtgId(int chef_id) {
		return sqlSession.selectList("RecipeCtgChefs.selectRecipeCtgId", chef_id);
	}

}
