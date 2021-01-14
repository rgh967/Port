package com.yorijori.cook.recipeDAO;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yorijori.cook.DTO.NoticeDTO;
import com.yorijori.cook.DTO.QuestionDTO;

@Repository
public class BoardDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int getListCount() {
		return sqlSession.selectOne("Notices.count");
	}

	
	public List<NoticeDTO> getBoardList(HashMap<String, Integer> map) {
		return sqlSession.selectList("Notices.list", map); 
	}
	

	public Object insertBoard(NoticeDTO notice) {
		return sqlSession.insert("Notices.insert",notice);
	}
	
	public int setReadCountUpdate(int num) {
		return sqlSession.update("Notices.ReadCountUpdate",num);
	}

	public NoticeDTO getDetail(int num) {
		return sqlSession.selectOne("Notices.Detail",num);
	}


	public int getQuestionCount() {
		return sqlSession.selectOne("Questions.count");
	}


	public List<QuestionDTO> getQuestionList(HashMap<String, Integer> map) {
		return sqlSession.selectList("Questions.list", map); 
	}


	public Object insertBoard(QuestionDTO question) {
		return sqlSession.insert("Questions.insert",question);
	}

	public QuestionDTO getQuestionDetail(int num) {
		return sqlSession.selectOne("Questions.Detail",num);
	}


	public int setQuestionReadCountUpdate(int num) {
		return sqlSession.update("Questions.ReadCountUpdate",num);
	}
	
	public int deleteBoard(int num) {
		return sqlSession.delete("Notices.Delete",num);
	}


	public int boardModify(NoticeDTO notice) {
		return sqlSession.update("Notices.modify", notice);
	}


	public int deleteQuestion(int num) {
		return sqlSession.delete("Questions.Delete",num);
	}


	public int QuestionModify(QuestionDTO question) {
		return sqlSession.update("Questions.modify", question);
	}
	
}
