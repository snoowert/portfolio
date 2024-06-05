 package com.pms.dto;

import java.util.List;

public class ProjectVO {
	
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
	@Override
	public String toString() {
		return "ProjectVO [projectId=" + projectId + ", projectName=" + projectName + ", projectDescription=" + projectDescription
				+ "]";
	}
	
	
	

}
