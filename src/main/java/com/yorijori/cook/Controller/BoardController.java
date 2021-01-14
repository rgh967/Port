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

//	----------------By 태훈----------------고객지원 > 공지사항페이지
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
//	----------------By 태훈----------------고객지원 > 자주찾는질문페이지
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
	
// 	----------------By 태훈----------------고객지원 > 1대1문의페이지 
	@RequestMapping(value = "/NoticeOno.bo")
	public String boardOno() {
		return "notice/Customer_Ono";
	}
			
// 	----------------By 태훈----------------고객지원 > 1대1문의 이메일 처리
	@ResponseBody
    @RequestMapping( value = "/Onomail.bo" , method=RequestMethod.POST )
    public ModelAndView mailSending(HttpServletRequest request,
    		HttpServletResponse response, String MEMBER_EMAIL, ModelAndView mv) 
    				throws IOException {

    	mv = boardService.mailSend(request, response, MEMBER_EMAIL , mv);
    	System.out.println("mv : "+mv);
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out_email = response.getWriter();
        out_email.println("<script>alert('이메일이 발송되었습니다. 확인후 답장 바랍니다.');</script>");
        out_email.flush();
       
        return mv;
        
    }
	
	
	
// 	----------------By 태훈----------------고객지원 > 공지사항 글쓰기
		@GetMapping(value = "/NoticeWrite.bo")
		public String board_write() {
			return "notice/qna_notice_write";
		}
//	 	----------------By 태훈----------------고객지원 > 공지사항 글 추가
		@PostMapping("/NoticeAddAction.bo")
		public String bbs_write_ok(NoticeDTO notice, HttpServletRequest request)
				throws Exception{
		
			boardService.insertBoard(notice);
			
			return "redirect:NoticeList.bo";
		}
		
//	 	----------------By 태훈----------------고객지원 > 공지사항 글 상세보기
		 @GetMapping("/BoardDetailAction.bo")
		 public ModelAndView Detail(int num, ModelAndView mv,
				 HttpServletRequest request) {
			 NoticeDTO notice = boardService.getDetail(num);
			 if(notice == null) {
				 System.out.println("상세보기 실패");
				 mv.setViewName("error/error");
				 mv.addObject("url",request.getRequestURL());
				 mv.addObject("message", "상세보기 실패 입니다.");
		 }else {
			 System.out.println("상세보기 성공");
			 mv.setViewName("notice/qna_notice_view");
			 mv.addObject("boarddata", notice);
		 }
		 return mv;
		 }
	
//		 	----------------By 태훈----------------고객지원 > 자주찾는질문 글쓰기
			@GetMapping(value = "/QuestionWrite.bo")
			public String Question_write() {
				return "notice/qna_write";
			}
			
//		 	----------------By 태훈----------------고객지원 > 자주찾는질문 글 추가
			@PostMapping("/QuestionAddAction.bo")
			public String qna_write_ok(QuestionDTO question, HttpServletRequest request)
					throws Exception{
			
				boardService.insertBoard(question);
				
				return "redirect:NoticeQuestion.bo";
			}
			
//		 	----------------By 태훈----------------고객지원 > 자주찾는질문 글 상세보기
			 @GetMapping("/QuestionDetailAction.bo")
			 public ModelAndView QuestionDetail(int num, ModelAndView mv,
					 HttpServletRequest request) {
				 QuestionDTO question = boardService.getQuestionDetail(num);
				 if(question == null) {
					 System.out.println("상세보기 실패");
					 mv.setViewName("error/error");
					 mv.addObject("url",request.getRequestURL());
					 mv.addObject("message", "상세보기 실패 입니다.");
			 }else {
				 System.out.println("상세보기 성공");
				 mv.setViewName("notice/qna_view");
				 mv.addObject("boarddata", question);
			 }
			 return mv;
			 }
//			 	----------------By 태훈----------------고객지원 > 공지사항 글 삭제
			 @GetMapping("/noticeDelete.bo")
			 public String delete(@RequestParam("num")int num) {
			 	boardService.deleteBoard(num);
			 	return "redirect: main.net";
			 }
