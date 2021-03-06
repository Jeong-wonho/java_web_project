package com.day.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.day.dao.CustomerDAO;
import com.day.dao.CustomerDAOOracle;
import com.day.dao.ProductDAO;
import com.day.dao.ProductDAOOracle;
import com.day.dto.Customer;
import com.day.dto.Product;
import com.day.service.ProductService;

@Configuration
public class Factory {
	
	@Bean //이거 안붇히면 오류나
	public Customer c() {
		Customer customer = new Customer();
		customer.setId("id1");
		customer.setPwd("p1");
		customer.setName("정원호");
		return customer;
	}
	
	@Bean 
	public CustomerDAO customerDAO() { //노출은 interface까지만 노출되면됨 
		return new CustomerDAOOracle(); // 하지만 값은customerdaooracle에서 받아온다.
	}
	
	@Bean
	public Product p() {
		return new Product("C0001", "아메리카노", 1000);
	}
	
	@Bean
	public ProductDAO productDAO() throws Exception {
		return new ProductDAOOracle();
	}
	
//	@Bean
//	public ProductService productService() throws Exception {
//		return new ProductService(productDAO());
//	}
	
	
	
	
}
