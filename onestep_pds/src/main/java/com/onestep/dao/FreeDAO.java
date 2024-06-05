package com.onestep.dao;

import java.sql.SQLException;
import java.util.List;

import com.onestep.command.PageMaker;
import com.onestep.dto.FreeVO;

public interface FreeDAO {

	List<FreeVO> selectFreeList(PageMaker pageMaker)throws SQLException;
	int selectFreeListCount(PageMaker pageMaker)throws SQLException;
	FreeVO selectFreeByFreeId(int freeid)throws SQLException;
	int selectFreeSeqNext()throws SQLException;
	void insertFree(FreeVO free)throws SQLException;
	void updateFree(FreeVO free)throws SQLException;
	void deleteFree(int freeid)throws SQLException;
	void increaseFreeViewPoint(int freeid)throws SQLException;

	
}
