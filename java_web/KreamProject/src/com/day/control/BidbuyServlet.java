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

public class BidbuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer c = (Customer) session.getAttribute("loginInfo");
		Order minO = (Order) session.getAttribute("minO");
		Order maxO = (Order) session.getAttribute("maxO");


		String path = "bidbuy.jsp";
		
		request.setAttribute("c", c);
		request.setAttribute("minO", minO);
		request.setAttribute("maxO", maxO);
		
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
}
 