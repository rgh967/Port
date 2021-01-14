package com.yorijori.cook.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.yorijori.cook.DTO.MemberDTO;

public interface MemberService {

	int loginProcess(String id, String pass);
	
	int memberRegiProcess(MemberDTO m);
	
	// join ajax 처리
	int memberRegiIdCheck(String id);

	int memberRegiNameCheck(String name);
	
	int memberModifyProcess(MemberDTO m);
	
	MemberDTO memberInfo(String id);
	
	// 회원가입 할 때와 비밀번호 찾기 할 때의 경우에 로직을 합칠 수 있느냐에 따라 메서드명이 달라질 수 있음
	ModelAndView memberRegiMailProcess(HttpServletRequest request,
    		String MEMBER_ID, String MEMBER_PASSWORD, String MEMBER_NAME,
    		String MEMBER_PHONE, String MEMBER_EMAIL,
    		HttpServletResponse response, ModelAndView mv);
	
	ModelAndView memberRegiMailAuthProcess(String email_injeung,  String MEMBER_ID,
    		String MEMBER_PASSWORD, String MEMBER_NAME,
    		String MEMBER_PHONE, String MEMBER_EMAIL,
    		@PathVariable String dice, HttpServletResponse response_equals, HttpServletRequest request);

	int findpw(String MEMBER_ID, String MEMBER_EMAIL);
	
	public ModelAndView pwmailSend(HttpServletRequest request, String mEMBER_ID, String MEMBER_EMAIL , HttpServletResponse response,
			ModelAndView mv);
	public ModelAndView pwinjeung(String email_injeung, String mEMBER_ID, String mEMBER_EMAIL, String dice,
			HttpServletResponse response_equals, HttpServletRequest request);
	
	public int updatepw(MemberDTO member);
	
	public MemberDTO updateinfo(String id);
}
