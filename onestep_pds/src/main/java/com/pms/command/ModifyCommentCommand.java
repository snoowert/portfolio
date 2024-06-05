package com.pms.command;

import com.pms.dto.NoteCommentVO;

public class ModifyCommentCommand extends RegistCommentCommand{
	
	private int dncomid;
	
	



	public int getDncomid() {
		return dncomid;
	}

	public void setDncomid(int dncomid) {
		this.dncomid = dncomid;
	}




	@Override
	public NoteCommentVO toNoteCommentVO() {
		NoteCommentVO notecomment = super.toNoteCommentVO();
		
		notecomment.setDncomid(this.dncomid);
		
		return notecomment;
	}
	
	

}
