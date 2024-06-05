package com.pms.command;

import com.pms.dto.NoteVO;

public class RegistFeedCommand {
	
	private String noteTitle;
	private String noteContent;
	private int memberId;
	private int projectId;
	
	public String getNoteTitle() {
		return noteTitle;
	}
	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}
	public String getNoteContent() {
		return noteContent;
	}
	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
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
	
	
	public NoteVO toNoteVO() {
		NoteVO note = new NoteVO();
		note.setNoteContent(noteContent);
		note.setNoteTitle(noteTitle);
		note.setMemberId(memberId);
		note.setProjectId(projectId);
		return note;
	}
	
	

}
