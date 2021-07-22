package com.day.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyContextListener implements ServletContextListener {

	public MyContextListener() {
		//톰캣이 켜지자마자
		System.out.println("MyContextListener객체생성됨!");
		
	}

	public void contextDestroyed(ServletContextEvent sce)  { 
		//톰캣이 꺼지기 직전
		System.out.println("MyContextListener의 contextDestroyed() 메소드가 호출됨! 꺼짐");
		
	}

	public void contextInitialized(ServletContextEvent sce)  { 
		//톰캣이 켜진 직후
		System.out.println("MyContextListener의 contextInitialized 메소드가 호출됨! 켜짐");
		
	}

}
