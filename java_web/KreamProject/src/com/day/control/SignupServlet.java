package com.day.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.day.dto.Customer;
import com.day.exception.AddException;
import com.day.service.CustomerService;

public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 요청전달데이터 얻기
		String id = request.getParameter("id");		
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String ssn = request.getParameter("ssn");
		String phone = request.getParameter("phone");
		String addr = request.getParameter("addr");
		
		Customer c = new Customer(id, pwd, name, ssn, phone, addr);

		ServletContext sc = getServletContext();		
		CustomerService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		CustomerService service = CustomerService.getInstance();
		
		//2.비지니스로직 호출
		String path = "";
		try {
			Customer signup = service.signup(c);
			request.setAttribute("c", signup);
			path = "success.jsp";
		} catch (AddException e) {
			e.printStackTrace();
			path = "fail.jsp";
		}
		//5.페이지 이동
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}