package com.day.service;

import java.io.FileInputStream;
import java.util.Properties;

import com.day.dao.BoardDAO;

public class CommentService {
	private BoardDAO dao;
	private static CommentService service;
	public static String envProp;	
	private CommentService() {
		Properties env = new Properties();
		try {	
			//env.load(new FileInputStream("classes.prop"));
			env.load(new FileInputStream(envProp));
			String className = env.getProperty("CustomerDAO");
			Class c = Class.forName(className);	 // JVM에 로드
			dao = (BoardDAO) c.newInstance(); // 객체 생성
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static CommentService getInstance() {
		if(service==null) {
			service = new CommentService();
		}
		return service;
	}
	
	
	
	
	
	
}
