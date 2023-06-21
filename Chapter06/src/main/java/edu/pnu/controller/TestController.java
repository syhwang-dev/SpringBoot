package edu.pnu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
	
//	@GetMapping("/home")
//	public String home() {
//		// [/WEB-INF/board/home.jsp]
//		return "home";
//	}
	
	@GetMapping("/home")
	public String home() {  // String이 아니라 void가 들어간다면?
		// [/WEB-INF/board/home.jsp]
		return "home";
	}
	
	// [/WEB-INF/board/home1.jsp]
	@GetMapping("/home1")
	public void home1() {
	}
	
	// 모델 테스트
	@GetMapping("/model")
	public String model(Model model) {
		
//		List<Board> list = testService.get
		
		model.addAttribute("data", "홍길동");
		model.addAttribute("data", "홍길동");
		return "model";
	}
}
