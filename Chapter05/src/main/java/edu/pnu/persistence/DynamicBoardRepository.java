package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import edu.pnu.domain.Board;

public interface DynamicBoardRepository extends QuerydslPredicateExecutor<Board>, JpaRepository<Board, Long> {

	// Board 테이블에 테이터 100개 입력
	List<Board> findByTitleLike(String searchKeyword);
	
	// Junit에서 실행 > Title이 "%Title1%인 데이터를 출력"
	List<Board> findByTitleContainingAndCntGreaterThan(String searchKeyword, Long cnt);

	// Junit에서 실행 > cnt가 50보다 큰 데이터를 
}
