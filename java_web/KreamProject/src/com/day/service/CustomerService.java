package com.day.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.naming.directory.ModificationItem;

import com.day.dao.CustomerDAO;
import com.day.dto.Customer;
import com.day.exception.AddException;
import com.day.exception.FindException;
import com.day.exception.ModifyException;

public class CustomerService {	
	private CustomerDAO dao;
	private static CustomerService service;
	public static String envProp;	
	private CustomerService() {
		Properties env = new Properties();
		try {	
			//env.load(new FileInputStream("classes.prop"));
			env.load(new FileInputStream(envProp));
			String className = env.getProperty("CustomerDAO");
			Class c = Class.forName(className);	 // JVM에 로드
			dao = (CustomerDAO) c.newInstance(); // 객체 생성
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
	/**
	 * 고객이 회원가입한다 
	 * @param c 추가할 고객의 정보
	 * @return 
	 * @throws AddException 회원가입 실패(아이디 중복)했을 경우 발생
	 */
	public Customer signup(Customer c) throws AddException {
		return dao.insert(c);
	}

	/**
	 * 고객이 로그인한다
	 * @param id 고객의 아이디
	 * @param pwd 고객의 비밀번호
	 * @throws FindException DB에서 해당 아이디와 비밀번호에 해당하는 값을 찾지 못했을 경우 발생
	 */
	public Customer login(String user_id, String user_pwd) throws FindException {
		Customer c = dao.selectById(user_id);
		if(!c.getUser_pwd().equals(user_pwd)) {
			throw new FindException("로그인 실패!");
		}
		return c;
	}	
	
	/**
	 * 고객의 휴대폰번호로 아이디를 찾는다.
	 * @param phone 고객 휴대폰 번호
	 * @return 
	 * @throws FindException DB에서 해당 휴대폰 번호값을 찾지 못했을 경우 발생
	 */
	public String findid(String phone) throws FindException {
		return dao.findId(phone);		
	}
	
	/**
	 * 고객의 휴대폰번호와 아이디 값으로 비밀번호를 찾는다.
	 * @param phone 고객 휴대폰 번호
	 * @param id 고객 아이디
	 * @throws FindException DB에서 해당 휴대폰 번호값과 아이디값을 찾지 못했을 경우 발생
	 */
	public String findpwd(String phone, String id) throws FindException {
		return dao.findPwd(phone, id);
	}
	
	/**
	 * 회원의 정보를 수정한다.
	 * @param c 수정할 고객의 정보
	 * @return 
	 * @throws ModifyException
	 */
    public void update(Customer c) throws ModifyException {
    	dao.update(c);
    }
    
    public Customer findById(String id) throws FindException {
    	return dao.selectById(id);
    	
    }
       
}
