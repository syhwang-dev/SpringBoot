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
import lombok.RequiredArgsConstructor;

@RestController  // 부트 인식을 위해 필요
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	
//	public MemberController() {
//		System.out.println("MemberController가 생성되었습니다.");
//		memberService = new MemberService();
//	}
	
	@GetMapping("/members")
	public List<MemberVO> getMembers() {
		return memberService.getMembers();
	}
	
	// {id} 추가하는 이유: 아래의 insert / update / delete와 경로가 중복되므로
	@GetMapping("/member/{id}")
	public MemberVO getMember(@PathVariable Integer id) {
		return memberService.getMember(id);
	}

	// 아래의 3개는 각각 insert / update / delete 이기 때문에 경로가 같아도 됨. 
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
}
