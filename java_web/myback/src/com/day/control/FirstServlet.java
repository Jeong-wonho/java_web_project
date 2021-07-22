package com.day.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;

import com.sun.org.apache.bcel.internal.classfile.Field;

/**
 * Servlet implementation class FirstServlet
 */
//@WebServlet("/first")
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public FirstServlet() {
        super();
        System.out.println("FirstServlet 객체 생성 완료");
    }

	public void init(ServletConfig config) throws ServletException {
		System.out.println("FirstServlet의 init 메소드 호출");
		super.init(config);
		// context-param 얻기
        ServletContext sc = getServletContext();
        String devName = sc.getInitParameter("Developer");
        System.out.println("책임 개발자 : " + devName);
        
        String realPath = sc.getRealPath("logo.jpg");
        System.out.println("logo.jpg의 실제 경로 : " + realPath);
        File file = new File(realPath);
        if(!file.exists()) {
        	System.out.println("logo.jpg 파일이 없습니다");
        }
        
        // Servlet의 init-param 얻기
        String fileName = this.getInitParameter("fileName"); 
        System.out.println("FirstServlet에서만 사용 가능한 파라미터 fileName : " + fileName);
	}

	public void destroy() {
		System.out.println("FirstServlet의 destroy 메소드 호출");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("FirstServlet의 doGet 메소드 호출");
		// 클라이언트가 요청한 url의 파라미터 얻기
		// http://localhost:8888/myback/first?id=1&pwd=p1
		String id_Val = request.getParameter("id");
		String pwd_Val = request.getParameter("pwd");
		System.out.println("요청 ID : " + id_Val + ", pwd : " + pwd_Val);
		
		/*
		 * 파라미터 하나가 여러 개의 데이터를 전송할 때
		 * http://localhost:8888/myback/first?id=9&pwd=p9&c=c1&c=c2
		 * return : String[] 형태
		 * http://localhost:8888/myback/first?id=9&pwd=p9와 같이 요청할 파라미터의 값이 하나도 없이 요청 시 NullPointerException 반환
		 */
//		String[] c_Arr = request.getParameterValues("c");
//		for (String s : c_Arr) {
//			System.out.println("요청 데이터 : " + s);
//		}
		
		String con_Path = request.getContextPath();
		String req_URI = request.getRequestURI();
		StringBuffer req_URL = request.getRequestURL();
		
		System.out.println("Context Path 1 : " + con_Path);
		System.out.println("Context Path 2 : " + getServletContext().getContextPath());
		System.out.println("Request URI : " + req_URI);
		System.out.println("Request URL : " + req_URL);
		
		String servlet_Path = request.getServletPath();
		System.out.println("Servlet Path : " + servlet_Path);
		
		Object obj = request.getAttribute("reqAttr1");
		String attrValue = (String)obj;
		
		/*
		 * response의 응답 형식 지정
		 * 응답 형식은 MIME 표준 방식을 따름
		 * https://developer.mozilla.org/ko/docs/Web/HTTP/Basics_of_HTTP/MIME_Types
		 */
		response.setContentType("text/html;charset=utf-8"); // 응답 형식을 먼저 설정 필요
		PrintWriter out = response.getWriter(); // 응답 출력 스트림을 얻는 작업
		out.print("<html>");
		out.print("<body>");
		out.print("<h1>응답결과</h1>");
		out.print("요청 속성 값 : " + attrValue);
		out.print("</body>");
		out.print("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("FirstServlet의 doPost 메소드 호출");
	}

}
