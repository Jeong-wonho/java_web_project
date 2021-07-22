package com.day.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.day.dto.Customer;
import com.day.dto.Order;
import com.day.exception.ModifyException;
import com.day.service.OrderService;

/**
 * Servlet implementation class ImmedesellendServlet
 */
public class ImmedesellendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Customer s = (Customer) session.getAttribute("loginInfo");
		Order maxO = (Order) session.getAttribute("maxO");
		maxO.setSeller_id(s);
		maxO.setOrder_type(1);
	 	
		ServletContext sc = getServletContext();
		OrderService serviceO;
		OrderService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		serviceO = OrderService.getInstance();
		
		try {
			serviceO.update(maxO);
			request.setAttribute("customprice", maxO.getOrder_price());
		} catch (ModifyException e) {
			e.printStackTrace();
		}
		String path = "bidbuyend.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
 