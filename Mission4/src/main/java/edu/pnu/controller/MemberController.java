package edu.pnu.controller;

import java.util.List;

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
	
	@GetMapping("/members")
	public List<MemberVO> getMembers() {
		return memberService.getMembers();
	}
	
	@GetMapping("/member/{id}")
	public MemberVO getMember(@PathVariable Integer id) {
		return memberService.getMember(id);
	}

	@PostMapping("/member")
//	@RequestMapping(value="/member", method=RequestMethod.POST)
	public MemberVO addMember(MemberVO member) {  
		return memberService.addMember(member);
	}
	
	@PutMapping("/member")  
	public MemberVO updateMember(MemberVO member){
		return memberService.updateMember(member);
	}
	
	@DeleteMapping("/member/{id}")  
	public int deleteMember(@PathVariable Integer id){
		return memberService.deleteMember(id);
	}
	
	// leave log - 이것은 단순히 찍어보는 것이기 때문에 반드시 필요한 코드는 아님.
//	@PostMapping("/dblog")
//	@RequestMapping(value="/member", method=RequestMethod.POST)
//	public void dblog() {  
//		return memberService.dblog();
//	}
}
