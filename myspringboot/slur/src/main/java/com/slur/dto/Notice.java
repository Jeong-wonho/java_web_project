package com.slur.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Notice {
	private int notice_num;
	private String notice_title;
	private String notice_content;
	@JsonFormat(pattern = "yy/MM/dd", timezone = "Asia/Seoul")
	private Date notice_date;
	private User user_id;
	
	public Notice() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Notice(String notice_title) {
		super();
		this.notice_title = notice_title;
	}

	

	public Notice(int notice_num, String notice_title, Date notice_date, User user) {
		super();
		this.notice_num = notice_num;
		this.notice_title = notice_title;
		this.notice_date = notice_date;
		this.user_id = user;
	}


	public Notice(int notice_num, String notice_title, String notice_content, Date notice_date, User user) {
		super();
		this.notice_num = notice_num;
		this.notice_title = notice_title;
		this.notice_content = notice_content;
		this.notice_date = notice_date;
		this.user_id = user;
	}



	public int getNotice_num() {
		return notice_num;
	}



	public void setNotice_num(int notice_num) {
		this.notice_num = notice_num;
	}



	public String getNotice_title() {
		return notice_title;
	}



	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}



	public String getNotice_content() {
		return notice_content;
	}



	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}



	public Date getNotice_date() {
		return notice_date;
	}



	public void setNotice_date(Date notice_date) {
		this.notice_date = notice_date;
	}



	public User getUser_id() {
		return user_id;
	}



	public void setUser_id(User user) {
		this.user_id = user;
	}



	@Override
	public String toString() {
		return "Notice [notice_num=" + notice_num + ", notice_title=" + notice_title + ", notice_content="
				+ notice_content + ", notice_date=" + notice_date + ", user=" + user_id + "]";
	}




	
}
