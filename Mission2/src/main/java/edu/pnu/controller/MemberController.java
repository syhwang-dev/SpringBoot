package edu.pnu.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Member;
import edu.pnu.service.MemberService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MemberController {
	
	// step05
	private MemberService memberService;
	
	
	// step01
	public MemberController() {
		System.out.println("MemberController가 생성되었습니다.");
		log.info("MemberController가 생성되었습니다.");
		
	}
	
	// step02
	// {id}: 경로 변수로 사용됨.
	// localhost:8080/member/1
	@GetMapping("/member/{id}")
	public Member getMember(@PathVariable Long id) {  // Member 클래스를 생성해야 함.
		// step06
		return memberService.getMember(id);
	}
	
	// step12
	@GetMapping("/member")  // 모든 멤버 데이터를 리턴
	public List<Member> getMembers(){  
		return memberService.getMembers();
	}
	
	// step15
	@PostMapping("/member")  // 새로운 멤버 입력
	public int insertMember(Member member) {  // int나 Member가 아닌 void라면 성공했는지 실패했는지 알 수 없음.
		return memberService.insertMember(member);
//		return 0;
	}
	
	// step18
	@PutMapping("/member")  // 멤버 이름, 암호 수정
	public int updateMembers(Member member){
		return memberService.updateMember(member);
	}
	
	// step21
	@DeleteMapping("/member/{id}")  // 멤버 삭제 
	public int deleteMember(@PathVariable Long id){  // {id}: 경로상의 변수
		return memberService.deleteMember(id);
	}
}
