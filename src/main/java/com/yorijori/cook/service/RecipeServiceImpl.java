package com.yorijori.cook.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.yorijori.cook.DTO.CategoryChefDTO;
import com.yorijori.cook.DTO.CategoryNationDTO;
import com.yorijori.cook.DTO.CategoryPartDTO;
import com.yorijori.cook.DTO.PagingDTO;
import com.yorijori.cook.DTO.RecipeDTO;
import com.yorijori.cook.DTO.RecipeCtgChefDTO;
import com.yorijori.cook.DTO.RecipeCtgNationDTO;
import com.yorijori.cook.DTO.RecipeCtgPartDTO;
import com.yorijori.cook.DTO.RecipeIngDTO;
import com.yorijori.cook.DTO.RecipeReviewDTO;
import com.yorijori.cook.DTO.RecipeStepDTO;
import com.yorijori.cook.recipeDAO.ChefDAO;
import com.yorijori.cook.recipeDAO.MemberDAO;
import com.yorijori.cook.recipeDAO.NationDAO;
import com.yorijori.cook.recipeDAO.PartDAO;
import com.yorijori.cook.recipeDAO.RecipeCtgChefDAO;
import com.yorijori.cook.recipeDAO.RecipeCtgNationDAO;
import com.yorijori.cook.recipeDAO.RecipeCtgPartDAO;
import com.yorijori.cook.recipeDAO.RecipeDAO;
import com.yorijori.cook.recipeDAO.RecipeIngDAO;
import com.yorijori.cook.recipeDAO.RecipeReviewDAO;
import com.yorijori.cook.recipeDAO.RecipeStepDAO;

