package com.qna.dao;

import java.sql.SQLException;
import java.util.List;

import com.qna.dto.QnAVO;
import com.spring.command.PageMaker;

public interface QnADAO {

	List<QnAVO> selectSearchQnAList(PageMaker pageMaker) throws SQLException;
	int selectSearchQnAListCount(PageMaker pageMaker) throws SQLException;
	QnAVO selectQnAByQnAId(int qnaid) throws SQLException;
	int selectQnASeqNext() throws SQLException;
	
	
	void insertQnA(QnAVO qna) throws SQLException;
	void updateQnA(QnAVO qna) throws SQLException;
	void increaseViewCnt(int qnaid) throws SQLException;
	void deleteQnA(int qnaid) throws SQLException;
	List<QnAVO> selectSearchQnAList(com.onestep.command.PageMaker pageMaker) throws SQLException;
}
