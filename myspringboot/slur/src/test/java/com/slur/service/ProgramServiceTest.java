package com.slur.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.slur.dto.Review;
import com.slur.exception.FindException;

@SpringBootTest
class ProgramServiceTest {
	
	@Autowired
	ProgramService service;
	@Test
	void test() throws FindException{
		assertNotNull(service);
		List<Review> review = service.reviewWord("진짜");
		assertEquals("진짜진짜감사해요", review.get(0).getReview_title());
	}

}
