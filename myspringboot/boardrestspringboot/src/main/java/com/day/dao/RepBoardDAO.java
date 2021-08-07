package com.day.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.day.dto.RepBoard;
import com.day.exception.AddException;
import com.day.exception.FindException;
import com.day.exception.ModifyException;
import com.day.exception.RemoveException;

public interface RepBoardDAO {
	/**
	 * 게시글 추가
	 * @param repBoard
	 * @throws AddException
	 */
	void insert(RepBoard repBoard) throws AddException;
	/**
	 * 글번호로 게시글 검색
	 * @param boardNo
	 * @return
	 * @throws FindException
	 */
	RepBoard selectByNo(int boardNo) throws FindException;
	/**
	 * 게시글 전체 검색
	 * @return
	 * @throws FindException
	 */
	List<RepBoard> selectAll() throws FindException;
	/**
	 * 제목이나 글 내용 단어를 포함한 게시글 검색
	 * @param word
	 * @return
	 * @throws FindException
	 */
	List<RepBoard> selectByWord(String word) throws FindException;
	/**
	 * 조회수 추가 관련 메서드
	 * @param boardNo
	 * @throws ModifyException
	 */
	void plusViewCount(int boardNo) throws ModifyException;
	/**
	 * 글내용 수정, 글 번호는 수정 안됨
	 * @param repBoard
	 * @throws ModifyException
	 */
	void update(RepBoard repBoard) throws ModifyException;
	
	
	void delete(RepBoard repBoard) throws RemoveException;
	/**
	 * 답글 모두 삭제
	 * @param boardNo
	 * @throws RemoveException
	 */

	void deleteReply(SqlSession session,int boardNo) throws RemoveException;
	/**
	 * 글 삭제
	 * @param repBoard (게시글객체, 게시글 작성자를 포함)
	 * @throws RemoveException
	 */
	void deleteWrite(SqlSession session,RepBoard repBoard) throws RemoveException;
}
