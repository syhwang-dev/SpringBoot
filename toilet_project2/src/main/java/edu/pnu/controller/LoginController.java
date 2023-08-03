package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Member;
import edu.pnu.service.MemberService;

@RestController
public class LoginController {
	
	@Autowired
	private MemberService memberService;
	
	
	@PostMapping("/user/login")	
	public ResponseEntity<?> userLogin( Member member){
		Member findMember = memberService.getMember(member);
		
		if(findMember != null
				&& findMember.getPassword().equals(member.getPassword())) {
			
			return ResponseEntity.ok("ok");
		}
		return ResponseEntity.ok("not found user");
		
	}
	
//	@GetMapping("/user/logout") 
//	public ResponseEntity<String> userLogout(){
//		
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        SecurityContextHolder.clearContext();
//
//        return new ResponseEntity<>("Logout successful", HttpStatus.OK); 
//	}
	
//	@PostMapping("user/join")
//	public ResponseEntity<?> userJoin(){
//		return ResponseEntity.
//	}
	
	
	/*
	 * @GetMapping("/user/logout") public
	 */
//	@GetMapping("/user/login")
//	public void loginView() {
//		
//	}
	
//	@PostMapping("/user/login")
//	public String login(Member member, Model model) {
//		Member findMember = memberService.getMember(member);
//		
//		if(findMember != null
//				&& findMember.getPassword().equals(member.getPassword())) {
//			model.addAttribute("member", findMember);
//			return "Main";
//		}
//		else {
//			return "redirect:Login";
//		}
//		
//	}
//	
//	@GetMapping("/user/logout")
//	public String logout(SessionStatus status) {
//		status.setComplete();
//		return "redirect:Login";
//	}
//	
//	@GetMapping("/user/new")
//	public void usernew() {
//		
//	}
//	
//	@PostMapping("/user/new")
//	public String usernewProc(Member member) {
//		Member member1 = new Member();
//		member1.setId("");
//		member1.setName("");
//		member1.setPassword("");
////		memberRepo.save(member1);
//		return "Login";
//	}
	

}
