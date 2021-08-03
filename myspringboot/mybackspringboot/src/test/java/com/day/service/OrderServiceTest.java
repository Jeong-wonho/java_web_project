package com.day.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.day.dto.OrderInfo;
import com.day.exception.FindException;
@SpringBootTest
class OrderServiceTest {
	@Autowired
	OrderService service;
	@Test
	void test() throws FindException{
		assertNotNull(service);
		List<OrderInfo> list = service.findById("id1");
		int expectedSize = 1;
		
		assertTrue(expectedSize == list.size());
	}

}
