package com.day.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.day.dto.Customer;
import com.day.dto.OrderInfo;
import com.day.exception.FindException;
import com.day.service.OrderService;

public class OrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 세션에서데이터 얻기
		HttpSession session = request.getSession();
		Customer c = (Customer)session.getAttribute("loginInfo");
		if(c == null) {
			request.setAttribute("status", 0); //로그인 안할 경우
		}else {
			//2. 비지니스로직 호출
			ServletContext sc = getServletContext();
			OrderService.envProp = sc.getRealPath(sc.getInitParameter("env"));
			OrderService service;
			service = OrderService.getInstance();
			try {
				List<OrderInfo> infos = service.findById(c.getId());
				request.setAttribute("infos",  infos);
			} catch (FindException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//3. 
		String path = "orderlist.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);

	}

}
