package com.day.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.day.dto.Customer;
import com.day.dto.OrderInfo;
import com.day.exception.AddException;
import com.day.exception.FindException;
import com.day.exception.ModifyException;

@ExtendWith(SpringExtension.class)

@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
class OrderDAOOracleTest {
	@Autowired

	private OrderDAO dao;
	private Logger log = Logger.getLogger(OrderDAOOracle.class.getName());

	@Test
	void testSelectById() throws FindException {
		log.info("testSelectById debug");

		String id = "id1";
		List<OrderInfo> list = dao.selectById(id);

		int expectedSize = 3;
		int expectedOrder_no = 107;
		assertTrue(expectedSize == list.size());
		assertTrue(expectedOrder_no == list.get(0).getOrder_no());
	}

	void testInsert() throws Exception{
		
	}


	@AfterEach
	void tearDown() throws Exception {

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

}
