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

//	 	----------------By 태훈----------------로그인 페이지 이동
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
// 	----------------By 태훈----------------회원가입 페이지 이동
	@RequestMapping(value="/join.net", method = RequestMethod.GET)
	public String join() {
		return "member/joinForm";
	}
	
// 	----------------By 태훈----------------회원가입 아이디 중복검사 ajax 처리
	@RequestMapping(value="/idCheck.net", method = RequestMethod.GET)
	public void idCheck(@RequestParam("id") String id,
						HttpServletResponse response) throws Exception{
		int result = memberService.memberRegiIdCheck(id);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(result);
	}
// 	----------------By 태훈----------------회원가입 닉네임 중복검사 ajax 처리
	@RequestMapping(value="/nameCheck.net", method = RequestMethod.GET)
	public void nameCheck(@RequestParam("name") String name,
						HttpServletResponse response) throws Exception{
		int result = memberService.memberRegiNameCheck(name);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(result);
	}
	
// 	----------------By 태훈----------------회원가입 mail발송 처리
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
        out_email.println("<script>alert('이메일이 발송되었습니다. 인증번호를 입력해주세요.');</script>");
        out_email.flush();
       
        return mv;
        
    }
	
// 	----------------By 태훈----------------회원가입 mail인증 처리
    //이메일로 받은 인증번호를 입력하고 전송 버튼을 누르면 맵핑되는 메소드.
    //내가 입력한 인증번호와 메일로 입력한 인증번호가 맞는지 확인해서 맞으면 회원가입 페이지로 넘어가고,
    //틀리면 다시 원래 페이지로 돌아오는 메소드
    @RequestMapping(value = "/joinMailAuth.net{dice}", method = RequestMethod.POST)
    public ModelAndView joinMailAuth(String email_injeung,  String MEMBER_ID,
    		String MEMBER_PASSWORD, String MEMBER_NAME,
    		String MEMBER_PHONE, String MEMBER_EMAIL,
    		@PathVariable String dice, HttpServletResponse response_equals, HttpServletRequest request)
    		throws IOException {
        
    	ModelAndView mv = memberService.memberRegiMailAuthProcess(email_injeung, MEMBER_ID, MEMBER_PASSWORD,
    			MEMBER_NAME, MEMBER_PHONE, MEMBER_EMAIL, dice, response_equals, request);
    	
    	System.out.println("마지막 : email_injeung : "+ MEMBER_EMAIL);
        System.out.println("마지막 : dice : "+dice);
        
        if (email_injeung.equals(dice)) {
            
            //인증번호가 일치할 경우 인증번호가 맞다는 창을 출력하고 회원가입창으로 이동함
            mv.setViewName("member/joinForm2");

            //만약 인증번호가 같다면 이메일을 회원가입 페이지로 같이 넘겨서 이메일을
            //한번더 입력할 필요가 없게 한다.
            response_equals.setContentType("text/html; charset=UTF-8");
            PrintWriter out_equals = response_equals.getWriter();
            out_equals.println("<script>alert('인증번호가 일치하였습니다. 회원가입창으로 이동합니다.');</script>");
            out_equals.flush();
    
            return mv;
            
        }else if (email_injeung != dice) {
            
            ModelAndView mv2 = new ModelAndView(); 
            mv2.setViewName("member/email_injeung");
            response_equals.setContentType("text/html; charset=UTF-8");
            PrintWriter out_equals = response_equals.getWriter();
            out_equals.println("<script>alert('인증번호가 일치하지않습니다. 인증번호를 다시 입력해주세요.'); history.go(-1);</script>");
            out_equals.flush();
            
            return mv2;
        }    
        return mv; 
    }
// 	----------------By 태훈----------------비밀번호 찾기화면 이동
    @RequestMapping(value="/find.net", method = RequestMethod.GET)
	public String find() {
		return "member/findpw";
	}
    
// 	----------------By 태훈----------------비밀번호 찾기 이메일발송 및 등록된 아이디, 이메일 검사
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
		//삽입 성공
		if(result == 1) {
			out.println("alert('이메일이 발송되었습니다. 인증번호를 입력해주세요.');");
			mv = memberService.pwmailSend(request, MEMBER_ID, MEMBER_EMAIL, response, mv);
		}else if(result == -1) {
			out.println("alert('일치하는 정보가 없습니다. 다시 입력하세요.');");
			mv.setViewName("member/findpw");
		}
		out.println("</script>");	
		out.flush();
		
		return mv;
	}
