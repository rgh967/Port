package com.yorijori.cook.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.yorijori.cook.service.RecipeService;
import com.yorijori.cook.service.SearchService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private RecipeService recipeService;
	
	@Autowired
	private SearchService searchService;
	
	// 메인 페이지로 이동
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(ModelAndView mv) {
		int recipeCount = recipeService.mainProcess();
		mv.setViewName("home/main");
		mv.addObject("recipeCount", recipeCount);
		return mv;
	}
		
	@RequestMapping(value = "/main.net", method = RequestMethod.GET)
	public ModelAndView main(ModelAndView mv) {
		int recipeCount = recipeService.mainProcess();
		mv.setViewName("home/main");
		mv.addObject("recipeCount", recipeCount);
		return mv;
	}
	
	// 검색 엔진
	@RequestMapping(value = "/search.net")
	public ModelAndView search(String srhType, String srhText, ModelAndView mv) {
		Map<String, Object> map = searchService.searchProcess(srhType, srhText);
		
		mv.setViewName("home/search_list");
		mv.addObject("recipeCount", map.get("recipeCount"));
		mv.addObject("classCount", map.get("classCount"));
		mv.addObject("recipeList", map.get("recipeList"));
		mv.addObject("classList", map.get("classList"));

		return mv;
	}
	
}