//			 	----------------By 태훈----------------고객지원 > 공지사항 글 수정페이지 이동
			 @GetMapping("/noticeModifyView.bo")
				public ModelAndView NoticeModifyView(int num, ModelAndView mv,
													HttpServletRequest request) {
					NoticeDTO boarddata = boardService.getDetail(num);
					// 글 내용 불러오기 실패한 경우
					if(boarddata == null) {
						System.out.println("(수정)상세보기 실패");
						mv.setViewName("error/error");
						mv.addObject("url", request.getRequestURL());
						mv.addObject("message", "(수정)상세보기 실패입니다.");
						return mv;
					}
					System.out.println("(수정)상세보기 성공");
					
					// 수정 폼 페이지로 이동할 때 원문 글 내용을 보여주기 때문에 board 객체를 ModelAndView 객체에 저장
					mv.addObject("boarddata", boarddata);
					
					// 글 수정 폼 페이지로 이동하기 위해 경로를 설정합니다.
					mv.setViewName("notice/qna_notice_update");
					return mv;
				}
			 
//			 	----------------By 태훈----------------고객지원 > 공지사항 글 수정
			 @PostMapping("/BoardModifyAction.bo")
				public ModelAndView BoardModifyAction(NoticeDTO notice,
						ModelAndView mv, HttpServletRequest request,
						HttpServletResponse response) throws Exception {
				 
				 int result = boardService.boardModify(notice);
				 System.out.println(result);
					// 수정에 실패한 경우
					if(result == 0) {
						System.out.println("게시판 수정 실패");
						mv.setViewName("error/error");
						mv.addObject("url", request.getRequestURL());
						mv.addObject("message", "게시판 수정 실패");
					} else { // 수정 성공의 경우
						System.out.println("게시판 수정 완료");
						String url = "redirect:BoardDetailAction.bo?num=" + notice.getNOTICE_NUM();
						
						// 수정한 글 내용을 보여주기 위해 글 내용 보기 보기 페이지로 이동하기 위해 경로를 설정합니다.
						mv.setViewName(url);
					}
					return mv;
				}
//			 	----------------By 태훈----------------고객지원 > 자주찾는질문 글 삭제
			 @GetMapping("/QuestionDelete.bo")
			 public String qnadelete(@RequestParam("num")int num) {
			 	boardService.deleteQuestion(num);
			 	return "redirect: main.net";
			 }
//			 	----------------By 태훈----------------고객지원 > 자주찾는질문 글 수정 페이지 이동
			 @GetMapping("/QuestionModifyView.bo")
				public ModelAndView qnaModifyView(int num, ModelAndView mv,
													HttpServletRequest request) {
				 QuestionDTO boarddata = boardService.getQuestionDetail(num);
					// 글 내용 불러오기 실패한 경우
					if(boarddata == null) {
						System.out.println("(수정)상세보기 실패");
						mv.setViewName("error/error");
						mv.addObject("url", request.getRequestURL());
						mv.addObject("message", "(수정)상세보기 실패입니다.");
						return mv;
					}
					System.out.println("(수정)상세보기 성공");
					
					// 수정 폼 페이지로 이동할 때 원문 글 내용을 보여주기 때문에 board 객체를 ModelAndView 객체에 저장
					mv.addObject("boarddata", boarddata);
					
					// 글 수정 폼 페이지로 이동하기 위해 경로를 설정합니다.
					mv.setViewName("notice/qna_Question_update");
					return mv;
				}
			 
//			 	----------------By 태훈----------------고객지원 > 자주찾는질문 글 수정
			 @PostMapping("/QuestionModifyAction.bo")
				public ModelAndView qnaModifyAction(QuestionDTO question,
						ModelAndView mv, HttpServletRequest request,
						HttpServletResponse response) throws Exception {
				 
				 int result = boardService.QuestionModify(question);
				 System.out.println(result);
					// 수정에 실패한 경우
					if(result == 0) {
						System.out.println("게시판 수정 실패");
						mv.setViewName("error/error");
						mv.addObject("url", request.getRequestURL());
						mv.addObject("message", "게시판 수정 실패");
					} else { // 수정 성공의 경우
						System.out.println("게시판 수정 완료");
						String url = "redirect:QuestionDetailAction.bo?num=" + question.getNOTICE_NUM();
						
						// 수정한 글 내용을 보여주기 위해 글 내용 보기 보기 페이지로 이동하기 위해 경로를 설정합니다.
						mv.setViewName(url);
					}
					return mv;
				}
			 
}
