package com.koreait.pjt.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.pjt.Const;
import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.vo.BoardVO;
import com.koreait.pjt.vo.UserVO;

@WebServlet("/board/regmod")
public class BoardRegmodSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//화면 띄우는 용도(등록창/수정창)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		if(MyUtils.isLogout(request)) {
			response.sendRedirect("/login");
			return;
		}
		
		String strI_board = request.getParameter("i_board");
		if(strI_board != null) { //수정
			int i_board = MyUtils.parseStrToInt(strI_board);
			request.setAttribute("data", BoardDAO.selBoard(i_board));
		}
		
		ViewResolver.forward("board/regmod", request, response);
	}

	//처리 용도(DB에 등록/수정)실시
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strI_board = request.getParameter("i_board");
		String title = request.getParameter("title");
		String ctnt = request.getParameter("ctnt");
		
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO) hs.getAttribute(Const.LOGIN_USER);
		
		System.out.println("title : " + title);
		System.out.println("ctnt : " + ctnt);
		
		BoardVO param = new BoardVO();
		param.setTitle(title);
		param.setCtnt(ctnt);
		param.setI_user(loginUser.getI_user());
		int result = 0;
		
		if("".equals(strI_board)) { //등록
			result = BoardDAO.insBoard(param);
			response.sendRedirect("/board/list");
		} else { //수정
			int i_board = MyUtils.parseStrToInt(strI_board);
			param.setI_board(i_board);
			result = BoardDAO.updBoard(param);
			response.sendRedirect("/board/detail?i_board=" + strI_board);
		}
		
	}
}
