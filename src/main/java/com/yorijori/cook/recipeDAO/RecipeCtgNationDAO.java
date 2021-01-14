package com.yorijori.cook.recipeDAO;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class RecipeCtgNationDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	public List<Object> selectRecipeCtgId(int nation_id) {
		return sqlSession.selectList("RecipeCtgNations.selectRecipeCtgId", nation_id);
	}
}
