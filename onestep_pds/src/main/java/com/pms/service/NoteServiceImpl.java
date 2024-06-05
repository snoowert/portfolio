package com.pms.service;

import java.sql.SQLException;
import java.util.List;

import com.spring.command.PageMaker;
import com.pms.dao.NoteDAO;
import com.pms.dto.NoteVO;

public class NoteServiceImpl implements NoteService{
	
	private NoteDAO noteDAO;
	public void setNoteDAO(NoteDAO noteDAO) {
		this.noteDAO = noteDAO;
	}
	

	@Override
	public List<NoteVO> feedList(int projectId) throws SQLException {
		return noteDAO.selectNoteList(projectId);
	}

	@Override
	public void regist(NoteVO note) throws SQLException {
		noteDAO.insertNote(note);
		
	}

	@Override
	public NoteVO detail(int noteId) throws SQLException {
		return noteDAO.selectNoteByNoteId(noteId);
	}

	@Override
	public void modify(NoteVO note) throws SQLException {
		noteDAO.updateNote(note);
		
	}

	@Override
	public void remove(int noteId) throws SQLException {
		noteDAO.deleteNote(noteId);
	}

}
