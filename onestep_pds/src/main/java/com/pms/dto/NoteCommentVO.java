package com.pms.dto;

import java.util.Date;

public class NoteCommentVO {
	private int noteid;
	private int dncomid;
	private String dncomcontent;
	private Date dncomregdate;
	private int memberid;
	private int projectid;
	
	String writer;
	
	public int getNoteid() {
		return noteid;
	}
	public void setNoteid(int noteid) {
		this.noteid = noteid;
	}
	public int getDncomid() {
		return dncomid;
	}
	public void setDncomid(int dncomid) {
		this.dncomid = dncomid;
	}
	public String getDncomcontent() {
		return dncomcontent;
	}
	public void setDncomcontent(String dncomcontent) {
		this.dncomcontent = dncomcontent;
	}
	public Date getDncomregdate() {
		return dncomregdate;
	}
	public void setDncomregdate(Date dncomregdate) {
		this.dncomregdate = dncomregdate;
	}
	public int getMemberid() {
		return memberid;
	}
	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}
	public int getProjectid() {
		return projectid;
	}
	public void setProjectid(int projectid) {
		this.projectid = projectid;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	
}
