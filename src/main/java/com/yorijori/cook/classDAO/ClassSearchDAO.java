package com.yorijori.cook.classDAO;

import java.util.HashMap;
import java.util.List;

import com.yorijori.cook.DTO.ClassDTO;

public interface ClassSearchDAO {

	public int selectClassAllCount(HashMap<String, String> map);

	public List<ClassDTO> selectClassAllList(HashMap<String, String> map);

	public int selectClassTitleCount(String srhText);

	public List<ClassDTO> selectClassTitleList(String srhText);

	public int selectClassNameCount(String srhText);

	public List<ClassDTO> selectClassNameList(String srhText);

}
