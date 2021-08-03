package com.day.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.day.dto.Customer;
import com.day.exception.FindException;
@SpringBootTest
class CustomerServiceTest {
	@Autowired
	CustomerService service;
	@Test
	void test() throws FindException{
		assertNotNull(service);
		Customer c = service.findById("id1");
		assertEquals("id1", c.getId());
	}

}
