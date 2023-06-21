package edu.pnu;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.querydsl.core.BooleanBuilder;

import edu.pnu.domain.Board;
import edu.pnu.domain.QBoard;
import edu.pnu.persistence.DynamicBoardRepository;

@SpringBootTest
public class DynamicQueryTest {
	
	@Autowired
	private DynamicBoardRepository boardRepo;
	
	private void test(String searchCondition, String seachKeyword) {
		BooleanBuilder builder = new BooleanBuilder();
		QBoard qboard = QBoard.board;
		
		if (searchCondition.equals("TITLE")) {
			// testDynamicQuery()에서 값이 넘어오면 select b from Board b where title like '%'||:searchKeyword||'%'와 같은 쿼리가 만들어지는 것임. 
			builder.and(qboard.title.like("%" + seachKeyword + "%"));
		} else if (searchCondition.equals("CONTENT")) {
			// testDynamicQuery()에서 값이 넘어오면 select b from Board b where content like '%'||:searchKeyword||'%'와 같은 쿼리가 만들어지는 것임. 
			builder.and(qboard.content.like("%" + seachKeyword + "%"));
		}
		
		Iterable<Board> list = boardRepo.findAll(builder);
		for (Board b : list) {
			System.out.println("---->" + b);
		}
	}
	
	private void test1(Map<String, String> map) {
		
		BooleanBuilder builder = new BooleanBuilder();
		QBoard qboard = QBoard.board;
		
		Set<String> keys = map.keySet();
		for(String key : keys) {
//			System.out.println("----> " + key);
			if (key.equals("TITLE")) {
				builder.and(qboard.title.like("%" + map.get("TITLE") + "%"));
			} else if (key.equals("CONTENT")) {
				builder.and(qboard.content.like("%" + map.get("CONTENT") + "%"));
			}
		
		}
	}
	
	@Test
	public void testDynamicQuery() {
//		test("TITLE", "title1");
//		test("CONTENT", "content2");
//		test1("TITLE", "title1", "CONTENT", "content2");  // 이 코드를 실행하기 위해 test1을 만들어 map을 활용
		
		Map<String, String> map = new HashMap<>();
		map.put("TITLE", "title1");
		map.put("CONTENT", "content1");
		test1(map);
	}
	
	@Test
	public void queryInsertBoard() {
		for (int i=0; i<=10; i++) {
			boardRepo.save(Board.builder()
					.title("Title" + i)
					.writer("writer" + i)
					.content("content" + i)
					.createDate(new Date())
					.cnt(0L)
					.build());
		}	
	}
	
	// Title이 '%title1%'인 데이터를 출력
	@Test
	public void testDynamicQuery1() {
		BooleanBuilder builder = new BooleanBuilder();
		QBoard qboard = QBoard.board;
		
		builder.and(qboard.title.like("%title1%"));
		
		Iterable<Board> list = boardRepo.findAll(builder);
		
		for (Board b : list) {
			System.out.println(b);
		}
	}
	
	// 데이터를 출력할 때 Page 기능 추가
//	@Test
	public void testDynamicQuery3() {
	
		
		
	}
	
	

}
