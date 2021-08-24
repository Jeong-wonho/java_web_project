package com.slur.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.slur.dto.Program;
import com.slur.dto.Review;
import com.slur.dto.User;
import com.slur.exception.FindException;

@SpringBootTest
class ProjectDAOTest {
	@Autowired
	ProgramDAO dao;
	@Test
	void test() throws FindException {
		Review review = dao.selectByInfo(1);
		assertEquals("id1", review.getUser().getUser_id());
		
				
		//unread문제 어떻게 해결할건지.!
		
		
	}

}
