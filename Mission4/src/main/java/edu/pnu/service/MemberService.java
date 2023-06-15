package edu.pnu.service;

import java.util.List;
import java.util.Map;

import edu.pnu.dao.LogDao;
import edu.pnu.dao.MemberDaoH2Impl;
import edu.pnu.dao.MemberDaoListImpl;
import edu.pnu.dao.MemberInterface;
import edu.pnu.domain.MemberVO;

public class MemberService {
	
//	private List<MemberVO> list;
	
	private MemberInterface memberDao;
	private LogDao logDao;
	
	// map을 사용해서 값 받아오기
	
	public MemberService() {
//		memberDao = new MemberDaoListImpl();
		memberDao = new MemberDaoH2Impl();
		logDao = new LogDao();
	}
	
	public List<MemberVO> getMembers() {
		Map<String, Object> map = memberDao.getMembers();
		
		String sqlstring = map.get("sqlstring").toString();
		boolean success = (boolean) map.get("success");
		
        logDao.insertLog("GET", sqlstring, success);
        
//        return memberDao.getMembers();
        return (List<MemberVO>) map.get("result");
	}
	

	public MemberVO getMember(Integer id) {
		Map<String, Object> map = memberDao.getMember(id);
		
		// String sqlstring = map.get("sqlstring").toString();
		String sqlstring = (String) map.get("sqlstring");
		boolean success = false;
		if(map.get("result") != null) success = true;
						
        logDao.insertLog("GET", sqlstring, success);
		
//		return memberDao.getMember(id);
        return (MemberVO) map.get("result");
	}
	

	public MemberVO addMember(MemberVO member) {
		Map<String, Object> map = memberDao.addMember(member);
		
		String sqlstring = map.get("sqlstring").toString();
		boolean success = (boolean) map.get("success");
		
        logDao.insertLog("POST", sqlstring, success);
        
		return (MemberVO) map.get("result");
	}
	

	public MemberVO updateMember(MemberVO member) {
		Map<String, Object> map = memberDao.updateMember(member);
		
		String sqlstring = map.get("sqlstring").toString();
		boolean success = (boolean) map.get("success");
		
        logDao.insertLog("PUT", sqlstring, success);
        
		return (MemberVO) map.get("result");
	}

	public int deleteMember(Integer id) {
		Map<String, Object> map = memberDao.deleteMember(id);
		
		String sqlstring = map.get("sqlstring").toString();
		boolean success = (boolean) map.get("success");
		
        logDao.insertLog("DELETE", sqlstring, success);
		
		return (int) map.get("result");
	}
}
