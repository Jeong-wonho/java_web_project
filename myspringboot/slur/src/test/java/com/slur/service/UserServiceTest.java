package com.slur.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.slur.dto.User;
import com.slur.exception.FindException;
@SpringBootTest
class UserServiceTest {
	@Autowired
	UserService service;
	@Test
	void test() throws FindException{
		assertNotNull(service);

		User u1 = service.login("id1", "pwd1");
		assertEquals("박민수", u1.getUser_name());
	}

}
