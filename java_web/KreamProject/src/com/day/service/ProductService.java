package com.day.service;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import com.day.dao.ProductDAO;
import com.day.dto.Product;
import com.day.exception.FindException;

public class ProductService {
	private ProductDAO dao;
	private static ProductService service;
	public static String envProp;	
	private ProductService() {
		Properties env = new Properties();
		try {	
			//env.load(new FileInputStream("classes.prop"));
			env.load(new FileInputStream(envProp));
			String className = env.getProperty("ProductDAO");
			Class c = Class.forName(className);	 // JVM에 로드
			dao = (ProductDAO) c.newInstance(); // 객체 생성
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static ProductService getInstance() {
		if(service == null) {
			service = new ProductService();
		}
		return service;
	}
	
	/**
	 * 
	 * @return 전체상품에 대한 리스트를 불러온다.
	 * @throws FindException 상품이 없을경우 or 상품찾기를 실패한 경우
	 * 나중에 사용하지 않을시 삭제 필요!
	 */
	public List<Product> findAll() throws FindException{
		return dao.selectAll();
	}
	
	/**
	 * 
	 * @param prod_no 상품 고유 번호
	 * @return 프로덕트 한개 개체 반환
	 * @throws FindException
	 */
	public Product findByNo(int prod_num) throws FindException{
		return dao.selectByNo(prod_num);
	}
	
	public List<Product> findBy() throws FindException{
		return dao.selectBy();
	}
}
