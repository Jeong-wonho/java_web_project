package com.day.dao;

import java.util.List;

import com.day.dto.Board;
import com.day.exception.AddException;
import com.day.exception.FindException;
import com.day.exception.ModifyException;
import com.day.exception.RemoveException;

public interface BoardDAO {
	
	/**
	 * 게시판 전체 검색
	 * @return
	 * @throws FindException
	 */
	public List<Board> selectAll() throws FindException;
	
	/**
	 * 
	 * @param b
	 * @throws AddException
	 */
	public void insert(Board b) throws AddException;
	
	/**
	 * 
	 * @param b
	 * @throws ModifyException
	 */
	public void update(Board b) throws ModifyException;
	
	/**
	 * 
	 * @param board_num 게시판 번호
	 * @throws RemoveException
	 */
	public void delete(int board_num) throws RemoveException;
	
	//게시물 하나만 가져오는거 추가
	
	public Board selectByNum(int board_num) throws FindException;
	
}
