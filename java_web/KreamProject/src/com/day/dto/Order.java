package com.day.dto;

import java.util.Date;

public class Order {
	private int order_num;
	private Customer buyer_c;
	private Customer seller_c;
	private Product order_p;
	private int order_type; // 0 - 판매입찰자 , 1 - 구매입찰자
	private int order_size;
	private int order_price;
	private Date order_date;
	private int order_status; // -1-거래취소, 1-입찰진행중, 2-거래완료
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(Product prod_num, int order_type, int order_price, Date order_date, int order_status) { // 입찰일
		super();
		this.order_p = order_p;
		this.order_type = order_type;
		this.order_price = order_price;
		this.order_date = order_date;
		this.order_status = order_status;
	}
	
	public Order(Product prod_num, int order_type, int order_size, int order_price, int order_status) { //오더사이즈
		super();
		this.order_p = order_p;
		this.order_type = order_type;
		this.order_size = order_size;
		this.order_price = order_price;
		this.order_status = order_status;
	}

	public Order( Product prod_num, int order_num, int order_type, int order_price, Date order_date) { 
		super();
		this.order_num = order_num;
		this.order_p = order_p;
		this.order_type = order_type;
		this.order_price = order_price;
		this.order_date = order_date;
	}

	public Order(int order_num, Product prod_num, int order_type, int order_size, int order_price, int order_status) {
		super();
		this.order_num = order_num;
		this.order_p = order_p;
		this.order_type = order_type;
		this.order_size = order_size;
		this.order_price = order_price;
		this.order_status = order_status;
	}

	public int getOrder_num() {
		return order_num;
	}


	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}


	public Customer getBuyer_id() {
		return buyer_c;
	}


	public void setBuyer_id(Customer buyer_c) {
		this.buyer_c = buyer_c;
	}


	public Customer getSeller_id() {
		return seller_c;
	}


	public void setSeller_id(Customer seller_c) {
		this.seller_c = seller_c;
	}


	public Product getProd_num() {
		return order_p;
	}


	public void setProd_num(Product order_p) {
		this.order_p = order_p;
	}


	public int getOrder_type() {
		return order_type;
	}


	public void setOrder_type(int order_type) {
		this.order_type = order_type;
	}


	public int getOrder_size() {
		return order_size;
	}


	public void setOrder_size(int order_size) {
		this.order_size = order_size;
	}


	public int getOrder_price() {
		return order_price;
	}


	public void setOrder_price(int order_price) {
		this.order_price = order_price;
	}


	public Date getOrder_date() {
		return order_date;
	}


	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}


	public int getOrder_status() {
		return order_status;
	}


	public void setOrder_status(int order_status) {
		this.order_status = order_status;
	}


	@Override
	public String toString() {
		return "Order [order_num=" + order_num + ", buyer_id=" + buyer_c + ", seller_id=" + seller_c + ", order_p="
				+ order_p + ", order_type=" + order_type + ", order_size=" + order_size + ", order_price="
				+ order_price + ", order_date=" + order_date + ", order_status=" + order_status + "]";
	}
	
	
	
	
}
