package edu.pnu.config.auth;

import org.springframework.security.authentication.AuthenticationManager;

import edu.pnu.persistence.MemberRepository;

public class JWTAuthorizationFilter {

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, MemberRepository memberRepository) {
		// TODO Auto-generated constructor stub
	}

}
