package com.slur.dao;

import com.slur.dto.Student;
import com.slur.dto.Teacher;
import com.slur.dto.User;
import com.slur.exception.AddException;
import com.slur.exception.FindException;
import com.slur.exception.ModifyException;

public interface ApplicationDAO {
	/**
	 * 선생님 신청
	 * @param t
	 * @throws AddException
	 */
	void insertTeacher(Teacher t) throws AddException;
	
	/**
	 * 학생 신청
	 * @param s
	 * @throws AddException
	 */
	void insertStudent(Student s) throws AddException;
	
	/**
	 * 선생님 신청 내용 수정
	 * @param t
	 * @throws ModifyException
	 */
	void updateTeacher(Teacher t) throws ModifyException;
	
	/**
	 * 학생 신청 내용 수정
	 * @param s
	 * @throws ModifyException
	 */
	void updateStudent(Student s) throws ModifyException;
	
	/**
	 * 선생님 신청 내용 확인하기
	 * @param u
	 * @return
	 * @throws FindException
	 */
	Teacher selectByTeacherApplication(User u) throws FindException;
	
	/**
	 * 학생 신청 내용 확인하기
	 * @param u
	 * @return
	 * @throws FindException
	 */
	Student selectByStudentApplication(User u) throws FindException;
}
