package com.day.dao;

import java.util.List;

import com.day.dto.Bookmark;
import com.day.exception.AddException;
import com.day.exception.FindException;
import com.day.exception.RemoveException;


public interface BookmarkDAO {
	/**
	 * 
	 * @param bm
	 * @throws AddException
	 */
	public void insert(Bookmark bm) throws AddException;	
	/**
	 * 
	 * @param bm_num
	 * @throws RemoveException
	 */
	public void delete(int bm_num) throws RemoveException;
	/**
	 * 
	 * @param bm_id
	 * @throws FindException
	 */
	public List<Bookmark> selectAll(String bm_id) throws FindException;

}
