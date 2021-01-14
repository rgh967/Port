package com.yorijori.cook.service;

import java.util.Map;

import com.yorijori.cook.DTO.ClassCurriculumDTO;
import com.yorijori.cook.DTO.ClassDTO;
import com.yorijori.cook.DTO.ClassDetailDTO;

public interface ClassService {

	int classRegiProcess(String saveFolder, ClassDTO classDTO, ClassCurriculumDTO curriculum, ClassDetailDTO detail);
	
	Map<String, Object> classDetailProcess(int cLASS_ID);

	Map<String, Object> classPagingProcess(int page, int limit);

	Map<String, Object> classInitListProcess(int page, int limit);

	Map<String, Object> classChkListProcess(int page, int limit, String from, String to, String amount1, String amount2);
}
