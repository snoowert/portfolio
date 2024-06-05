package com.qna.dto;

import java.util.Date;

public class AnswerVO {
	
	private int answerid;
	private int qnaid;
	private String answercontent;
	private Date answerregdate;
	private Date answerupdatedate;
	private int memberid;
	
	String writer;
	public int getAnswerid() {
		return answerid;
	}
	public void setAnswerid(int answerid) {
		this.answerid = answerid;
	}
	public int getQnaid() {
		return qnaid;
	}
	public void setQnaid(int qnaid) {
		this.qnaid = qnaid;
	}
	public String getAnswercontent() {
		return answercontent;
	}
	public void setAnswercontent(String answercontent) {
		this.answercontent = answercontent;
	}
	public Date getAnswerregdate() {
		return answerregdate;
	}
	public void setAnswerregdate(Date answerregdate) {
		this.answerregdate = answerregdate;
	}
	public Date getAnswerupdatedate() {
		return answerupdatedate;
	}
	public void setAnswerupdatedate(Date answerupdatedate) {
		this.answerupdatedate = answerupdatedate;
	}
	public int getMemberid() {
		return memberid;
	}
	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	
}
