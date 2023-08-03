package edu.pnu.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@RestController
public class LoginController {	
	@Autowired
	private MemberRepository memRepo;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login")	
	public ResponseEntity<?> userLogin(@RequestBody Member member){
		
		Optional<Member> option = memRepo.findById(member.getUsername());
		if(!option.isPresent()) {
			return ResponseEntity.ok("Not found user!");
		}
		
		Authentication authToken = new UsernamePasswordAuthenticationToken(member.getUsername(), member.getPassword());
		Authentication auth = authenticationManager.authenticate(authToken);  // username이나 password가 틀리면 예외를 발생시킴.

		User user = (User)auth.getPrincipal();
		
		String jwtToken = JWT.create()
				.withClaim("username", user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis()+1000*60*10))  // 시간과 관련해서 1000이 있으면 밀리세컨드 사용
				.sign(Algorithm.HMAC256("edu.pnu.jwtkey"));  // "edu.pnu.jwtkey": 내가 아는 키로 정해도 됨.
		
	
		return ResponseEntity.ok()
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken).build();
				
	}
	
	// 연장
//	@
	
	@GetMapping("/member")
	public ResponseEntity<?> member() {
		return ResponseEntity.ok("member");
	}
}
