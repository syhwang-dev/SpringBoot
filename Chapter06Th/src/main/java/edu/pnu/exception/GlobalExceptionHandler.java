package edu.pnu.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(BoardException.class)  // board의 자식 클래스들은 모두 이 예외를 햄들
	public String handleCustomException(BoardException exception, Model model) {
		model.addAttribute("exception", exception);
		return "/errors/boardError";		
	}
	
	@ExceptionHandler(Exception.class)
	public String handleException(Exception exception, Model model) {
		model.addAttribute("exception", exception);
		return "/errors/globalError";		
	}
	
	// 로그인 시 사용자 이름을 찾을 수 없을 때 예외 처리
	@ExceptionHandler(UsernameNotFoundException.class)
	public String handleLoginException(Exception exception, Model model) {
		model.addAttribute("exception", exception);
		return "/errors/loginError";		
	}
	
}
