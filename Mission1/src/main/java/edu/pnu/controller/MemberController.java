// 스프링 부트에서 사용하는 DB와 관련하여 레파지토리도 쓰고 DAO도 쓰고~ 하지만 레파지토리라는 용어를 쓰는 추세
package edu.pnu.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;

//@Slf4j
@RestController
public class MemberController {

	private MemberService memberService;
	
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);  // 이 코드가 주석이 되어도 log.~ 가 가능한 이유: @Slf4j 덕분에
	
	public MemberController() {
		memberService = new MemberService();
	}
	
	@GetMapping("/members")
	public List<MemberVO> getMembers() {
		log.info("getMembers()");
		return memberService.getMembers();
	}

	// localhost:8080/member/1
	// rest 방식
	@GetMapping("/member/{id}")  // GetMapping: 데이터를 가져오는 코드라는 의미
	public MemberVO getMember(@PathVariable Integer id) {
		log.info("getMember()");
		return memberService.getMember(id);
	}
	// @PathVariable: 경로상에 있는 것을 매핑하겠다는 의미
	
	// localhost:8080/member?id=1
	// 호출 방식만 다르고 결과는 위와 같음. - 위와 아래는 맞고 틀림의 차이가 아님!
	// 전통적인 url 호출 방식
	@GetMapping("/member") // member? 뒤에 들어오는 값이 아래의 id에 매핑이 됨.
//	public MemberVO getMember1( Integer id) {
//		log.info("getMember()");
//		return memberService.getMember(id);
//	}
	
//	@PostMapping("/member")
	@RequestMapping(value="/member", method=RequestMethod.POST)
	public MemberVO addMember(MemberVO member) {
		log.info("addMember()");
		return memberService.addMember(member);
	}

	@PutMapping("/member")
	public MemberVO updateMember(MemberVO member) {
		log.info("updateMember()");
		return memberService.updateMember(member);
	}
	
	@DeleteMapping("/member/{id}")
	public MemberVO deleteMember(@PathVariable Integer id) {
		log.info("deleteMember()");
		return memberService.deleteMember(id);
	}
}
