package com.day.dao;

import java.util.List;

import com.day.dto.Product;
import com.day.exception.FindException;

public interface ProductDAO{
	/**
	 * 상품 전체검색
	 * @return 전체상품
	 * @throws FindException 상품이 없을경우 or 상품찾기를 실패한경우
	 * 상세페이지, 제품 상세 정보
 	 */
	public List<Product> selectAll() throws FindException;
	/**
	 * 상품 번호로 검색
	 * @param prod_no 상품번호
	 * @return 상품번호에 해당하는 상품
	 * @throws FindException 상품이 없을경우 or 상품찾기를 실패한경우
	 * 상품의 번호가 필요할 경우
	 */
	public Product selectByNo(int prod_num) throws FindException;
	/**
	 * 상품명으로 검색
	 * @param word 상품명에 해당하는 단어
	 * @return 단어를 포함한 상품들
	 * @throws FindException 상품이 없을경우 or 상품찾기를 실패한경우
	 * 상품명으로 상품 검색시..! 필요한지 아닌지는 아직 잘 모르겠음
	 * 필터용 데이터 추가
	 */
	public List<Product> selectBy() throws FindException;
	
}
