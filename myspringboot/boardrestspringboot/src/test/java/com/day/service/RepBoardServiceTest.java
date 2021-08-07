package com.day.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.day.dto.RepBoard;
import com.day.exception.FindException;

@SpringBootTest
class RepBoardServiceTest {
	@Autowired
	RepBoardService service;
	@Test
	void test() throws FindException{
		assertNotNull(service);
		RepBoard rep = service.findByNo(2);
		assertEquals(2, rep.getBoardNo());
	}

}
