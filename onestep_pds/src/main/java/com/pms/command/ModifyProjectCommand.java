package com.pms.command;

import com.pms.dto.ProjectVO;

public class ModifyProjectCommand {
	
	private int projectId;
	private String projectName;
	private String projectDescription;
	
	
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
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
	
	public ProjectVO toProjectVO() {
		ProjectVO project = new ProjectVO();
		project.setProjectId(this.projectId);
		project.setProjectName(this.projectName);
		project.setProjectDescription(this.projectDescription);
		
		return project;
	}
	
	
	

}
