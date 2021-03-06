package com.day.control;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.day.dto.Product;
import com.day.exception.FindException;
import com.day.service.ProductService;

public class ProductController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String contextPath = request.getContextPath();
		String servletPath = request.getServletPath();
		System.out.println("contextPath:" + contextPath +", servletPath:" + servletPath);
		String methodName = servletPath.split("/", 2)[1];
		//if("login".equals(methodName)) {
		//	login(request, response);
		//}	
		//메소드 이름으로 메소드 호출하기
		try {
			Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			String viewPath = (String)method.invoke(this, request, response);
			return viewPath;
		} catch (Exception e) {
			e.printStackTrace();
			return "fail.jsp";
		} 
	}
	
	public String productlist(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		ProductService service = ProductService.getInstance();
		String path;
		try {
			List<Product> list = service.findAll();
			request.setAttribute("productList", list);
			path = "/productlist.jsp"; 
		} catch (FindException e) {
			e.printStackTrace();
			path = "/fail.jsp";
		}
		return path;
	}	
	
	public String productinfo(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		//1. 요청전달데이터 얻기
		String prod_no = request.getParameter("prod_no");
		//2. 비지니스로직호출	
		ProductService service = ProductService.getInstance();
		String path = "";
		try {
			Product p = service.findByNo(prod_no);
			request.setAttribute("p", p);
			path = "productinfo.jsp";
		} catch (FindException e) {
			e.printStackTrace();
			path = "fail.jsp";
		}
		return path;
	}

}
