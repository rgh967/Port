package com.yorijori.cook.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yorijori.cook.DTO.BuyDTO;
import com.yorijori.cook.DTO.ClassCurriculumDTO;
import com.yorijori.cook.DTO.ClassDTO;
import com.yorijori.cook.DTO.ClassDetailDTO;
import com.yorijori.cook.DTO.CloveDTO;
import com.yorijori.cook.service.BasketService;
import com.yorijori.cook.service.ClassService;

@Controller
public class ClassController {
	
	@Autowired
	private ClassService classService;
	
	@Autowired
	private BasketService basktService;
	
	// 테스트 용
	@GetMapping("/test.net")
	public String test() {
		System.out.println("테스트 화면");
		return "class/test";
	}
	
	// 요리클래스 등록 페이지로 이동
	@RequestMapping(value = "/classRegi.net", method = RequestMethod.GET)
	public String classRegi() {
		return "class/class_regi";
	}
	
	// 레시피 등록 처리
	@RequestMapping(value = "/classRegiProcess.net", method = RequestMethod.POST)
	public void classRegiProcess( ClassDTO classDTO, ClassCurriculumDTO curriculum, ClassDetailDTO detail,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setContentType("text/html;charset=UTF-8");
		
		// 이미지 파일이 저장될 폴더 위치
		String saveFolder = request.getSession().getServletContext().getRealPath("resources") + "/upload/recipe/";
			
		// 클래스 등록 처리 
		int result = 0;
		result = classService.classRegiProcess(saveFolder, classDTO, curriculum, detail);

		PrintWriter out = response.getWriter();
		out.println("<script>");
		if (result == 1) {
			out.println("alert('클래스 등록이 완료되었습니다.');");
			out.println("location.href='main.net';");
		} else {
			out.println("alert('클래스 등록을 실패했습니다.');");
			out.println("location.href=history.back();");
		}
		out.println("</script>");
		out.close();
		
	}	
	
	// 클래스 상세 화면 출력
	@GetMapping("/classDetailProcess.net")
	public ModelAndView boardDetail(int CLASS_ID, ModelAndView mv, HttpServletRequest request){
		Map<String, Object> map = classService.classDetailProcess(CLASS_ID);
		//System.out.println(map.get("class").toString());
		if(map.get("class") == null || map.get("classDetail") == null || map.get("classCurriculum") == null) {
			System.out.println("상세보기 실패");
			mv.setViewName("error/error");
			mv.addObject("url", request.getRequestURI());
			mv.addObject("message", "상세보기 실패입니다.");
		}else {
			System.out.println("상세보기 성공");
			mv.setViewName("class/class_view");
			mv.addObject("classData", map.get("class"));
			mv.addObject("classWriter", map.get("classWriter"));
			mv.addObject("classDetail", map.get("classDetail"));
			mv.addObject("classCurriculum", map.get("classCurriculum"));
			mv.addObject("regiOK", map.get("regiOK"));
		}
			
		return mv;
	}
		
	// 찜하기
	@PostMapping("/ClassCloveProcess.net")
	public void classCLoveProcess(CloveDTO clove, HttpServletRequest request, HttpServletResponse response) throws IOException{
		int cloveResult = basktService.cloveProcess(clove);  // 찜하기가 완료되었는지 확인하는 값(찜하기 불가: 0, 찜하기 성공: 1, 찜하기 실패: 2)
			
		if(cloveResult == 1) {
			System.out.println("찜하기 성공");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('해당 클래스가 찜하기되었습니다.'); history.go(-1);</script>");
			out.close();
		}else if(cloveResult == 2){
			System.out.println("찜하기 실패");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('찜하기에 실패했습니다.'); history.go(-1);</script>");
			out.close();
		}else {  // 찜하기 불가능(이미 찜하기한 경우)
			System.out.println("찜하기 불가능");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('이미 찜한 레시피입니다.'); history.go(-1);</script>");
			out.close();
		}
	}

	// 수강신청
	@PostMapping("/ClassBuyProcess.net")
	public void classBuyProcess(BuyDTO buy, HttpServletRequest request, HttpServletResponse response) throws IOException{
		int buyResult = basktService.buyProcess(buy);  // 수강신청이 완료되었는지 확인하는 값(수강신청 불가: 0, 수강신청 성공: 1, 수강신청 실패: 2)
			
		if(buyResult == 1) {
			System.out.println("수강신청 성공");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('해당 클래스에 수강신청했습니다.'); history.go(-1);</script>");
			out.close();
		}else if(buyResult == 2){
			System.out.println("수강신청 실패");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('수강신청 실패했습니다.'); history.go(-1);</script>");
			out.close();
		}else {  // 수강신청 불가능(이미 수강신청한 경우)
			System.out.println("수강신청 불가능");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('이미 수강신청한 클래스입니다.'); history.go(-1);</script>");
			out.close();
		}
	}

	@RequestMapping(value = "/classPaging.net")
	public ModelAndView classPaging(@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "limit", defaultValue = "8", required = false) int limit,
			ModelAndView mv) {
		
		Map<String, Object> map = classService.classPagingProcess(page, limit);
		mv.setViewName("class/class_list");
		mv.addObject("page", page);
		mv.addObject("maxpage", map.get("maxpage"));
		mv.addObject("startpage", map.get("startpage"));
		mv.addObject("endpage", map.get("endpage"));
		mv.addObject("listcount", map.get("listcount"));
		mv.addObject("limit", limit);
		mv.addObject("classlist", map.get("classlist"));
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/classInitList.net")
	public Map<String, Object> classInitList(
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "limit", defaultValue = "8", required = false) int limit) {

		Map<String, Object> map = classService.classInitListProcess(page, limit);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/classChkList.net", method = RequestMethod.POST)
	public Map<String, Object> classChkList(@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "limit", defaultValue = "8", required = false) int limit,
			@RequestParam(value = "from", required=false) String from,
			@RequestParam(value = "to", required=false) String to,
			@RequestParam(value = "class_value1", required=false) String amount1,
			@RequestParam(value = "class_value2", required=false) String amount2) {

		Map<String, Object> map = classService.classChkListProcess(page, limit, from, to, amount1, amount2);
		return map;
		
	}
		
}
