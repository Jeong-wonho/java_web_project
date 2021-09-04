package com.slur.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Qa {
	private int qa_num;
	private String qa_title;
	private String qa_content;
	@JsonFormat(pattern = "yy/MM/dd", timezone = "Asia/Seoul")
	private Date qa_date;
	private User user_id;
	private Qa_Reply qa_reply;
	
	public Qa() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Qa(String qa_title) {
		super();
		this.qa_title = qa_title;
	}

	

	public Qa(int qa_num, String qa_title, User user_id) {
		super();
		this.qa_num = qa_num;
		this.qa_title = qa_title;
		this.user_id = user_id;
	}


	public Qa(int qa_num, String qa_title, String qa_content, Date qa_date, User user_id) {
		super();
		this.qa_num = qa_num;
		this.qa_title = qa_title;
		this.qa_content = qa_content;
		this.qa_date = qa_date;
		this.user_id = user_id;
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


	public User getUser_id() {
		return user_id;
	}


	public void setUser_id(User user) {
		this.user_id = user;
	}


	public Qa_Reply getQa_reply() {
		return qa_reply;
	}


	public void setQa_reply(Qa_Reply qa_reply) {
		this.qa_reply = qa_reply;
	}


	@Override
	public String toString() {
		return "Qa [qa_num=" + qa_num + ", qa_title=" + qa_title + ", qa_content=" + qa_content + ", qa_date=" + qa_date
				+ ", user_id=" + user_id + ", qa_reply=" + qa_reply + "]";
	}

	
	



	
	
}
