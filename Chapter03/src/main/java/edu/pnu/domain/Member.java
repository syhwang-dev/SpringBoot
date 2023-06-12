package edu.pnu.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// 롬복을 사용하면 아래처럼 어노테이션으로 편하게 사용할 수 있음.
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	private Long id;
	private String name;
	private Integer age;
	private String nickname;

}
