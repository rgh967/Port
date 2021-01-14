package com.yorijori.cook.recipeDAO;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yorijori.cook.DTO.CategoryNationDTO;
import com.yorijori.cook.DTO.RecipeCtgNationDTO;

@Repository
public class NationDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	public CategoryNationDTO selectNation(int find) {
		return sqlSession.selectOne("Nations.selectNation", find);
	}
	
	public int insertNation(RecipeCtgNationDTO nation) {
		return sqlSession.insert("Nations.insertNation", nation);
	}

}
