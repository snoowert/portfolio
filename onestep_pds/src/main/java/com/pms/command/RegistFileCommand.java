package com.pms.command;

import org.springframework.web.multipart.MultipartFile;

import com.pms.dto.ProjectFileVO;

public class RegistFileCommand {
	
	private int memberId;
	private int projectId;
	private MultipartFile fileName;
	

	


	public MultipartFile getFileName() {
		return fileName;
	}



	public void setFileName(MultipartFile fileName) {
		this.fileName = fileName;
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



	public ProjectFileVO toProjectFileVO(){
		ProjectFileVO pjFile = new ProjectFileVO();
		pjFile.setMemberId(this.memberId);
		pjFile.setProjectId(this.projectId);
		
		return pjFile;
	}

	

}
