package com.koreait.pjt.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.Const;
import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.vo.BoardDomain;

@WebServlet("/board/list")
public class BoardListSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(MyUtils.isLogout(request)) {
			response.sendRedirect("/login");
			return;
		}
		
		int recordCnt = MyUtils.getIntParameter(request, "record_cnt");
		recordCnt = (recordCnt == 0 ? 10 : recordCnt);
		
		int page = MyUtils.getIntParameter(request, "page");
		page = (page == 0 ? 1 : page);
		System.out.println("page : " + page);
		
		int eIdx = page * recordCnt;
		int sIdx = eIdx - recordCnt;
		
		BoardDomain param = new BoardDomain();
		param.setsIdx(sIdx);
		param.seteIdx(eIdx);
		
		param.setRecord_cnt(recordCnt);
		
		request.setAttribute("pagingCnt", BoardDAO.selPagingCnt(param));
		request.setAttribute("list", BoardDAO.selBoardList(param));
		ViewResolver.forward("board/list", request, response);
	}
}










