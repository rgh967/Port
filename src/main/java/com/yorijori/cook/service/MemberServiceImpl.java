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
		return (rmember == null) ? -1 : 1; 	//-1 은 아이디가 존재하지 않는 경우
											//1은 아이디가 존재하는 경우
	}
	@Override
	public int memberRegiNameCheck(String name) {
		MemberDTO rmember = memberDao.selectMemberInfo(name);
		return (rmember == null) ? -1 : 1; 	//-1 은 아이디가 존재하지 않는 경우
											//1은 아이디가 존재하는 경우
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
        int dice = r.nextInt(4589362) + 49311; //이메일로 받는 인증코드 부분 (난수)
        
        String setfrom = "Tjsejalfk@gamil.com";
        String tomail = request.getParameter("MEMBER_EMAIL"); // 받는 사람 이메일
        String title = "회원가입 인증 이메일 입니다."; // 제목
        String content =
        
        System.getProperty("line.separator")+ //한줄씩 줄간격을 두기위해 작성
        System.getProperty("line.separator")+    
        "안녕하세요. 요리조리 홈페이지를 찾아주셔서 감사합니다"
        +System.getProperty("line.separator")+
        System.getProperty("line.separator")+
        " 인증번호는 " +dice+ " 입니다. "
        +System.getProperty("line.separator")+
        System.getProperty("line.separator")+
        "받으신 인증번호를 홈페이지에 입력해 주시면 다음으로 넘어갑니다."; // 내용
        
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
        
        mv.setViewName("member/email_injeung");     //뷰의이름
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
        
        //페이지이동과 자료를 동시에 하기위해 ModelAndView를 사용해서 이동할 페이지와 자료를 담음
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
        int dice = r.nextInt(4589362) + 49311; //이메일로 받는 인증코드 부분 (난수)
        
        String setfrom = "Tjsejalfk@gamil.com";
        String tomail = request.getParameter("MEMBER_EMAIL"); // 받는 사람 이메일
        String title = "비밀번호 찾기 인증 이메일 입니다."; // 제목
        String content =
        
        System.getProperty("line.separator")+ //한줄씩 줄간격을 두기위해 작성
        System.getProperty("line.separator")+    
        "안녕하세요. 요리조리 홈페이지를 찾아주셔서 감사합니다"
        +System.getProperty("line.separator")+
        System.getProperty("line.separator")+
        " 인증번호는 " +dice+ " 입니다. "
        +System.getProperty("line.separator")+
        System.getProperty("line.separator")+
        "받으신 인증번호를 홈페이지에 입력해 주시면 다음으로 넘어갑니다."; // 내용
        
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
        
        mv.setViewName("member/email_injeung2");     //뷰의이름
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