package com.pds.dao;

import java.sql.SQLException;
import java.util.List;

import com.pds.vo.PdsVO;
import com.spring.command.PageMaker;

public interface PdsDAO {
	List<PdsVO> selectSearchPdsList(PageMaker pageMaker)throws SQLException;
	int selectSearchPdsListCount(PageMaker pageMaker)throws SQLException;
	PdsVO selectPdsByPdsid(int pdsid)throws SQLException;
	int selectPdsidSeqNext() throws SQLException;
	void insertPds(PdsVO pds)throws SQLException;
	void updatePds(PdsVO pds)throws SQLException;
	void increaseViewPoint(int pdsid)throws SQLException;
	void deletePds(int pdsid)throws SQLException;
}
