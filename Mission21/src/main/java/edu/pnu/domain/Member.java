// step03 member 파일 만들기
package edu.pnu.domain;

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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	private Long id;
	private String pass;
	private String name;
	private Date regidate;
	
//	public Member() { }
//	
//	public Member(Long id, String pass, String name, Date regidate) {
//		this.id = id;
//		this.pass = pass;
//		this.name = name;
//		this.regidate = regidate;
//	}
	
}
