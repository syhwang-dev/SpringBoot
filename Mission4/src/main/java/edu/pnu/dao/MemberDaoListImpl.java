package edu.pnu.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import edu.pnu.domain.MemberVO;

public class MemberDaoListImpl implements MemberInterface {
	
	private List<MemberVO> list;

	// public void MemberService() {
	public MemberDaoListImpl() {
		list = new ArrayList<>();  // list라는 배열 생성
		for (int i = 1 ; i <= 5 ; i++) {
			list.add(new MemberVO(i, "1234", "이름"+i, new Date()));
		}
	}
	
	@Override
	public Map<String, Object> getMembers() {
		return (Map<String, Object>) list;
	}
	
	@Override
	public Map<String, Object> getMember(Integer id) {
		for (MemberVO m : list) {
			if (m.getId() == id)
				return (Map<String, Object>) m;
		}
		return null;
	}
	
	@Override
	public Map<String, Object> addMember(MemberVO member) {
		member.setId(list.size() + 1);
		member.setRegidate(new Date());
		list.add(member);
		return (Map<String, Object>) member;
	}

	@Override
	public Map<String, Object> updateMember(MemberVO member) {
		for (MemberVO m : list) {
			if (m.getId() == member.getId()) {
				// name과 pass의 속성 모두 바꿔야 하며, 둘 중 하나만 바꿀 경우 'null' 값이 들어감.
				// 리스트인 현재는 'null' 값이 들어가도 에러가 발생하지 않으나 데이터베이스에선 에러가 발생함.
				m.setName(member.getName());
				m.setPass(member.getPass());
				
				// 둘 중 하나만 들어가도 되는 코드
				if (member.getName() != null) 
					m.setName(member.getName());
				if (member.getPass() != null) 
					m.setPass(member.getPass());
				return (Map<String, Object>) m;
			}
		}
		return null;
	}
	
	@Override
	public Map<String, Object> deleteMember(Integer id) {
		for (MemberVO m : list) {
			if (m.getId() == id) {
				list.remove(m);
//				return 0;
			}
		}
		return null;
	}
}
