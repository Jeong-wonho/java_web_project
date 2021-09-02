package com.slur.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.slur.dto.Role;
import com.slur.exception.FindException;

import jdk.internal.org.jline.utils.Log;
@SpringBootTest
class UserServiceTest {
	@Autowired
	UserService service;
	@Test
	void test() throws FindException{
		assertNotNull(service);
		
		List<Role> list = service.findByRole("id6");
		for (Role role : list) {
			System.out.println("롤입니다." + role.getProgram_time());
		}
		
	}

}
