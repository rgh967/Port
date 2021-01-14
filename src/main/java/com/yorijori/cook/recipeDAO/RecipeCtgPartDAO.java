package com.yorijori.cook.recipeDAO;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class RecipeCtgPartDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	public List<Object> selectRecipeCtgId(int part_id) {
		return sqlSession.selectList("RecipeCtgParts.selectRecipeCtgId", part_id);
	}

}
