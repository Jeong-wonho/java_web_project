package com.slur.dto;

import java.util.Date;

public class Review {
	private int review_num;
	private String review_title;
	private Date review_date;
	private String review_content;
	private User user; // program이 student와 teacher에 student ,  teacher가 user에 그러면 program 따로 안불러와도 되는지.!
	private String review_program_times;
	
	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Review(String review_title) {
		super();
		this.review_title = review_title;
	}

	public Review(String review_title, Date review_date) {
		super();
		this.review_title = review_title;
		this.review_date = review_date;
	}

	
	public Review(int review_num, String review_title, Date review_date, String review_content, User user) {
		super();
		this.review_num = review_num;
		this.review_title = review_title;
		this.review_date = review_date;
		this.review_content = review_content;
		this.user = user;
	}
	
	public Review(int review_num, String review_title, Date review_date, String review_content,
			String review_program_times) {
		super();
		this.review_num = review_num;
		this.review_title = review_title;
		this.review_date = review_date;
		this.review_content = review_content;
		this.review_program_times = review_program_times;
	}

	public Review(int review_num, String review_title, Date review_date, String review_content, User user,
			String review_program_times) {
		super();
		this.review_num = review_num;
		this.review_title = review_title;
		this.review_date = review_date;
		this.review_content = review_content;
		this.user = user;
		this.review_program_times = review_program_times;
	}

	protected int getReview_num() {
		return review_num;
	}

	protected void setReview_num(int review_num) {
		this.review_num = review_num;
	}

	protected String getReview_title() {
		return review_title;
	}

	protected void setReview_title(String review_title) {
		this.review_title = review_title;
	}

	protected Date getReview_date() {
		return review_date;
	}

	protected void setReview_date(Date review_date) {
		this.review_date = review_date;
	}

	protected String getReview_content() {
		return review_content;
	}

	protected void setReview_content(String review_content) {
		this.review_content = review_content;
	}

	protected User getUser() {
		return user;
	}

	protected void setUser(User user) {
		this.user = user;
	}

	protected String getReview_program_times() {
		return review_program_times;
	}

	protected void setReview_program_times(String review_program_times) {
		this.review_program_times = review_program_times;
	}

	@Override
	public String toString() {
		return "Review [review_num=" + review_num + ", review_title=" + review_title + ", review_date=" + review_date
				+ ", review_content=" + review_content + ", user=" + user + ", review_program_times="
				+ review_program_times + "]";
	}
	

	
	
	
	
}
