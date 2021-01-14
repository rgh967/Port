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

	// recipe_list.js ajax 처리 부분
	@ResponseBody
	@RequestMapping(value = "/recipeInitList.net")
	public Map<String, Object> recipeInitList(@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "limit", defaultValue = "8", required = false) int limit,
			@RequestParam(value = "chef_id", defaultValue = "4", required = false) int CHEF_ID,
			@RequestParam(value = "nation_id", defaultValue = "4", required = false) int NATION_ID,
			@RequestParam(value = "part_id", defaultValue = "13", required = false) int PART_ID,
			@RequestParam(value = "filter", defaultValue = "rct", required = false) String filter) {

		// rct = 별점 , rnk = 최신(레시피 아이디), rlv = 등급
		System.out.println("filter= " + filter);
		
		Map<String, Object> map = recipeService.recipeInitListProcess(page, limit, CHEF_ID, NATION_ID, PART_ID, filter);
		return map;
	}

	// 레시피 등록 페이지로 이동
	@RequestMapping(value = "/recipeRegi.net", method = RequestMethod.GET)
	public String recipeRegi() {
		return "recipe/recipe_regi";
	}
		
	// 레시피 등록 처리
	@RequestMapping(value = "/recipeRegiProcess.net", method = RequestMethod.POST)
	public void recipeRegiProcess(String MEMBER_ID, RecipeDTO recipe, String[] CHEF_ID, String[] NATION_ID, String[] PART_ID, String[] ING_NAME,
			String[] ING_AMOUNT, String[] STEP_NUM, String[] STEP_CONTENT, MultipartFile[] STEP_IMAGE,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("-- RecipeController Class - regiProcess()");
		response.setContentType("text/html;charset=UTF-8");

		// 이미지 파일이 저장될 폴더 위치
		String saveFolder = request.getSession().getServletContext().getRealPath("resources") + "/upload/recipe/";
			
		// 레시피 등록 처리 
		int result = 0;
		result = recipeService.recipeRegiProcess(saveFolder, recipe, CHEF_ID, NATION_ID, PART_ID, 
													ING_NAME, ING_AMOUNT, STEP_NUM,  STEP_CONTENT, STEP_IMAGE);

		PrintWriter out = response.getWriter();
		out.println("<script>");
		if (result == 1) {
			out.println("alert('레시피 등록이 완료되었습니다.');");
			out.println("location.href='main.net';");
		} else {
			out.println("alert('레시피 등록을 실패했습니다.');");
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

	// 레시피 상세 화면 출력
	@GetMapping("/recipeDetailProcess.net")
	public ModelAndView recipeDetailProcess(int RECIPE_ID, ModelAndView mv, HttpServletRequest request){
		System.out.println(RECIPE_ID+"kkkk");
		Map<String, Object> map = recipeService.recipeDetailProcess(RECIPE_ID);
		if(map.get("recipe") == null || map.get("recipeStep") == null || map.get("recipeIng") == null || map.get("readCount") == null) {
			System.out.println("상세보기 실패");
			mv.setViewName("error/error");
			mv.addObject("url", request.getRequestURI());
			mv.addObject("message", "상세보기 실패입니다.");
		}else {
			System.out.println("상세보기 성공");
			mv.setViewName("recipe/recipe_view");
			mv.addObject("recipe", map.get("recipe"));
			mv.addObject("recipeWriter", map.get("recipeWriter"));
			mv.addObject("recipeStep", map.get("recipeStep"));
			mv.addObject("recipeIng", map.get("recipeIng"));
		}
		
		return mv;
	}
	
	// 스크랩
	@PostMapping("/recipeScrapProcess.net")
	public void recipeScrapProcess(ScrapDTO scrap, HttpServletRequest request, HttpServletResponse response) throws IOException{
		int scrapResult = basktService.scrapProcess(scrap);  // 스크랩이 완료되었는지 확인하는 값(스크랩 불가: 0, 스크랩 성공: 1, 스크랩 실패: 2)
		
		if(scrapResult == 1) {
			System.out.println("스크랩 성공");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('해당 레시피가 스크랩되었습니다.'); history.go(-1);</script>");
			out.close();
		}else if(scrapResult == 2){
			System.out.println("스크랩 실패");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('스크랩에 실패했습니다.'); history.go(-1);</script>");
			out.close();
		}else {  // 스크랩 불가능(이미 스크랩한 경우)
			System.out.println("스크랩 불가능");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('이미 스크랩된 레시피입니다.'); history.go(-1);</script>");
			out.close();
		}
	}

	// 구독
	@PostMapping("/memberSubscribeProcess.net")
	public void memberSubscribeProcess(SubscribeDTO subscribe, HttpServletRequest request, HttpServletResponse response) throws IOException{
		int subscribeResult = basktService.subscribeProcess(subscribe);  // 구독이 완료되었는지 확인하는 값(구독 불가: 0, 구독 성공: 1, 구독 실패: 2)
		
		if(subscribeResult == 1) {
			System.out.println("구독 성공");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('해당 작성자를 구독합니다.'); history.go(-1);</script>");
			out.close();
		}else if(subscribeResult == 2){
			System.out.println("구독 실패");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('구독 실패했습니다.'); history.go(-1);</script>");
			out.close();
		}else {  // 구독 불가능(이미 구독한 경우)
			System.out.println("구독 불가능");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('이미 구독한 작성자입니다.'); history.go(-1);</script>");
			out.close();
		}
	}
	
}
