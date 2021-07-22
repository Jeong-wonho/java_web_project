package com.day.dao;

import java.util.List;

import com.day.dto.Comment;
import com.day.exception.AddException;
import com.day.exception.FindException;
import com.day.exception.ModifyException;
import com.day.exception.RemoveException;

public interface CommentDAO {

	/**
	 * 댓글 전체 검색
	 * @return
	 * @throws FindException
	 */
	public List<Comment> selectAll(int board_num) throws FindException;
	
	/**
	 * 
	 * @param b
	 * @throws AddException
	 */
	public void insert(Comment c) throws AddException;
	
	/**
	 * 
	 * @param b
	 * @throws ModifyException
	 */
	public void update(Comment c) throws ModifyException;
	
	/**
	 * 
	 * @param comment_num 댓글번호
	 * @throws RemoveException
	 */
	public void delete(int board_num, int comment_num) throws RemoveException;
	
}
