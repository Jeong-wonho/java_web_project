package com.day.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.day.dao.ProductDAO;
import com.day.dto.Product;
import com.day.exception.FindException;

@Service
public class ProductService {
	@Autowired
	@Qualifier("productDAO1")
	private ProductDAO dao;
	public ProductService() {}
	
//	@Autowired
//	@Autowired(required = false)
//	public ProductService(ProductDAO dao) {
//		this.dao = dao;
//		System.out.println("productService의 생성자 호출됨");
//	}
//	public void setDao(ProductDAO dao) {
//		this.dao = dao;
//		System.out.println("setDAO() 호출됨");
//	}
	
	public ProductDAO getDao() {
		return dao;
	}

	public List<Product> findAll() throws FindException{
		return dao.selectAll();
		//List<Product> list = dao.selectAll();
		//list를 암호화
		//암호화된 리스트반환
	}
	public List<Product> findAll(int currentPage) throws FindException{
		return dao.selectAll(currentPage);
	}
	public Product findByNo(String prod_no) throws FindException{
		//상품번호를 복호화
		//dao.selectByNo(복호화된 값);
		return dao.selectByNo(prod_no);
	}
	public List<Product> findByName(String word) throws FindException{
		return dao.selectByName(word);
	}

}
