package com.koreait.pjt.db;

import java.sql.*;

public class JdbcTemplate {
	//insert, update, delete에 쓸 친구
	public static int executeUpdate(String sql, JdbcUpdateInterface jdbc) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DbCon.getCon();
			ps = con.prepareStatement(sql);			
			result = jdbc.update(ps);
		} catch (Exception e) {		
			e.printStackTrace();
		} finally {
			DbCon.close(con, ps);
		}
		
		return result;
	}
}
