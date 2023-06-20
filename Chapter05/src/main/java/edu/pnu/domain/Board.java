package edu.pnu.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


// 3
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
// 2
@Entity
public class Board {
	// 4
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	// @GeneratedValue: 자동으로 시퀀스를 증가시킴. auto-increment와 같음.
	// strategy: 자동으로 증가되는 전략
	// 여기까지 테이블을 생성하는 설정 - 이대로 실행하면 테이블이 생성됨.
	// @Entity 로 인해 Board 라는 테이블 생성

	// 1
	private Long seq;
	private String title;
	@Column(length = 10)
	private String writer;
	private String content;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	private Long cnt;
}

