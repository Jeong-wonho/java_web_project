package com.day.control;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private void forward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 응답형식 지정 : text/html
		response.setContentType("text/html;charset=utf-8");
		
		// 응답 출력 스트림 얻기
		PrintWriter out = response.getWriter();
		out.print("Before forward");
		
		request.setAttribute("reqAttr1", "reqAttr1Value");
		
		String path = "./first";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		
		out.print("After forward");
	}
	
	private void include(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 응답형식 지정 : text/html
		response.setContentType("text/html;charset=utf-8");
		
		// 응답 출력 스트림 얻기
		PrintWriter out = response.getWriter();
		out.print("Before include");
		
		String path = "./first";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.include(request, response);
		
		out.print("After include");
	}
	
	private void redirect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 응답형식 지정 : text/html
		response.setContentType("text/html;charset=utf-8");
		
		// 응답 출력 스트림 얻기
		PrintWriter out = response.getWriter();
		out.print("Before redirect");
		
		String path = "./first";
		response.sendRedirect(path);
		
		out.print("After redirect");
	}
	
	private void show(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 응답형식 지정 : text/html
		response.setContentType("text/html;charset=utf-8");
		
		// 응답 출력 스트림 얻기
		PrintWriter out = response.getWriter();
		
		out.print("<html>");
		out.print("<body>");
		out.print("<ol>");
		out.print("<li><a href = \"./move?opt=forward&id=id1&pwd=p1\">포워드</a></li>");
		out.print("<li><a href = \"./move?opt=include\">인클루드</a></li>");
		out.print("<li><a href = \"./move?opt=redirect&id=id1&pwd=p1\">리다이렉트</a></li>");
		out.print("</ol>");
		out.print("</body>");
		out.print("</html>");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청 전달 데이터(이름 : opt)가 전달되지 않은 경우
		String opt = request.getParameter("opt");
		if (opt == null || opt.equals("")) {
			System.out.println("null 구문");
			show(request, response);
		}else if(opt.equals("forward")) {
			System.out.println("forward 구문");
			forward(request, response);
		}else if(opt.equals("include")) {
			System.out.println("include 구문");
			include(request, response);
		}else if(opt.equals("redirect")) {
			System.out.println("redirect 구문");
			redirect(request, response);
		}
	}

}
