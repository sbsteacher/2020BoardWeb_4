package com.koreait.pjt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewResolver {
	
	public static void forward(String fileNm, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String jsp = String.format("/WEB-INF/jsp/%s.jsp", fileNm);
		request.getRequestDispatcher(jsp).forward(request, response);
	}
	
}
