package com.slur.dto;

import java.util.List;

public class User {
	private String user_id;
	private String user_pwd;
	private String user_name;
	private String user_addr;
	private String user_email;
	private String user_cp;
	private List<Teacher> teachers;
	private List<Student> students;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public User(String user_id) {
		super();
		this.user_id = user_id;
	}
	
	

	public User(String user_id, String user_pwd) {
		super();
		this.user_id = user_id;
		this.user_pwd = user_pwd;
	}
	
	public User(String user_id, String user_name, String user_addr, String user_email, String user_cp) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_addr = user_addr;
		this.user_email = user_email;
		this.user_cp = user_cp;
	}

	public User(String user_id, String user_pwd, String user_name, String user_addr, String user_email, String user_cp,
			List<Teacher> teachers, List<Student> students) {
		super();
		this.user_id = user_id;
		this.user_pwd = user_pwd;
		this.user_name = user_name;
		this.user_addr = user_addr;
		this.user_email = user_email;
		this.user_cp = user_cp;
		this.teachers = teachers;
		this.students = students;
	}


	public String getUser_id() {
		return user_id;
	}


	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	public String getUser_pwd() {
		return user_pwd;
	}


	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}


	public String getUser_addr() {
		return user_addr;
	}


	public void setUser_addr(String user_addr) {
		this.user_addr = user_addr;
	}


	public String getUser_email() {
		return user_email;
	}


	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}


	public String getUser_cp() {
		return user_cp;
	}


	public void setUser_cp(String user_cp) {
		this.user_cp = user_cp;
	}


	public List<Teacher> getTeacher() {
		return teachers;
	}


	public void setTeacher(List<Teacher> teachers) {
		this.teachers = teachers;
	}


	public List<Student> getStudent() {
		return students;
	}


	public void setStudent(List<Student> students) {
		this.students = students;
	}


	public String getUser_name() {
		return user_name;
	}


	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}


	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_pwd=" + user_pwd + ", user_name=" + user_name + ", user_addr="
				+ user_addr + ", user_email=" + user_email + ", user_cp=" + user_cp + ", teacher=" + teachers
				+ ", student=" + students + "]";
	}




	
	
	
	
}
