package com.slur.dto;

import java.util.List;

public class Program {
	private String program_time;
	private String program_title;
	private String program_description;
	private List<Review> reviews;
	private Teacher teacher;
	private Student student;
	
	public Program() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Program(String program_times) {
		super();
		this.program_time = program_times;
	}

	public Program(String program_times, String program_description) {
		super();
		this.program_time = program_times;
		this.program_title = program_description;
	}
	
	public Program(String program_times, String program_description, List<Review> reviews) {
		super();
		this.program_time = program_times;
		this.program_title = program_description;
		this.reviews = reviews;
	}

	public Program(String program_times, String program_description, Teacher teacher, Student student) {
		super();
		this.program_time = program_times;
		this.program_title = program_description;
		this.teacher = teacher;
		this.student = student;
	}
	
	public Program(String program_times, String program_description, List<Review> reviews, Teacher teacher,
			Student student) {
		super();
		this.program_time = program_times;
		this.program_title = program_description;
		this.reviews = reviews;
		this.teacher = teacher;
		this.student = student;
	}

	public Program(String program_time, String program_title, String program_description, List<Review> reviews,
			Teacher teacher, Student student) {
		super();
		this.program_time = program_time;
		this.program_title = program_title;
		this.reviews = reviews;
		this.teacher = teacher;
		this.student = student;
	}

	public String getProgram_title() {
		return program_title;
	}

	public void setProgram_title(String program_title) {
		this.program_title = program_title;
	}

	public String getProgram_description() {
		return program_description;
	}

	public void setProgram_description(String program_description) {
		this.program_description = program_description;
	}

	public String getProgram_time() {
		return program_time;
	}

	public void setProgram_time(String program_times) {
		this.program_time = program_times;
	}

	protected List<Review> getReviews() {
		return reviews;
	}

	protected void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	protected Teacher getTeacher() {
		return teacher;
	}

	protected void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	protected Student getStudent() {
		return student;
	}

	protected void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "Program [program_time=" + program_time + ", program_title=" + program_title + ", program_description="
				+ program_description + ", reviews=" + reviews + ", teacher=" + teacher + ", student=" + student + "]";
	}

}
