package com.day.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.day.dto.Customer;
/**
 * Servlet implementation class Bidbuy2Servlet
 */
public class Bidbuy2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String customprice = request.getParameter("customprice");
		Customer c = (Customer) session.getAttribute("loginInfo");
		session.setAttribute("customprice", customprice);


		String path = "bidbuy2.jsp";
		session.setAttribute("customprice", customprice);
		request.setAttribute("customprice", customprice);
		request.setAttribute("c", c);
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	 
	}
}
