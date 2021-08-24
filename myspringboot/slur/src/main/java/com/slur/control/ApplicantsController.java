package com.slur.control;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slur.dto.Program;
import com.slur.dto.Student;
import com.slur.dto.Teacher;
import com.slur.dto.User;
import com.slur.service.ApplicantsService;

@RestController
@RequestMapping("/applicants/*")
public class ApplicantsController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ApplicantsService service;
	
	@PostMapping("/teacher_app")
	public Object teacher_app(@RequestBody Teacher teacher){
		Map<String, Object> result = new HashMap<>();
		try {
			
			log.error(teacher.toString());
			service.teacher_application(teacher);
			result.put("status", 1);
			
		}catch (Exception e){
			e.printStackTrace();
			result.put("status", 0);
			result.put("msg", e.getMessage());
			
		}
		return result;
	}
	
	@PostMapping("/student_app")
	public Object student_app(@RequestBody Student student){
		Map<String, Object> result = new HashMap<>();
		try {
			
			log.error(student.toString());
			service.student_application(student);
			result.put("status", 1);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	
	@PutMapping("/teacher_app_modify")
	public Map<String, Object> modify(@RequestBody Teacher teacher){
		log.error(teacher.toString());
		Map<String, Object> result = new HashMap<>();
		// --> session의 loginInfo속성으로 차후 변경
		User teacherUser = new User();
		teacherUser.setUser_id("id1");
		Program teacherProgram = new Program();
		teacherProgram.setProgram_times("2101");
		
		Teacher t = new Teacher();
		t.setTeacher_user_id(teacherUser);
		t.setTeacher_program(teacherProgram);
		
		try {
			service.teacher_modify(t);
			result.put("status", 1);
		}catch(Exception e) {
			e.printStackTrace();
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	
	@PutMapping("/student_app_modify")
	public Map<String, Object> modify(@RequestBody Student student){
		log.error(student.toString());
		Map<String, Object> result = new HashMap<>();
		// --> session의 loginInfo속성으로 차후 변경
		User studentUser = new User();
		studentUser.setUser_id("id1");
		Program studentProgram = new Program();
		studentProgram.setProgram_times("2101");
		
		Student s = new Student();
		s.setStudent_user_id(studentUser);
		s.setStudent_program(studentProgram);
		
		try {
			service.student_modify(s);
			result.put("status", 1);
		}catch(Exception e) {
			e.printStackTrace();
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	

}
