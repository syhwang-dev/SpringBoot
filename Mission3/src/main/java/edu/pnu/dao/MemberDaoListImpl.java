package edu.pnu.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.pnu.domain.MemberVO;

public class MemberDaoListImpl implements MemberInterface {
	
	private List<MemberVO> list;

	public void MemberService() {
		list = new ArrayList<>();  // list라는 배열 생성
		for (int i = 1 ; i <= 5 ; i++) {
			list.add(new MemberVO(i, "1234", "이름"+i, new Date()));
		}
	}
	
	@Override
	public List<MemberVO> getMembers() {
		return list;
	}
	
	public MemberVO Member(Integer id) {
		for (MemberVO m : list) {
			if (m.getId() == id)
				return m;
		}
		return null;
	}
	
	@Override
	public MemberVO addMember(MemberVO member) {
		member.setId(list.size() + 1);
		member.setRegidate(new Date());
		list.add(member);
		return member;
	}

	@Override
	public MemberVO updateMember(MemberVO member) {
		for (MemberVO m : list) {
			if (m.getId() == member.getId()) {
				m.setName(member.getName());
				m.setPass(member.getPass());
				return m;
			}
		}
		return null;
	}
	
	@Override
	public int deleteMember(Integer id) {
		for (MemberVO m : list) {
			if (m.getId() == id) {
				list.remove(m);
				return 0;
			}
		}
		return -1;
	}

	@Override
	public MemberVO getMember(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberVO updataMember(MemberVO member) {
		// TODO Auto-generated method stub
		return null;
	}
}
