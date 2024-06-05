package com.pds.dao;

import java.util.List;

import com.pds.vo.PdsFileVO;

public interface PdsFileDAO {
	List<PdsFileVO> selectPdsFileList(int pdsid);
	PdsFileVO selectPdsFileById(int pdsfileid);
	
	void insertPdsFile(PdsFileVO pdsfile);
	void deletePdsFile(int PdsFileId);
	void deleteAllFile(int pdsid);
}
