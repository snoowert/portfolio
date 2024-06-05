package com.qna.dao;

import java.sql.SQLException;
import java.util.List;

import com.qna.dto.AnswerVO;

public interface AnswerDAO {
	
	List<AnswerVO> selectAnswerList(int qnaid) throws SQLException;
	
	AnswerVO selectAnswerByAnswerId(int answerid) throws SQLException;
	
	void insertAnswer(AnswerVO answer) throws SQLException;
	void updateAnswer(AnswerVO answer) throws SQLException;
	void deleteAnswer(int answerid) throws SQLException;
	
}
