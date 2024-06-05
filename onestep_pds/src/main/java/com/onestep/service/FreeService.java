package com.onestep.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.onestep.command.PageMaker;
import com.onestep.dto.FreeCommentVO;
import com.onestep.dto.FreeVO;

public interface FreeService {
	
	List<FreeVO> searchList(PageMaker pageMaker)throws SQLException;
	//읽기
	void increaseFreeViewPoint(int freeid) throws SQLException;
	
	FreeVO detail(int freeid)throws SQLException;
	//등록
	void regist(FreeVO free)throws SQLException;
	//수정
	void modify(FreeVO free)throws SQLException;
	//삭제
	void remove(int freeid)throws SQLException;
	
	FreeCommentVO readcomment(int commentid)throws SQLException;
	
	void registcomment(FreeCommentVO freecomment, int freeid)throws SQLException;
	
	void modifycomment(FreeCommentVO freecomment)throws SQLException;
	
	void removecomment(int commentid)throws SQLException;

}
