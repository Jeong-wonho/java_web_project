package com.slur.dto;

public class Student {
	private User student_user_id;
	private Program student_program;
	private String student_edu;
	private String student_inst;
	private String student_introduce;
	private String student_selection;
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(User student_user_id, String student_edu, String student_introduce) {
		super();
		this.student_user_id = student_user_id;
		this.student_edu = student_edu;
		this.student_introduce = student_introduce;
	}

	public Student(User student_user_id, Program student_program, String student_edu, String student_inst,
			String student_introduce, String student_selection) {
		super();
		this.student_user_id = student_user_id;
		this.student_program = student_program;
		this.student_edu = student_edu;
		this.student_inst = student_inst;
		this.student_introduce = student_introduce;
		this.student_selection = student_selection;
	}

	public User getStudent_user_id() {
		return student_user_id;
	}

	public void setStudent_user_id(User student_user_id) {
		this.student_user_id = student_user_id;
	}

	public Program getStudent_program() {
		return student_program;
	}

	public void setStudent_program(Program student_program) {
		this.student_program = student_program;
	}

	public String getStudent_edu() {
		return student_edu;
	}

	public void setStudent_edu(String student_edu) {
		this.student_edu = student_edu;
	}

	public String getStudent_inst() {
		return student_inst;
	}

	public void setStudent_inst(String student_inst) {
		this.student_inst = student_inst;
	}

	public String getStudent_introduce() {
		return student_introduce;
	}

	public void setStudent_introduce(String student_introduce) {
		this.student_introduce = student_introduce;
	}

	public String getStudent_selection() {
		return student_selection;
	}

	public void setStudent_selection(String student_selection) {
		this.student_selection = student_selection;
	}

	@Override
	public String toString() {
		return "Student [student_user_id=" + student_user_id + ", student_program=" + student_program
				+ ", student_edu=" + student_edu + ", student_inst=" + student_inst + ", student_introduce="
				+ student_introduce + ", student_selection=" + student_selection + "]";
	}
	
	
	
}
