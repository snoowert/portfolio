package com.onestep.dto;

import java.util.Date;

public class FreeCommentVO {
	
	private int commentid;
	private int freeid;
	private int memberid;
	private String commentcontent;
	private Date commentregdate;
	
	String writer;
	public int getCommentid() {
		return commentid;
	}
	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}
	public int getFreeid() {
		return freeid;
	}
	public void setFreeid(int freeid) {
		this.freeid = freeid;
	}
	public int getMemberid() {
		return memberid;
	}
	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}
	public String getCommentcontent() {
		return commentcontent;
	}
	public void setCommentcontent(String commentcontent) {
		this.commentcontent = commentcontent;
	}
	public Date getCommentregdate() {
		return commentregdate;
	}
	public void setCommentregdate(Date commentregdate) {
		this.commentregdate = commentregdate;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	
}
