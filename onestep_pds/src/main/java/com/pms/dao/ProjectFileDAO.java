package com.pms.dao;

import java.util.List;

import com.pms.dto.ProjectFileVO;
import com.spring.command.PageMaker;

public interface ProjectFileDAO {
	
	List<ProjectFileVO> selectProjectFileList(int projectid) throws Exception;
	
	ProjectFileVO selectProjectFileByFileId(int fileId) throws Exception;
	
	public int selectProjectFileListCount(int projectid)throws Exception;
	int selectProjectFileIdSeqNext() throws Exception;
	void insertProjectFile(ProjectFileVO projectFile) throws Exception;
	void increaseProjectFileDownCnt(int fileId)throws Exception;
	
	void deleteProjectFile(int fileId) throws Exception;
	

}
