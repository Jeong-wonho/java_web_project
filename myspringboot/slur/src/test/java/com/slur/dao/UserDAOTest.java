package com.slur.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.slur.dto.User;
import com.slur.exception.FindException;

@SpringBootTest
class UserDAOTest {
	@Autowired
	UserDAO dao;
	@Test
	void test() throws FindException {
		User u = dao.selectById("id1");
		String expectedName = "박민수";
		assertEquals(expectedName, u.getUser_name());
	}

}
