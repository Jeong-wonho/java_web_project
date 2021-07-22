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
 * Servlet implementation class ImmedibuyendServlet
 */
public class ImmedibuyendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Customer b = (Customer) session.getAttribute("loginInfo");
		Order minO = (Order) session.getAttribute("minO");
		minO.setBuyer_id(b);
		minO.setOrder_type(0);
	 	
		ServletContext sc = getServletContext();
		OrderService serviceO;
		OrderService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		serviceO = OrderService.getInstance();
		try {
			serviceO.update(minO);
			request.setAttribute("customprice", minO.getOrder_price());

		} catch (ModifyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String path = "bidbuyend.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);

	}

}
 