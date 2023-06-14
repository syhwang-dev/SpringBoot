package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class LogDao {
	
	private Connection con;
	
	public LogDao() {
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/mission3", "scott", "tiger");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int insertLog(String method, String sqlString, boolean success) {
		
		try {
			Statement st = con.createStatement();
			String sql = String.format("insert into dblog (method, sqlstring, success) values ('%s', '%s', '%s')",
					method, sqlString,  success);			
			
			int result = st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
		
	}

}
