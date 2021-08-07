package com.day.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.day.dto.RepBoard;
import com.day.exception.FindException;

@SpringBootTest
class RepBoardDAOTest {
	@Autowired
	RepBoardDAO dao;
	@Test
	void testRep() throws FindException {
		RepBoard rep = dao.selectByNo(2);
		int expectedValue = 2;
		assertEquals(expectedValue, rep.getBoardNo());
			
	}

}
