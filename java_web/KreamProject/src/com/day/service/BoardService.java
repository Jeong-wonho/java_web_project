package com.day.service;

import java.io.FileInputStream;
import java.util.Properties;

import com.day.dao.BoardDAO;

public class BoardService {
	private BoardDAO dao;
	private static BoardService service;
	public static String envProp;	
	private BoardService() {
		Properties env = new Properties();
		try {	
			//env.load(new FileInputStream("classes.prop"));
			env.load(new FileInputStream(envProp));
			String className = env.getProperty("BoardDAO");
			Class c = Class.forName(className);	 // JVM에 로드
			dao = (BoardDAO) c.newInstance(); // 객체 생성
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static BoardService getInstance() {
		if(service==null) {
			service = new BoardService();
		}
		return service;
	}
	
	
	
	
	
	
}
