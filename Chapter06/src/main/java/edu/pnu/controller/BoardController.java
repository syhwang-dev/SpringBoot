package edu.pnu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.pnu.domain.Board;
import edu.pnu.service.BoardService;

@Controller
public class BoardController {
	
//	@RequestMapping("/getBoardList")
//	public String getBoardList(Model model) {
//		List<Board> boardList = new ArrayList<>();
//		
//		for (int i=1; i<=10; i++) {
//			
////			Board board = new Board();
////			board.setSeq(new Long(i));
////			board.setTitle("title" + i);
////			board.setWriter("writer" + i);
////			board.setContent("content" + i);
////			board.setCnt(0L);
////			boardList.add(board);
//			
//			boardList.add(Board.builder()
//					.seq((long)i)
//					.title("title" + i)
//					.writer("writer" + i)
//					.content("content" + i)
//					.createDate(new Date())
//					.cnt(0L).build());
//		}
//		model.addAttribute("boardList", boardList);
//		
//		return "getBoardList";
//	}
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/getBoardList")
	public String getBoardList(Model model) {
		model.addAttribute("boardList",
				boardService.getBoardList());
		return "getBoardList";
	}
	
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
	
	@GetMapping("/getBoard")
	public String getBoard(Long seq, Model model) {
		Board board = boardService.getBoard(Board.builder().seq(seq).build());
		model.addAttribute("board", board);
		// jsp에서 쓰는 방식 > EL > ${board.seq}
		return "getBoard";
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
