package com.koreait.pjt.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.koreait.pjt.vo.BoardVO;

public class BoardDAO {
	public static List<BoardVO> selBoardList() {
		final List<BoardVO> list = new ArrayList();
		
		String sql = " SELECT i_board, title, hits, i_user, r_dt FROM t_board4 ";
		int result = JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				while(rs.next()) {
					
					
					BoardVO vo = new BoardVO();
				}
				return 0;
			}
			
		});
		
		return list;
	}
}
