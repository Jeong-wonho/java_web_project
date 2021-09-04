package com.slur.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.slur.dto.Notice;
import com.slur.exception.FindException;

@SpringBootTest
class NoticeDAOTest {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	NoticeDAO dao;
	@Test
	void test() throws FindException{
		List<Notice> list = dao.selectAll(1, 10);
		
		for (Notice notice:list) {
			log.error(notice.toString());
		}
	}

}
