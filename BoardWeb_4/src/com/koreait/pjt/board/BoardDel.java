package com.koreait.pjt.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.MyUtils;
import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.vo.BoardVO;
import com.koreait.pjt.vo.UserVO;

@WebServlet("/board/del")
public class BoardDel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String strI_board = request.getParameter("i_board");
    	int i_board = MyUtils.parseStrToInt(strI_board);
    	
    	UserVO loginUser = MyUtils.getLoginUser(request);
    	if(loginUser == null) {
    		response.sendRedirect("/login");
    		return;
    	}
   
    	BoardVO param = new BoardVO();
    	param.setI_board(i_board);
    	param.setI_user(loginUser.getI_user());
    	
    	int result = BoardDAO.delBoard(param);
    	response.sendRedirect("/board/list");
	}

}





