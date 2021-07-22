package com.day.dto;

import java.util.Date;
import java.util.List;

public class Board {

	/**
	 * 성호 cm_count추가 : 댓글 개수만 필요해서 Customer userId를 그냥 String user id로 변경 String
	 * prod_name 으로 변경
	 */
	private int board_num;
	private String user_id;
	private String prod_name;
	private int prod_releaseprice;
	private int board_recomnened;
	private String board_maintext;
	private Date board_date;
	private String board_title;
	private List<Comment> cm_lines;
	private int cm_count;

	public Board() {
		super();
	}

	public Board(int board_num, String user_id, String prod_name, int prod_releaseprice, int board_recomnened,
			String board_maintext, Date board_date, String board_title, List<Comment> cm_lines, int cm_count) {
		super();
		this.board_num = board_num;
		this.user_id = user_id;
		this.prod_name = prod_name;
		this.prod_releaseprice = prod_releaseprice;
		this.board_recomnened = board_recomnened;
		this.board_maintext = board_maintext;
		this.board_date = board_date;
		this.board_title = board_title;
		this.cm_lines = cm_lines;
		this.cm_count = cm_count;
	}

	public Board(int board_num, String user_id, String prod_name, int board_recomnened, String board_maintext,
			Date board_date, String board_title) { 
		// 게시글 생성
		super();
		this.board_num = board_num;
		this.user_id = user_id;
		this.prod_name = prod_name;
		this.board_recomnened = board_recomnened;
		this.board_maintext = board_maintext;
		this.board_date = board_date;
		this.board_title = board_title;
	}

	public Board(int board_num, String user_id, String prod_name, int board_recomnened, String board_maintext,
			Date board_date, String board_title, int cm_count) {
		// 게시판 목록, 댓글수만 필요해서 cm_line지우고 count로 바꿈
		super();
		this.board_num = board_num;
		this.user_id = user_id;
		this.prod_name = prod_name;
		this.board_recomnened = board_recomnened;
		this.board_maintext = board_maintext;
		this.board_date = board_date;
		this.board_title = board_title;
		this.cm_count = cm_count;

	}

	public String getProd_name() {
		return prod_name;
	}

	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}

	public int getProd_releaseprice() {
		return prod_releaseprice;
	}

	public void setProd_releaseprice(int prod_releaseprice) {
		this.prod_releaseprice = prod_releaseprice;
	}

	public List<Comment> getCm_lines() {
		return cm_lines;
	}

	public void setCm_lines(List<Comment> cm_lines) {
		this.cm_lines = cm_lines;
	}

	public int getBoard_num() {
		return board_num;
	}

	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getprod_name() {
		return prod_name;
	}

	public void setprod_name(String prod_name) {
		this.prod_name = prod_name;
	}

	public int getBoard_recomnened() {
		return board_recomnened;
	}

	public void setBoard_recomnened(int board_recomnened) {
		this.board_recomnened = board_recomnened;
	}

	public String getBoard_maintext() {
		return board_maintext;
	}

	public void setBoard_maintext(String board_maintext) {
		this.board_maintext = board_maintext;
	}

	public Date getBoard_date() {
		return board_date;
	}

	public void setBoard_date(Date board_date) {
		this.board_date = board_date;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	public int getCm_count() {
		return cm_count;
	}

	public void setCm_count(int cm_count) {
		this.cm_count = cm_count;
	}

	@Override
	public String toString() {
		return "Board [board_num=" + board_num + ", user_id=" + user_id + ", prod_name=" + prod_name
				+ ", prod_releaseprice=" + prod_releaseprice + ", board_recomnened=" + board_recomnened
				+ ", board_maintext=" + board_maintext + ", board_date=" + board_date + ", board_title=" + board_title
				+ ", cm_lines=" + cm_lines + ", cm_count=" + cm_count + "]";
	}

}
