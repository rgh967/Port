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
        String tomail = request.getParameter("MEMBER_EMAIL"); // �޴� ��� �̸���
        String title = "1��1���� �̸��� �Դϴ�."; // ����
        String content =
        
        System.getProperty("line.separator")+ //���پ� �ٰ����� �α����� �ۼ�
        System.getProperty("line.separator")+    
        "�ȳ��ϼ���. �丮���� Ȩ�������� ã���ּż� �����մϴ�"
        +System.getProperty("line.separator")+
        System.getProperty("line.separator")+
        " ���ǳ����� ���� "
        +System.getProperty("line.separator")+
        System.getProperty("line.separator")+
        "������ �ֽø� ���� �� �̸��Ϸ� �亯 �帮�ڽ��ϴ�."
        +System.getProperty("line.separator")+
        System.getProperty("line.separator")+
        "�����մϴ�.";        // ����
        
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message,
                    true, "UTF-8");

            messageHelper.setFrom(setfrom); // �����»�� �����ϸ� �����۵��� ����
            messageHelper.setTo(tomail); // �޴»�� �̸���
            messageHelper.setSubject(title); 
            messageHelper.setText(content); // ���� ����
            
            mailSender.send(message);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        mv.setViewName("board/main");     //�����̸�
        
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
