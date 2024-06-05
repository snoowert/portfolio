package com.spring.command;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.pds.vo.PdsVO;

public class PdsRegistCommand {
	private String pdstitle;
	private String pdscontent;
	private int memberid;
	private List<MultipartFile> uploadFile;
	
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
	public int getMemberid() {
		return memberid;
	}
	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}
	public List<MultipartFile> getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(List<MultipartFile> uploadFile) {
		this.uploadFile = uploadFile;
	}
	
	public PdsVO toPdsVO(){
		PdsVO pds = new PdsVO();
		pds.setPdscontent(this.pdscontent);
		pds.setPdstitle(this.pdstitle);
		pds.setMemberid(this.memberid);

		return pds;
	}
}
