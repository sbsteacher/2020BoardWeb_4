package com.koreait.pjt.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.koreait.pjt.vo.UserVO;

public class UserDAO {
	
	public static int insUser(UserVO param) {		
		String sql = " INSERT INTO t_user "
				+ " (i_user, user_id, user_pw, nm, email) "
				+ " VALUES "
				+ " (seq_user.nextval, ?, ?, ?, ?) ";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			@Override
			public void update(PreparedStatement ps) throws SQLException {				
				ps.setNString(1, param.getUser_id());
				ps.setNString(2, param.getUser_pw());
				ps.setNString(3, param.getNm());
				ps.setNString(4, param.getEmail());				
			}
		});
	}
	
	//0:에러 발생, 1:로그인 성공, 2:비밀번호 틀림, 3:아이디 없음
	public static int selUser(UserVO param) {
		
		String sql = " SELECT i_user, user_pw, nm "
				+ " FROM t_user "
				+ " WHERE user_id = ? ";
				
		return JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {
			@Override
			public ResultSet prepared(PreparedStatement ps) throws SQLException {
				ps.setNString(1, param.getUser_id());				
				return ps.executeQuery();
			}
			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				if(rs.next()) {
					String dbPw = rs.getNString("user_pw");					
					if(dbPw.equals(param.getUser_pw())) { //로그인 성공
						int i_user = rs.getInt("i_user");
						String nm = rs.getNString("nm");						
						param.setUser_pw(null);
						param.setI_user(i_user);
						param.setNm(nm);						
						return 1;
					} else { //비밀번호 틀림
						return 2; 
					}
				} else { //아이디 없음
					return 3;
				}
			}		
		});
	}
}

