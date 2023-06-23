package edu.pnu.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@Service  // IOC 컨테이너에 올리기 위해 @Service 어노테이션 사용
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository boardRepo;
	
	@Override
	public List<Board> getBoardList() {
		return boardRepo.findAll();
	}
	
	@Override
	public void insertBoard(Board board) {
		board.setCreateDate(new Date());
		board.setCnt(0L);
		boardRepo.save(board);
	}
	
	@Override
	public Board getBoard(Board board) {
		// return boardRepo.findById(board.getSeq()); 일 때 에러가 발생함.
		// return boardRepo.findById(board.getSeq()).get();  // cnt를 계산하기 위해 코드 수정
		Board b = boardRepo.findById(board.getSeq()).get();
		if (b.getCnt() == null)
			b.setCnt(1L);
		else
			b.setCnt(b.getCnt() + 1L);
		
		return boardRepo.save(b);
		
	}
	
	@Override
	public void updateBoard(Board board) {
		Board findBoard = boardRepo.findById(board.getSeq()).get();
		
		findBoard.setTitle(board.getTitle());
		findBoard.setContent(board.getContent());
		boardRepo.save(findBoard);
		
	}
	
	@Override
	public void deleteBoard(Board board) {
		boardRepo.deleteById(board.getSeq());
	}
	
}
