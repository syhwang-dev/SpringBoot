package com.rubypaper.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.domain.BoardVO;

//ctrl + shift + o: 자동으로 import
//@Controller
@RestController
public class BoardController{
	public BoardController() {
		System.out.println("=".repeat(50));
		System.out.println("BoardController가 생성되었습니다.");
		System.out.println("=".repeat(50));
	}
	
	// 호출하는 url은 다 같음
	@GetMapping("/hello")
//	public @ResponseBody String hello1(String name) {  // @Controller를 사용하고 싶으면 @ResponseBody를 넣으면 주면 됨.
	public String hello1(String name) { 
		return "Get Hello : " + name;
	}
	
	// 브라우저에서 안 됨
	@PostMapping("/hello")
	public String hello2(String name) {
		return "Post Hello : " + name;
	}
	
	@PutMapping("/hello")
	public String hello3(String name) {
		return "Put Hello : " + name;
	}
	
	@DeleteMapping("/hello")
	public String hello4(String name) {
		return "Delete Hello : " + name;
	}
	
	// 추가
	// 가장 고전적인 방법
	@GetMapping("/getBoard")
	public BoardVO getBoard() {
		BoardVO board = new BoardVO();
		board.setSeq(1);
		board.setTitle("테스트 제목");
		board.setWriter("테스터");
		board.setContent("테스트 내용입니다!");
		board.setCreateDate(new Date());
		board.setCnt(0);
		return board;
	}
	
	@GetMapping("/getBoard1")
	public BoardVO getBoard1() {
		BoardVO board = new BoardVO(
				1,
				"테스트 제목",
				"테스터",
				"테스트 내용입니다!",
				new Date(),
				0);
		return board;
	}
	
	// 요새의 추세 - ...을 계속 사용 - 체인방식
	@GetMapping("/getBoard2")
	// 빌드를 안 만들고 builder를 사용
	public BoardVO getBoard2() {
		return BoardVO.builder()
				.seq(1)
				.title("테스트 제목")
				.writer("테스터")
				.content("테스트 내용입니다!")
				.createDate(new Date())
				.cnt(0)
				.build();
	}
	
	@GetMapping("/getBoardList")
	public List<BoardVO> getBoardList() {
		// ArrayLis(배열임으로)를 활용하여 for문으로 10개 출력
		List<BoardVO> bdlist = new ArrayList<BoardVO>();
		for (int i=1; i<=10; i++) {
			BoardVO bd = new BoardVO();
			bd.setSeq(i);
			bd.setTitle("테스트 제목" + i);
			bd.setWriter("테스터");
			bd.setContent(i+ "번 테스트 내용입니다!");
			bd.setCreateDate(new Date());
			bd.setCnt(0);
			bdlist.add(bd);
		}
		return bdlist;
	}

	// 이 예제를 실행하기 위해 @Controller로 바꿀 필요 없음.
	@GetMapping("/board")
	public BoardVO board(@RequestBody BoardVO b) {  // @RequestBody가 없으면 form으로 받는?
		b.setCreateDate(new Date());
		b.setCnt(100);
		System.out.println("Board:" + b);
		return b;
	}
}

// 내가 인스턴스를 만든적이 없는데 자동으로 객체를 생성 -> 부트가 만들어줌!
// 어디에다가 저장을 하지 않을까? 컨테이너에 저장을 함.
// @RestController 이런 어노테이션이 붙어있으면 부트가 자동으로 생성해줌.
// @RestController든 @Controller든 다른 어노테이션이든, 시작점이 따로 있음.
// 이 클래스가 하는 역할에 따라 조금씩 다른 어노테이션 이름을 줌.
// 그러니까 굳이 @Controller를 사용하지 않아도 된다는 뜻임. 하지만 협업을 위해 통상적으로 사용하자.