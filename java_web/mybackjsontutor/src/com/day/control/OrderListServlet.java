package com.day.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.fasterxml.jackson.databind.ObjectMapper;


public class OrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//1.세션데이터얻기
		HttpSession session = request.getSession();
		Customer c = (Customer)session.getAttribute("loginInfo");
		if(c == null) {
			//request.setAttribute("status", 0); //로그인 안할 경우
			Map<String, Integer>map = new HashMap<>();
			map.put("status", 0);
			out.print(mapper.writeValueAsString(map));
		}else {
			//2.비지니스로직
			ServletContext sc = getServletContext();
			OrderService.envProp = sc.getRealPath(sc.getInitParameter("env"));
			OrderService service;
			service = OrderService.getInstance();
			try {
				List<OrderInfo> infos = service.findById(c.getId());
				String jsonStr = mapper.writeValueAsString(infos);
				System.out.println(jsonStr);
				out.print(jsonStr);
			} catch (FindException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//3.
	}

}
