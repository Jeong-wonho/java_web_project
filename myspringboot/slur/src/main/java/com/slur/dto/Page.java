package com.slur.dto;

public class Page {
	private int startPage, endPage;
	private boolean prev, next;
	
	private int total;
//	private Criteria cri;
//	
//	public Page(Criteria cri, int total) {
//		this.cri = cri;
//		this.total = total;
//		
//		this.endPage = (int)Math.ceil(cri.getPageNum()/10.0) * 10;
//		
//		this.startPage = endPage - 9;
//		
//		this.prev = this.startPage > 1;
//		
//		int realEnd = (int)(Math.ceil((total * 1.0) / cri.getAmount()));
//		
//		this.endPage = realEnd <= endPage ? realEnd : endPage;
//		
//		this.next = this.endPage < realEnd;
//		
//	}
//	

	private int pageNo;
	//public static final int AMOUNT=10; //페이지별 보여줄 최대목록수 
	private int amount;
	
	
	
	public Page() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Page(int pageNo, int amount, int total) {
		super();
		this.pageNo = pageNo;
		this.total = total;
		this.endPage = (int)Math.ceil(pageNo/10.0) * 10;
//		
		this.startPage = endPage - 9;
//		
		this.prev = this.startPage > 1;
//		
		int realEnd = (int)(Math.ceil((total * 1.0) / amount));
//		
		this.endPage = realEnd <= endPage ? realEnd : endPage;
//		
		this.next = this.endPage < realEnd;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

//	public Criteria getCri() {
//		return cri;
//	}
//
//	public void setCri(Criteria cri) {
//		this.cri = cri;
//	}

//	@Override
//	public String toString() {
//		return "Page [startPage=" + startPage + ", endPage=" + endPage + ", prev=" + prev + ", next=" + next
//				+ ", total=" + total + ", cri=" + cri + "]";
//	}
	

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Page [startPage=" + startPage + ", endPage=" + endPage + ", prev=" + prev + ", next=" + next
				+ ", total=" + total + ", pageNo=" + pageNo + ", amount=" + amount + "]";
	}

}
