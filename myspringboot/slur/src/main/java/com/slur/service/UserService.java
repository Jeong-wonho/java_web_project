package com.slur.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slur.dao.UserDAO;
import com.slur.dto.User;
import com.slur.exception.AddException;
import com.slur.exception.FindException;
import com.slur.exception.ModifyException;

@Service
public class UserService {
	@Autowired
	private UserDAO dao;
	
	/**
	 * 고객이 회원가입 한다.
	 * @param u 고객정보
	 * @throws AddException
	 * @throws FindException
	 */
	public void signup(User u) throws AddException, FindException {
		dao.insert(u);
	}
	
	/**
	 * 고객이 로그인합니다.
	 * @param user_id 고객 id
	 * @param user_pwd 고객 password
	 * @return
	 * @throws FindException
	 */
	public User login(String user_id, String user_pwd) throws FindException{
		User u = dao.selectById(user_id);
		if(!u.getUser_pwd().equals(user_pwd)) {
			throw new FindException("로그인 실패!");
		}
		return u;
	}
	
	/**
	 * myinfo페이지에서 자기 정보 조회
	 * @param user_id 고객 id
	 * @return
	 * @throws FindException
	 */
	public User detail(String user_id) throws FindException{
		return dao.selectById(user_id);
	}
	
	/** 
	 * 고객의 정보를 수정한다.
	 * @param u 고객정보
	 * @throws ModifyException
	 */
	public void modify(User u) throws ModifyException{
		dao.update(u);
	}
	
	/**
	 * 아이디에 해당하는 고객 찾는 요도
	 * @param user_id
	 * @return
	 * @throws FindException
	 */
	public User findById(String user_id) throws FindException {
		return dao.selectById(user_id);
	}
	
	
	
	
}
