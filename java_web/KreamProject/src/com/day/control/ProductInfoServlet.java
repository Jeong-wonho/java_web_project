package com.day.control;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.day.dto.Bookmark;
import com.day.dto.Order;
import com.day.dto.Product;
import com.day.exception.FindException;
import com.day.service.BookmarkService;
import com.day.service.CustomerService;
import com.day.service.OrderService;
import com.day.service.ProductService;


public class ProductInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 1. 요청전달데이터얻기
		String prod_num = request.getParameter("prod_num");
		String id = "ash1234";
		
		System.out.println("prodnum:" + prod_num);
		ProductService service_p;
		OrderService service_o;
		BookmarkService service_b;

		
	  //2. 비지니스 로직 호출 
	  ServletContext sc = getServletContext();
	  ProductService.envProp = sc.getRealPath(sc.getInitParameter("env"));
	  OrderService.envProp = sc.getRealPath(sc.getInitParameter("env"));
	  CustomerService.envProp = sc.getRealPath(sc.getInitParameter("env"));
	  BookmarkService.envProp = sc.getRealPath(sc.getInitParameter("env"));
	  
	  service_p = ProductService.getInstance(); 
	  service_o = OrderService.getInstance(); 
	  service_b = BookmarkService.getInstance();
	   
	  String path = ""; 
	  try {
		  //데이터가지고오기!
		  Product p = service_p.findByNo(Integer.parseInt(prod_num));
		  Map<Order, Integer> minmap = service_o.findMinprice(Integer.parseInt(prod_num));
		  List<Order> recent = service_o.findRecentOrder(Integer.parseInt(prod_num));
		  List<Integer> collectpricemin = service_o.findcollectmin(p); 
		  List<Integer> collectpricemax = service_o.findcollectmax(p);
		  List<Bookmark> bookmark = service_b.findAll(id); 
		  request.setAttribute("p", p);
		  request.setAttribute("minmap", minmap);
		  request.setAttribute("recent", recent);
		  request.setAttribute("colmin", collectpricemin);
		  request.setAttribute("colmax", collectpricemax);
		  request.setAttribute("bm", bookmark);
		  
		  path = "productinfo.jsp";
		  }catch(FindException e) {
			  e.printStackTrace(); 
			  }
	  
	  RequestDispatcher rd = request.getRequestDispatcher(path);
	  rd.forward(request, response);
	 
 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
