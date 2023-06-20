package edu.pnu.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.pnu.domain.Board;

import java.util.List;


public interface BoardRepository extends JpaRepository<Board, Long> {
	
	// 페이징 1
	List<Board> findByTitleLike(String title, Pageable paging);

	// 페이징 2
	Page<Board> findAll(Pageable paging);
	
	// 쿼리 메소드 4개 생성
	// 테스트 데이터를 100건 입력 (cnt는 random으로 0~100까지 입력)
	// BoardDataInitializer 파일 생성
//	List<Board> findByTitleContaining(String searchKeyword);
//	List<Board> findByTitleLike(String searchKeyword);
	
	// title에 "1"이 포함되는 데이터  출력
//	List<Board> findByTitleContainingAndCntGreaterThan(String searchKeyword, Long cnt);
	
	// title에 "1"이 포함되면서 cnt가 50보다 큰 데이터 출력
//	List<Board> findByCntBetweenOrderBySeqAsc(Long from, Long to);
	
	// cnt가 10 ~ 50 사이인 데이터를 seq 오름차순으로 출력
//	List<Board> findByTitleContainingOrContentContainingOrderBySeqDesc(String searchKeyword1, String searchKeyword2);
//	List<Board> findByTitleLikeOrContentLikeOrderBySeqDesc(String searchKeyword1, String searchKeyword2);

	@Query("select b from Board b where b.title like %?1% order by b.seq desc") 
	// select b from Board b: Board는 테이블 이름이 아니라 클래스명임.
	// %?1%: ?가 없으면 에러 발생
	List<Board> queryAnnotationTest1(String searchKeyword);
	
	@Query("select b from Board b where b.title like %:searchKeyword% order by b.seq desc") 
	// select b from Board b: Board는 테이블 이름이 아니라 클래스명임.
	// %?1%: ?가 없으면 에러 발생
	List<Board> queryAnnotationTest2(String searchKeyword);
	
	// 자동으로 매핑을 시켜주는 방식
	
	@Query("select b.seq, b.title, b.writer, b.createDate from Board b "
			+ "where b.title like %:searchKeyword% order by b.seq desc")
	// 쿼리문이 길어서 + 로 나누는 경우 '공백' 사용 유의할 것.
	List<Object[]> queryAnnotationTest3(String searchKeyword);
	
	// Query 안에 값이 두 개 이상일 때, value 사용
	// 주의할 것: createDate 말고 create_date를 사용할 것
	@Query(value="select seq, title, writer, create_date "
			+ "from board where title like '%'||:sk||'%' "
			+ "order by seq desc", nativeQuery = true)
	List<Object[]> queryAnnotationTest4(@Param("sk") String searchKeyword);
	
	@Query("select b from Board b order by b.seq asc") // where 절이 없기 때문에 전부 가져옴.
	// 쿼리문에 소팅이 있기 때문에 BoardRepositoryTest 파일에 소팅 관련 코드가 없어도 됨.
	List<Board> queryAnnotationTest5(Pageable paging);
	
}




