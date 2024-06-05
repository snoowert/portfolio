package com.pms.dao;

import java.sql.SQLException;
import java.util.List;

import com.pms.dto.NoteVO;

public interface NoteDAO {
	
	
	 List<NoteVO> selectNoteList(int projectId) throws SQLException;
	 NoteVO selectNoteByNoteId(int noteId) throws SQLException;
	 int selectNoteSeqNext() throws SQLException;
	 
	 void insertNote(NoteVO note) throws SQLException;
	 void updateNote(NoteVO note) throws SQLException;
	 void deleteNote(int noteId) throws SQLException;
	 
	 void increaseNoteViewPoint(int noteId) throws SQLException;
	 
	 
	 
	 
}
