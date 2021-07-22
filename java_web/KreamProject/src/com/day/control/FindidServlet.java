package com.day.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.day.exception.FindException;
import com.day.service.CustomerService;

public class FindidServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 요청전달데이터 얻기
		String phone = request.getParameter("phone");
		ServletContext sc = getServletContext();		
		CustomerService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		CustomerService service = CustomerService.getInstance();
 
		//2.비지니스로직 호출
		String path = "";
		try {
			String findid = service.findid(phone);
			request.setAttribute("findid", findid);
			path = "findid.jsp";
		} catch (FindException e) {
			e.printStackTrace();
			path = "findidfail.jsp";
		}
		//5.페이지 이동
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
}
 