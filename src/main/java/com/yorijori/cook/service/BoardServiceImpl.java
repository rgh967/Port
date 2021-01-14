package com.yorijori.cook.service;


import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.yorijori.cook.DTO.NoticeDTO;
import com.yorijori.cook.DTO.QuestionDTO;
import com.yorijori.cook.recipeDAO.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject
	JavaMailSender mailSender;
	
	@Autowired
	private BoardDAO dao;

	@Override
	public int getListCount() {
		return dao.getListCount();
	}
	
	@Override
	public int getQuestionCount() {
		return dao.getQuestionCount();
	}
	
	 @Override 
	 public List<NoticeDTO> getBoardList(int page, int limit) {
	  
	 HashMap<String, Integer>map = new HashMap<String, Integer>(); 
	 int startrow=(page-1)*limit+1;
	 int endrow=startrow+limit+1;
	 map.put("start",startrow);
	 map.put("end", endrow);
	 
	 return dao.getBoardList(map);
	 }
	 
	 @Override
	 public List<QuestionDTO> getQuestionList(int page, int limit) {
		 
		HashMap<String, Integer>map = new HashMap<String, Integer>(); 
		int startrow=(page-1)*limit+1;
		int endrow=startrow+limit+1;
		map.put("start",startrow);
		map.put("end", endrow);
		 
		return dao.getQuestionList(map);
	} 

	@Override
	public void insertBoard(NoticeDTO notice) {
		dao.insertBoard(notice);
	}

	@Override
	public NoticeDTO getDetail(int num) {
		if(setReadCountUpdate(num)!=1)
			return null;
		return dao.getDetail(num);
	}
	
	@Override
	public int setReadCountUpdate(int num) {
		
		return dao.setReadCountUpdate(num);
		
	}
	
	@Override
	public void insertBoard(QuestionDTO question) {
		dao.insertBoard(question);		
	}
	
	@Override
	public QuestionDTO getQuestionDetail(int num) {
		if(setQuestionReadCountUpdate(num)!=1)
			return null;
		return dao.getQuestionDetail(num);
	}
	
	@Override
	public int setQuestionReadCountUpdate(int num) {
		
		return dao.setQuestionReadCountUpdate(num);
		
	}

	@Override
	public ModelAndView mailSend(HttpServletRequest request, HttpServletResponse response, String MEMBER_EMAIL,ModelAndView mv) {
		
		String setfrom = "Tjsejalfk@gamil.com";
        String tomail = request.getParameter("MEMBER_EMAIL"); // 받는 사람 이메일
        String title = "1대1문의 이메일 입니다."; // 제목
        String content =
        
        System.getProperty("line.separator")+ //한줄씩 줄간격을 두기위해 작성
        System.getProperty("line.separator")+    
        "안녕하세요. 요리조리 홈페이지를 찾아주셔서 감사합니다"
        +System.getProperty("line.separator")+
        System.getProperty("line.separator")+
        " 문의내용을 적어 "
        +System.getProperty("line.separator")+
        System.getProperty("line.separator")+
        "답장을 주시면 검토 후 이메일로 답변 드리겠습니다."
        +System.getProperty("line.separator")+
        System.getProperty("line.separator")+
        "감사합니다.";        // 내용
        
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message,
                    true, "UTF-8");

            messageHelper.setFrom(setfrom); // 보내는사람 생략하면 정상작동을 안함
            messageHelper.setTo(tomail); // 받는사람 이메일
            messageHelper.setSubject(title); 
            messageHelper.setText(content); // 메일 내용
            
            mailSender.send(message);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        mv.setViewName("board/main");     //뷰의이름
        
		return mv;
	}

	@Override
	public int deleteBoard(int num) {
		return dao.deleteBoard(num);
	}

	@Override
	public int boardModify(NoticeDTO notice) {
		return dao.boardModify(notice);
	}

	@Override
	public int deleteQuestion(int num) {
		return dao.deleteQuestion(num);
	}

	@Override
	public int QuestionModify(QuestionDTO question) {
		return dao.QuestionModify(question);
	}
	

}
