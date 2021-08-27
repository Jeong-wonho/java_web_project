package com.slur.dto;

public class Role {
	String program_time;
	String program_title;
	String role_name; //'s', 't'
	String role_selection; //2, 1, 0...
	
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(String program_time, String program_title, String role_name, String role_selection) {
		super();
		this.program_time = program_time;
		this.program_title = program_title;
		this.role_name = role_name;
		this.role_selection = role_selection;
	}

	public String getProgram_time() {
		return program_time;
	}

	public void setProgram_time(String program_time) {
		this.program_time = program_time;
	}

	public String getProgram_title() {
		return program_title;
	}

	public void setProgram_title(String program_title) {
		this.program_title = program_title;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getRole_selection() {
		return role_selection;
	}

	public void setRole_selection(String role_selection) {
		this.role_selection = role_selection;
	}

	@Override
	public String toString() {
		return "Role [program_time=" + program_time + ", program_title=" + program_title + ", role_name=" + role_name
				+ ", role_selection=" + role_selection + "]";
	}
	
	
	
	
	
}
