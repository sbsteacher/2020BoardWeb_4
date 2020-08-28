package com.koreait.pjt.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.vo.BoardDomain;

@WebServlet("/board/list")
public class BoardListSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = (HttpSession)request.getSession();
		
		if(MyUtils.isLogout(request)) {
			response.sendRedirect("/login");
			return;
		}
		
		int page = MyUtils.getIntParameter(request, "page");
		page = (page == 0 ? 1 : page);
		
		int recordCnt = MyUtils.getIntParameter(request, "record_cnt");
		recordCnt = (recordCnt == 0 ? 10 : recordCnt);
		
		BoardDomain param = new BoardDomain();
		param.setRecord_cnt(recordCnt);
		int pagingCnt = BoardDAO.selPagingCnt(param);
		
		Integer beforeRecordCnt = (Integer)hs.getAttribute("recordCnt"); //이전 레코드수 가져오기
		
		//이전 레코드수 값이 있고, 이전 레코드수보다 변경한 레코드 수가 더 크다면 마지막 페이지수로 변경
		if(beforeRecordCnt != null && beforeRecordCnt < recordCnt) {  
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
		
		
		hs.setAttribute("recordCnt", recordCnt); //현재 레코드수 저장
		ViewResolver.forward("board/list", request, response);
	}
}










