package com.day.dto;

import java.util.Date;

public class Product {
	
	private int prod_num;
	private String prod_name;
	private String prod_brand;
	private String prod_collection;
	private String prod_modelnum;
	private int prod_releaseprice;
	private Date prod_releaseddt;
	private int prod_count;
	private String prod_gender;

	
	public Product() {
		super();
	}	
	
	public Product(int prod_num) {
		super();
		this.prod_num = prod_num;
	}
	public Product(String prod_name) {
		super();
		this.prod_name = prod_name;
	}
	
	
	public Product(int prod_num, String prod_name) {
		super();
		this.prod_num = prod_num;
		this.prod_name = prod_name;
	}

	public Product(int prod_num, String prod_name, String prod_brand, String prod_collection) {
		super();
		this.prod_num = prod_num;
		this.prod_name = prod_name;
		this.prod_brand = prod_brand;
		this.prod_collection = prod_collection;
	}

	public Product(int prod_num, String prod_name, String prod_brand, String prod_collection, String prod_gender) {
		super();
		this.prod_num = prod_num;
		this.prod_name = prod_name;
		this.prod_brand = prod_brand;
		this.prod_collection = prod_collection;
		this.prod_gender = prod_gender;
	}
	
	public Product(int prod_num, String prod_name, String prod_brand, String prod_collection, String prod_modelnum,
			int prod_releaseprice, Date prod_releaseddt, int prod_count) {
		super();
		this.prod_num = prod_num;
		this.prod_name = prod_name;
		this.prod_brand = prod_brand;
		this.prod_collection = prod_collection;
		this.prod_modelnum = prod_modelnum;
		this.prod_releaseprice = prod_releaseprice;
		this.prod_releaseddt = prod_releaseddt;
		this.prod_count = prod_count;
	}

	public Product(int prod_num, String prod_name, String prod_brand, String prod_collection, String prod_modelnum,
			int prod_releaseprice, Date prod_releaseddt, int prod_count, String prod_gender) {
		super();
		this.prod_num = prod_num;
		this.prod_name = prod_name;
		this.prod_brand = prod_brand;
		this.prod_collection = prod_collection;
		this.prod_modelnum = prod_modelnum;
		this.prod_releaseprice = prod_releaseprice;
		this.prod_releaseddt = prod_releaseddt;
		this.prod_count = prod_count;
		this.prod_gender = prod_gender;
	}

	public String getProd_gender() {
		return prod_gender;
	}

	public void setProd_gender(String prod_gender) {
		this.prod_gender = prod_gender;
	}

	public int getProd_num() {
		return prod_num;
	}
	public void setProd_num(int prod_num) {
		this.prod_num = prod_num;
	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	public String getProd_brand() {
		return prod_brand;
	}
	public void setProd_brand(String prod_brand) {
		this.prod_brand = prod_brand;
	}
	public String getProd_collection() {
		return prod_collection;
	}
	public void setProd_collection(String prod_collection) {
		this.prod_collection = prod_collection;
	}
	public String getProd_modelnum() {
		return prod_modelnum;
	}
	public void setProd_modelnum(String prod_modelnum) {
		this.prod_modelnum = prod_modelnum;
	}
	public int getProd_releaseprice() {
		return prod_releaseprice;
	}
	public void setProd_releaseprice(int prod_releaseprice) {
		this.prod_releaseprice = prod_releaseprice;
	}
	public Date getProd_releaseddt() {
		return prod_releaseddt;
	}
	public void setProd_releaseddt(Date prod_releaseddt) {
		this.prod_releaseddt = prod_releaseddt;
	}
	public int getProd_count() {
		return prod_count;
	}
	public void setProd_count(int prod_count) {
		this.prod_count = prod_count;
	}
	@Override
	public String toString() {
		return "Product [prod_num=" + prod_num + ", prod_name=" + prod_name + ", prod_brand=" + prod_brand
				+ ", prod_collection=" + prod_collection + ", prod_modelnum=" + prod_modelnum + ", prod_releaseprice="
				+ prod_releaseprice + ", prod_releaseddt=" + prod_releaseddt + ", prod_count=" + prod_count
				+ ", prod_gender=" + prod_gender + "]";
	}

}
