package com.qna.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qna.dto.AnswerVO;

public class AnswerDAOImpl implements AnswerDAO {
	
	private SqlSession session;
	public void setSqlSession(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public List<AnswerVO> selectAnswerList(int qnaid) throws SQLException {
		
		return session.selectList("Answer-Mapper.selectAnswerList", qnaid);
	}
	
	@Override
	public AnswerVO selectAnswerByAnswerId(int answerid) throws SQLException {
		
		return session.selectOne("Answer-Mapper.selectAnswerByAnswerId", answerid);
	}
	
	@Override
	public void insertAnswer(AnswerVO answer) throws SQLException {
		session.insert("Answer-Mapper.insertAnswer", answer);
		
	}
	
	@Override
	public void updateAnswer(AnswerVO answer) throws SQLException {
		session.update("Answer-Mapper.updateAnswer", answer);
		
	}
	
	@Override
	public void deleteAnswer(int answerid) throws SQLException {
		session.delete("Answer-Mapper.deleteAnswer", answerid);
		
	}


}
