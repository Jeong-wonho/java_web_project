package com.slur.dao;

import java.util.List;

import com.slur.dto.Notice;
import com.slur.exception.FindException;

public interface NoticeDAO {
	/**
	 * 공지사항 전부를 불러온다.
	 * @param pageNo
	 * @param amount
	 * @return
	 * @throws FindException
	 */
	List<Notice> selectAll(int pageNo, int amount) throws FindException;
	
	/**
	 * 전체 목록수 확인용
	 * @return
	 * @throws FindException
	 */
	List<Notice> selectAll() throws FindException;
	/**
	 * 공지사항 상세 내용 호출
	 * @param notice_num
	 * @return
	 * @throws FindException
	 */
	Notice selectByInfo(int notice_num) throws FindException;
}
