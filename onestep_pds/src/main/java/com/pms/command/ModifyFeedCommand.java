package com.pms.command;

import com.pms.dto.NoteVO;

public class ModifyFeedCommand extends RegistFeedCommand{
	private int noteId;

	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}
	
	@Override
	public NoteVO toNoteVO() {
		NoteVO note = super.toNoteVO();
		
		note.setNoteId(this.noteId);
		
		return note;
	}
}
