package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.pnu.domain.MemberVO;

@Repository
public class MemberDaoH2Impl implements MemberInterface {
	
//	private Connection con;
	
	@Autowired
	private DataSource dataSource;
	
//	public MemberDaoH2Impl() {
//		try {
//			Class.forName("org.h2.Driver");
////			DriverManager.getConnection(url, username, password);
//			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/mission3", "scott", "tiger");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	@Override
	public List<MemberVO> getMembers() {
		List<MemberVO> mList = new ArrayList<>();
		try {
			Statement st = dataSource.getConnection().createStatement();
			ResultSet rs = st.executeQuery("select * from member order by id");
			
			while (rs.next()) {
				MemberVO m = MemberVO.builder()
							.id(rs.getInt("id"))
							.pass(rs.getString("pass"))
							.name(rs.getString("name"))
							.build();
				mList.add(m);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return mList;
	}

	@Override
	public MemberVO getMember(Integer id) {
		String sql = "select * from member where id=?";
		PreparedStatement ps;
		try {
			ps = dataSource.getConnection().prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			if (rs != null) {
				return MemberVO.builder()
						.id(rs.getInt("id"))
						.pass(rs.getString("pass"))
						.name(rs.getString("name"))
						.build();	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public MemberVO addMember(MemberVO member) {
		try {
			Statement st = dataSource.getConnection().createStatement();
			String sql = String.format("insert into member (name, pass) values ('%s', '%s')", member.getName(), member.getPass());			
			
			int result = st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}

	@Override
	public MemberVO updateMember(MemberVO member) {
		PreparedStatement ps;
	    try {
	        ps = dataSource.getConnection().prepareStatement("update member SET name = ?, pass = ? WHERE id = ?");
	        ps.setString(1, member.getName());
	        ps.setString(2, member.getPass());
	        ps.setInt(3, member.getId());
	        ps.executeUpdate();
	        
	        ps.close();
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return member;
	}

	@Override
	public int deleteMember(Integer id) {
		try {
		Statement st = dataSource.getConnection().createStatement();
		int cnt = st.executeUpdate(String.format("delete from member where id = %d", id));
 
		st.close();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

}
