package edu.pnu;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
//@TestMethodOrder(OrderAnnotation.class)
public class BoardRepositoryTest {
	@Autowired
	private BoardRepository bdRepo;
	
//	@Test
//	@Order(1)
	void testInsertBoard() {
		for (int i=0; i<=10; i++) {
			bdRepo.save(Board.builder()
					.title("Title" + i)
					.writer("writer" + i)
					.content("content" + i)
					.createDate(new Date())
					.cnt(0L)
					.build());
		}	
	}
	
//	@Test
	void testGetBoard() {
//		Optional<Board> opt = bdRepo.findById(1L);
//		Board board = opt.get();
				
		Board board = bdRepo.findById(1L).get();
		System.out.println("Board");
		
	}
	
	//@Test
	void testUpdateBoard() {
		{
			Board board = bdRepo.findById(1L).get();
			System.out.println("수정 전");
			System.out.println(board);
			
			board.setTitle("제목 수정");
			bdRepo.save(board);
		}
		{
			Board board = bdRepo.findById(1L).get();
			System.out.println("수정 후");
			System.out.println(board);
		}		
	}
	
//	@Test
//	void testDeleteBoard() {
//		bdRepo.deleteById(2L);
//	}
	
//	@Test
	void testFindAll() {
		List<Board>list =  bdRepo.findAll();
		
		System.out.println("모든 데이터를 출력합니다.");
//		for  Board b: list
	}
	
//	@Test
	public void testQueryAnnotationTest1() {
		List<Board> list = bdRepo.queryAnnotationTest1("title1");
		for (Board b : list) {
			System.out.println(b);
		}
	}
	
//	@Test
	public void testQueryAnnotationTest2() {
		List<Board> list = bdRepo.queryAnnotationTest2("title1");
		for (Board b : list) {
			System.out.println(b);
		}
	}
	
//	@Test
	public void testQueryAnnotationTest3() {
		List<Object[]> list = bdRepo.queryAnnotationTest3("title2");
		for (Object[] b : list) {
			System.out.println(Arrays.toString(b));
		}
	}
	
//	@Test
	public void testQueryAnnotationTest4() {
		List<Object[]> list = bdRepo.queryAnnotationTest4("title2");
		for (Object[] b : list) {
			System.out.println(Arrays.toString(b));
		}
	}
	
	@Test
	public void testQueryAnnotationTest5() {
//		Pageable paging = PageRequest.of(0, 3, Sort.Direction.DESC, "seq");
		// 쿼리문에 소팅이 있기 때문에 BoardRepositoryTest 파일에 소팅 관련 코드가 없어도 됨.
		Pageable paging = PageRequest.of(5, 5);
		List<Board> list = bdRepo.queryAnnotationTest5(paging);
		for (Board b : list) {
			System.out.println("---->" + b);
		}
	}
}
