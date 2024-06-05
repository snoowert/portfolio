package com.pms.command;

import com.pms.dto.ProjectVO;

public class RegistProjectCommand {
	String projectName;
	String projectDescription;
	int memberid;
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectDescription() {
		return projectDescription;
	}
	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}
	public int getMemberid() {
		return memberid;
	}
	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}
	
	public ProjectVO setProjectVO() {
		ProjectVO pj = new ProjectVO();
		pj.setProjectName(projectName);
		pj.setProjectDescription(projectDescription);
		
		return pj;
	}
}
