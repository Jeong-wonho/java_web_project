package com.day.dao;

import com.day.dto.Customer;
import com.day.exception.AddException;
import com.day.exception.FindException;
import com.day.exception.ModifyException;

public interface CustomerDAO {
	
	/**
	 * 가입
	 * @param c
	 * @return 
	 * @throws AddException 추가 실패시 발생예외
	 */
	Customer insert(Customer c) throws AddException;
	/**
	 * 
	 * @param id
	 * @return
	 * @throws FindException 검색 실패시 발생예외
	 */
	Customer selectById(String id) throws FindException;
	/**
	 * 
	 * @param phone
	 * @return
	 * @throws FindException
	 */
	String findId(String phone) throws FindException;
	/**
	 * 
	 * @param phone
	 * @param id
	 * @return
	 * @throws FindException
	 */
	String findPwd(String phone, String id) throws FindException;
	/**
	 * 
	 * @param c
	 * @throws ModifyException 수정 실패시 발생예외
	 */
	void update(Customer c) throws ModifyException;
	
}
