package com.slur.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slur.dao.NoticeDAO;
import com.slur.dto.Notice;
import com.slur.dto.Review;
import com.slur.exception.FindException;

@Service
public class NoticeService {
	
	@Autowired
	private NoticeDAO dao;
	
	/**
	 * 전체 공지 사항
	 * @param pageNum
	 * @param amount
	 * @return
	 * @throws FindException
	 */
	public List<Notice> reviewAll(int pageNum, int amount) throws FindException{
		return dao.selectAll(pageNum, amount);
	}
	
	/**
	 * 공지 사항 상세
	 * @param notice_num
	 * @return
	 * @throws FindException
	 */
	public Notice reviewInfo(int notice_num) throws FindException{
		return dao.selectByInfo(notice_num);
	}
	
	public List<Notice> reviewAll() throws FindException{
		return dao.selectAll();
	}
}
