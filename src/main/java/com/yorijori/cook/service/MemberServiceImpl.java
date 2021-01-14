package com.yorijori.cook.service;

import java.util.Random;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.yorijori.cook.DTO.MemberDTO;
import com.yorijori.cook.recipeDAO.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	JavaMailSender mailSender;
	
	@Autowired
	MemberDAO memberDao;
	
	@Override
	public int memberRegiIdCheck(String id) {
		MemberDTO rmember = memberDao.selectMemberId(id);
		return (rmember == null) ? -1 : 1; 	//-1 �� ���̵� �������� �ʴ� ���
											//1�� ���̵� �����ϴ� ���
	}
	@Override
	public int memberRegiNameCheck(String name) {
		MemberDTO rmember = memberDao.selectMemberInfo(name);
		return (rmember == null) ? -1 : 1; 	//-1 �� ���̵� �������� �ʴ� ���
											//1�� ���̵� �����ϴ� ���
	}
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public int loginProcess(String id, String password) { 
		MemberDTO rmember = memberDao.selectMemberId(id);
		int result = -1;
		if(rmember != null) {

			if(passwordEncoder.matches(password, rmember.getMEMBER_PASSWORD())) {
				result = 1;
			}else 
				result = 0;
		}
		return result;
	}

	@Override
	public MemberDTO memberInfo(String id) {
		return memberDao.selectMemberId(id);
	}	

	@Override
	public int memberRegiProcess(MemberDTO m) {
		return memberDao.insertMember(m);
	}
	
	@Override
	public int memberModifyProcess(MemberDTO m) {
		return memberDao.updateMember(m);
	}
	
	@Override
	public ModelAndView memberRegiMailProcess(HttpServletRequest request, String MEMBER_ID, String MEMBER_PASSWORD, String MEMBER_NAME,
			String MEMBER_PHONE, String MEMBER_EMAIL, HttpServletResponse response, ModelAndView mv) {
		
		
		HttpSession session = request.getSession();
    	session.setAttribute("MEMBER_ID", MEMBER_ID);
    	session.setAttribute("MEMBER_PASSWORD", MEMBER_PASSWORD);
    	session.setAttribute("MEMBER_NAME", MEMBER_NAME);
    	session.setAttribute("MEMBER_EMAIL", MEMBER_EMAIL);
    	session.setAttribute("MEMBER_PHONE", MEMBER_PHONE);
    	
    	
        Random r = new Random();
        int dice = r.nextInt(4589362) + 49311; //�̸��Ϸ� �޴� �����ڵ� �κ� (����)
        
        String setfrom = "Tjsejalfk@gamil.com";
        String tomail = request.getParameter("MEMBER_EMAIL"); // �޴� ��� �̸���
        String title = "ȸ������ ���� �̸��� �Դϴ�."; // ����
        String content =
        
        System.getProperty("line.separator")+ //���پ� �ٰ����� �α����� �ۼ�
        System.getProperty("line.separator")+    
        "�ȳ��ϼ���. �丮���� Ȩ�������� ã���ּż� �����մϴ�"
        +System.getProperty("line.separator")+
        System.getProperty("line.separator")+
        " ������ȣ�� " +dice+ " �Դϴ�. "
        +System.getProperty("line.separator")+
        System.getProperty("line.separator")+
        "������ ������ȣ�� Ȩ�������� �Է��� �ֽø� �������� �Ѿ�ϴ�."; // ����
        
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
        
        mv.setViewName("member/email_injeung");     //�����̸�
        mv.addObject("dice", dice);
        
		return mv;
	}
	
	@Override
	public ModelAndView memberRegiMailAuthProcess(String email_injeung, String MEMBER_ID, String MEMBER_PASSWORD, String MEMBER_NAME,
			String MEMBER_PHONE, String MEMBER_EMAIL, String dice, HttpServletResponse response_equals,
			HttpServletRequest request) {
		
		HttpSession session = request.getSession();
        MEMBER_ID = (String) session.getAttribute("MEMBER_ID");
        MEMBER_PASSWORD = (String) session.getAttribute("MEMBER_PASSWORD");
        MEMBER_NAME = (String) session.getAttribute("MEMBER_NAME");
        MEMBER_PHONE = (String) session.getAttribute("MEMBER_PHONE");
        MEMBER_EMAIL = (String) session.getAttribute("MEMBER_EMAIL");
        
        //�������̵��� �ڷḦ ���ÿ� �ϱ����� ModelAndView�� ����ؼ� �̵��� �������� �ڷḦ ����
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/joinProcess.net");
        
		
		return mv;
	}
	@Override
	public int findpw(String MEMBER_ID, String MEMBER_EMAIL) {
		
		MemberDTO rmember = memberDao.selectMemberId(MEMBER_ID);
		int result = -1;
		if(rmember != null) {

			if(MEMBER_EMAIL.equals(rmember.getMEMBER_EMAIL())) {
				result = 1;
			}else 
				result = -1;
		}
		return result;
		
	}
	@Override
	public ModelAndView pwmailSend(HttpServletRequest request, String MEMBER_ID, String MEMBER_EMAIL, HttpServletResponse response,
			ModelAndView mv) {
		
		HttpSession session = request.getSession();
    	session.setAttribute("MEMBER_ID", MEMBER_ID);
    	
    	Random r = new Random();
        int dice = r.nextInt(4589362) + 49311; //�̸��Ϸ� �޴� �����ڵ� �κ� (����)
        
        String setfrom = "Tjsejalfk@gamil.com";
        String tomail = request.getParameter("MEMBER_EMAIL"); // �޴� ��� �̸���
        String title = "��й�ȣ ã�� ���� �̸��� �Դϴ�."; // ����
        String content =
        
        System.getProperty("line.separator")+ //���پ� �ٰ����� �α����� �ۼ�
        System.getProperty("line.separator")+    
        "�ȳ��ϼ���. �丮���� Ȩ�������� ã���ּż� �����մϴ�"
        +System.getProperty("line.separator")+
        System.getProperty("line.separator")+
        " ������ȣ�� " +dice+ " �Դϴ�. "
        +System.getProperty("line.separator")+
        System.getProperty("line.separator")+
        "������ ������ȣ�� Ȩ�������� �Է��� �ֽø� �������� �Ѿ�ϴ�."; // ����
        
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
        
        mv.setViewName("member/email_injeung2");     //�����̸�
        mv.addObject("dice", dice);
        
		return mv;
	}
	
	@Override
	public ModelAndView pwinjeung(String email_injeung, String MEMBER_ID, String MEMBER_EMAIL, String dice,
			HttpServletResponse response_equals, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
        MEMBER_ID = (String) session.getAttribute("MEMBER_ID");
        
        ModelAndView mv = new ModelAndView();
        
		
		return mv;
	}
	@Override
	public int updatepw(MemberDTO member) {
		return memberDao.update2(member);
	}
	@Override
	public MemberDTO updateinfo(String id) {
		return memberDao.member_info(id);
	}

}