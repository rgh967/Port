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
	
	// �׽�Ʈ ��
	@GetMapping("/test.net")
	public String test() {
		System.out.println("�׽�Ʈ ȭ��");
		return "class/test";
	}
	
	// �丮Ŭ���� ��� �������� �̵�
	@RequestMapping(value = "/classRegi.net", method = RequestMethod.GET)
	public String classRegi() {
		return "class/class_regi";
	}
	
	// ������ ��� ó��
	@RequestMapping(value = "/classRegiProcess.net", method = RequestMethod.POST)
	public void classRegiProcess( ClassDTO classDTO, ClassCurriculumDTO curriculum, ClassDetailDTO detail,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setContentType("text/html;charset=UTF-8");
		
		// �̹��� ������ ����� ���� ��ġ
		String saveFolder = request.getSession().getServletContext().getRealPath("resources") + "/upload/recipe/";
			
		// Ŭ���� ��� ó�� 
		int result = 0;
		result = classService.classRegiProcess(saveFolder, classDTO, curriculum, detail);

		PrintWriter out = response.getWriter();
		out.println("<script>");
		if (result == 1) {
			out.println("alert('Ŭ���� ����� �Ϸ�Ǿ����ϴ�.');");
			out.println("location.href='main.net';");
		} else {
			out.println("alert('Ŭ���� ����� �����߽��ϴ�.');");
			out.println("location.href=history.back();");
		}
		out.println("</script>");
		out.close();
		
	}	
	
	// Ŭ���� �� ȭ�� ���
	@GetMapping("/classDetailProcess.net")
	public ModelAndView boardDetail(int CLASS_ID, ModelAndView mv, HttpServletRequest request){
		Map<String, Object> map = classService.classDetailProcess(CLASS_ID);
		//System.out.println(map.get("class").toString());
		if(map.get("class") == null || map.get("classDetail") == null || map.get("classCurriculum") == null) {
			System.out.println("�󼼺��� ����");
			mv.setViewName("error/error");
			mv.addObject("url", request.getRequestURI());
			mv.addObject("message", "�󼼺��� �����Դϴ�.");
		}else {
			System.out.println("�󼼺��� ����");
			mv.setViewName("class/class_view");
			mv.addObject("classData", map.get("class"));
			mv.addObject("classWriter", map.get("classWriter"));
			mv.addObject("classDetail", map.get("classDetail"));
			mv.addObject("classCurriculum", map.get("classCurriculum"));
			mv.addObject("regiOK", map.get("regiOK"));
		}
			
		return mv;
	}
		
	// ���ϱ�
	@PostMapping("/ClassCloveProcess.net")
	public void classCLoveProcess(CloveDTO clove, HttpServletRequest request, HttpServletResponse response) throws IOException{
		int cloveResult = basktService.cloveProcess(clove);  // ���ϱⰡ �Ϸ�Ǿ����� Ȯ���ϴ� ��(���ϱ� �Ұ�: 0, ���ϱ� ����: 1, ���ϱ� ����: 2)
			
		if(cloveResult == 1) {
			System.out.println("���ϱ� ����");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('�ش� Ŭ������ ���ϱ�Ǿ����ϴ�.'); history.go(-1);</script>");
			out.close();
		}else if(cloveResult == 2){
			System.out.println("���ϱ� ����");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('���ϱ⿡ �����߽��ϴ�.'); history.go(-1);</script>");
			out.close();
		}else {  // ���ϱ� �Ұ���(�̹� ���ϱ��� ���)
			System.out.println("���ϱ� �Ұ���");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('�̹� ���� �������Դϴ�.'); history.go(-1);</script>");
			out.close();
		}
	}

	// ������û
	@PostMapping("/ClassBuyProcess.net")
	public void classBuyProcess(BuyDTO buy, HttpServletRequest request, HttpServletResponse response) throws IOException{
		int buyResult = basktService.buyProcess(buy);  // ������û�� �Ϸ�Ǿ����� Ȯ���ϴ� ��(������û �Ұ�: 0, ������û ����: 1, ������û ����: 2)
			
		if(buyResult == 1) {
			System.out.println("������û ����");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('�ش� Ŭ������ ������û�߽��ϴ�.'); history.go(-1);</script>");
			out.close();
		}else if(buyResult == 2){
			System.out.println("������û ����");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('������û �����߽��ϴ�.'); history.go(-1);</script>");
			out.close();
		}else {  // ������û �Ұ���(�̹� ������û�� ���)
			System.out.println("������û �Ұ���");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('�̹� ������û�� Ŭ�����Դϴ�.'); history.go(-1);</script>");
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
