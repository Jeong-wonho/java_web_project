package com.day.dao;

import java.util.List;

import com.day.dto.Shop;
import com.day.exception.FindException;

public interface ShopDAO {
	/**
	 * 
	 * @param 판매량으로 분류
	 * @return 해당 상품정보
	 * @throws FindException 상품이 존재하지 않을때
	 */
	public List<Shop> selectAll(String id) throws FindException;
	/**
	 * @param prod_name 상품명
	 * @return 상품명으로 분류
	 * @throws FindException
	 */
	public List<Shop> selectByName(String id, String prod_name) throws FindException;
	
	/**
	 * 
	 * @param gender 성별
	 * @return 성별로 분류
	 * @throws FindException
	 */
//	public List<Shop> selectByGender(String id,String[] gender) throws FindException;
	
	/**
	 * 
	 * @param price 가격
	 * @return 가격으로 분류
	 * @throws FindException
	 */
	public List<Shop> selectByReleaseddt(String id) throws FindException;
	
	/**
	 * 
	 * @param id
	 * @param brand 필터를 위한 리스트 출력!
	 * @return
	 * @throws FindException
	 */
	public List<Shop> selectByList(String id ,String[] brand) throws FindException;
}
