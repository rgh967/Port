package com.yorijori.cook.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.yorijori.cook.DTO.ClassCurriculumDTO;
import com.yorijori.cook.DTO.ClassDTO;
import com.yorijori.cook.DTO.ClassDetailDTO;
import com.yorijori.cook.DTO.PagingDTO;
import com.yorijori.cook.classDAO.ClassCurriculumDAO;
import com.yorijori.cook.classDAO.ClassDAO;
import com.yorijori.cook.classDAO.ClassDetailDAO;
import com.yorijori.cook.recipeDAO.MemberDAO;

@Service
@Transactional
public class ClassServiceImpl implements ClassService {
	@Autowired
	private ClassDAO classDao;
	
	@Autowired
	private ClassCurriculumDAO classCurriculumDao;
	
	@Autowired
	private ClassDetailDAO classDetailDao;
	
	@Autowired
	private MemberDAO memberDao;
	
	@Override
	@Transactional
	public int classRegiProcess(String saveFolder, ClassDTO classDTO, ClassCurriculumDTO curriculum, ClassDetailDTO detail) {
		int result = 0;

		int class_id = classDao.selectClassId();
		
		//---------------------------------------------------------
		// classDTO 처리 
		//---------------------------------------------------------
		// 클래스 대표 이미지 처리 
		MultipartFile uploadFile = classDTO.getUploadFile();
		if (!uploadFile.isEmpty()) {
			String fileName = uploadFile.getOriginalFilename(); // 원래 파일명
			classDTO.setOriginalFile(fileName); // 원래 파일명 저장

			String fileDBName = fileProcess("rep_class", fileName, saveFolder, class_id);

			// transferTo(File path) : 업로드한 파일을 매개변수의 경로에 저장합니다.
			try {
				uploadFile.transferTo(new File(saveFolder + fileDBName));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			classDTO.setCLASS_MAIN_IMG(fileDBName);
		}
		

		classDTO.setCLASS_ID(class_id);
		
		// 주소 처리 --> DTO에서 처리할까 ? 일단 나중에 생각 
		String postcode = "[" + classDTO.getPostcode() + "] ";
		String addr = postcode + classDTO.getAddress() + " " + classDTO.getAddress_etc();
		classDTO.setCLASS_ADDRESS(addr);
			
		result = classDao.insertClass(classDTO);
		if(result == 0) return result;
		
		//---------------------------------------------------------
		// 커리큘럼 처리 
		//---------------------------------------------------------
		curriculum.setCLASS_ID(class_id);
		String[] cur_day = curriculum.getARR_CUR_DAY();
		String[] cur_content = curriculum.getARR_CUR_CONTENT();
		
		for (int i = 0; i<cur_day.length-1; i++) {
			curriculum.setCUR_DAY(Integer.parseInt(cur_day[i]));
			curriculum.setCUR_CONTENT(cur_content[i]);
			result = classCurriculumDao.insertCurriculum(curriculum);
			if(result == 0) return result;
		}
		
		//---------------------------------------------------------
		// 상세내용 처리 
		//---------------------------------------------------------
		// 요리 순서 이미지 파일 처리 
		String[] detail_step = detail.getARR_DETAIL_STEP();
		String[] detail_content = detail.getARR_DETAIL_CONTENT();
		MultipartFile[] detail_uploadfile = detail.getARR_DETAIL_UPLOADFILE();
		
		String[] detail_image = new String[detail_step.length];
		
		for (int i = 0; i < detail_step.length - 1; i++) {
			MultipartFile step_image = detail_uploadfile[i];
			if (!step_image.isEmpty()) {
				String fileName = step_image.getOriginalFilename(); // 원래 파일명

				String storeName = "detail" + detail_step[i];
				String fileDBName = fileProcess(storeName, fileName, saveFolder, class_id);

				// transferTo(File path) : 업로드한 파일을 매개변수의 경로에 저장합니다.
				try {
					step_image.transferTo(new File(saveFolder + fileDBName));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}

				detail_image[i] = fileDBName;
			} else {
				detail_image[i] = "";
			}
		}
		
		detail.setCLASS_ID(class_id);
		for (int i = 0; i < detail_step.length - 1; i++) {
			detail.setDETAIL_STEP(Integer.parseInt(detail_step[i]));
			detail.setDETAIL_CONTENT(detail_content[i]);
			detail.setDETAIL_IMAGE(detail_image[i]);
			
			result = classDetailDao.insertDetail(detail);
			if(result == 0) return result;
		}

		return result;
	}
	
	// 이미지 파일 처리
	private String fileProcess(String storeName, String fileName, String saveFolder, int class_id) {
		// 새로운 폴더 이름 설정
		String homedir = saveFolder + "class_" + class_id;
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
		String fileDBName = "class_" + class_id + "/" + refileName;
		System.out.println("--- fileDBName = " + fileDBName);

		return fileDBName;
	}

	@Override
	public Map<String, Object> classDetailProcess(int CLASS_ID) {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(CLASS_ID);
		ClassDTO classDto = classDao.selectClass(CLASS_ID);  // 상세정보 가져오기
		System.out.println(classDto.getMEMBER_ID());
		String classWriter = memberDao.selectMemberName(classDto.getMEMBER_ID());  // 클래스 작성자 이름 가져오기
		List<ClassDetailDTO> classDetail = classDao.selectClassDetailList(CLASS_ID);  // 클래스 step 가져오기
		List<ClassCurriculumDTO> classCurriculum = classDao.selectClassCurriculumList(CLASS_ID);  // 클래스 컬리큘럼 가져오기
		int regiOK = classDao.selectRegiOK(CLASS_ID);  // 등록 기간인지 확인
		
		//System.out.println(classCurriculum.get(0).toString());
		map.put("class", classDto);
		map.put("classWriter", classWriter);
		map.put("classDetail", classDetail);
		map.put("classCurriculum", classCurriculum);
		map.put("regiOK", regiOK);
		
		return map;
	}

	@Override
	public Map<String, Object> classPagingProcess(int page, int limit) {
		int listcount = classDao.selectClassCount();
		PagingDTO<ClassDTO> pagingVO = new PagingDTO<ClassDTO>(page, limit, listcount);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", pagingVO.getStartrow());
		map.put("end", pagingVO.getEndrow());
		
		List<ClassDTO> classlist = classDao.selectClassList(map);
		pagingVO.setList(classlist);
		
		map.put("page", page);
		map.put("maxpage", pagingVO.getMaxpage());
		map.put("startpage", pagingVO.getStartpage());
		map.put("endpage", pagingVO.getEndpage());
		map.put("listcount", listcount);
		map.put("classlist", classlist);
		return map;
	}

	@Override
	public Map<String, Object> classInitListProcess(int page, int limit) {
		int listcount = classDao.selectClassCount();
		PagingDTO<ClassDTO> pagingVO = new PagingDTO<ClassDTO>(page, limit, listcount);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", pagingVO.getStartrow());
		map.put("end", pagingVO.getEndrow());
		
		List<ClassDTO> classlist = classDao.selectClassList(map);
		pagingVO.setList(classlist);
		
		map.put("page", page);
		map.put("maxpage", pagingVO.getMaxpage());
		map.put("startpage", pagingVO.getStartpage());
		map.put("endpage", pagingVO.getEndpage());
		map.put("listcount", listcount);
		map.put("classlist", classlist);
		return map;
	}

	@Override
	public Map<String, Object> classChkListProcess(int page, int limit, String from, String to, String amount1,
			String amount2) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(amount1 == null || amount1.equals("") || amount2 == null || amount2.equals("")) {
			amount1 = "0";
			amount2 = "200000";
		}
		map.put("amount1", amount1);
		map.put("amount2", amount2);
		
		if(from == null || from.equals("")) {
			from = classDao.selectClassStartDate();
		}
		if(to == null || to.equals("")) { 
			to = classDao.selectClassEndDate();
		}
		map.put("from", from);
		map.put("to", to);
		
		int listcount = classDao.selectChkClassCount(map);
		
		if(listcount < 8) {
			page = 1;
			limit = 8;
		}
		
		// 생각해야할 부분
		// 체크해서 개수가 8개 넘어갔을경우 페이지가 1과 2로 바뀌긴 한다.
		// 다음 2를 클릭하면 기본 갯수가 12개로가고 ajax가 안먹은 화면으로 가는부분 처리해야함.
		
		PagingDTO<ClassDTO> pagingVO = new PagingDTO<ClassDTO>(page, limit, listcount);
		map.put("start", pagingVO.getStartrow());
		map.put("end", pagingVO.getEndrow());
		
		List<ClassDTO> classlist = classDao.selectChkClassList(map);
		pagingVO.setList(classlist);
		
		map.put("page", page);
		map.put("maxpage", pagingVO.getMaxpage());
		map.put("startpage", pagingVO.getStartpage());
		map.put("endpage", pagingVO.getEndpage());
		map.put("listcount", listcount);
		map.put("classlist", classlist);
		return map;
	}
}
