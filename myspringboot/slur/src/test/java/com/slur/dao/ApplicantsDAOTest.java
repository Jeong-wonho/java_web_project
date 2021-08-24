package com.slur.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.slur.dto.Program;
import com.slur.dto.Teacher;
import com.slur.dto.User;
import com.slur.exception.FindException;

@SpringBootTest
class ApplicantsDAOTest {
	
	@Autowired
	ApplicantsDAO dao;
	@Test
	void test() throws FindException{
		Teacher t1 = new Teacher();
		User u = new User();
		u.setUser_id("id1");
		Program p = new Program();
		p.setProgram_times("2101");
		t1.setTeacher_user_id(u);
		t1.setTeacher_program(p);
		
		System.out.println("-----------------------------------");
		
		System.out.println(t1.getTeacher_user_id().getUser_id());
		System.out.println(t1.getTeacher_program().getProgram_times());
		
		Teacher t = dao.selectByTeacherApplicants(t1);
		assertEquals("바이올린", t.getTeacher_inst());
		
	}

}
