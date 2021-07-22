package com.day.dto;

import java.util.Date;

public class Comment {
/**
 * 
 */
	private int board_num; // 
	private int cm_number;
	private Date cm_date;
	private String cm_maintext;
	private String cm_user_id;  
	
	public Comment() {
		super();
		
	}
	
	public Comment(int board_num, int cm_number, Date cm_date, String cm_maintext, String cm_user_id) { // ��� ����
		super();
		this.board_num = board_num;
		this.cm_number = cm_number;
		this.cm_date = cm_date;
		this.cm_maintext = cm_maintext;
		this.cm_user_id = cm_user_id;
	}
	
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public int getCm_number() {
		return cm_number;
	}
	public void setCm_number(int cm_number) {
		this.cm_number = cm_number;
	}
	public Date getCm_date() {
		return cm_date;
	}
	public void setCm_date(Date cm_date) {
		this.cm_date = cm_date;
	}
	public String getCm_maintext() {
		return cm_maintext;
	}
	public void setCm_maintext(String cm_maintext) {
		this.cm_maintext = cm_maintext;
	}
	public String getcm_user_id() {
		return cm_user_id;
	}
	public void setUser_id(String cm_user_id) {
		this.cm_user_id = cm_user_id;
	}

	@Override
	public String toString() {
		return "Comment [board_num=" + board_num + ", cm_number=" + cm_number + ", cm_date=" + cm_date
				+ ", cm_maintext=" + cm_maintext + ", cm_user_id=" + cm_user_id + "]";
	}

	
}
