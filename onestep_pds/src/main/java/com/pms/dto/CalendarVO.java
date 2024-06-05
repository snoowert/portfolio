package com.pms.dto;

import java.util.Date;

public class CalendarVO {
	
	private int id;
	private int callId;
	private String title;
	private Date startd;
	private Date endd;
	private String content;
	private int memberId;
	private int projectId;
	
	private String startDate;
	private String endDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCallId() {
		return callId;
	}
	public void setCallId(int callId) {
		this.callId = callId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}	
	public Date getStartd() {
		return startd;
	}
	public void setStartd(Date startd) {
		this.startd = startd;
	}
	public Date getEndd() {
		return endd;
	}
	public void setEndd(Date endd) {
		this.endd = endd;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	

	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	

	
	

}
