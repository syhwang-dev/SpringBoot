package edu.pnu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public void login() {  // void이기 때문에 login.html을 찾을 것임.
	}
	
	@GetMapping("/loginSuccess")
	public void loginSuccess() {
	}
	
	@GetMapping("/accessDenied")  // 멤버로 로그인해서 어드민으로 이동하려고 했을 때 에러 발생 처리
	public void accessDenied() {
	}
	

}
