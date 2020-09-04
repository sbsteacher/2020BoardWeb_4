package com.koreait.pjt.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.koreait.pjt.vo.BoardDomain;
import com.koreait.pjt.vo.BoardVO;

public class BoardDAO {
	
	public static int insBoardLike(BoardVO param) {
		String sql = " INSERT INTO t_board4_like "
				+ " (i_user, i_board) "
				+ " VALUES "
				+ " (?, ?) ";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_user());
				ps.setInt(2, param.getI_board());
			}
		});
	}
	
	public static int insBoard(BoardVO param) {
		String sql = " INSERT INTO t_board4 "
				+ " (i_board, title, ctnt, i_user) "
				+ " VALUES "
				+ " (seq_board4.nextval, ?, ?, ?) ";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setNString(1, param.getTitle());
				ps.setNString(2, param.getCtnt());
				ps.setInt(3, param.getI_user());
			}
		});
	}
	
	public static List<BoardDomain> selBoardList(BoardDomain param) {
		List<BoardDomain> list = new ArrayList();
		/*
		String sql = " SELECT A.i_board, A.title, A.hits, A.i_user, A.r_dt, B.nm "
				+ " FROM t_board4 A INNER JOIN t_user B ON A.i_user = B.i_user "
				+ " ORDER BY i_board DESC ";
		*/
		String sql = " SELECT A.* FROM ( "
				+ " SELECT ROWNUM as RNUM, A.* FROM ( "
				+ " 		SELECT A.i_board, A.title, A.hits, A.i_user, A.r_dt, B.nm, B.profile_img "
				+ "         , nvl(C.cnt, 0) as like_cnt "
				+ "			, nvl(D.cnt, 0) as cmt_cnt "
				+ "			, DECODE(E.i_board, null, 0, 1) as yn_like "
				+ " 		FROM t_board4 A "
				+ " 		INNER JOIN t_user B "
				+ " 		ON A.i_user = B.i_user"
				+ "			LEFT JOIN ( "  
				+ "    			SELECT i_board, count(i_board) as cnt FROM t_board4_like GROUP BY i_board " 
				+ "			) C "  
				+ "			ON A.i_board = C.i_board "
				+ "			LEFT JOIN ( "
				+ "				SELECT i_board, count(i_board) as cnt FROM t_board4_cmt GROUP BY i_board "
				+ "			) D "
				+ "			ON A.i_board = D.i_board "
				+ "			LEFT JOIN ( "
				+ "				 SELECT i_board FROM t_board4_like WHERE i_user = ? "
				+ "			) E "
				+ "			ON A.i_board = E.i_board "
				+ " 		WHERE  ";
				switch(param.getSearchType()) {
				case "a":					
					sql += " A.title like ? ";
					break;
				case "b":
					sql += " A.ctnt like ? ";
					break;
				case "c":
					sql += " (A.ctnt like ? or A.title like ?) ";
					break;
				}
		
				sql += " 		ORDER BY i_board DESC "
				+ " 	) A WHERE ROWNUM <= ? "
				+ " ) A WHERE A.RNUM > ? ";
		
		int result = JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				int seq = 1;
				ps.setInt(seq, param.getI_user()); //로그인한 사람의 i_user
				ps.setNString(++seq, param.getSearchText());
				
				if("c".equals(param.getSearchType())) {
					ps.setNString(++seq, param.getSearchText());	
				}
				
				ps.setInt(++seq, param.geteIdx());
				ps.setInt(++seq, param.getsIdx());
			}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				while(rs.next()) {
					int i_board = rs.getInt("i_board");	
					String title = rs.getNString("title");
					int hits = rs.getInt("hits");
					int i_user = rs.getInt("i_user");
					String r_dt = rs.getNString("r_dt");
					String nm = rs.getNString("nm");
					String profile_img = rs.getNString("profile_img");
					int like_cnt = rs.getInt("like_cnt");
					int cmt_cnt = rs.getInt("cmt_cnt");
					int yn_like = rs.getInt("yn_like");
					
					BoardDomain vo = new BoardDomain();
					vo.setI_board(i_board);
					vo.setTitle(title);
					vo.setHits(hits);
					vo.setI_user(i_user);
					vo.setR_dt(r_dt);
					vo.setNm(nm);
					vo.setProfile_img(profile_img);
					vo.setLike_cnt(like_cnt);
					vo.setCmt_cnt(cmt_cnt);
					vo.setYn_like(yn_like);
					
					list.add(vo);
				}
				return 1;
			}			
		});
		
		return list;
	}
	
	public static BoardDomain selBoard(final BoardVO param) {
		final BoardDomain result = new BoardDomain();
		result.setI_board(param.getI_board());
		
		String sql = " SELECT B.profile_img, B.nm, A.i_user "
				+ " , A.title, A.ctnt, A.hits, TO_CHAR(A.r_dt, 'YYYY/MM/DD HH24:MI') as r_dt"
				+ " , DECODE(C.i_user, null, 0, 1) as yn_like"
				+ " , nvl(D.cnt, 0) as like_cnt "
				+ " FROM t_board4 A "
				+ " INNER JOIN t_user B "
				+ " ON A.i_user = B.i_user "
				+ " LEFT JOIN t_board4_like C "
				+ " ON A.i_board = C.i_board "
				+ " AND C.i_user = ? "
				+ " LEFT JOIN ("
				+ " 	SELECT i_board, count(i_board) as cnt FROM t_board4_like "
				+ "		WHERE i_board = ? "
				+ "		GROUP BY i_board "
				+ " ) D"
				+ " ON A.i_board = D.i_board "
				+ " WHERE A.i_board = ? ";
		
		int resultInt = JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_user());
				ps.setInt(2, param.getI_board());
				ps.setInt(3, param.getI_board());
			}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				if(rs.next()) {
					result.setProfile_img(rs.getNString("profile_img"));
					result.setI_user(rs.getInt("i_user")); //작성자 i_user
					result.setNm(rs.getNString("nm"));
					result.setTitle(rs.getNString("title"));
					result.setCtnt(rs.getNString("ctnt"));
					result.setHits(rs.getInt("hits"));
					result.setR_dt(rs.getNString("r_dt"));
					result.setYn_like(rs.getInt("yn_like"));
					result.setLike_cnt(rs.getInt("like_cnt"));
				}
				return 1;
			}
		});
		
		return result;
	}
	
	//페이징 숫자 가져오기 
	public static int selPagingCnt(final BoardDomain param) {
		String sql = " SELECT CEIL(COUNT(i_board) / ?) FROM t_board4 WHERE ";
		
		switch(param.getSearchType()) {
		case "a":					
			sql += " title like ? ";
			break;
		case "b":
			sql += " ctnt like ? ";
			break;
		case "c":
			sql += " (ctnt like ? or title like ?) ";
			break;
		}				
		
		return JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getRecord_cnt());
				ps.setNString(2, param.getSearchText());				
				if(param.getSearchType().equals("c")) {
					ps.setNString(3, param.getSearchText());	
				}
			}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				if(rs.next()) {
					return rs.getInt(1);
				}
				return 0;
			}
			
		});
	}
	
	public static List<BoardDomain> selBoardLikeList(final int i_board) {
		List<BoardDomain> list = new ArrayList();		
		String sql = " SELECT B.i_user, B.nm, B.profile_img"
				+ " FROM t_board4_like A"
				+ " INNER JOIN t_user B"
				+ " ON A.i_user = B.i_user"
				+ " WHERE A.i_board = ?"
				+ " ORDER BY A.R_DT ASC "; 		
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {
			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setInt(1, i_board);
			}
			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				while(rs.next()) {
					BoardDomain vo = new BoardDomain();
					vo.setI_user(rs.getInt("i_user"));
					vo.setNm(rs.getNString("nm"));
					vo.setProfile_img(rs.getNString("profile_img"));					
					list.add(vo);
				}
				return 1;
			}
		});		
		return list;
	}
	
	
	public static int updBoard(final BoardVO param) {
		String sql = " UPDATE t_board4 "
				+ " SET m_dt = sysdate "
				+ " , title = ? "
				+ " , ctnt = ? "
				+ " WHERE i_board = ? "
				+ " AND i_user = ? ";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setNString(1, param.getTitle());
				ps.setNString(2, param.getCtnt());
				ps.setInt(3, param.getI_board());
				ps.setInt(4, param.getI_user());
			}
		});
	}
	
	public static void addHits(final int i_board) {
		String sql = " UPDATE t_board4 "
				+ " SET hits = hits + 1 "
				+ " WHERE i_board = ? ";
		JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, i_board);
			}
		});
	}
	
	public static int delBoardLike(BoardVO param) {
		String sql = " DELETE FROM t_board4_like "
				+ " WHERE i_user = ? AND i_board = ? ";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_user());
				ps.setInt(2, param.getI_board());
			}
		});
	}
	
	public static int delBoard(final BoardVO param) {
		String sql = " DELETE FROM t_board4 WHERE i_board = ?  AND i_user = ? ";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_board());
				ps.setInt(2, param.getI_user());
			}
		});
	}
	
}
















