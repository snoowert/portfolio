package com.pms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.pms.dto.NoteVO;

public class NoteDAOImpl implements NoteDAO {
	
	private SqlSession sqlsession;
	public void setSqlSession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}

	@Override
	public List<NoteVO> selectNoteList(int projectId) throws SQLException {
		return sqlsession.selectList("Note-Mapper.selectNoteList", projectId);
	}

	@Override
	public NoteVO selectNoteByNoteId(int noteId) throws SQLException {
		return sqlsession.selectOne("Note-Mapper.selectNoteByNoteId", noteId);
	}


	@Override
	public int selectNoteSeqNext() throws SQLException {
		int seq = sqlsession.selectOne("Note-Mapper.selectNoteSeqNext");
		return seq;
	}
	
	
	@Override
	public void insertNote(NoteVO note) throws SQLException {
		sqlsession.insert("Note-Mapper.insertNote", note);		
	}

	@Override
	public void updateNote(NoteVO note) throws SQLException {
		sqlsession.update("Note-Mapper.updateNote", note);
	}

	@Override
	public void deleteNote(int noteId) throws SQLException {
		sqlsession.update("Note-Mapper.deleteNote", noteId);
	}

	@Override
	public void increaseNoteViewPoint(int noteId) throws SQLException {
		sqlsession.update("Note-Mapper.increaseNoteViewPoint", noteId);
		
	}





}
