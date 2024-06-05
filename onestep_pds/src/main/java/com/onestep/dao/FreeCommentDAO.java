package com.onestep.dao;

import java.sql.SQLException;
import java.util.List;

import com.onestep.dto.FreeCommentVO;


public interface FreeCommentDAO {

	List<FreeCommentVO> selectFreeCommentList(int freeid)throws SQLException;
	
	FreeCommentVO selectFreeByCommentId(int commentid)throws SQLException;

	void insertFreeComment(FreeCommentVO freecomment)throws SQLException;
	void updateFreeComment(FreeCommentVO freecomment)throws SQLException;
	void deleteFreeComment(int commentid)throws SQLException;
}
