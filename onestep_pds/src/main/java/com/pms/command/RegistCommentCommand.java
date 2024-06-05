package com.pms.command;

import com.pms.dto.NoteCommentVO;

public class RegistCommentCommand {
	
	private int noteid;
	private String dncomcontent;
	private int memberid;
	private int projectid;
	
	

	public int getNoteid() {
		return noteid;
	}
	public void setNoteid(int noteid) {
		this.noteid = noteid;
	}



	public String getDncomcontent() {
		return dncomcontent;
	}
	public void setDncomcontent(String dncomcontent) {
		this.dncomcontent = dncomcontent;
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



	public NoteCommentVO toNoteCommentVO() {
		NoteCommentVO notecomment = new NoteCommentVO();
		notecomment.setNoteid(this.noteid);
		notecomment.setMemberid(this.memberid);
		notecomment.setProjectid(this.projectid);
		notecomment.setDncomcontent(this.dncomcontent);
		return notecomment;
	}
	
	

}
