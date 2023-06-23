package edu.pnu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.pnu.domain.Board;
import edu.pnu.domain.Member;
import edu.pnu.service.BoardService;

@SessionAttributes("member")
@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@ModelAttribute("member")
	public Member setMember() {
		return new Member();
	}
	
	@RequestMapping("/getBoardList")
//	@GetMapping("/getBoardList")  // 405 에러 발생
	public String getBoardList(@ModelAttribute("member") Member member, Model model, Board board) {
		if (member.getId() == null) {
			return "redirect:login";
		}
		
		List<Board> boardList = boardService.getBoardList();
		
		model.addAttribute("boardList", boardList);
		return "getBoardList";
	}
	
//	@PostMapping("/getBoardList")
//	public String getBoardList1(Model model) {
//		model.addAttribute("boardList",
//				boardService.getBoardList());
//		return "getBoardList";
//	}
	
	@GetMapping("/insertBoard")
	public String insertBoard() {
		return "insertBoard";
		// 다른 내용 없이 바로 리턴 → WEB-INF/board/insertBoard.jsp를 호출
		// 기억할 것 - controller를 통해 view를 호출함.
		// url 이름 jsp 파일 이름
	}
	
	@PostMapping("/insertBoard")
	public String insertBoardPost(Board board) {
		boardService.insertBoard(board);  // BoardServiceImpl로 이동
		
		// 일반적으로 저장한 후 다시 보여줌.
		// redirect를 하는 이유: 기본 - 포워드 방식
		return "redirect:getBoardList";
	}
	
	// 서비스 호출
	@GetMapping("/getBoard")
	public String getBoard(Long seq, Model model) {
		Board board = boardService.getBoard(Board.builder().seq(seq).build());
		model.addAttribute("board", board);
		// jsp에서 쓰는 방식 > EL > ${board.seq}
		return "getBoard";
	}
	
	@PostMapping("/updateBoard")
	public String updateBoard(Board board) {
		boardService.updateBoard(board);
		// return "redirect:getBoardList";
		return "forward:getBoardList";
	}

	@GetMapping("/deleteBoard")
	public String deleteBoard(Board board) {
		boardService.deleteBoard(board);
		return "forward:getBoardList";
	}
	
	
	
	
	
	
}
