// step08
package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.pnu.domain.Member;

public class MemberDao {
	
	private String driver = "org.h2.Driver";
	private String url = "jdbc:h2:tcp://localhost/~/Mission02";
	private String username = "scott";
	private String password = "tiger";
	
	private Connection con;
	
	public MemberDao() {
		try {
			Class.forName(driver);
			
			DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// step10
	public Member getMember(Long id) {
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(String.format("select * from Member where id=%d", id));
			
			rs.next();
			
			Member m = Member.builder()
					.id(rs.getLong("id"))
					.pass(rs.getString("pass"))
					.name(rs.getString("name"))
					.regidate(rs.getDate("regidate"))
					.build();
			rs.close();
			stmt.close();
			
			return m;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// step14.
	public List<Member> getMembers() {
		List<Member> memberList = new ArrayList<>();  // 리턴할 객체 생성
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(String.format("select * from Member order by id"));
			
			while(rs.next()) {
				Member m = Member.builder()
						.id(rs.getLong("id"))
						.pass(rs.getString("pass"))
						.name(rs.getString("name"))
						.regidate(rs.getDate("regidate"))
						.build();
				memberList.add(m);
//				memberList.add(Member.builder()
//						.id(rs.getLong("id"))
//						.pass(rs.getString("pass"))
//						.name(rs.getString("name"))
//						.regidate(rs.getDate("regidate"))
//						.build());
			}
			
			rs.close();
			stmt.close();
			
//			return m;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return memberList;
	}

	// step17
	public int insertMember(Member member) {
		
		try {
			String sql = "insert into Member (id, name, pass) value (?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, member.getId());
			ps.setString(2, member.getName());
			ps.setString(3, member.getPass());
			ps.executeUpdate();
			
			ps.close();
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

	// step20
	public int updateMember(Member member) {
		PreparedStatement ps;
	    try {
	        ps = con.prepareStatement("update member SET name = ?, pass = ? WHERE id = ?");
	        ps.setString(1, member.getName());
	        ps.setString(2, member.getPass());
	        ps.setLong(3, member.getId());
	        ps.executeUpdate();
	        
	        ps.close();
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return -1;
	    }
		return 0;
	}

	// step23
	public int deleteMember(Long id) {
		try {
		Statement st = con.createStatement();
		int cnt = st.executeUpdate(String.format("delete from member where id = %d", id));
 
		st.close();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}
}



