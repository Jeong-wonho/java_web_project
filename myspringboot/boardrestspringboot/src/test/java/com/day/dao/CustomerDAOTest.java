package com.day.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.day.dto.Customer;
import com.day.exception.FindException;
@SpringBootTest
class CustomerDAOTest {
	@Autowired
	CustomerDAO dao;
	@Test
	void test() throws FindException {
		Customer c = dao.selectById("id9");
		String expectedName = "id9";
		assertEquals(expectedName, c.getId());
	}

}
