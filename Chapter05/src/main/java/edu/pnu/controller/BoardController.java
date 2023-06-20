package edu.pnu.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@RestController  // 없으면 404 에러 발생! 반드시 넣기!
public class BoardController {
	
	@Autowired
	BoardRepository boardRepo;
	
	@GetMapping("/findbytitlelike")
	public List<Board> findByTitleLike(String title) {
		Pageable page = PageRequest.of(0, 5);
		return boardRepo.findByTitleLike("%"+title+"%", page);
	}
	
	@GetMapping("/findpage")
	public List<Board> findPage(int pagenum, int size) {
		Pageable paging = PageRequest.of(pagenum, size);
		Page<Board> page = boardRepo.findAll(paging);
		return page.getContent();
	}
	
	
	@GetMapping("/getBoardList")
	public List<Board> getBoardList() {
		return boardRepo.findAll();
	}
	
	// Create - insert
	@PostMapping("/board")
	public Board insertBoard(Board board) {
		if (board.getCreateDate() == null)
			board.setCreateDate(new Date());
		return boardRepo.save(board);
	}
	
	// Json을 입력을 받는다면? @RequestBody만 사용하면 됨.
	@PostMapping("/boardjson")
	public Board insertJsonBoard(@RequestBody Board board) {
		if (board.getCreateDate() == null)
			board.setCreateDate(new Date());
		return boardRepo.save(board);
	}
	
	// Read - 값 한 개 가져오기
//	@GetMapping("/getBoard/{id}")
//	public Board getBoard(@PathVariable Long id) {
//		return boardRepo.findById(id).get();
//	}
	
	@GetMapping("/board")
	public Board getBoard(Long id) {
		return boardRepo.findById(id).get();
	}
	
	// Update
	@PutMapping("/board")
	public Board updateBoard(Board board) {
		return boardRepo.save(board);
	}
	
	// Delete
//	@DeleteMapping("/deleteBoard/{id}")
//	public String deleteBoard(@PathVariable Long id) {
//		boardRepo.deleteById(id);
//		return "삭제되었습니다.";
//	}
	
	@DeleteMapping("/deleteBoard")
	public String deleteBoard(Long id) {
		boardRepo.deleteById(id);
		return String.format("seq가 %d인 데이터가 삭제되었습니다.", id);
	}
	
	// 쿼리 메소드 화면에 출력
//	@GetMapping("/querytest1")
//	public String querytest1() {
//		List<Board> list = boardRepo.findByTitleLike("1");
//		
//		for (Board board : list) {
//			String result = board.toString());
//		}
//		
//	}
	
}
