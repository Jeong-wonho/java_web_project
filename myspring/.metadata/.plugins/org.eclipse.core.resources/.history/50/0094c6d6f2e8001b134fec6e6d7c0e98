package com.day.dao;

import java.util.List;
import com.day.dto.Product;
import com.day.exception.FindException;

/*
 * 상품추가
 * 상품조회 (전체/상세)
 * 상품번호로 조회
 * 상품명으로 조회
 * 상품수정
 * 상품삭제
 * 
 */

public interface ProductDAO {
	/**
	 * 상품 전체검색
	 * @return 전체상품
	 * @throws FindException 상품이 없을경우 or 상품찾기를 실패한경우
 	 */

	public List<Product> selectAll() throws FindException;
	
	/**
	 * 상품 전체검색
	 * @param currentPage 페이지
	 * @return 페이지에 해당하는 상품들
	 * @throws FindException 상품이 없을경우 or 상품찾기를 실패한경우
	 */
	public List<Product> selectAll(int currentPage) throws FindException;
	
	/**
	 * 상품 번호로 검색
	 * @param prod_no 상품번호
	 * @return 상품번호에 해당하는 상품
	 * @throws FindException 상품이 없을경우 or 상품찾기를 실패한경우
	 */
	public Product selectByNo(String prod_no) throws FindException;
	
	/**
	 * 상품명으로 검색
	 * @param word 상품명에 해당하는 단어
	 * @return 단어를 포함한 상품들
	 * @throws FindException 상품이 없을경우 or 상품찾기를 실패한경우
	 */
	public List<Product> selectByName(String word) throws FindException;
	
	

}