// 	----------------By 태훈----------------비밀번호 찾기 이메일인증
    @RequestMapping(value = "/find_injeung.do{dice}", method = RequestMethod.POST)
    public ModelAndView join_injeung(String email_injeung,  String MEMBER_ID, String MEMBER_EMAIL,
    		@PathVariable String dice, HttpServletResponse response_equals, HttpServletRequest request)
    		throws IOException {
        
    	ModelAndView mv = memberService.pwinjeung(email_injeung, MEMBER_ID, MEMBER_EMAIL,
    			dice, response_equals, request);
    	
    	System.out.println("마지막 : email_injeung : "+MEMBER_EMAIL);
        System.out.println("마지막 : dice : "+dice);
        
        if (email_injeung.equals(dice)) {
            
            //인증번호가 일치할 경우 인증번호가 맞다는 창을 출력하고 회원가입창으로 이동함
            mv.setViewName("member/pwchange");

            //만약 인증번호가 같다면 이메일을 회원가입 페이지로 같이 넘겨서 이메일을
            //한번더 입력할 필요가 없게 한다.
            response_equals.setContentType("text/html; charset=UTF-8");
            PrintWriter out_equals = response_equals.getWriter();
            out_equals.println("<script>alert('인증번호가 일치하였습니다. 정보수정으로 이동합니다.');</script>");
            out_equals.flush();
    
            return mv;
            
        }else if (email_injeung != dice) {
            
            ModelAndView mv2 = new ModelAndView(); 
            mv2.setViewName("member/email_injeung2");
            response_equals.setContentType("text/html; charset=UTF-8");
            PrintWriter out_equals = response_equals.getWriter();
            out_equals.println("<script>alert('인증번호가 일치하지않습니다. 인증번호를 다시 입력해주세요.'); history.go(-1);</script>");
            out_equals.flush();
            
            return mv2;
        }    
        return mv; 
    }
// 	----------------By 태훈----------------비밀번호 변경
    @RequestMapping(value = "/change_password.do", method = RequestMethod.POST)
	public void changepw(MemberDTO member,
							HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		System.out.println(member.getMEMBER_PASSWORD());
		//비밀번호 암호화 추가
		String encPassword = passwordEncoder.encode(member.getMEMBER_PASSWORD());
		System.out.println(encPassword);
		member.setMEMBER_PASSWORD(encPassword);
		
		int result = memberService.updatepw(member);
		out.println("<script>");
		
		if(result == 1) {
			out.println("alert('수정되었습니다.');");
			out.println("location.href='login.net';");
		}else {
			out.println("alert('회원 정보 수정에 실패했습니다.');");
			out.println("history.back()");
		}
		out.println("</script>");
		out.close();
	}
// 	----------------By 태훈----------------회원가입 처리
	@RequestMapping(value="/joinProcess.net", method=RequestMethod.POST)
	public void joinProcess( MemberDTO member, HttpServletResponse response) throws Exception{
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		System.out.println(member.getMEMBER_PASSWORD());
		//비밀번호 암호화 추가
		String encPassword = passwordEncoder.encode(member.getMEMBER_PASSWORD());
		System.out.println(encPassword);
		member.setMEMBER_PASSWORD(encPassword);
		
		int result = memberService.memberRegiProcess(member);
		out.println("<script>");
		//삽입 성공
		if(result == 1) {
			out.println("alert('회원가입을 축하합니다.');");
			out.println("location.href='login.net';");
		}else if(result == -1) {
			out.println("alert('아이디가 중복되었습니다. 다시 입력하세요.');");
			out.println("history.back()");
		}
		out.println("</script>");
		out.close();
	}
// 	----------------By 태훈----------------로그인 처리(아이디, 비밀번호 DB 확인)
	@RequestMapping(value="/loginProcess.net", method=RequestMethod.POST)
	public String loginProcess(@RequestParam("id") String id,
						@RequestParam("password") String password,
						@RequestParam(value="remember", defaultValue="") String remember,
						HttpServletResponse response,
						HttpSession session) throws Exception {
		
		System.out.println("id : " + id);
		System.out.println("password : " + password);
		
		int result = memberService.loginProcess(id,password);
		System.out.println("결과는" + result);
		
		if(result == 1) {
			session.setAttribute("id", id);
			Cookie savecookie = new Cookie("saveid",id);
			if(!remember.equals("") ) {
				savecookie.setMaxAge(60*60);
				System.out.println("쿠키저장 : 60*60");
			}else {
				System.out.println("쿠키저장 : 0");
				savecookie.setMaxAge(0);
			}
			response.addCookie(savecookie);
			return "redirect:main.net";
		}else {
			String message= "비밀번호가 일치하지 않습니다.";
			if(result == -1)
				message = "아이디가 존재하지 않습니다.";
			
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
// 	----------------By 태훈----------------회원정보수정 화면 이동
	@RequestMapping(value = "/memberInfo.net", method = RequestMethod.GET)
	public ModelAndView memberInfo(HttpSession session,
							ModelAndView mv) throws Exception {
		String id = (String) session.getAttribute("id");
		MemberDTO m = memberService.memberInfo(id);
		mv.setViewName("member/updateForm");
		mv.addObject("memberinfo",m);
		
		return mv;
	}
// 	----------------By 태훈----------------회원정보수정 처리
	@RequestMapping(value = "/updateProcess.net", method = RequestMethod.POST)
	public void updateProcess(MemberDTO member,
							HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		System.out.println(member.getMEMBER_PASSWORD());
		//비밀번호 암호화 추가
		String encPassword = passwordEncoder.encode(member.getMEMBER_PASSWORD());
		System.out.println(encPassword);
		member.setMEMBER_PASSWORD(encPassword);
		
		int result = memberService.memberModifyProcess(member);
		out.println("<script>");
		
		if(result == 1) {
			out.println("alert('수정되었습니다.');");
			out.println("location.href='main.net';");
		}else {
			out.println("alert('회원 정보 수정에 실패했습니다.');");
			out.println("history.back()");
		}
		out.println("</script>");
		out.close();
	}
// 	----------------By 태훈----------------로그아웃
	@RequestMapping(value="/logout.net", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception {

		session.invalidate();
		return "redirect:login.net";
	}
	
}


