package com.yorijori.cook.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yorijori.cook.DTO.NoticeDTO;
import com.yorijori.cook.DTO.QuestionDTO;
import com.yorijori.cook.service.BoardService;

@Controller
public class BoardController {
	
	@Inject
	 JavaMailSender mailSender;
	
	@Autowired
	private BoardService boardService;

//	----------------By ����----------------������ > ��������������
	@RequestMapping(value = "/NoticeList.bo")
	public ModelAndView boardList(@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			ModelAndView mv) {
		int limit = 10;

		int listcount = boardService.getListCount();
		List<NoticeDTO> boardlist = boardService.getBoardList(page, limit);

		mv.setViewName("notice/Customer_Support");
		mv.addObject("page", page);
		mv.addObject("listcount", listcount);
		mv.addObject("boardlist", boardlist);
		mv.addObject("limit", limit);

		return mv;
	}
//	----------------By ����----------------������ > ����ã������������
	@RequestMapping(value = "/NoticeQuestion.bo")
	public ModelAndView boardQuestion(@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			ModelAndView mv) {
		int limit = 10;

		int listcount = boardService.getQuestionCount();
		List<QuestionDTO> boardQuestion = boardService.getQuestionList(page, limit);

		mv.setViewName("notice/Customer_Question");
		mv.addObject("page", page);
		mv.addObject("listcount", listcount);
		mv.addObject("boardQuestion", boardQuestion);
		mv.addObject("limit", limit);

		return mv;
	}
	
// 	----------------By ����----------------������ > 1��1���������� 
	@RequestMapping(value = "/NoticeOno.bo")
	public String boardOno() {
		return "notice/Customer_Ono";
	}
			
// 	----------------By ����----------------������ > 1��1���� �̸��� ó��
	@ResponseBody
    @RequestMapping( value = "/Onomail.bo" , method=RequestMethod.POST )
    public ModelAndView mailSending(HttpServletRequest request,
    		HttpServletResponse response, String MEMBER_EMAIL, ModelAndView mv) 
    				throws IOException {

    	mv = boardService.mailSend(request, response, MEMBER_EMAIL , mv);
    	System.out.println("mv : "+mv);
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out_email = response.getWriter();
        out_email.println("<script>alert('�̸����� �߼۵Ǿ����ϴ�. Ȯ���� ���� �ٶ��ϴ�.');</script>");
        out_email.flush();
       
        return mv;
        
    }
	
	
	
// 	----------------By ����----------------������ > �������� �۾���
		@GetMapping(value = "/NoticeWrite.bo")
		public String board_write() {
			return "notice/qna_notice_write";
		}
//	 	----------------By ����----------------������ > �������� �� �߰�
		@PostMapping("/NoticeAddAction.bo")
		public String bbs_write_ok(NoticeDTO notice, HttpServletRequest request)
				throws Exception{
		
			boardService.insertBoard(notice);
			
			return "redirect:NoticeList.bo";
		}
		
//	 	----------------By ����----------------������ > �������� �� �󼼺���
		 @GetMapping("/BoardDetailAction.bo")
		 public ModelAndView Detail(int num, ModelAndView mv,
				 HttpServletRequest request) {
			 NoticeDTO notice = boardService.getDetail(num);
			 if(notice == null) {
				 System.out.println("�󼼺��� ����");
				 mv.setViewName("error/error");
				 mv.addObject("url",request.getRequestURL());
				 mv.addObject("message", "�󼼺��� ���� �Դϴ�.");
		 }else {
			 System.out.println("�󼼺��� ����");
			 mv.setViewName("notice/qna_notice_view");
			 mv.addObject("boarddata", notice);
		 }
		 return mv;
		 }
	
//		 	----------------By ����----------------������ > ����ã������ �۾���
			@GetMapping(value = "/QuestionWrite.bo")
			public String Question_write() {
				return "notice/qna_write";
			}
			
//		 	----------------By ����----------------������ > ����ã������ �� �߰�
			@PostMapping("/QuestionAddAction.bo")
			public String qna_write_ok(QuestionDTO question, HttpServletRequest request)
					throws Exception{
			
				boardService.insertBoard(question);
				
				return "redirect:NoticeQuestion.bo";
			}
			
//		 	----------------By ����----------------������ > ����ã������ �� �󼼺���
			 @GetMapping("/QuestionDetailAction.bo")
			 public ModelAndView QuestionDetail(int num, ModelAndView mv,
					 HttpServletRequest request) {
				 QuestionDTO question = boardService.getQuestionDetail(num);
				 if(question == null) {
					 System.out.println("�󼼺��� ����");
					 mv.setViewName("error/error");
					 mv.addObject("url",request.getRequestURL());
					 mv.addObject("message", "�󼼺��� ���� �Դϴ�.");
			 }else {
				 System.out.println("�󼼺��� ����");
				 mv.setViewName("notice/qna_view");
				 mv.addObject("boarddata", question);
			 }
			 return mv;
			 }
//			 	----------------By ����----------------������ > �������� �� ����
			 @GetMapping("/noticeDelete.bo")
			 public String delete(@RequestParam("num")int num) {
			 	boardService.deleteBoard(num);
			 	return "redirect: main.net";
			 }
//			 	----------------By ����----------------������ > �������� �� ���������� �̵�
			 @GetMapping("/noticeModifyView.bo")
				public ModelAndView NoticeModifyView(int num, ModelAndView mv,
													HttpServletRequest request) {
					NoticeDTO boarddata = boardService.getDetail(num);
					// �� ���� �ҷ����� ������ ���
					if(boarddata == null) {
						System.out.println("(����)�󼼺��� ����");
						mv.setViewName("error/error");
						mv.addObject("url", request.getRequestURL());
						mv.addObject("message", "(����)�󼼺��� �����Դϴ�.");
						return mv;
					}
					System.out.println("(����)�󼼺��� ����");
					
					// ���� �� �������� �̵��� �� ���� �� ������ �����ֱ� ������ board ��ü�� ModelAndView ��ü�� ����
					mv.addObject("boarddata", boarddata);
					
					// �� ���� �� �������� �̵��ϱ� ���� ��θ� �����մϴ�.
					mv.setViewName("notice/qna_notice_update");
					return mv;
				}
			 
//			 	----------------By ����----------------������ > �������� �� ����
			 @PostMapping("/BoardModifyAction.bo")
				public ModelAndView BoardModifyAction(NoticeDTO notice,
						ModelAndView mv, HttpServletRequest request,
						HttpServletResponse response) throws Exception {
				 
				 int result = boardService.boardModify(notice);
				 System.out.println(result);
					// ������ ������ ���
					if(result == 0) {
						System.out.println("�Խ��� ���� ����");
						mv.setViewName("error/error");
						mv.addObject("url", request.getRequestURL());
						mv.addObject("message", "�Խ��� ���� ����");
					} else { // ���� ������ ���
						System.out.println("�Խ��� ���� �Ϸ�");
						String url = "redirect:BoardDetailAction.bo?num=" + notice.getNOTICE_NUM();
						
						// ������ �� ������ �����ֱ� ���� �� ���� ���� ���� �������� �̵��ϱ� ���� ��θ� �����մϴ�.
						mv.setViewName(url);
					}
					return mv;
				}
//			 	----------------By ����----------------������ > ����ã������ �� ����
			 @GetMapping("/QuestionDelete.bo")
			 public String qnadelete(@RequestParam("num")int num) {
			 	boardService.deleteQuestion(num);
			 	return "redirect: main.net";
			 }
//			 	----------------By ����----------------������ > ����ã������ �� ���� ������ �̵�
			 @GetMapping("/QuestionModifyView.bo")
				public ModelAndView qnaModifyView(int num, ModelAndView mv,
													HttpServletRequest request) {
				 QuestionDTO boarddata = boardService.getQuestionDetail(num);
					// �� ���� �ҷ����� ������ ���
					if(boarddata == null) {
						System.out.println("(����)�󼼺��� ����");
						mv.setViewName("error/error");
						mv.addObject("url", request.getRequestURL());
						mv.addObject("message", "(����)�󼼺��� �����Դϴ�.");
						return mv;
					}
					System.out.println("(����)�󼼺��� ����");
					
					// ���� �� �������� �̵��� �� ���� �� ������ �����ֱ� ������ board ��ü�� ModelAndView ��ü�� ����
					mv.addObject("boarddata", boarddata);
					
					// �� ���� �� �������� �̵��ϱ� ���� ��θ� �����մϴ�.
					mv.setViewName("notice/qna_Question_update");
					return mv;
				}
			 
//			 	----------------By ����----------------������ > ����ã������ �� ����
			 @PostMapping("/QuestionModifyAction.bo")
				public ModelAndView qnaModifyAction(QuestionDTO question,
						ModelAndView mv, HttpServletRequest request,
						HttpServletResponse response) throws Exception {
				 
				 int result = boardService.QuestionModify(question);
				 System.out.println(result);
					// ������ ������ ���
					if(result == 0) {
						System.out.println("�Խ��� ���� ����");
						mv.setViewName("error/error");
						mv.addObject("url", request.getRequestURL());
						mv.addObject("message", "�Խ��� ���� ����");
					} else { // ���� ������ ���
						System.out.println("�Խ��� ���� �Ϸ�");
						String url = "redirect:QuestionDetailAction.bo?num=" + question.getNOTICE_NUM();
						
						// ������ �� ������ �����ֱ� ���� �� ���� ���� ���� �������� �̵��ϱ� ���� ��θ� �����մϴ�.
						mv.setViewName(url);
					}
					return mv;
				}
			 
}
