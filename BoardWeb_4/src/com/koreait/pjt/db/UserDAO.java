package com.koreait.pjt.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.koreait.pjt.vo.UserVO;

public class UserDAO {
	public static int insUser(UserVO param) {		
		String sql = " INSERT INTO t_user "
				+ " (i_user, user_id, user_pw, nm, email) "
				+ " VALUES "
				+ " (seq_user.nextval, ?, ?, ?, ?) ";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			@Override
			public int update(PreparedStatement ps) throws SQLException {				
				ps.setNString(1, param.getUser_id());
				ps.setNString(2, param.getUser_pw());
				ps.setNString(3, param.getNm());
				ps.setNString(4, param.getEmail());
				return ps.executeUpdate();
			}
		});
	}
}
