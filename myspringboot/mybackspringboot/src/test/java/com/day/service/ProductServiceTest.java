package com.day.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.day.dto.Product;
import com.day.exception.FindException;
@SpringBootTest
class ProductServiceTest {
	@Autowired
	ProductService service;
	@Test
	void test() throws FindException{
		assertNotNull(service);
		Product p =  service.findByNo("C0001");
		assertEquals("아메리카노", p.getProd_name());
	}

}
