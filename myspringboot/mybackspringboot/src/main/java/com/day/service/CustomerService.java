package com.day.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.day.dao.CustomerDAO;
import com.day.dto.Customer;
import com.day.exception.AddException;
import com.day.exception.FindException;
import com.day.exception.ModifyException;
@Service
public class CustomerService {
	@Autowired
	private CustomerDAO dao;
	
	/**
	 * 고객이 회원가입한다 
	 * @param c 추가할 고객의 정보
	 * @throws AddException 회원가입 실패(아이디 중복)했을 경우 발생
	 * @throws FindException 
	 */
	public void signup(Customer c) throws AddException, FindException {
		dao.insert(c);
	}

	/**
	 * 고객이 로그인한다
	 * @param id 고객의 아이디
	 * @param pwd 고객의 비밀번호
	 * @throws FindException DB에서 해당 아이디와 비밀번호에 해당하는 값을 찾지 못했을 경우 발생
	 */
	public Customer login(String id, String pwd) throws FindException {
		Customer c = dao.selectById(id);
		if(!c.getPwd().equals(pwd)) {
			throw new FindException("로그인 실패!");
		}
		return c;
	} 
	/**
	 * 고객이 자신의 정보를 조회한다
	 * @param id 고객의 아이디
	 * @return 조회한 고객의 정보
	 * @throws FindException 고객을 찾지 못했을 경우 발생
	 */
	public Customer detail(String id) throws FindException{
		return dao.selectById(id);
	}
	
	/**
	 * 고객의 정보를 수정한다
	 * @param c 고객 정보
	 * @throws ModifyException 고객의 정보를 수정할 수 없을 경우 발생
	 */
	public void modify(Customer c) throws ModifyException{
		if(c.getEnabled()==0) {
			throw new ModifyException("이미 탈퇴한 회원입니다.");
		}
		c.setEnabled(-1);
		dao.update(c);
	}
	/**
	 * 고객이 탈퇴한다
	 * @param c 고객 정보 
	 * @throws ModifyException 탈퇴처리 못했을 경우 발생
	 */
	public void leave(Customer c) throws ModifyException{
		c.setEnabled(0);
		dao.update(c);
	}
	/**
	 * 아이디에 해당하는 고객객체찾기
	 * @param id
	 * @return
	 * @throws FindException 아이디에 해당고객이없거나, Network문제가 나면 예외가 발생한다.
	 */
	public Customer findById(String id) throws FindException {
		return dao.selectById(id);
	}
	public static void main(String[] args) {
		//회원가입 테스트
//		String id="jenny";
//		String pwd="pwd";
//		String name="jisu";
//		Customer c = new Customer(id,pwd,name);
//		try {
//			service.signup(c);
//		} catch (AddException e) {
//			e.printStackTrace();
//		}
		
		//로그인 테스트
//		String id="jenny";
//		String pwd="pwd";
//		try {
//			service.login(id, pwd);
//			System.out.println("로그인 성공!");
//		} catch (FindException e) {
//			e.printStackTrace();
//		}
		
		//정보조회 테스트
//		String id="jenny";
//		try {
//			Customer c = service.detail(id);
//			System.out.println(c);
//		} catch (FindException e) {
//			e.printStackTrace();
//		}
		
		//정보수정 테스트
//		String id="id11";
//		String modiPwd="";
//		String modiName="";
//		String modiBuildingNo="1";
//		
//		Customer c;
//		try {
//			c = service.detail(id);
//			c.setPwd(modiPwd);
//			c.setName(modiName);
//			c.setBuildingno(modiBuildingNo);
//			service.modify(c);
//		} catch (ModifyException | FindException e) {
//			e.printStackTrace();
//		}
		
		//탈퇴 테스트
//		String id = "id11";
//		try {
//			Customer c = service.detail(id);
//			c.setEnabled(0);
//			
//			service.leave(c);
//		} catch (FindException | ModifyException e) {
//			e.printStackTrace();
//		}
		
		
	}

}
