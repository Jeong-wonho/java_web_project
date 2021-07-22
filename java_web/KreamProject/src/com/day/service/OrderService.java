package com.day.service;

import java.io.FileInputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.day.dao.OrderDAO;
import com.day.dto.Order;
import com.day.dto.Product;
import com.day.exception.AddException;
import com.day.exception.FindException;
import com.day.exception.ModifyException;
import com.day.exception.RemoveException;

public class OrderService {
	private OrderDAO dao;
	private static OrderService service;
	public static String envProp;	
	private OrderService() {
		Properties env = new Properties();
		try {	
			//env.load(new FileInputStream("classes.prop"));
			env.load(new FileInputStream(envProp));
			String className = env.getProperty("OrderDAO");
			Class c = Class.forName(className);	 // JVM에 로드
			dao = (OrderDAO) c.newInstance(); // 객체 생성
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static OrderService getInstance() {
		if(service==null) {
			service = new OrderService();
		}
		return service;
	}
	
	public Order findMinOrder(Order o) throws FindException{
		return dao.selectMinOrder(o);
	}
	public Order findMaxOrder(Order o) throws FindException{
		return dao.selectMaxOrder(o);
	}
	/**
	 맵형태로 한 상품(prod_num)의 각 사이즈별 최저가를 저장
	 key 값으로 Order에 상품명, 사이즈만 저장, value는 상품별 최고가
	 * @param prod_num
	 * @return
	 * @throws FindException
	 */
	public Map<Order, Integer> findMinprice(int prod_num) throws FindException{
		return dao.selectMinprice(prod_num);	
	}
	/**
	 맵형태로 한 상품(prod_num)의 각 사이즈별 최저가를 저장
	 key 값으로 Order에 상품명, 사이즈만 저장, value는 상품별 최고가
	 * @param prod_num
	 * @return
	 * @throws FindException
	 */
	public Map<Order, Integer> findMaxprice(int prod_num) throws FindException{
		return dao.selectMaxprice(prod_num);	
	}
	/**
	 * 해당상품의 최근거래완료 10건의 데이터를 오더에 담아 리스트로 출력
	 * @param prod_num
	 * @return
	 * @throws FindException
	 */
	public List<Order> findRecentOrder(int prod_num) throws FindException{
		return dao.selectRecent(prod_num);
	}
	
	public List<Integer> findcollectmin(Product p) throws FindException{
		return dao.collectMinprice(p);
	}
	
	public List<Integer> findcollectmax(Product p) throws FindException{
		return dao.collectMaxprice(p);
	}
	/**
	 * 구매입찰 또는 판매입찰할시 Order 테이블에 추가.
	 * 추가시 Order status는 1
	 * @param o
	 * @throws AddException
	 */
	public void insert(Order o) throws AddException{
		dao.insert(o);
	}
	/**
	 * 즉시구매 또는 즉시판매 할시 Order테이블의 해당주문에 id와 order_status 업데이트
	 * @param o
	 * @throws ModifyException
	 */
	public void update(Order o) throws ModifyException{
		dao.update(o);
	}
	/**
	 * 마이페이지에서 구매입찰 또는 판매입찰 삭제시 Order테이블에서 해당 주문 삭제
	 * @param order_num
	 * @throws RemoveException
	 */
	public void delete(int order_num) throws RemoveException{
		dao.delete(order_num);
	}
}
