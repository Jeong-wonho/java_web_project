package com.slur.dto;

public class Teacher {
	private User teacher_user_id; // user를 상속하는게 맞는지.!? 여쭤보기
	private Program teacher_program; // program 회차 입력 
	private String teacher_edu; // 교육수준 ex. 대재, 대졸, 휴학중
	private String teacher_inst; // 전공 악기
	private String teacher_introduce; // 자기소개
	private String teacher_curr; // 커리큘럼, 수업방식
	private String teacher_selection; // 선정여부
	
	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Teacher(User teacher_user_id, String teacher_introduce, String teacher_curr) {
		super();
		this.teacher_user_id = teacher_user_id;
		this.teacher_introduce = teacher_introduce;
		this.teacher_curr = teacher_curr;
	}
	
	public Teacher(User teacher_user_id, Program teacher_program, String teacher_edu, String teacher_inst,
			String teacher_introduce, String teacher_curr, String teacher_selection) {
		super();
		this.teacher_user_id = teacher_user_id;
		this.teacher_program = teacher_program;
		this.teacher_edu = teacher_edu;
		this.teacher_inst = teacher_inst;
		this.teacher_introduce = teacher_introduce;
		this.teacher_curr = teacher_curr;
		this.teacher_selection = teacher_selection;
	}

	public User getTeacher_user_id() {
		return teacher_user_id;
	}

	public void setTeacher_user_id(User teacher_user_id) {
		this.teacher_user_id = teacher_user_id;
	}

	public Program getTeacher_program() {
		return teacher_program;
	}

	public void setTeacher_program(Program teacher_program) {
		this.teacher_program = teacher_program;
	}

	public String getTeacher_edu() {
		return teacher_edu;
	}

	public void setTeacher_edu(String teacher_edu) {
		this.teacher_edu = teacher_edu;
	}

	public String getTeacher_inst() {
		return teacher_inst;
	}

	public void setTeacher_inst(String teacher_inst) {
		this.teacher_inst = teacher_inst;
	}

	public String getTeacher_introduce() {
		return teacher_introduce;
	}

	public void setTeacher_introduce(String teacher_introduce) {
		this.teacher_introduce = teacher_introduce;
	}

	public String getTeacher_curr() {
		return teacher_curr;
	}

	public void setTeacher_curr(String teacher_curr) {
		this.teacher_curr = teacher_curr;
	}

	public String getTeacher_selection() {
		return teacher_selection;
	}

	public void setTeacher_selection(String teacher_selection) {
		this.teacher_selection = teacher_selection;
	}

	@Override
	public String toString() {
		return "Teacher [teacher_user_id=" + teacher_user_id + ", program=" + teacher_program + ", teacher_edu=" + teacher_edu
				+ ", teacher_inst=" + teacher_inst + ", teacher_introduce=" + teacher_introduce + ", teacher_curr="
				+ teacher_curr + ", teacher_selection=" + teacher_selection + "]";
	}


	
	
}
