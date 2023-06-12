package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.pnu.domain.Member;

public class MemberDao {
//	private String driver = "org.h2.Driver";
//	private String url = "jdbc:h2:tcp://localhost/~/chapter03";
//	private String username = "scott";
//	private String password = "tiger";
	
	private Connection con;
	
	// Database Connection 설정 (con)
	public MemberDao() {
		try {
			Class.forName("org.h2.Driver");
//			DriverManager.getConnection(url, username, password);
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/chaper03", "scott", "tiger");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int insertMember(Member m) {
		try {
			Statement st = con.createStatement();
			String sql = String.format("insert into member (name, age, nickname) values ('%s', %d, '%s')", m.getName(), m.getAge(), m.getNickname());			
			
			return st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<Member> getMembers() {
		List<Member> mList = new ArrayList<>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from member order by id");
			
			while (rs.next()) {
				Member m = Member.builder()
							.id(rs.getLong("id"))
							.name(rs.getString("name"))
							.age(rs.getInt("age"))
							.build();
				mList.add(m);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mList;
	}
	
	public Member getMember(Long id) {
		String sql = "select * from member where id=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			if (rs != null) {
				return Member.builder()
						.id(rs.getLong("id"))
						.name(rs.getString("name"))
						.age(rs.getInt("age"))
						.build();	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
