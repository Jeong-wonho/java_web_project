package com.slur.dto;

public class QA_Reply {
	private QA qa_num;
	private String qa_reply_content;
	
	public QA_Reply() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QA_Reply(QA qa_num, String qa_reply_content) {
		super();
		this.qa_num = qa_num;
		this.qa_reply_content = qa_reply_content;
	}
	

	public QA getQa_num() {
		return qa_num;
	}

	public void setQa_num(QA qa_num) {
		this.qa_num = qa_num;
	}

	public String getQa_reply_content() {
		return qa_reply_content;
	}

	public void setQa_reply_content(String qa_reply_content) {
		this.qa_reply_content = qa_reply_content;
	}

	@Override
	public String toString() {
		return "QA_Reply [qa_num=" + qa_num + ", qa_reply_content=" + qa_reply_content + "]";
	}
	
	
	
}
