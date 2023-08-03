package edu.pnu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@SpringBootTest
public class DaralnitializeTest {
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Test
	public void testDataInsert() {
		Member member1 = new Member();
		member1.setUsername("member");
		member1.setName("둘리");
		member1.setPassword(encoder.encode("abcd"));
		member1.setRole("ROLE_MEMBER");
		member1.setEnabled(true);
		memberRepo.save(member1);
		
		Member member2 = new Member();
		member2.setUsername("manager");
		member2.setName("도우너");
		member2.setPassword(encoder.encode("abcd"));
		member2.setRole("ROLE_MANAGER");
		member2.setEnabled(true);
		memberRepo.save(member2);
		
	}
}
