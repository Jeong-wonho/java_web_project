package com.day.dto;

public class Customer {

	private String user_id;
	private String user_pwd;
	private String user_name;
	private String user_ssn;
	private String user_phone;
	private String user_addr;
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(String user_phone) { 
		super();
		this.user_phone = user_phone;
	}

	public Customer(String user_phone, String user_id) { 
		super();		
		this.user_phone = user_phone;
		this.user_id = user_id;
	}

	public Customer(String user_id, String user_pwd, String user_name, String user_ssn, String user_phone,
			String user_addr) {
		super();
		this.user_id = user_id;
		this.user_pwd = user_pwd;
		this.user_name = user_name;		
		this.user_ssn = user_ssn;
		this.user_phone = user_phone;
		this.user_addr = user_addr;
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

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_ssn() {
		return user_ssn;
	}

	public void setUser_ssn(String user_ssn) {
		this.user_ssn = user_ssn;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getUser_addr() {
		return user_addr;
	}

	public void setUser_addr(String user_addr) {
		this.user_addr = user_addr;
	}

	@Override
	public String toString() {
		return "Customer [user_id=" + user_id + ", user_pwd=" + user_pwd + ", user_name=" + user_name + ", user_ssn="
				+ user_ssn + ", user_phone=" + user_phone + ", user_addr=" + user_addr + "]";
	}
	
}
