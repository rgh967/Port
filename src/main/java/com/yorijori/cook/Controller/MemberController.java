package com.yorijori.cook.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yorijori.cook.DTO.MemberDTO;
import com.yorijori.cook.service.MemberService;

@Controller
public class MemberController {
	
	@Inject
	 JavaMailSender mailSender;
	
	 @Autowired 
	 private MemberService memberService;
	 @Autowired 
	 private PasswordEncoder passwordEncoder;

//	 	----------------By ����----------------�α��� ������ �̵�
	@RequestMapping(value="/login.net", method = RequestMethod.GET)
	public ModelAndView login(ModelAndView mv,
			@CookieValue(value="saveid", required=false) Cookie readCookie) throws Exception{
		
		if(readCookie != null) {
			mv.addObject("saveid", readCookie.getValue());
			System.out.println("cookie time=" + readCookie.getMaxAge());
		}
		mv.setViewName("member/loginForm");
		return mv;
	}
// 	----------------By ����----------------ȸ������ ������ �̵�
	@RequestMapping(value="/join.net", method = RequestMethod.GET)
	public String join() {
		return "member/joinForm";
	}
	
// 	----------------By ����----------------ȸ������ ���̵� �ߺ��˻� ajax ó��
	@RequestMapping(value="/idCheck.net", method = RequestMethod.GET)
	public void idCheck(@RequestParam("id") String id,
						HttpServletResponse response) throws Exception{
		int result = memberService.memberRegiIdCheck(id);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(result);
	}
// 	----------------By ����----------------ȸ������ �г��� �ߺ��˻� ajax ó��
	@RequestMapping(value="/nameCheck.net", method = RequestMethod.GET)
	public void nameCheck(@RequestParam("name") String name,
						HttpServletResponse response) throws Exception{
		int result = memberService.memberRegiNameCheck(name);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(result);
	}
	
// 	----------------By ����----------------ȸ������ mail�߼� ó��
    @ResponseBody
    @RequestMapping( value = "/joinMail.net" , method=RequestMethod.POST )
    public ModelAndView joinMail(HttpServletRequest request,
    		String MEMBER_ID, String MEMBER_PASSWORD, String MEMBER_NAME,
    		String MEMBER_PHONE, String MEMBER_EMAIL,
    		HttpServletResponse response, ModelAndView mv) 
    				throws IOException {
    	
    	mv = memberService.memberRegiMailProcess(request, MEMBER_ID, MEMBER_PASSWORD,
    			MEMBER_NAME, MEMBER_PHONE, MEMBER_EMAIL, response, mv);
    	System.out.println("mv : "+mv);
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out_email = response.getWriter();
        out_email.println("<script>alert('�̸����� �߼۵Ǿ����ϴ�. ������ȣ�� �Է����ּ���.');</script>");
        out_email.flush();
       
        return mv;
        
    }
	
// 	----------------By ����----------------ȸ������ mail���� ó��
    //�̸��Ϸ� ���� ������ȣ�� �Է��ϰ� ���� ��ư�� ������ ���εǴ� �޼ҵ�.
    //���� �Է��� ������ȣ�� ���Ϸ� �Է��� ������ȣ�� �´��� Ȯ���ؼ� ������ ȸ������ �������� �Ѿ��,
    //Ʋ���� �ٽ� ���� �������� ���ƿ��� �޼ҵ�
    @RequestMapping(value = "/joinMailAuth.net{dice}", method = RequestMethod.POST)
    public ModelAndView joinMailAuth(String email_injeung,  String MEMBER_ID,
    		String MEMBER_PASSWORD, String MEMBER_NAME,
    		String MEMBER_PHONE, String MEMBER_EMAIL,
    		@PathVariable String dice, HttpServletResponse response_equals, HttpServletRequest request)
    		throws IOException {
        
    	ModelAndView mv = memberService.memberRegiMailAuthProcess(email_injeung, MEMBER_ID, MEMBER_PASSWORD,
    			MEMBER_NAME, MEMBER_PHONE, MEMBER_EMAIL, dice, response_equals, request);
    	
    	System.out.println("������ : email_injeung : "+ MEMBER_EMAIL);
        System.out.println("������ : dice : "+dice);
        
        if (email_injeung.equals(dice)) {
            
            //������ȣ�� ��ġ�� ��� ������ȣ�� �´ٴ� â�� ����ϰ� ȸ������â���� �̵���
            mv.setViewName("member/joinForm2");

            //���� ������ȣ�� ���ٸ� �̸����� ȸ������ �������� ���� �Ѱܼ� �̸�����
            //�ѹ��� �Է��� �ʿ䰡 ���� �Ѵ�.
            response_equals.setContentType("text/html; charset=UTF-8");
            PrintWriter out_equals = response_equals.getWriter();
            out_equals.println("<script>alert('������ȣ�� ��ġ�Ͽ����ϴ�. ȸ������â���� �̵��մϴ�.');</script>");
            out_equals.flush();
    
            return mv;
            
        }else if (email_injeung != dice) {
            
            ModelAndView mv2 = new ModelAndView(); 
            mv2.setViewName("member/email_injeung");
            response_equals.setContentType("text/html; charset=UTF-8");
            PrintWriter out_equals = response_equals.getWriter();
            out_equals.println("<script>alert('������ȣ�� ��ġ�����ʽ��ϴ�. ������ȣ�� �ٽ� �Է����ּ���.'); history.go(-1);</script>");
            out_equals.flush();
            
            return mv2;
        }    
        return mv; 
    }
// 	----------------By ����----------------��й�ȣ ã��ȭ�� �̵�
    @RequestMapping(value="/find.net", method = RequestMethod.GET)
	public String find() {
		return "member/findpw";
	}
    
// 	----------------By ����----------------��й�ȣ ã�� �̸��Ϲ߼� �� ��ϵ� ���̵�, �̸��� �˻�
    @ResponseBody
    @RequestMapping( value = "/find_password.net" , method=RequestMethod.POST )
    public ModelAndView findpassword(HttpServletRequest request,
    		String MEMBER_ID, String MEMBER_EMAIL,
    		HttpServletResponse response, ModelAndView mv) 
    				throws IOException {
    	
    	int result = memberService.findpw(MEMBER_ID, MEMBER_EMAIL);
    	System.out.println("result : "+ result);
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>");
		//���� ����
		if(result == 1) {
			out.println("alert('�̸����� �߼۵Ǿ����ϴ�. ������ȣ�� �Է����ּ���.');");
			mv = memberService.pwmailSend(request, MEMBER_ID, MEMBER_EMAIL, response, mv);
		}else if(result == -1) {
			out.println("alert('��ġ�ϴ� ������ �����ϴ�. �ٽ� �Է��ϼ���.');");
			mv.setViewName("member/findpw");
		}
		out.println("</script>");	
		out.flush();
		
		return mv;
	}
// 	----------------By ����----------------��й�ȣ ã�� �̸�������
    @RequestMapping(value = "/find_injeung.do{dice}", method = RequestMethod.POST)
    public ModelAndView join_injeung(String email_injeung,  String MEMBER_ID, String MEMBER_EMAIL,
    		@PathVariable String dice, HttpServletResponse response_equals, HttpServletRequest request)
    		throws IOException {
        
    	ModelAndView mv = memberService.pwinjeung(email_injeung, MEMBER_ID, MEMBER_EMAIL,
    			dice, response_equals, request);
    	
    	System.out.println("������ : email_injeung : "+MEMBER_EMAIL);
        System.out.println("������ : dice : "+dice);
        
        if (email_injeung.equals(dice)) {
            
            //������ȣ�� ��ġ�� ��� ������ȣ�� �´ٴ� â�� ����ϰ� ȸ������â���� �̵���
            mv.setViewName("member/pwchange");

            //���� ������ȣ�� ���ٸ� �̸����� ȸ������ �������� ���� �Ѱܼ� �̸�����
            //�ѹ��� �Է��� �ʿ䰡 ���� �Ѵ�.
            response_equals.setContentType("text/html; charset=UTF-8");
            PrintWriter out_equals = response_equals.getWriter();
            out_equals.println("<script>alert('������ȣ�� ��ġ�Ͽ����ϴ�. ������������ �̵��մϴ�.');</script>");
            out_equals.flush();
    
            return mv;
            
        }else if (email_injeung != dice) {
            
            ModelAndView mv2 = new ModelAndView(); 
            mv2.setViewName("member/email_injeung2");
            response_equals.setContentType("text/html; charset=UTF-8");
            PrintWriter out_equals = response_equals.getWriter();
            out_equals.println("<script>alert('������ȣ�� ��ġ�����ʽ��ϴ�. ������ȣ�� �ٽ� �Է����ּ���.'); history.go(-1);</script>");
            out_equals.flush();
            
            return mv2;
        }    
        return mv; 
    }
// 	----------------By ����----------------��й�ȣ ����
    @RequestMapping(value = "/change_password.do", method = RequestMethod.POST)
	public void changepw(MemberDTO member,
							HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		System.out.println(member.getMEMBER_PASSWORD());
		//��й�ȣ ��ȣȭ �߰�
		String encPassword = passwordEncoder.encode(member.getMEMBER_PASSWORD());
		System.out.println(encPassword);
		member.setMEMBER_PASSWORD(encPassword);
		
		int result = memberService.updatepw(member);
		out.println("<script>");
		
		if(result == 1) {
			out.println("alert('�����Ǿ����ϴ�.');");
			out.println("location.href='login.net';");
		}else {
			out.println("alert('ȸ�� ���� ������ �����߽��ϴ�.');");
			out.println("history.back()");
		}
		out.println("</script>");
		out.close();
	}
// 	----------------By ����----------------ȸ������ ó��
	@RequestMapping(value="/joinProcess.net", method=RequestMethod.POST)
	public void joinProcess( MemberDTO member, HttpServletResponse response) throws Exception{
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		System.out.println(member.getMEMBER_PASSWORD());
		//��й�ȣ ��ȣȭ �߰�
		String encPassword = passwordEncoder.encode(member.getMEMBER_PASSWORD());
		System.out.println(encPassword);
		member.setMEMBER_PASSWORD(encPassword);
		
		int result = memberService.memberRegiProcess(member);
		out.println("<script>");
		//���� ����
		if(result == 1) {
			out.println("alert('ȸ�������� �����մϴ�.');");
			out.println("location.href='login.net';");
		}else if(result == -1) {
			out.println("alert('���̵� �ߺ��Ǿ����ϴ�. �ٽ� �Է��ϼ���.');");
			out.println("history.back()");
		}
		out.println("</script>");
		out.close();
	}
// 	----------------By ����----------------�α��� ó��(���̵�, ��й�ȣ DB Ȯ��)
	@RequestMapping(value="/loginProcess.net", method=RequestMethod.POST)
	public String loginProcess(@RequestParam("id") String id,
						@RequestParam("password") String password,
						@RequestParam(value="remember", defaultValue="") String remember,
						HttpServletResponse response,
						HttpSession session) throws Exception {
		
		System.out.println("id : " + id);
		System.out.println("password : " + password);
		
		int result = memberService.loginProcess(id,password);
		System.out.println("�����" + result);
		
		if(result == 1) {
			session.setAttribute("id", id);
			Cookie savecookie = new Cookie("saveid",id);
			if(!remember.equals("") ) {
				savecookie.setMaxAge(60*60);
				System.out.println("��Ű���� : 60*60");
			}else {
				System.out.println("��Ű���� : 0");
				savecookie.setMaxAge(0);
			}
			response.addCookie(savecookie);
			return "redirect:main.net";
		}else {
			String message= "��й�ȣ�� ��ġ���� �ʽ��ϴ�.";
			if(result == -1)
				message = "���̵� �������� �ʽ��ϴ�.";
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('"+message+"');");
			out.println("location.href='login.net';");
			out.println("</script>");
			out.close();
			return null;
		}
	}
// 	----------------By ����----------------ȸ���������� ȭ�� �̵�
	@RequestMapping(value = "/memberInfo.net", method = RequestMethod.GET)
	public ModelAndView memberInfo(HttpSession session,
							ModelAndView mv) throws Exception {
		String id = (String) session.getAttribute("id");
		MemberDTO m = memberService.memberInfo(id);
		mv.setViewName("member/updateForm");
		mv.addObject("memberinfo",m);
		
		return mv;
	}
// 	----------------By ����----------------ȸ���������� ó��
	@RequestMapping(value = "/updateProcess.net", method = RequestMethod.POST)
	public void updateProcess(MemberDTO member,
							HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		System.out.println(member.getMEMBER_PASSWORD());
		//��й�ȣ ��ȣȭ �߰�
		String encPassword = passwordEncoder.encode(member.getMEMBER_PASSWORD());
		System.out.println(encPassword);
		member.setMEMBER_PASSWORD(encPassword);
		
		int result = memberService.memberModifyProcess(member);
		out.println("<script>");
		
		if(result == 1) {
			out.println("alert('�����Ǿ����ϴ�.');");
			out.println("location.href='main.net';");
		}else {
			out.println("alert('ȸ�� ���� ������ �����߽��ϴ�.');");
			out.println("history.back()");
		}
		out.println("</script>");
		out.close();
	}
// 	----------------By ����----------------�α׾ƿ�
	@RequestMapping(value="/logout.net", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception {

		session.invalidate();
		return "redirect:login.net";
	}
	
}


