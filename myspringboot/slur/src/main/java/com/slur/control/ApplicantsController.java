package com.slur.control;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	public Object teacher_app(@RequestBody Teacher teacher, HttpSession session){
		Map<String, Object> result = new HashMap<>();
		try {
			User u = (User)session.getAttribute("loginInfo");
			log.error(u.getUser_id());
			teacher.setTeacher_user_id(u);
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
	public Object student_app(@RequestBody Student student, HttpSession session){
		Map<String, Object> result = new HashMap<>();
		try {
			User u = (User)session.getAttribute("loginInfo");
			log.error(u.getUser_id());
			student.setStudent_user_id(u);
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
	public Map<String, Object> modify(@RequestBody Teacher teacher, HttpSession session){
		log.error(teacher.toString());
		Map<String, Object> result = new HashMap<>();
		User u = (User)session.getAttribute("loginInfo");
		log.error(u.getUser_id());
		teacher.setTeacher_user_id(u);
		
		try {
			service.teacher_modify(teacher);
			result.put("status", 1);
		}catch(Exception e) {
			e.printStackTrace();
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	
	@PutMapping("/student_app_modify")
	public Map<String, Object> modify(@RequestBody Student student ,HttpSession session){
		log.error(student.toString());
		log.error(student.toString());
		Map<String, Object> result = new HashMap<>();
		User u = (User)session.getAttribute("loginInfo");
		log.error(u.getUser_id());
		student.setStudent_user_id(u);
		
		try {
			service.student_modify(student);
			result.put("status", 1);
		}catch(Exception e) {
			e.printStackTrace();
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	
	@GetMapping("/teacher_select")
	public Map<String, Object> select_teacher(Teacher teacher, HttpSession session){
		Map<String, Object> application_result = new HashMap<>();
		User u = (User)session.getAttribute("loginInfo");
		log.error(u.getUser_id());
		teacher.setTeacher_user_id(u);
		try {
			Teacher t = service.teacherApplicationcontents(teacher);
			application_result.put("stauts", 1);
			application_result.put("teacher", t);
		}catch(Exception e) {
			e.printStackTrace();
			application_result.put("status", 0);
			application_result.put("msg", e.getMessage());
			
		}
		return application_result;
	}
	
	@GetMapping("/student_select")
	public Map<String, Object> select_student(Student student, HttpSession session){
		User u = (User)session.getAttribute("loginInfo");
		log.error(u.getUser_id());
		student.setStudent_user_id(u);
		Map<String, Object> application_result = new HashMap<>();
		try {
			Student s = service.studentApplicationcontents(student);
			application_result.put("status", 1);
			application_result.put("student", s);
		}catch(Exception e) {
			e.printStackTrace();
			application_result.put("status", 0);
			application_result.put("msg", e.getMessage());
			
		}
		return application_result;
	}
	
}
