package com.pds.service;

import java.sql.SQLException;
import java.util.List;

import com.pds.vo.PdsFileVO;
import com.pds.vo.PdsVO;
import com.spring.command.PageMaker;

public interface PdsService {
	//목록
	List<PdsVO> searchList(PageMaker pageker) throws SQLException;
	
	//등록
	void regist(PdsVO pds)throws SQLException;
	
	//읽기
	void increaseViewCnt(int pdsid) throws SQLException;
	
	PdsVO getPds(int pdsid) throws SQLException;
	
	//수정
	void modify(PdsVO pds) throws SQLException;
	
	//삭제
	void remove(int pdsid) throws SQLException;
	
	//파일조회
	PdsFileVO getAttachByAno(int pdsfileid)throws SQLException;
	
	//파일삭제
	void removeAttachByAno(int pdsfileid)throws SQLException;
}
