package com.onestep.command;

import java.util.Date;

import com.onestep.dto.FreeCommentVO;


public class CommentRegistCommand {
	private int freeid;
	private int memberid;
	private String commentcontent;
	private Date commentregdate;

    
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

	public FreeCommentVO toFreeCommentVO(){
		FreeCommentVO comment = new FreeCommentVO();
		comment.setFreeid(this.freeid);
		comment.setCommentcontent(this.commentcontent);
		comment.setMemberid(this.memberid);
		
		return comment;
	}
}



