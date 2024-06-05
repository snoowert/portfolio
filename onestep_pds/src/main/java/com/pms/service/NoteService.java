package com.pms.service;

import java.sql.SQLException;
import java.util.List;

import com.spring.command.PageMaker;
import com.pms.dto.NoteVO;

public interface NoteService {
	
	List<NoteVO> feedList(int projectId) throws SQLException;
	
	void regist(NoteVO note) throws SQLException;
	NoteVO detail(int noteId)throws SQLException;
	void modify(NoteVO note) throws SQLException;
	void remove(int noteId) throws SQLException;
	
	
}
