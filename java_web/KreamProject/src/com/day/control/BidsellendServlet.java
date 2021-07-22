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
import com.day.service.OrderService;

/**
 * Servlet implementation class BidsellendServlet
 */
public class BidsellendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String customprice = (String) session.getAttribute("customprice");
		Customer s = (Customer) session.getAttribute("loginInfo");
		Customer c = new Customer();
		Product p = (Product) session.getAttribute("productInfo");
		String size = (String) session.getAttribute("size");
		ServletContext sc = getServletContext();
		OrderService serviceO;
		OrderService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		serviceO = OrderService.getInstance();

		String path = "bidbuyend.jsp";
		Order o = new Order();
		o.setProd_num(p);
		o.setBuyer_id(c);
		o.setSeller_id(s);
		o.setOrder_type(0);
		o.setOrder_size(Integer.parseInt(size));
		o.setOrder_price(Integer.parseInt(customprice));

		try {
			request.setAttribute("customprice", customprice);
			serviceO.insert(o);
		} catch (Exception e) {
			System.out.println("실패");
			e.printStackTrace();
			
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
 