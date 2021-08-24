package com.slur.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slur.dao.ProgramDAO;
import com.slur.dto.Program;
import com.slur.dto.Review;
import com.slur.exception.AddException;
import com.slur.exception.FindException;
import com.slur.exception.ModifyException;
import com.slur.exception.RemoveException;

@Service
public class ProgramService {
	@Autowired
	private ProgramDAO dao;
	
	/**
	 * 리뷰작성
	 * @param r
	 * @throws AddException
	 * @throws FindException
	 */
	public void reviewWrite(Review r) throws AddException{
		dao.insertReview(r);
	}
	
	/**
	 * 리뷰 수정
	 * @param r
	 * @throws ModifyException
	 */
	public void reviewModify(Review r) throws ModifyException{
		dao.updateReview(r);
	}
	
	/**
	 * 리뷰삭제
	 * @param review_num
	 * @throws RemoveException
	 */
	public void reviewDelete(Review r) throws RemoveException{
		dao.deleteReview(r);
	}
	
	/**
	 * 리뷰 목록 조회
	 * @param r
	 * @return
	 * @throws FindException
	 */
	public List<Review> reviewAll() throws FindException{
		return dao.selectAll();
	}
	/**
	 * 리뷰목록 상세조회
	 * @param review_num
	 * @return
	 * @throws FindException
	 */
	public Review reviewInfo(int review_num) throws FindException{
		return dao.selectByInfo(review_num);
	}
	/**
	 * 리뷰 검색 조회
	 * @param word
	 * @return
	 * @throws FindException
	 */
	public List<Review> reviewWord(String word) throws FindException{
		return dao.selectByWord(word);
	}
	
	/**
	 * 프로그램 기간 조회
	 * @return
	 * @throws FindException
	 */
	public List<Program> programTimes() throws FindException{
		return dao.selectByTimes();
	}
	
	/**
	 * 프로그램 기간별 목록 조회
	 * @param program_times
	 * @return
	 * @throws FindException
	 */
	public List<Program> programReview(String program_times) throws FindException{
		return dao.selectByReviews(program_times);
	}
	

	
}
