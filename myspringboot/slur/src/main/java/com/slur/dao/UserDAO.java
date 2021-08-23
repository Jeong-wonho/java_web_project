package com.slur.dao;

import com.slur.dto.User;
import com.slur.exception.AddException;
import com.slur.exception.FindException;
import com.slur.exception.ModifyException;

public interface UserDAO {
	/**
	 * 회원가입
	 * @param User u
	 * @throws AddException
	 * @throws FindException
	 */
	void insert(User u) throws AddException, FindException;
	
	/**
	 * 로그인 할 때 사용할 정보
	 * @param user_id
	 * @return
	 * @throws FindException
	 */
	User selectById(String user_id) throws FindException;
	
	/** 
	 * 회원정보 수정할 때 사용
	 * @param User u
	 * @throws ModifyException
	 */
	void update(User u) throws ModifyException;
}
