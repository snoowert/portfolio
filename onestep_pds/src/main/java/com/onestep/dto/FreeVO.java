package com.onestep.dto;

import java.util.Date;
import java.util.List;

public class FreeVO {
	
	private int freeid;
	private int memberid;
	private String freetitle;
	private String freecontent;
	private Date freeregdate;
	private int freeviewpoint;
	private String freeimage;
	private Date freeupdatedate;
	
	List<FreeCommentVO> commentlist;
	String writer;
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
	public String getFreetitle() {
		return freetitle;
	}
	public void setFreetitle(String freetitle) {
		this.freetitle = freetitle;
	}
	public String getFreecontent() {
		return freecontent;
	}
	public void setFreecontent(String freecontent) {
		this.freecontent = freecontent;
	}
	public Date getFreeregdate() {
		return freeregdate;
	}
	public void setFreeregdate(Date freeregdate) {
		this.freeregdate = freeregdate;
	}
	public int getFreeviewpoint() {
		return freeviewpoint;
	}
	public void setFreeviewpoint(int freeviewpoint) {
		this.freeviewpoint = freeviewpoint;
	}
	public String getFreeimage() {
		return freeimage;
	}
	public void setFreeimage(String freeimage) {
		this.freeimage = freeimage;
	}
	public Date getFreeupdatedate() {
		return freeupdatedate;
	}
	public void setFreeupdatedate(Date freeupdatedate) {
		this.freeupdatedate = freeupdatedate;
	}
	public List<FreeCommentVO> getCommentlist() {
		return commentlist;
	}
	public void setCommentlist(List<FreeCommentVO> commentlist) {
		this.commentlist = commentlist;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	

	}

