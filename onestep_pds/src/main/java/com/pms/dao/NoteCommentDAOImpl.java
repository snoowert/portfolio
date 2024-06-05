package com.pms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.pms.dto.NoteCommentVO;

public class NoteCommentDAOImpl implements NoteCommentDAO{
	private SqlSession sqlsession;
	public void setSqlSession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}
	@Override
	public List<NoteCommentVO> selectNoteCommentList(int noteid) throws SQLException {
		// TODO Auto-generated method stub
		return sqlsession.selectList("NoteComment-Mapper.selectNoteCommentList", noteid);
	}

	@Override
	public NoteCommentVO selectNoteCommentById(int dncomid) throws SQLException {
		// TODO Auto-generated method stub
		return sqlsession.selectOne("NoteComment-Mapper.selectNoteCommentById", dncomid);
	}

	@Override
	public void insertNoteComment(NoteCommentVO notecomment) throws SQLException {
		// TODO Auto-generated method stub
		sqlsession.insert("NoteComment-Mapper.insertNoteComment", notecomment);
	}

	@Override
	public void updateNoteComment(NoteCommentVO notecomment) throws SQLException {
		// TODO Auto-generated method stub
		sqlsession.update("NoteComment-Mapper.updateNoteComment", notecomment);
	}

	@Override
	public void deleteNoteComment(int dncomid) throws SQLException {
		// TODO Auto-generated method stub
		sqlsession.delete("NoteComment-Mapper.deleteNoteComment", dncomid);
	}
	
}
