package com.onestep.command;

import com.onestep.dto.FreeVO;

public class FreeRegistCommand {
	private String freetitle;
	private String freecontent;
	private int memberid;
	
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

	public int getMemberid() {
		return memberid;
	}

	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}

	public FreeVO toFreeVO(){
		FreeVO free = new FreeVO();
		free.setFreecontent(this.freecontent);
		free.setFreetitle(this.freetitle);
		free.setMemberid(this.memberid);
		
		return free;
	}
}



