package com.yorijori.cook.recipeDAO;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yorijori.cook.DTO.MemberDTO;

@Repository
public class MemberDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public String selectMemberName(String MEMBER_ID) {
		return sqlSession.selectOne("Members.selectMemberName", MEMBER_ID);
	}
	
	public MemberDTO selectMemberId(String id) {
		return sqlSession.selectOne("Members.selectMemberId", id);
	}
	
	public MemberDTO selectMemberInfo(String name) {
		return sqlSession.selectOne("Members.selectMemberInfo", name);
	}
	
	public int insertMember(MemberDTO m) {
		return sqlSession.insert("Members.insertMember", m);
	}
	
	public int updateMember(MemberDTO m) {
		return sqlSession.update("Members.updateMember",m);
	}
	
	// 이 밑으로 아직 안함.
	// selectSearchListCount
	public int getSearchListCount(Map<String, String> map) {
		return sqlSession.selectOne("Members.searchCount",map);
	}
	
	// selectSearchList
	public List<MemberDTO> getSearchList(Map<String, Object> map) {
		return sqlSession.selectList("Members.getSerchList", map);
	}

	// deleteMember
	public void delete(String id) {
		sqlSession.delete("Members.delete",id);
	}
	
	public int update2(MemberDTO member) {
		return sqlSession.update("Members.update2",member);
	}
	
	public MemberDTO member_info(String id) {
		return sqlSession.selectOne("Members.idcheck",id);
	}

}
