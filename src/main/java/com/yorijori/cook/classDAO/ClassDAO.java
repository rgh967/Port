package com.yorijori.cook.classDAO;

import java.util.List;
import java.util.Map;

import com.yorijori.cook.DTO.ClassCurriculumDTO;
import com.yorijori.cook.DTO.ClassDTO;
import com.yorijori.cook.DTO.ClassDetailDTO;

public interface ClassDAO {
	
	public int selectClassId();
	
	public int insertClass(ClassDTO classDTO);

	public ClassDTO selectClass(int CLASS_ID);

	public List<ClassDetailDTO> selectClassDetailList(int CLASS_ID);

	public List<ClassCurriculumDTO> selectClassCurriculumList(int CLASS_ID);

	public int selectRegiOK(int CLASS_ID);

	int selectClassCount();

	List<ClassDTO> selectClassList(Map<String, Object> map);

	int selectChkClassCount(Map<String, Object> map);

	List<ClassDTO> selectChkClassList(Map<String, Object> map);
	
	String selectClassStartDate();
	
	String selectClassEndDate();
}
