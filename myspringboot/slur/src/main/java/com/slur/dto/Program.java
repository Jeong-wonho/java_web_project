package com.slur.dto;

import java.util.List;

public class Program {
	private String program_times;
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
		this.program_times = program_times;
	}

	public Program(String program_times, String program_description) {
		super();
		this.program_times = program_times;
		this.program_description = program_description;
	}
	
	public Program(String program_times, String program_description, List<Review> reviews) {
		super();
		this.program_times = program_times;
		this.program_description = program_description;
		this.reviews = reviews;
	}

	public Program(String program_times, String program_description, Teacher teacher, Student student) {
		super();
		this.program_times = program_times;
		this.program_description = program_description;
		this.teacher = teacher;
		this.student = student;
	}
	
	public Program(String program_times, String program_description, List<Review> reviews, Teacher teacher,
			Student student) {
		super();
		this.program_times = program_times;
		this.program_description = program_description;
		this.reviews = reviews;
		this.teacher = teacher;
		this.student = student;
	}

	public String getProgram_times() {
		return program_times;
	}

	public void setProgram_times(String program_times) {
		this.program_times = program_times;
	}

	public String getProgram_description() {
		return getProgram_description();
	}

	public void setProgram_descrition(String program_description) {
		this.program_description = program_description;
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
		return "Program [program_times=" + program_times + ", program_descrition=" + program_description + ", reviews="
				+ reviews + ", teacher=" + teacher + ", student=" + student + "]";
	}

	
	
	
}
