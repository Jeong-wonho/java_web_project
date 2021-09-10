package com.slur.dao;

import java.util.List;

import com.slur.dto.Notice;
import com.slur.dto.Qa;
import com.slur.exception.AddException;
import com.slur.exception.FindException;
import com.slur.exception.ModifyException;
import com.slur.exception.RemoveException;

public interface QaDAO {
	
	/**
	 * 질문등록하는 메서드
	 * @param qa
	 * @throws AddException
	 */
	void insertQA(Qa qa) throws AddException;
	
	/**
	 * 작성글 수정하는 메서드
	 * @param qa
	 * @throws ModifyException
	 */
	void updateQa(Qa qa) throws ModifyException;
	
	/**
	 * 작성글 삭제하는 메서드
	 * @param qa
	 * @throws RemoveException
	 */
	void deleteQa(int qa_num) throws RemoveException;
	
	/**
	 * 모든목록 조회하는 메서드
	 * @param pageNo
	 * @param amount
	 * @return
	 * @throws FindException
	 */
	List<Qa> selectAll(int pageNo, int amount) throws FindException;
	
	/**
	 * 
	 * @param qa_num
	 * @return
	 * @throws FindException
	 */
	Qa selectByInfo(int qa_num) throws FindException; 
	
	/**
	 * 전체 목록수 확인용
	 * @return
	 * @throws FindException
	 */
	List<Qa> selectAll() throws FindException;
}
