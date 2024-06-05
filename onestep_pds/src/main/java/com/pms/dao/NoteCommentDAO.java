package com.pms.dao;

import java.sql.SQLException;
import java.util.List;

import com.pms.dto.NoteCommentVO;

public interface NoteCommentDAO {
	public List<NoteCommentVO> selectNoteCommentList(int noteid) throws SQLException;
	public NoteCommentVO selectNoteCommentById(int dncomid) throws SQLException;
	public void insertNoteComment(NoteCommentVO notecomment) throws SQLException;
	public void updateNoteComment(NoteCommentVO notecomment) throws SQLException;
	public void deleteNoteComment(int dncomid) throws SQLException;
}
