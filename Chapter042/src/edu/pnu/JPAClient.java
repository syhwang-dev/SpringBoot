package edu.pnu;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import edu.pnu.domain.Board;

public class JPAClient {

	public static void main(String[] args) {
//		System.out.println("JPA");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter042");
		EntityManager em = emf.createEntityManager();
		
//		// 트랙잭션 생성
//		EntityTransaction tx =  em.getTransaction();
//		
//		// 트랜잭션 시작
//		tx.begin();
//		
//		Board b = new Board();
//		b.setTitle("Title");
//		b.setWriter("Writer");
//		b.setContnet("Content");
//		b.setCreateDate(new Date());
//		b.setCnt(0L);
//		
//		em.persist(b);
//		
//		// 트랜잭션 종료
//		tx.commit();
		
		insertData(em);
		updataData(em);
		deleteData(em);
		
		em.close();
		emf.close();
		
	}
	
	// create 모드이기 때문에 실행될 때 마다 테이블을 계속 새로 만듦.
	
	static void insertData(EntityManager em) {  // static을 붙이는 이유: main이 static이기 때문에
		// 트랙잭션 생성
		EntityTransaction tx =  em.getTransaction();
		try {
			// 트랜잭션 시작
			tx.begin();
			
			// 10개의 데이터 넣기
			for (int i=1; i<=10; i++) {
				Board b = new Board();
				b.setTitle("JAP" + i);
				b.setWriter("관리자" + i);
				b.setContnet("내용" + i);
				b.setCreateDate(new Date());
				b.setCnt(0L);
				
				em.persist(b);
			}
			
			// 트랜잭션 종료
			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
	}
	
	static void updataData(EntityManager em) {
		
		Board b = em.find(Board.class, 5L);  // 5번 데이터
		
		b.setTitle("나는 새로운 타이틀이다.");
		
		// 트랙잭션으로 싸주기
		EntityTransaction tx =  em.getTransaction();
		try {
			// 트랜잭션 시작
			tx.begin();
			
			em.persist(b);
			
			// 트랜잭션 종료
			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
	}
	
	static void deleteData(EntityManager em) {
		
		Board b = em.find(Board.class, 8L);
		
		b.setTitle("나는 새로운 타이틀이다.");
		
		// 트랙잭션으로 싸주기
		EntityTransaction tx =  em.getTransaction();
		try {
			// 트랜잭션 시작
			tx.begin();
			
			em.remove(b);

			// 트랜잭션 종료
			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
	}
}
