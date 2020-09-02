package com.koreait.pjt.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.vo.BoardDomain;
import com.koreait.pjt.vo.UserVO;

@WebServlet("/board/list")
public class BoardListSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserVO loginUser = MyUtils.getLoginUser(request);
		if(loginUser == null) {
			response.sendRedirect("/login");
			return;
		}
		
		String searchText = request.getParameter("searchText");
		searchText = (searchText == null ? "": searchText);
		
		int page = MyUtils.getIntParameter(request, "page");
		page = (page == 0 ? 1 : page);
		
		int recordCnt = MyUtils.getIntParameter(request, "record_cnt");
		recordCnt = (recordCnt == 0 ? 10 : recordCnt);
		
		BoardDomain param = new BoardDomain();
		param.setI_user(loginUser.getI_user());
		param.setRecord_cnt(recordCnt);
		param.setSearchText("%" + searchText + "%");
		int pagingCnt = BoardDAO.selPagingCnt(param); //
				
		//이전 레코드수 값이 있고, 이전 레코드수보다 변경한 레코드 수가 더 크다면 마지막 페이지수로 변경
		if(page > pagingCnt) {  
			page = pagingCnt; //마지막 페이지 값으로 변경
		}
		request.setAttribute("page", page); 
		System.out.println("page : " + page);
		
		int eIdx = page * recordCnt;
		int sIdx = eIdx - recordCnt;
		
		param.setsIdx(sIdx);
		param.seteIdx(eIdx);
		
		request.setAttribute("pagingCnt", pagingCnt);
		request.setAttribute("list", BoardDAO.selBoardList(param));
				
		ViewResolver.forward("board/list", request, response);
	}
}










