package edu.pnu.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;

@RestController  // 부트 인식을 위해 필요
public class MemberController {
	
	private MemberService memberService;
	
	public MemberController() {
		System.out.println("MemberController가 생성되었습니다.");
		
		memberService = new MemberService();
	}
	
	// {id} 추가하는 이유: 아래의 insert / update / delete와 경로가 중복되므로
	@GetMapping("/memeber/{id}")
	public MemberVO getMember(@PathVariable Integer id) {
		return memberService.getMembers();
	}

	// 아래의 3개는 각각 insert / update / delete 이기 때문에 경로가 같아도 됨. 
	@PostMapping("/member")  
	public int addMember(MemberVO member) {  
		return memberService.addMember(member);
//		return 0;
	}
	
	@PutMapping("/member")  
	public int updateMembers(MemberVO member){
		return memberService.updateMember(member);
	}
	
	@DeleteMapping("/member")  
	public int deleteMember(Integer id){
		return memberService.deleteMember(id);
	}
}
