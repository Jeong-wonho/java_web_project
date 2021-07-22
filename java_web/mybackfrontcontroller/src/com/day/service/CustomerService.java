package com.day.service;

import java.io.FileInputStream;
import java.util.Properties;

import com.day.dao.CustomerDAO;
import com.day.dto.Customer;
import com.day.exception.AddException;
import com.day.exception.FindException;
import com.day.exception.ModifyException;

public class CustomerService {
	private CustomerDAO dao;
//	private static CustomerService service = new CustomerService();
	private static CustomerService service;
	public static String envProp;
	private CustomerService() {
		Properties env = new Properties();
		try {
			System.out.println(envProp);
//			env.load(new FileInputStream("classes.prop"));
			env.load(new FileInputStream(envProp));
			String className = env.getProperty("customerDAO");
			Class c = Class.forName(className);
			dao = (CustomerDAO)c.newInstance(); //jvm에 로드, 객체생성
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public static CustomerService getInstance() {
		if(service == null) {
			service = new CustomerService();
		}
		return service;
	}
	
	public void signup(Customer c) throws AddException{
		dao.insert(c);
	}
	/**
	 * 
	 * @param id
	 * @param pwd
	 * @return
	 * @throws FindException
	 */
	public Customer login(String id, String pwd) throws FindException{
		Customer c = dao.selectById(id);
		if(!c.getPwd().equals(pwd)) {
			throw new FindException("로그인 실패");
		}
		return c;
	}
	
	public Customer detail(String id) throws FindException{
		return dao.selectById(id);
	}
	
	public void modify(Customer c) throws ModifyException{
		if(c.getEnabled() == 0) { //
			throw new ModifyException("탈퇴작업은 할 수 없습니다.");
		}
		
		c.setEnabled(-1);
		dao.update(c);
	}
	
	public void leave(Customer c) throws ModifyException{
		c.setEnabled(0);
		dao.update(c);
	}
	
	
	
	
	
	
	
}
