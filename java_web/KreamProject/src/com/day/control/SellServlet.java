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
import com.day.dto.Product;
import com.day.exception.FindException;
import com.day.service.CustomerService;
import com.day.service.OrderService;
import com.day.service.ProductService;

/**
 * Servlet implementation class SellServlet
 */
public class SellServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "sell.jsp";
		
		HttpSession session = request.getSession();
		ProductService serviceP;
		CustomerService serviceC;
		OrderService serviceO;
		
		String prod_num = "1";
		String user_size = "230"; // 사이즈받아야하는값
		String user_id = "lsh1234";
		ServletContext sc = getServletContext();
		ProductService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		CustomerService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		OrderService.envProp = sc.getRealPath(sc.getInitParameter("env"));


		serviceP = ProductService.getInstance();
		serviceC = CustomerService.getInstance();
		serviceO = OrderService.getInstance();
 		
		try {
			Product p = serviceP.findByNo(Integer.parseInt(prod_num));
			request.setAttribute("p", p);
			request.setAttribute("user_size", user_size);
			session.setAttribute("productInfo", p);
			session.setAttribute("size", user_size);
			Customer c = serviceC.findById(user_id);
			request.setAttribute("c", c);
			session.setAttribute("loginInfo", c);
			Order o = new Order();
			o.setSeller_id(c);
			o.setProd_num(p);
			o.setOrder_size(Integer.parseInt(user_size));
			Order minO = serviceO.findMinOrder(o);
			session.setAttribute("minO", minO);
			Order maxO = serviceO.findMaxOrder(o);
			session.setAttribute("maxO", maxO);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