@Service
@Transactional
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	private RecipeDAO recipeDao;
	
	@Autowired
	private MemberDAO memberDao;
	
	@Autowired
	private ChefDAO chefDao;
	
	@Autowired
	private NationDAO nationDao;
	
	@Autowired
	private PartDAO partDao;
	
	@Autowired
	private RecipeIngDAO recipeIngDao;
	
	@Autowired
	private RecipeStepDAO recipeStepDao;
	
	@Autowired
	private RecipeReviewDAO recipeReviewDao;
	
	@Autowired
	private RecipeCtgChefDAO recipeCtgChefDao;
	
	@Autowired
	private RecipeCtgNationDAO recipeCtgNationDao;
	
	@Autowired
	private RecipeCtgPartDAO recipeCtgPartDao;

	
	// 레시피 테이블에 등록 한 총 갯수 불러올 메서드
	@Override
	public int mainProcess() {
		return recipeDao.selectRecipeCount();
	}

	@Override
	@Transactional
	public int recipeRegiProcess(String saveFolder, RecipeDTO recipe, 
			String[] CHEF_ID, String[] NATION_ID, String[] PART_ID, 
			String[] ING_NAME, String[] ING_AMOUNT,  
			String[] STEP_NUM,  String[] STEP_CONTENT, MultipartFile[] STEP_IMAGE) {
		
		int result = 0;
		
		int recipe_id = recipeDao.selectRecipeId();
		
		// 요리 대표 이미지 처리
		MultipartFile uploadFile = recipe.getUploadFile();
		if (!uploadFile.isEmpty()) {
			String fileName = uploadFile.getOriginalFilename(); // 원래 파일명
			recipe.setOriginalFile(fileName); // 원래 파일명 저장

			String fileDBName = fileProcess("rep_food", fileName, saveFolder, recipe_id);

			// transferTo(File path) : 업로드한 파일을 매개변수의 경로에 저장합니다.
			try {
				uploadFile.transferTo(new File(saveFolder + fileDBName));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			recipe.setRECIPE_MAIN_IMG(fileDBName);
		}
		
		// 레시피 등록
		recipe.setRECIPE_ID(recipe_id);
		result = recipeDao.insertRecipe(recipe);
		
		if(result == 0) return result;
		
		// 카테고리 - chef
		if(CHEF_ID != null) {
			for (int i = 0; i < CHEF_ID.length; i++) {
				RecipeCtgChefDTO chef = new RecipeCtgChefDTO();
				chef.setRECIPE_ID(recipe_id);
				chef.setCHEF_ID(Integer.parseInt(CHEF_ID[i]));
				result = chefDao.insertChefList(chef);		// INSERT
				
				if(result == 0) return result;
			}
		}
		
		// 카테고리 - nation
		if(NATION_ID != null) {
			for (int i = 0; i < NATION_ID.length; i++) {
				RecipeCtgNationDTO nation = new RecipeCtgNationDTO();
				nation.setRECIPE_ID(recipe_id);
				nation.setNATION_ID((Integer.parseInt(NATION_ID[i]) - 1) / 10);
				result = nationDao.insertNation(nation);		// INSERT
				
				if(result == 0) return result;
			}
		}
		

		// 카테고리 - part
		if(PART_ID != null) {
			for (int i = 0; i < PART_ID.length; i++) {
				RecipeCtgPartDTO part = new RecipeCtgPartDTO();
				part.setRECIPE_ID(recipe_id);
				part.setPART_ID((Integer.parseInt(PART_ID[i]) - 2) / 10);
				result = partDao.insertPart(part);		// INSERT
				
				if(result == 0) return result;
			}
		}
		
		// 재료 등록
		for (int i = 0; i < ING_NAME.length - 1; i++) {
			if (!ING_NAME[i].isEmpty() && !ING_NAME[i].isEmpty()) {
				RecipeIngDTO ing = new RecipeIngDTO();
				ing.setRECIPE_ID(recipe_id);
				ing.setING_NAME(ING_NAME[i]);
				ing.setING_AMOUNT(ING_AMOUNT[i]);
				result = recipeIngDao.insertIng(ing);
				
				if(result == 0) return result;
			}
		}
		
		// 스텝 등록 
		
		// 요리 순서 이미지 파일 처리 
		String[] STEP_IMAGE_DB = new String[STEP_NUM.length];
		for (int i = 0; i < STEP_NUM.length - 1; i++) {
			MultipartFile step_image = STEP_IMAGE[i];
			if (!step_image.isEmpty()) {
				String fileName = step_image.getOriginalFilename(); // 원래 파일명

				String storeName = "step" + STEP_NUM[i];
				String fileDBName = fileProcess(storeName, fileName, saveFolder, recipe_id);

				// transferTo(File path) : 업로드한 파일을 매개변수의 경로에 저장합니다.
				try {
					step_image.transferTo(new File(saveFolder + fileDBName));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}

				STEP_IMAGE_DB[i] = fileDBName;
			} else {
				STEP_IMAGE_DB[i] = "";
			}
		}
		
		for (int i = 0; i < STEP_NUM.length - 1; i++) {
			RecipeStepDTO step = new RecipeStepDTO();
			step.setRECIPE_ID(recipe_id);
			step.setSTEP_NUM(Integer.parseInt(STEP_NUM[i]));
			step.setSTEP_CONTENT(STEP_CONTENT[i]);
			step.setSTEP_IMAGE(STEP_IMAGE_DB[i]);
			result = recipeStepDao.insertStep(step);
			
			if(result == 0) return result;
		}
		return result;
	}
	
	// 이미지 파일 처리
	private String fileProcess(String storeName, String fileName, String saveFolder, int recipe_id) {
		// 새로운 폴더 이름 설정
		String homedir = saveFolder + "regi_" + recipe_id;
		System.out.println("--- homedir : " + homedir);

		File mainPath = new File(homedir);
		if (!(mainPath.exists())) {
			mainPath.mkdir();// 새로운 폴더를 생성
		}

		// 확장자 구하기
		int index = fileName.lastIndexOf(".");
		String fileExtension = fileName.substring(index + 1);

		// 새로운 파일명을 저장
		String refileName = storeName + "." + fileExtension;
		System.out.println("--- refileName = " + refileName);

		// 오라클 DB에 저장될 파일명 =>'regi_1/rep_food.jpg'
		String fileDBName = "regi_" + recipe_id + "/" + refileName;
		System.out.println("--- fileDBName = " + fileDBName);

		return fileDBName;
	}

	@Override
	public Map<String, Object> recipeDetailProcess(int RECIPE_ID) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		RecipeDTO recipe = recipeDao.selectRecipe(RECIPE_ID);  // 상세정보 가져오기
		System.out.println(recipe.getMEMBER_ID());
		String recipeWriter = memberDao.selectMemberName(recipe.getMEMBER_ID());  // 레시피 작성자 이름 가져오기
		List<RecipeStepDTO> recipeStep = recipeDao.selectRecipeStepList(RECIPE_ID);  // 레시피 step 가져오기
		List<RecipeIngDTO> recipeIng = recipeDao.selectRecipeIngList(RECIPE_ID);  // 레시피 재료 가져오기
		
		map.put("recipe", recipe);
		map.put("recipeWriter", recipeWriter);
		map.put("recipeStep", recipeStep);
		map.put("recipeIng", recipeIng);
		
		// 상세정보 조회 성공 시, 조회수 갱신
		int readCount = -1;
		if(map.get("recipe") != null && map.get("recipeStep") != null && map.get("recipeIng") != null) {
			readCount = recipeDao.updateRecipeReadCount(RECIPE_ID);
		}
		if(readCount == -1) {
			map.put("readCount", null);
		}else {
			map.put("readCount", readCount);
		}
		
		return map;
	}
	
	// 체크박스 선택 시 변경 레시피 리스트
	@Override
	public Map<String, Object> recipeChkListProcess(List<String> checkbox, String filter, int page, int limit) {
		List<Object> recipe_id = new ArrayList<Object>();
		List<RecipeDTO> recipelist = new ArrayList<RecipeDTO>();
//		String pattern_chef = "^[0-9]$";
//		String pattern_nation = "^.1$";
//		String pattern_part = "^.2$";
		int listcount = 0;
		for (int i = 0; i < checkbox.size(); i++) {
			if (checkbox.get(i).equals("11") || checkbox.get(i).equals("21") || checkbox.get(i).equals("31") || checkbox.get(i).equals("41")) {
				recipe_id = recipeCtgNationDao.selectRecipeCtgId((Integer.parseInt(checkbox.get(i)) - 1) / 10);
			} else if (checkbox.get(i).equals("12") || checkbox.get(i).equals("22") || checkbox.get(i).equals("32") || checkbox.get(i).equals("42") ||
					checkbox.get(i).equals("52") || checkbox.get(i).equals("62") || checkbox.get(i).equals("72") || checkbox.get(i).equals("82") ||
					checkbox.get(i).equals("92") || checkbox.get(i).equals("102") || checkbox.get(i).equals("112") || checkbox.get(i).equals("122") ||
					checkbox.get(i).equals("132")) {
				recipe_id = recipeCtgPartDao.selectRecipeCtgId((Integer.parseInt(checkbox.get(i)) - 2) / 10);
			} else if (checkbox.get(i).equals("1") || checkbox.get(i).equals("2") || checkbox.get(i).equals("3") || checkbox.get(i).equals("4")) {
				recipe_id = recipeCtgChefDao.selectRecipeCtgId(Integer.parseInt(checkbox.get(i)));
			}
			for (int j = 0; j < recipe_id.size(); j++) {
				RecipeDTO r = recipeDao.selectRecipeMId(recipe_id.get(j));
				if (recipelist.isEmpty()) {
					listcount++;
					recipelist.add(r);
				} else {
					Boolean check = false;
					for(int k = 0; k < recipelist.size(); k++) {
						if(recipelist.get(k).getRECIPE_ID() != r.getRECIPE_ID()) {
							check = true;
						}else {
							check = false;
							break;
						}
					}
					if(check) {
						listcount++;
						recipelist.add(r);
					}
				}
			}
		}
		
		List<RecipeReviewDTO> reviewlist = recipeReviewDao.selectReviewStar();
		starProcess(reviewlist);
		
		if (filter.equals("rct")) {
			for(int i = 0; i < recipelist.size(); i++) {
				for(int j = 0; j < reviewlist.size(); j++) {
					if(recipelist.get(i).getRECIPE_ID() == reviewlist.get(j).getRECIPE_ID() && reviewlist.get(j).getREVIEW_STAR() != 0) {
						recipelist.get(i).setRecipeReview(reviewlist.get(j));
					}
				}
			}
			// 람다식 이용해서 recipelist 정렬(별점순)
			Collections.sort(recipelist, (r1, r2) -> r2.getRecipeReview().compareTo(r1.getRecipeReview()));
		} else if (filter.equals("rnk")) {
			Collections.sort(recipelist, (r1, r2) -> r2.getRECIPE_ID() - r1.getRECIPE_ID());
		} else if (filter.equals("rlv")) {
			Collections.sort(recipelist, (r1, r2) -> r2.getMember().compareTo(r1.getMember()));
		}
		
		int maxpage = (listcount + limit - 1) / limit;
		int startpage = ((page - 1) / 10) * 10 + 1;
		int endpage = startpage + 10 - 1;
		
		if (endpage > maxpage)
			endpage = maxpage;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", page);
		map.put("maxpage", maxpage);
		map.put("startpage", startpage);
		map.put("endpage", endpage);
		map.put("listcount", listcount);
		map.put("recipelist", recipelist);
		map.put("reviewlist", reviewlist);
		return map;
	}

	@Override
	public Map<String, Object> recipePagingProcess(int page, int limit, String filter) {
		int listcount = recipeDao.selectRecipeCount();
		PagingDTO<RecipeDTO> pagingVO = new PagingDTO<RecipeDTO>(page, limit, listcount);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", pagingVO.getStartrow());
		map.put("end", pagingVO.getEndrow());
		
		List<RecipeReviewDTO> reviewlist = recipeReviewDao.selectReviewStar();
		starProcess(reviewlist);
		
		List<RecipeDTO> recipelist = new ArrayList<RecipeDTO>();
		if (filter.equals("rct")) {
			recipelist = recipeDao.selectRecipeList(map);
			for(int i = 0; i < recipelist.size(); i++) {
				for(int j = 0; j < reviewlist.size(); j++) {
					if(recipelist.get(i).getRECIPE_ID() == reviewlist.get(j).getRECIPE_ID() && reviewlist.get(j).getREVIEW_STAR() != 0) {
						recipelist.get(i).setRecipeReview(reviewlist.get(j));
					}
				}
			}
			// 람다식 이용해서 recipelist 정렬(별점순)
			Collections.sort(recipelist, (r1, r2) -> r2.getRecipeReview().compareTo(r1.getRecipeReview()));
		} else if (filter.equals("rnk")) {
			recipelist = recipeDao.selectRecipeListOrderId(map);
		} else if (filter.equals("rlv")) {
			recipelist = recipeDao.selectRecipeListOrderRank(map);
		}
		pagingVO.setList(recipelist);
		
		map.put("page", page);
		map.put("maxpage", pagingVO.getMaxpage());
		map.put("startpage", pagingVO.getStartpage());
		map.put("endpage", pagingVO.getEndpage());
		map.put("listcount", listcount);
		map.put("recipelist", recipelist);
		
		return map;
	}

	@Override
	public Map<String, Object> recipeInitListProcess(int page, int limit, int CHEF_ID, int NATION_ID, int PART_ID,
			String filter) {
		int listcount = recipeDao.selectRecipeCount();
		PagingDTO<RecipeDTO> pagingVO = new PagingDTO<RecipeDTO>(page, limit, listcount);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", pagingVO.getStartrow());
		map.put("end", pagingVO.getEndrow());
		
		List<CategoryChefDTO> cheflist = new ArrayList<CategoryChefDTO>();
		int find = 1;
		for(int i = 0; i < CHEF_ID; i++) {
			cheflist.add(chefDao.selectChef(find));
			find++;
		}
		
		find = 1;
		List<CategoryNationDTO> nationlist = new ArrayList<CategoryNationDTO>();
		for(int i = 0; i < NATION_ID; i++) {
			nationlist.add(nationDao.selectNation(find));
			find++;
		}
		
		find = 1;
		List<CategoryPartDTO> partlist = new ArrayList<CategoryPartDTO>();
		for(int i = 0; i < PART_ID; i++) {
			partlist.add(partDao.selectPart(find));
			find++;
		}
		
		List<RecipeReviewDTO> reviewlist = recipeReviewDao.selectReviewStar();
		starProcess(reviewlist);
	
		// 연습 : 람다식 이용해서 reviewlist 별점 정렬
		Collections.sort(reviewlist, (r1, r2) -> r2.getREVIEW_STAR() - r1.getREVIEW_STAR());
		
		List<RecipeDTO> recipelist = new ArrayList<RecipeDTO>();
		// select option이 별점순 일 경우 recipelist의 아이디와 reviewlist아이디가 일치할 경우 recipelist에 reviewlist담기
		if(filter.equals("rct")) {
			recipelist = recipeDao.selectRecipeList(map);
			for(int i = 0; i < recipelist.size(); i++) {
				for(int j = 0; j < reviewlist.size(); j++) {
					if(recipelist.get(i).getRECIPE_ID() == reviewlist.get(j).getRECIPE_ID() && reviewlist.get(j).getREVIEW_STAR() != 0) {
						recipelist.get(i).setRecipeReview(reviewlist.get(j));
					}
				}
			}
			// 람다식 이용해서 recipelist 정렬(별점순)
			Collections.sort(recipelist, (r1, r2) -> r2.getRecipeReview().compareTo(r1.getRecipeReview()));
		}else if (filter.equals("rnk")) {
			recipelist = recipeDao.selectRecipeListOrderId(map);
		} else if (filter.equals("rlv")) {
			recipelist = recipeDao.selectRecipeListOrderRank(map);
		}
		pagingVO.setList(recipelist);
		
		map.put("page", page);
		map.put("maxpage", pagingVO.getMaxpage());
		map.put("startpage", pagingVO.getStartpage());
		map.put("endpage", pagingVO.getEndpage());
		map.put("listcount", listcount);
		map.put("recipelist", recipelist);
		map.put("limit", limit);
		map.put("cheflist", cheflist);
		map.put("nationlist", nationlist);
		map.put("partlist", partlist);
		map.put("reviewlist", reviewlist);
		return map;
	}
	
	public void starProcess(List<RecipeReviewDTO> reviewlist) {
		int sum = 0;
		int divine = 1;
		for (int i = 0; i < reviewlist.size(); i++) {
			divine = 1;
			for (int j = 1; j < reviewlist.size(); j++) {
				if(i == j) continue;
				else if(i < j) {
					if (reviewlist.get(i).getRECIPE_ID() == reviewlist.get(j).getRECIPE_ID()) {
						if (divine == 1)
							sum = reviewlist.get(i).getREVIEW_STAR();
						sum += reviewlist.get(j).getREVIEW_STAR();
						divine++;
						reviewlist.get(i).setREVIEW_STAR(sum / divine);
						reviewlist.get(j).setREVIEW_STAR(0);
					}
				}
			}
		}
	}

}
