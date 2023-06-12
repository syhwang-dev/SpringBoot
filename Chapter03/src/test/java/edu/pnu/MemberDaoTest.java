package edu.pnu;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.dao.MemberDao;
import edu.pnu.domain.Member;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)  // 순서를 지정
public class MemberDaoTest {

	@Test
	@Order(1)
	public void insertMemberTest() {
		MemberDao memberDao = new MemberDao();
		
		for (int i=1; i<=10; i++) {
			// 방법1. Builder를 이용해 객체를 생성해서 사용하는 방법 - 체인코딩
			memberDao.insertMember(
					Member.builder()
						.name("name" + i)
						.age(20 + i)
						.nickname("nickname" + i)
						.build()
					);
			
			// 방법2. 기본 생성자를 이용한 방법
//			Member m = new Member();
//			m.setName("name" + i);
//			m.setAge(20+i);
//			m.setNickname("nickname" + i);
//			memberDao.insertMember(m);
			
			// 방법3. 파라미터가 필요한 생성자를 이용한 방법
//			memberDao.insertMember(new Member(-1L, "name"+i, 20+i, "nickname"+i)); // -1L: 어차피 사용을 안 하니까 -1을 사용했으며 아무 숫자나 사용해도 됨.
					
		}
	}
	// 부트에서는 순서를 보장해주지 않음. 순서를 정하기 위해 나온 어노테이션이 있음.
	
	@Test
	@Order(2)
	public void selectAllMemberTest() {
		MemberDao memberDao = new MemberDao();
		
		List<Member> list = memberDao.getMembers();
		for (Member m : list) {
			System.out.println(m);
		}
		
	}
}
