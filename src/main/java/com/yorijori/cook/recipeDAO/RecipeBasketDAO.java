package com.yorijori.cook.recipeDAO;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yorijori.cook.DTO.ScrapDTO;
import com.yorijori.cook.DTO.SubscribeDTO;

@Repository
public class RecipeBasketDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int selectScrap(ScrapDTO scrap) {
		return sqlSession.selectOne("Baskets.selectScrap", scrap);
	}
	
	public int insertScrap(ScrapDTO scrap) {
		return sqlSession.insert("Baskets.insertScrap", scrap);
	}

	public int selectSubscribe(SubscribeDTO subscribe) {
		return sqlSession.selectOne("Baskets.selectSubscribe", subscribe);
	}

	public int insertSubscribe(SubscribeDTO subscribe) {
		return sqlSession.insert("Baskets.insertSubscribe", subscribe);
	}

}
