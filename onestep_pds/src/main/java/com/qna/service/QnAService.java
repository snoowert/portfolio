package com.qna.service;

import java.sql.SQLException;
import java.util.List;

import com.qna.dto.AnswerVO;
import com.qna.dto.QnAVO;
import com.spring.command.PageMaker;

public interface QnAService {
	
	//목록
	List<QnAVO> searchList(PageMaker pageMaker) throws SQLException;
	
	//읽기
	void increaseViewCnt(int qnaid)throws SQLException;
	//답변 포함 조회
	QnAVO getQnA(int qnaid) throws SQLException;
	//답변 제외 조회
	QnAVO detail(int qnaid) throws SQLException;
	
	//등록
	void regist(QnAVO qna)throws SQLException;
	
	//수정
	void modify(QnAVO qna) throws SQLException;
	
	//삭제
	void remove(int qnaid) throws SQLException;
	//답변 조회
	AnswerVO readanswer(int answerid) throws SQLException;
	//답변 등록
	void registAnswer(AnswerVO answer) throws SQLException;
	//답변 수정
	void modifyAnswer(AnswerVO answer) throws SQLException;
	//답변 삭제
	void deleteAnswer(int answerid) throws SQLException;
}
