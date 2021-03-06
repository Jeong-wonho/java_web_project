package com.day.dao;

import java.util.List;

import com.day.dto.Customer;
import com.day.exception.AddException;
import com.day.exception.DuplicatedException;
import com.day.exception.FindException;
import com.day.exception.ModifyException;

public interface CustomerDAO {

	/**
	 * 가입
	 * @param c
	 * @throws AddException 추가 실패시 발생예외
	 * @throws FindException 
	 */
	void insert(Customer c) throws AddException, FindException;
	/**
	 * 
	 * @param id
	 * @return
	 * @throws FindException 검색 실패시 발생예외
	 */
	Customer selectById(String id) throws FindException;
	/**
	 * 
	 * @param c
	 * @throws ModifyException 수정 실패시 발생예외
	 */
	void update(Customer c) throws ModifyException;
	
	
}
