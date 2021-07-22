package com.day.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.day.dto.Customer;
import com.day.dto.Order;

/**
 * Servlet implementation class Immedisell2Servlet
 */
public class Immedisell2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		String path = "immedisell2.jsp";
	 	
		Customer c = (Customer) session.getAttribute("loginInfo");
		Order maxO = (Order) session.getAttribute("maxO");
		System.out.println(c);
		request.setAttribute("c", c);
		request.setAttribute("maxO", maxO);
		System.out.println(maxO);
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}


}
 