package edu.pnu.config.filter;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private final AuthenticationManager authenticationManager; 
	private final MemberRepository memRepo;
	
	// 로그인 시도
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse resp) throws AuthenticationException {
		ObjectMapper om = new ObjectMapper();
		try {
			Member member = om.readValue(req.getInputStream(), Member.class);
			
			Optional<Member> option = memRepo.findById(member.getUsername());
			if(!option.isPresent()) {
				log.info("Not Authenticated : Not found user!");
				return null;
			}
			
			
			Authentication authToken = new UsernamePasswordAuthenticationToken(member.getUsername(), member.getPassword());
			Authentication auth = authenticationManager.authenticate(authToken);  // username이나 password가 틀리면 예외를 발생시킴.
			
			log.info("attemptAuthentication :[" + member + "]");
			return auth;
		} catch (Exception e) {
//			e.printStackTrace();
//			log.info("Not Authenticated : " + e.getMessage());  // 자격 증명에 실패하였습니다.
			log.info("Not Authenticated : Not Match password!");
		}
		
		return null;
	}
	
	// 로그인을 성공하면 여기로 넘어옴.
	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse resp,
			FilterChain chain, Authentication authResult) throws IOException, ServletException {
		
		User user = (User)authResult.getPrincipal();
		log.info("successfulAuthentication:" + user.toString());
		
		String jwtToken = JWT.create()
				.withClaim("username", user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis()+1000*60*10))  // 시간과 관련해서 1000이 있으면 밀리세컨드 사용
				.sign(Algorithm.HMAC256("edu.pnu.jwtkey"));  // "edu.pnu.jwtkey": 내가 아는 키로 정해도 됨.
		
		resp.addHeader("Authorization", "Bearer " + jwtToken);  // jwt를 사용한다는 약속된 코드
		
		chain.doFilter(req, resp);
	}

}
