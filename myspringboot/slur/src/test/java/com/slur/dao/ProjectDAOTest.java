package com.slur.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.slur.dto.Criteria;
import com.slur.dto.Page;
import com.slur.dto.Review;
import com.slur.exception.FindException;

@SpringBootTest
class ProjectDAOTest {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	ProgramDAO dao;
	@Test
	void test() throws FindException {
		//1 10
		Criteria cri = new Criteria();
		
		List<Review> list = dao.getListWithPaging(cri);
		
		for (Review review : list) {
			log.error(review.getReview_content());
		}
		
	}
	
	@Test
	public void testPageDTO() {
		Criteria cri = new Criteria();
		cri.setPageNum(25);
		Page page = new Page(1,10, 250);
		
		log.info(page.toString());
	}
	

}
