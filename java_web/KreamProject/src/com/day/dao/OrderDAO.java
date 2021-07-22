package com.day.dao;

import java.util.List;
import java.util.Map;

import com.day.dto.Order;
import com.day.dto.Product;
import com.day.exception.AddException;
import com.day.exception.FindException;
import com.day.exception.ModifyException;
import com.day.exception.RemoveException;

public interface OrderDAO {
	/**
	 * 
	 * @param prod_name
	 * @return 최저값
	 * @throws FindException
	 */
	public Map<Order, Integer> selectMinprice(int prod_num) throws FindException;
	
	/**
	 * 
	 * @param prod_name
	 * @return 최대값
	 * @throws FindException
	 */
	public Map<Order, Integer> selectMaxprice(int prod_num) throws FindException;
	
	public Order selectMinOrder(Order o) throws FindException;
	public Order selectMaxOrder(Order o) throws FindException;

	
	
	/**
	 * 
	 * @param prod_name
	 * @return 최근거래내역
	 * @throws FindException
	 */
	public List<Order> selectRecent(int prod_num) throws FindException;
	
	public Order selectByNum(int order_num) throws FindException;
	
	public void insert(Order o) throws AddException;
	
	public void update(Order o) throws ModifyException;
	
	public void delete(int order_num) throws RemoveException;

	public List<Integer> collectMinprice(Product p) throws FindException;
	
	public List<Integer> collectMaxprice(Product p) throws FindException;
}
