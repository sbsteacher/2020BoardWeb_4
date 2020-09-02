package com.koreait.pjt.board;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.MyUtils;
import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.vo.BoardVO;
import com.koreait.pjt.vo.UserVO;

@WebServlet("/board/toggleLike")
public class BoardToggleLike extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strI_board = request.getParameter("i_board");
		String strYn_like = request.getParameter("yn_like");
		String page = request.getParameter("page");
		String record_cnt = request.getParameter("record_cnt");
		String searchText = request.getParameter("searchText");
		String searchType = request.getParameter("searchType");
		
		searchText = URLEncoder.encode(searchText, "UTF-8");
		
		UserVO loginUser = MyUtils.getLoginUser(request);
		
		int i_board = MyUtils.parseStrToInt(strI_board);
		int yn_like = MyUtils.parseStrToInt(strYn_like, 3);
		
		BoardVO param = new BoardVO();
		param.setI_board(i_board);
		param.setI_user(loginUser.getI_user()); //로그인한 사람의 i_user
		
		if(yn_like == 0) { //좋아요 처리
			BoardDAO.insBoardLike(param);
		} else if(yn_like == 1) { //좋아요 취소 처리
			BoardDAO.delBoardLike(param);
		}
		
		String target = String.format("/board/detail?i_board=%s&page=%s&record_cnt=%s&searchText=%s&searchType=%s"
				, strI_board, page, record_cnt, searchText, searchType);
		
		response.sendRedirect(target);
	}
}
