package com.day.control;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.day.service.CustomerService;

public class DispatcherServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    public DispatcherServlet() {
        super();
   
    }

   
   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      System.out.println("DispatcherServlet이 요청됨");
      ServletContext sc = getServletContext();      
      CustomerService.envProp = sc.getRealPath(sc.getInitParameter("env"));
      
      //요청servletpath에 따라 사용될 Controller와 method가 달라짐.
      String servletPath = request.getServletPath();
      Controller controller;      
      //controller.prop파일 로드
//      ServletContext sc = getServletContext();
      
      //프로퍼티 파일의 실제경로 찾기
//      String realPath = sc.getRealPath("/WEB-INF/controller.prop");
      String realPath = sc.getRealPath(sc.getInitParameter("env.controller"));
      Properties env = new Properties();
      env.load(new FileInputStream(realPath));
      
      //요청된 servletPath에 해당하는 클래스 이름 찾기.
      String controllerClassName = env.getProperty(servletPath);
      
      
      try {
         //클래스 이름에 해당하는 클래스로드
         Class clazz = Class.forName(controllerClassName);
         //로드된 클래스를 이용한 객체생성
         Object obj = clazz.newInstance();
         //인터페이스 타입으로 캐스팅
         controller = (Controller)obj;
         //execute메소드 호출
         String viewPath = controller.execute(request, response);
         RequestDispatcher rd = request.getRequestDispatcher(viewPath);
         rd.forward(request, response);         
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      } catch (InstantiationException e) {
         e.printStackTrace();
      } catch (IllegalAccessException e) {
         e.printStackTrace();
      }
      
   }

}