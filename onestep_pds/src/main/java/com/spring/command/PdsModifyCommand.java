package com.spring.command;

import com.pds.vo.PdsVO;

public class PdsModifyCommand extends PdsRegistCommand{
	private int pdsid;
	private int[] deleteFile;
	public int getPdsid() {
		return pdsid;
	}
	public void setPdsid(int pdsid) {
		this.pdsid = pdsid;
	}
	public int[] getDeleteFile() {
		return deleteFile;
	}
	public void setDeleteFile(int[] deleteFile) {
		this.deleteFile = deleteFile;
	}
	
	@Override
	public PdsVO toPdsVO() {
		PdsVO pds = super.toPdsVO();
		pds.setPdsid(this.pdsid);
		return pds;
	}
}
