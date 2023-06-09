package com.rubypaper.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder  // 빌드 패턴을 사용하게 해줌.
@NoArgsConstructor
@AllArgsConstructor
public class BoardVO {
	private int seq;
	private String title;
	private String writer;
	private String content;
	private Date createDate;
	private int cnt;
}
