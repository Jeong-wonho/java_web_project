package com.day.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.day.dto.Customer;
import com.day.exception.ModifyException;
import com.day.service.CustomerService;

public class ModifypwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		Customer c = new Customer();
		c.setUser_id(id);
		c.setUser_pwd(pwd);		
		ServletContext sc = getServletContext();		
		CustomerService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		CustomerService service = CustomerService.getInstance();
		String path = "";
		try {
			service.update(c);
			path = "profile.jsp";
		} catch (ModifyException e) {
			e.printStackTrace();
			path = "fail.jsp";
			request.setAttribute("msg", e.getMessage());
		}
		//5.페이지 이동
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
 
}
 