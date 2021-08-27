package com.slur.dto;

import java.util.Date;

public class QA {
	private int qa_num;
	private String qa_title;
	private String qa_content;
	private Date qa_date;
	private User user;
	
	public QA() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public QA(String qa_title) {
		super();
		this.qa_title = qa_title;
	}

	

	public QA(int qa_num, String qa_title, User user) {
		super();
		this.qa_num = qa_num;
		this.qa_title = qa_title;
		this.user = user;
	}


	public QA(int qa_num, String qa_title, String qa_content, Date qa_date, User user) {
		super();
		this.qa_num = qa_num;
		this.qa_title = qa_title;
		this.qa_content = qa_content;
		this.qa_date = qa_date;
		this.user = user;
	}


	public int getQa_num() {
		return qa_num;
	}


	public void setQa_num(int qa_num) {
		this.qa_num = qa_num;
	}


	public String getQa_title() {
		return qa_title;
	}


	public void setQa_title(String qa_title) {
		this.qa_title = qa_title;
	}


	public String getQa_content() {
		return qa_content;
	}


	public void setQa_content(String qa_content) {
		this.qa_content = qa_content;
	}


	public Date getQa_date() {
		return qa_date;
	}


	public void setQa_date(Date qa_date) {
		this.qa_date = qa_date;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "QA [qa_num=" + qa_num + ", qa_title=" + qa_title + ", qa_content=" + qa_content + ", qa_date=" + qa_date
				+ ", user=" + user + "]";
	}



	
	
}
