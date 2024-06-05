package com.pms.command;

import java.text.SimpleDateFormat;

import com.pms.dto.CalendarVO;

public class RegistCalendarCommand {
	
	private String title;
	private String startd;
	private String endd;
	private int memberId;
	private int projectId;
	
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
//	public Date getStartd() {
//		return startd;
//	}
//	public void setStartd(Date startd) {
//		this.startd = startd;
//	}
//	public Date getEndd() {
//		return endd;
//	}
//	public void setEndd(Date endd) {
//		this.endd = endd;
//	}
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
	
	public String getStartd() {
		return startd;
	}
	public void setStartd(String startd) {
		this.startd = startd;
	}
	public String getEndd() {
		return endd;
	}
	public void setEndd(String endd) {
		this.endd = endd;
	}
	public CalendarVO toVO() throws Exception{
		CalendarVO cal = new CalendarVO();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");	
		cal.setTitle(this.title);
		cal.setMemberId(this.memberId);
		cal.setProjectId(this.projectId);
		cal.setStartd(sdf.parse(this.startd));
		cal.setEndd(sdf.parse(this.endd));
		return cal;
	}
	

}
