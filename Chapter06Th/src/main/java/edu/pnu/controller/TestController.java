package edu.pnu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
	
	@GetMapping("/hello")
	public void hello(Model model) {  // void 이면 url 상에 있는 이름인 hello 파일을 찾음.
		model.addAttribute("greeting", "Hello, Thymeleaf");
	}
}
