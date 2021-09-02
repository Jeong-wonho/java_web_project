package com.slur.dao;

import java.util.List;

import com.slur.dto.Criteria;
import com.slur.dto.Program;
import com.slur.dto.Review;
import com.slur.exception.AddException;
import com.slur.exception.FindException;
import com.slur.exception.ModifyException;
import com.slur.exception.RemoveException;

public interface ProgramDAO {
	/**
	 * 리뷰 입력하는 페이지
	 * @param review
	 * @throws AddException
	 */
	void insertReview(Review review) throws AddException;
	
	/**
	 * 전체 리뷰 목록 불러오기
	 * @param review
	 * @return
	 * @throws FindException
	 */
	List<Review> selectAll() throws FindException;
	
	/**
	 * 리뷰 상세
	 * @param review
	 * @return
	 * @throws FindException
	 */
	Review selectByInfo(int review_num) throws FindException;
	
	/**
	 * 리뷰 수정하기
	 * @param review
	 * @throws ModifyException
	 */
	void updateReview(Review review) throws ModifyException;
	
	/**
	 * 리뷰 삭제하기
	 * @param review_num
	 * @throws RemoveException
	 */
	void deleteReview(Review review) throws RemoveException;
	
	/**
	 * 리뷰 좌측 회차 정보 표시
	 * @return
	 * @throws FindException
	 */
	List<Program> selectByTimes() throws FindException;
	
	/**
	 * 회차별 리뷰 목록 불러오기
	 * @param program_times
	 * @return
	 * @throws FindException
	 */
	List<Program> selectByReviews(String program_times) throws FindException;
	
	/**
	 * 제목으로 검색하기
	 * @param word
	 * @return
	 * @throws FindException
	 */
	List<Review> selectByWord(String word) throws FindException;
	
	/**
	 * 페이징 처리하는 값 리턴하는 메서드
	 * @return
	 */
	List<Review> getListWithPaging(Criteria cri) throws FindException;
}
