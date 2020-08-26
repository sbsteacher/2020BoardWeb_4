package com.koreait.pjt.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.koreait.pjt.vo.BoardCmtVO;

public class BoardCmtDAO {
	public static int insCmt(BoardCmtVO param) {
		String sql = " INSERT INTO t_board4_cmt "
				+ " (i_cmt, i_board, i_user, cmt) "
				+ " VALUES "
				+ " (seq_board4_cmt.nextval, ?, ?, ?) ";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_board());
				ps.setInt(2, param.getI_user());
				ps.setNString(3, param.getCmt());
			}
		});
	}
	
	
	public static int updCmt() {
		return 0;
	}
	
	public static int delCmt() {
		return 0;
	}
}
