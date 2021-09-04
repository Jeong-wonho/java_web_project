package com.slur.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.slur.dto.Qa;
import com.slur.exception.FindException;

@SpringBootTest
class QaDAOTest {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	QaDAO dao;
	@Test
	void test() throws FindException {
		List<Qa> list = dao.selectAll(1, 10);
		
		for (Qa qa:list) {
			log.error(qa.toString());
		}
	}

}
