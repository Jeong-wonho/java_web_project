package com.slur.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slur.dao.ApplicantsDAO;
import com.slur.dto.Student;
import com.slur.dto.Teacher;
import com.slur.exception.AddException;
import com.slur.exception.FindException;
import com.slur.exception.ModifyException;

@Service
public class ApplicantsService {
	@Autowired
	private ApplicantsDAO dao;
	
	/**
	 * 선생님 지원서 추가
	 * @param t
	 * @throws AddException
	 * @throws FindException
	 */
	public void teacher_application(Teacher t) throws AddException, FindException{
		dao.insertTeacher(t);
	}
	
	/**
	 * 학생지원서 추가
	 * @param s
	 * @throws AddException
	 * @throws FindException
	 */
	public void student_application(Student s) throws AddException, FindException{
		dao.insertStudent(s);
	}
	
	/**
	 * 선생님 지원서 수정
	 * @param t
	 * @throws ModifyException
	 */
	public void teacher_modify(Teacher t) throws ModifyException{
		dao.updateTeacher(t);
	}
	
	/**
	 * 학생 지원서 수정
	 * @param s
	 * @throws ModifyException
	 */
	public void student_modify(Student s) throws ModifyException{
		dao.updateStudent(s);
	}
	
	/**
	 * 선생님 지원서 확인
	 * @param t
	 * @return
	 * @throws FindException
	 */
	public Teacher teacherApplicationcontents(Teacher t) throws FindException{
		Teacher teacher = dao.selectByTeacherApplicants(t);
		if(teacher.getTeacher_curr() == null && teacher.getTeacher_curr()=="") {
			throw new FindException("신청해주세요");
		}
		return teacher;
	}
	
	/**
	 * 학생 지원서 확인
	 * @param s
	 * @return
	 * @throws FindException
	 */
	public Student studentApplicationcontents(Student s) throws FindException{
		Student student = dao.selectByStudentApplicants(s);
		if(student.getStudent_introduce() == null && student.getStudent_introduce()=="") {
			throw new FindException("신청해주세요");
		}
		return student;
	}
	
}
