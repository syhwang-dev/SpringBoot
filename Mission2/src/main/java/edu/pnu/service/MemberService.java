// step04
package edu.pnu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.pnu.dao.MemberDao;
import edu.pnu.domain.Member;

public class MemberService {
	
	// step07
	private MemberDao memberDao;
	
	// step11 생성자는 보통 이 위치에 생성
	public MemberService() {
		memberDao = new MemberDao();
	}

	// step09
	public Member getMember(Long id) {
		return memberDao.getMember(id);
	}
	
	// step13
	public List<Member> getMembers() {
		return memberDao.getMembers();
	}

	// step16
	public int insertMember(Member member) {
		return memberDao.insertMember(member);
	}

	// step19
	public int updateMember(Member member) {
		return memberDao.updateMember(member);
	}
	
	// step22
	public int deleteMember(Long id) {
		return memberDao.deleteMember(id);
	}
	
	
}
