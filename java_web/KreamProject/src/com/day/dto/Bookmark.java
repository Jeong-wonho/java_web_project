package com.day.dto;

public class Bookmark {

	private int bm_num;
	private Customer bm_c;
	private Product bm_p;
	private int prod_size;
	public Bookmark() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Bookmark(Customer bm_c, Product bm_p, int prod_size) {
		super();
		this.bm_c = bm_c;
		this.bm_p = bm_p;
		this.prod_size = prod_size;
	}



	public Bookmark(int bm_num, Customer bm_c, Product bm_p, int prod_size) {
		super();
		this.bm_num = bm_num;
		this.bm_c = bm_c;
		this.bm_p = bm_p;
		this.prod_size = prod_size;
	}

	public int getBm_num() {
		return bm_num;
	}



	public void setBm_num(int bm_num) {
		this.bm_num = bm_num;
	}



	public Customer getBm_c() {
		return bm_c;
	}



	public void setBm_c(Customer bm_c) {
		this.bm_c = bm_c;
	}



	public Product getBm_p() {
		return bm_p;
	}



	public void setBm_p(Product bm_p) {
		this.bm_p = bm_p;
	}



	public int getProd_size() {
		return prod_size;
	}



	public void setProd_size(int prod_size) {
		this.prod_size = prod_size;
	}



	@Override
	public String toString() {
		return "Bookmark [bm_num=" + bm_num + ", bm_c=" + bm_c + ", bm_p=" + bm_p + ", prod_size=" + prod_size + "]";
	}




}
