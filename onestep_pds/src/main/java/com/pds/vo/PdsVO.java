package com.pds.vo;

import java.util.Date;
import java.util.List;

public class PdsVO {
	private int pdsid;
	private int memberid;
	private String pdstitle;
	private String pdscontent;
	private Date pdsregdate;
	private int pdsviewpoint;
	
	List<PdsFileVO> pdsfilelist;
	String writer;
	public int getPdsid() {
		return pdsid;
	}
	public void setPdsid(int pdsid) {
		this.pdsid = pdsid;
	}
	public int getMemberid() {
		return memberid;
	}
	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}
	public String getPdstitle() {
		return pdstitle;
	}
	public void setPdstitle(String pdstitle) {
		this.pdstitle = pdstitle;
	}
	public String getPdscontent() {
		return pdscontent;
	}
	public void setPdscontent(String pdscontent) {
		this.pdscontent = pdscontent;
	}
	public Date getPdsregdate() {
		return pdsregdate;
	}
	public void setPdsregdate(Date pdsregdate) {
		this.pdsregdate = pdsregdate;
	}
	public int getPdsviewpoint() {
		return pdsviewpoint;
	}
	public void setPdsviewpoint(int pdsviewpoint) {
		this.pdsviewpoint = pdsviewpoint;
	}
	public List<PdsFileVO> getPdsfilelist() {
		return pdsfilelist;
	}
	public void setPdsfilelist(List<PdsFileVO> pdsfilelist) {
		this.pdsfilelist = pdsfilelist;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	
}
