package com.pms.dao;

import java.sql.SQLException;
import java.util.List;

import com.pms.dto.ProjectVO;
import com.spring.command.PageMaker;

public interface ProjectDAO {

	List<ProjectVO> selectProjectList(PageMaker pageMaker) throws SQLException;
	List<ProjectVO> selectPJList(com.onestep.command.PageMaker pageMaker) throws SQLException;
	ProjectVO selectProjectByProjectId(int projectId) throws SQLException;
	
	int selectProjectListCount(PageMaker pageMaker) throws SQLException;
	int selectProjectSeqNext() throws SQLException;
	
	void insertProject(ProjectVO pj) throws SQLException;
	void updateProject(ProjectVO pj) throws SQLException;
	void deleteProject(int projectId) throws SQLException;
	
	
	
}
