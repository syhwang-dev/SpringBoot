package edu.pnu.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@Service
public class BoardUserDetailsService implements UserDetailsService {

	@Autowired
	MemberRepository memRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Member> option = memRepo.findById(username);  // 데이터가 있는지 찾음.
		if (!option.isPresent()) {
			throw new UsernameNotFoundException("사용자가 없습니다.");
		}
		
		Member member = option.get(); // 디비에서 실제로 데이터를 가져옴.
		System.out.println(member);  // 콘솔창에 찍어보자.
		
		return new User(member.getUsername(), member.getPassword(), member.getAuthorities());
		// 권한들 이라는 용어를 사용하는 이유: 한 사람이 여러 권한을 가질 수 있음으로
	}

}
