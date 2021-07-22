package com.day.dto;

public class Shop {
	private Product shop_p;
	private int shop_price;
	private int shop_bm; //0은 없느거 1은 있는거
	
	public Shop() {
		super();
	}
	
	public Shop(Product shop_p, int shop_price, int shop_bm) {
		super();
		this.shop_p = shop_p;
		this.shop_price = shop_price;
		this.shop_bm = shop_bm;
	}
	
	public Product getShop_p() {
		return shop_p;
	}
	public void setShop_p(Product shop_p) {
		this.shop_p = shop_p;
	}
	public int getShop_price() {
		return shop_price;
	}
	public void setShop_price(int shop_price) {
		this.shop_price = shop_price;
	}
	public int getShop_bm() {
		return shop_bm;
	}
	public void setShop_bm(int shop_bm) {
		this.shop_bm = shop_bm;
	}

	@Override
	public String toString() {
		return "Shop [shop_p=" + shop_p + ", shop_price=" + shop_price + ", shop_bm=" + shop_bm + "]";
	}
	
	
}
