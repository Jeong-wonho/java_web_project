package com.slur.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.slur.dto.Program;
import com.slur.dto.Teacher;
import com.slur.dto.User;
import com.slur.exception.FindException;

@SpringBootTest
class ApplicantsServiceTest {
	@Autowired
	ApplicantsService service;
	@Test
	void test() throws FindException{
		assertNotNull(service);
		Teacher t1 = new Teacher();
		User u = new User();
		u.setUser_id("id1");
		Program p = new Program();
		p.setProgram_time("2101");
		t1.setTeacher_user_id(u);
		t1.setTeacher_program(p);
		
		Teacher teacher =  service.teacherApplicationcontents(t1);
		System.out.println(teacher);
		
	}

}
