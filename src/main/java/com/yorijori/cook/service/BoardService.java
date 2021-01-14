package com.yorijori.cook.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.yorijori.cook.DTO.NoticeDTO;
import com.yorijori.cook.DTO.QuestionDTO;

public interface BoardService {
	
	 // notice
	 public int getListCount();
	 
	 public List<NoticeDTO> getBoardList(int page, int limit);

	 public void insertBoard(NoticeDTO notice);
	 
	 public NoticeDTO getDetail(int num);
	
	 public int setReadCountUpdate(int num);
	
	 public int getQuestionCount();

	 public List<QuestionDTO> getQuestionList(int page, int limit);

	 public void insertBoard(QuestionDTO question);

	 public QuestionDTO getQuestionDetail(int num);

	 public int setQuestionReadCountUpdate(int num);

	 public ModelAndView mailSend(HttpServletRequest request, HttpServletResponse response,String MEMBER_EMAIL, ModelAndView mv);
		 
	 public int  deleteBoard(int num);

	 public int boardModify(NoticeDTO notice);

	public int deleteQuestion(int num);

	public int QuestionModify(QuestionDTO question);
}
