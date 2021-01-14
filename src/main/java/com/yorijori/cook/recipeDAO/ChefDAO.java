package com.yorijori.cook.recipeDAO;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yorijori.cook.DTO.CategoryChefDTO;
import com.yorijori.cook.DTO.RecipeCtgChefDTO;


@Repository
public class ChefDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	public CategoryChefDTO selectChef(int find) {
		return sqlSession.selectOne("Chefs.selectChef", find);
	}

	public int insertChefList(RecipeCtgChefDTO chef) {
		return sqlSession.insert("Chefs.insertChefList", chef);
	}

}
