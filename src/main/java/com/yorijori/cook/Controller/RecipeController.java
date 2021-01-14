package com.yorijori.cook.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.yorijori.cook.DTO.RecipeDTO;
import com.yorijori.cook.DTO.ScrapDTO;
import com.yorijori.cook.DTO.SubscribeDTO;
import com.yorijori.cook.service.BasketService;
import com.yorijori.cook.service.RecipeService;

@Controller
public class RecipeController {
	
	@Autowired
	private RecipeService recipeService;
	
	@Autowired
	private BasketService basktService;

	@RequestMapping(value = "/recipePaging.net")
	public ModelAndView recipePaging(@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "limit", defaultValue = "8", required = false) int limit,
			@RequestParam(value = "filter", defaultValue = "rct", required = false) String filter,
			ModelAndView mv) {
		Map<String, Object> map = recipeService.recipePagingProcess(page, limit, filter);

		mv.setViewName("recipe/recipe_list");
		mv.addObject("page", page);
		mv.addObject("maxpage", map.get("maxpage"));
		mv.addObject("startpage", map.get("startpage"));
		mv.addObject("endpage", map.get("endpage"));
		mv.addObject("limit", limit);
		mv.addObject("recipelist", map.get("recipelist"));
		return mv;
	}

	// recipe_list.js ajax ó�� �κ�
	@ResponseBody
	@RequestMapping(value = "/recipeInitList.net")
	public Map<String, Object> recipeInitList(@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "limit", defaultValue = "8", required = false) int limit,
			@RequestParam(value = "chef_id", defaultValue = "4", required = false) int CHEF_ID,
			@RequestParam(value = "nation_id", defaultValue = "4", required = false) int NATION_ID,
			@RequestParam(value = "part_id", defaultValue = "13", required = false) int PART_ID,
			@RequestParam(value = "filter", defaultValue = "rct", required = false) String filter) {

		// rct = ���� , rnk = �ֽ�(������ ���̵�), rlv = ���
		System.out.println("filter= " + filter);
		
		Map<String, Object> map = recipeService.recipeInitListProcess(page, limit, CHEF_ID, NATION_ID, PART_ID, filter);
		return map;
	}

	// ������ ��� �������� �̵�
	@RequestMapping(value = "/recipeRegi.net", method = RequestMethod.GET)
	public String recipeRegi() {
		return "recipe/recipe_regi";
	}
		
	// ������ ��� ó��
	@RequestMapping(value = "/recipeRegiProcess.net", method = RequestMethod.POST)
	public void recipeRegiProcess(String MEMBER_ID, RecipeDTO recipe, String[] CHEF_ID, String[] NATION_ID, String[] PART_ID, String[] ING_NAME,
			String[] ING_AMOUNT, String[] STEP_NUM, String[] STEP_CONTENT, MultipartFile[] STEP_IMAGE,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("-- RecipeController Class - regiProcess()");
		response.setContentType("text/html;charset=UTF-8");

		// �̹��� ������ ����� ���� ��ġ
		String saveFolder = request.getSession().getServletContext().getRealPath("resources") + "/upload/recipe/";
			
		// ������ ��� ó�� 
		int result = 0;
		result = recipeService.recipeRegiProcess(saveFolder, recipe, CHEF_ID, NATION_ID, PART_ID, 
													ING_NAME, ING_AMOUNT, STEP_NUM,  STEP_CONTENT, STEP_IMAGE);

		PrintWriter out = response.getWriter();
		out.println("<script>");
		if (result == 1) {
			out.println("alert('������ ����� �Ϸ�Ǿ����ϴ�.');");
			out.println("location.href='main.net';");
		} else {
			out.println("alert('������ ����� �����߽��ϴ�.');");
			out.println("location.href=history.back();");
		}
		out.println("</script>");
		out.close();
	}

	@ResponseBody
	@RequestMapping(value = "/recipeChkList.net", method = RequestMethod.POST)
	public Map<String, Object> recipeChkList(@RequestParam(value = "chbox[]", required = false) List<String> checkbox,
			@RequestParam(value = "filter", defaultValue = "rct", required = false) String filter,
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "limit", defaultValue = "8", required = false) int limit) {
		if(checkbox != null) {
			Map<String, Object> map = recipeService.recipeChkListProcess(checkbox, filter, page, limit);
			return map;
		}
		return null;
	}

	// ������ �� ȭ�� ���
	@GetMapping("/recipeDetailProcess.net")
	public ModelAndView recipeDetailProcess(int RECIPE_ID, ModelAndView mv, HttpServletRequest request){
		System.out.println(RECIPE_ID+"kkkk");
		Map<String, Object> map = recipeService.recipeDetailProcess(RECIPE_ID);
		if(map.get("recipe") == null || map.get("recipeStep") == null || map.get("recipeIng") == null || map.get("readCount") == null) {
			System.out.println("�󼼺��� ����");
			mv.setViewName("error/error");
			mv.addObject("url", request.getRequestURI());
			mv.addObject("message", "�󼼺��� �����Դϴ�.");
		}else {
			System.out.println("�󼼺��� ����");
			mv.setViewName("recipe/recipe_view");
			mv.addObject("recipe", map.get("recipe"));
			mv.addObject("recipeWriter", map.get("recipeWriter"));
			mv.addObject("recipeStep", map.get("recipeStep"));
			mv.addObject("recipeIng", map.get("recipeIng"));
		}
		
		return mv;
	}
	
	// ��ũ��
	@PostMapping("/recipeScrapProcess.net")
	public void recipeScrapProcess(ScrapDTO scrap, HttpServletRequest request, HttpServletResponse response) throws IOException{
		int scrapResult = basktService.scrapProcess(scrap);  // ��ũ���� �Ϸ�Ǿ����� Ȯ���ϴ� ��(��ũ�� �Ұ�: 0, ��ũ�� ����: 1, ��ũ�� ����: 2)
		
		if(scrapResult == 1) {
			System.out.println("��ũ�� ����");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('�ش� �����ǰ� ��ũ���Ǿ����ϴ�.'); history.go(-1);</script>");
			out.close();
		}else if(scrapResult == 2){
			System.out.println("��ũ�� ����");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('��ũ���� �����߽��ϴ�.'); history.go(-1);</script>");
			out.close();
		}else {  // ��ũ�� �Ұ���(�̹� ��ũ���� ���)
			System.out.println("��ũ�� �Ұ���");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('�̹� ��ũ���� �������Դϴ�.'); history.go(-1);</script>");
			out.close();
		}
	}

	// ����
	@PostMapping("/memberSubscribeProcess.net")
	public void memberSubscribeProcess(SubscribeDTO subscribe, HttpServletRequest request, HttpServletResponse response) throws IOException{
		int subscribeResult = basktService.subscribeProcess(subscribe);  // ������ �Ϸ�Ǿ����� Ȯ���ϴ� ��(���� �Ұ�: 0, ���� ����: 1, ���� ����: 2)
		
		if(subscribeResult == 1) {
			System.out.println("���� ����");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('�ش� �ۼ��ڸ� �����մϴ�.'); history.go(-1);</script>");
			out.close();
		}else if(subscribeResult == 2){
			System.out.println("���� ����");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('���� �����߽��ϴ�.'); history.go(-1);</script>");
			out.close();
		}else {  // ���� �Ұ���(�̹� ������ ���)
			System.out.println("���� �Ұ���");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('�̹� ������ �ۼ����Դϴ�.'); history.go(-1);</script>");
			out.close();
		}
	}
	
}
