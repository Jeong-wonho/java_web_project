package com.day.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.day.dto.OrderInfo;
import com.day.exception.FindException;
@SpringBootTest
class OrderDAOTest {
	@Autowired
	OrderDAO dao;
	@Test
	void test() throws FindException{
		List<OrderInfo> list = dao.selectById("id1");
		int expectedSize = 1;
		
		assertTrue(expectedSize == list.size());
	}

}
