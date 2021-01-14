package com.yorijori.cook.recipeDAO;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yorijori.cook.DTO.CategoryPartDTO;
import com.yorijori.cook.DTO.RecipeCtgPartDTO;

@Repository
public class PartDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	public CategoryPartDTO selectPart(int find) {
		return sqlSession.selectOne("Parts.selectPart", find);
	}
	
	public int insertPart(RecipeCtgPartDTO part) {
		return sqlSession.insert("Parts.insertPart", part);
	}

}
