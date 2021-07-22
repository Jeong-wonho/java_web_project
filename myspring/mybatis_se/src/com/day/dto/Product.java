package com.day.dto;

import java.util.Date;

/* 
 * 객체모델링
 * 객체의 속성
 * 상품번호 prod_no (String)
 * 상품이름 prod_name (String)
 * 상품가격 prod_price (int)
 * 제조일자 prod_mf_dt (java.utill.Date) 
 * 상세설명 prod_detail (String)
 * 
 * 
 * DB모델링
 * 엔터티의 속성
 * 상품번호 prod_no (String)
 * 상품이름 prod_name (String)
 * 상품가격 prod_price (int)
 * 
 */

public class Product {
	
	private String prod_no;
	private String prod_name;
	private int prod_price;
	private java.util.Date prod_mf_dt;
	private String prod_detail;
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Product(String prod_no, String prod_name, int prod_price) {
		super();
		this.prod_no = prod_no;
		this.prod_name = prod_name;
		this.prod_price = prod_price;
	}

	public Product(String prod_no, String prod_name, int prod_price, Date prod_mf_dt, String prod_detail) {
		super();
		this.prod_no = prod_no;
		this.prod_name = prod_name;
		this.prod_price = prod_price;
		this.prod_mf_dt = prod_mf_dt;
		this.prod_detail = prod_detail;
	} 

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((prod_no == null) ? 0 : prod_no.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (prod_no == null) {
			if (other.prod_no != null)
				return false;
		} else if (!prod_no.equals(other.prod_no))
			return false;
		return true; //현재 객체의 상품번호(prod_no)와 매개변수 객체의 상품번호가 같은경우만 true.
	}

	public String getProd_no() {
		return prod_no;
	}

	public void setProd_no(String prod_no) {
		this.prod_no = prod_no;
	}

	public String getProd_name() {
		return prod_name;
	}

	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}

	public int getProd_price() {
		return prod_price;
	}

	public void setProd_price(int prod_price) {
		this.prod_price = prod_price;
	}

	public java.util.Date getProd_mf_dt() {
		return prod_mf_dt;
	}

	public void setProd_mf_dt(java.util.Date prod_mf_dt) {
		this.prod_mf_dt = prod_mf_dt;
	}

	public String getProd_detail() {
		return prod_detail;
	}

	public void setProd_detail(String prod_detail) {
		this.prod_detail = prod_detail;
	}

	@Override
	public String toString() {
		return "Product [prod_no=" + prod_no + ", prod_name=" + prod_name + ", prod_price=" + prod_price
				+ ", prod_mf_dt=" + prod_mf_dt + ", prod_detail=" + prod_detail + "]";
	}
	
}
