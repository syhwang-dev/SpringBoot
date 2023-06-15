package edu.pnu.dao;

import java.util.List;
import java.util.Map;

import edu.pnu.domain.MemberVO;

public interface MemberInterface {
	
//	List<MemberVO> getMembers();
//	
//	MemberVO getMember(Integer id);
//	
//	MemberVO addMember(MemberVO member);
//	
//	MemberVO updateMember(MemberVO member);
//	
//	int deleteMember(Integer id);
	
	Map<String, Object> getMembers();
	
	MemberVO getMember(Integer id);
	
	Map<String, Object> addMember(MemberVO member);
	
	Map<String, Object> updateMember(MemberVO member);
	
	Map<String, Object> deleteMember(Integer id);
	
}
