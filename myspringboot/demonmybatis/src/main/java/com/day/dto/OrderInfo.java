package com.day.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OrderInfo {

	private int order_no;
	//private String order_id;
	private Customer order_c; //HAS A관계 - FK OrderInfo의 id가 Customer id 를 참고 하기 때문.
	
	@JsonFormat(pattern = "yy/MM/dd", timezone = "Asia/Seoul")
	private java.util.Date order_dt; //java.util.Date
	private List<OrderLine> lines;
	
	public OrderInfo() {
		super();
	}
	
	public OrderInfo(Customer order_c) {
		super();
		this.order_c = order_c;
	}
	
	public OrderInfo(Customer order_c, List<OrderLine> lines) {
		super();
		this.order_c = order_c;
		this.lines = lines;
	}

	public OrderInfo(int order_no, Customer order_c, Date order_dt, List<OrderLine> lines) {
		super();
		this.order_no = order_no;
		this.order_c = order_c;
		this.order_dt = order_dt;
		this.lines = lines;
	}

	public int getOrder_no() {
		return order_no;
	}

	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}

	public Customer getOrder_c() {
		return order_c;
	}

	public void setOrder_c(Customer order_c) {
		this.order_c = order_c;
	}

	public Date getOrder_dt() {
		return order_dt;
	}

	public void setOrder_dt(Date order_dt) {
		this.order_dt = order_dt;
	}

	public List<OrderLine> getLines() {
		return lines;
	}

	public void setLines(List<OrderLine> lines) {
		this.lines = lines;
	}

	@Override
	public String toString() {
		return "OrderInfo [order_no=" + order_no + ", order_c=" + order_c + ", order_dt=" + order_dt + ", lines="
				+ lines + "]";
	}
	
}
