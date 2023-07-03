package edu.pnu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@Component
public class MemberInitialize implements ApplicationRunner {
	
	@Autowired
	MemberRepository memRepo;
	
	@Autowired
	BCryptPasswordEncoder encoder;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		memRepo.save(Member.builder()
				.username("member")
				.password(encoder.encode("abcd"))
				.role("ROLE_MEMBER")
				.enabled(true)  // 이 아이디가 사용가능한지 묻는 것
				.build());  // 부트를 사용할 땐 앞에 ROLE_ 가 붙어야 함. 아까는 안 붙음.
		memRepo.save(Member.builder()
				.username("manager")
				.password(encoder.encode("abcd"))
				.role("ROLE_MANAGER")
				.enabled(true)
				.build());
		memRepo.save(Member.builder()
				.username("admin")
				.password(encoder.encode("abcd"))
				.role("ROLE_ADMIN")
				.enabled(true)
				.build());
	}
}
