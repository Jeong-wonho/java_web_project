package com.day.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.day.dto.Customer;
import com.day.exception.FindException;
import com.day.service.CustomerService;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*"); //보안 정책 허용.

		//1. 요청전달데이터 얻기
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		ServletContext sc = getServletContext();
		CustomerService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		CustomerService service = CustomerService.getInstance();
		//2. 비지니스로직 호출
		String path = "";
		// 로그인 세션에 남아있을 수도 잇는 경우의 수를 제거하기 위해서
		HttpSession session = request.getSession();
		session.removeAttribute("loginInfo");
		
		try {
			Customer loginInfo = service.login(id, pwd);
			//로그인 정보를 세션에 추가
			session.setAttribute("loginInfo", loginInfo);
			//3. 성공
//			path = "success";
			path = "success.jsp";
		} catch (FindException e) {
			e.printStackTrace();
			//4. 실패
//			path = "fail";
			path = "fail.jsp";
		}
		//5. 페이지 이동
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);	
	}
}
