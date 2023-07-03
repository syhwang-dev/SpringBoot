// 제일 손이 많이 가는 파일

package edu.pnu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	BoardUserDetailsService boardUserDetailsService;
	
	@Bean
	public BCryptPasswordEncoder encoder() {  // 암호화하는 메소드
 		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
		
		security.authorizeHttpRequests(auth->{
			// auth.antMatchers("/").permitAll();
			
			// auth.requestMatchers("/").permitAll();
			// auth.requestMatchers("/member/**").authenticated();
			
			// 일반적인 방식
//			auth.requestMatchers("/").permitAll()
//				.requestMatchers("/member/**").authenticated()
////				.requestMatchers("/manager/**").hasRole("MANAGER")
//				.requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")  
//				.requestMatchers("/admin/**").hasRole("ADMIN");
			
			// 교수님 방식
			auth.requestMatchers("/member/**").authenticated()  // url 중에 member가 붙는 url
			.requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")  
			.requestMatchers("/admin/**").hasRole("ADMIN")
			.anyRequest().permitAll();  // 그 외의 모든 요청은 허용하겠다는 의미 
			
		});
		// -> : 익명함수 문법
		
		// 막는 기능을 무효화하겠다는 의미
		security.csrf(csrf->csrf.disable());
		
		
		security.formLogin(form->{
			// form.defaultSuccessUrl("/"); // 로그인에 성공하면 index 화면을 띄어준다는 내용 // 멤버나 치면 로그인창
			form.loginPage("/login")
				.defaultSuccessUrl("/loginSuccess", true);	
		});
		
		security.exceptionHandling(ex->ex.accessDeniedPage("/accessDenied")); // accessDenied가 발생하면 accessDenied페이지로 가라.
		security.logout(logt->{
			logt.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.logoutSuccessUrl("/login");  // 로그인 페이지로 이동
		});
		
		security.userDetailsService(boardUserDetailsService);  // userDetailsService 메서드 호출
		
		return security.build();
	}
	
	@Autowired
	public void authenticate(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("member")
			.password("{noop}abcd")
			.roles("MEMBER");
		auth.inMemoryAuthentication()
			.withUser("manager")
			.password("{noop}abcd")
			.roles("MAMAGER");
		auth.inMemoryAuthentication()
			.withUser("admin")
			.password("{noop}abcd")  // noop: 암호화하는 작업을 하지 않겠다는 의미
			.roles("ADMIN");
	}
}
