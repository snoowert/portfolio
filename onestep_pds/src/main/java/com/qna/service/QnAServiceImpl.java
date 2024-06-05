package com.qna.service;

import java.sql.SQLException;
import java.util.List;

import com.member.dao.MemberDAO;
import com.qna.dao.AnswerDAO;
import com.qna.dao.QnADAO;
import com.qna.dto.AnswerVO;
import com.qna.dto.QnAVO;
import com.spring.command.PageMaker;

public class QnAServiceImpl implements QnAService {

	private QnADAO QnaDAO;
	public void setQnADAO(QnADAO qnaDAO) {
		this.QnaDAO = qnaDAO;
	}
	
	private AnswerDAO answerDAO;
	public void setAnswerDAO(AnswerDAO answerDAO) {
		this.answerDAO = answerDAO;
	}
	
	private MemberDAO memberDAO;
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	@Override
	public List<QnAVO> searchList(PageMaker pageMaker) throws SQLException {
		List<QnAVO> QnAList = QnaDAO.selectSearchQnAList(pageMaker);
		
		if(QnAList.size() > 0 ) {
			for (QnAVO qna : QnAList) {
				int qnaid = qna.getQnaid();
				List<AnswerVO> answerList = answerDAO.selectAnswerList(qnaid);
				for(AnswerVO ans : answerList) {
					ans.setWriter(memberDAO.selectMemberById(ans.getMemberid()).getUsername());
					
				}
				qna.setWriter(memberDAO.selectMemberById(qna.getMemberid()).getUsername());
				qna.setAnswerlist(answerList);
			}
		}
		pageMaker.setTotalCount(QnaDAO.selectSearchQnAListCount(pageMaker));
		
		return QnAList;
	}
	
	@Override
	public void increaseViewCnt(int qnaid) throws SQLException {
	
		QnaDAO.increaseViewCnt(qnaid);		
	
	}
	
	@Override
	public QnAVO getQnA(int qnaid) throws SQLException {
		QnAVO qna = QnaDAO.selectQnAByQnAId(qnaid);
		qna.setAnswerlist(answerDAO.selectAnswerList(qnaid));
		for(AnswerVO ans : qna.getAnswerlist()) {
			ans.setWriter(memberDAO.selectMemberById(ans.getMemberid()).getUsername());
		}
		qna.setWriter(memberDAO.selectMemberById(qna.getMemberid()).getUsername());
		return qna;
	}
	
	@Override
	public QnAVO detail(int qnaid) throws SQLException {
		QnAVO qna = QnaDAO.selectQnAByQnAId(qnaid);
		qna.setWriter(memberDAO.selectMemberById(qna.getMemberid()).getUsername());
		return qna;
	}

	
	@Override
	public void regist(QnAVO qna) throws SQLException {
		
		int qnaid = QnaDAO.selectQnASeqNext();
		qna.setQnaid(qnaid);
		QnaDAO.insertQnA(qna);
		
	}
	
	@Override
	public void modify(QnAVO qna) throws SQLException {
		
		QnaDAO.updateQnA(qna);
		
	}
	
	@Override
	public void remove(int qnaid) throws SQLException {
		
		QnaDAO.deleteQnA(qnaid);
		
	}

	@Override
	public AnswerVO readanswer(int answerid) throws SQLException {
		AnswerVO ans = answerDAO.selectAnswerByAnswerId(answerid);
		ans.setWriter(memberDAO.selectMemberById(ans.getMemberid()).getUsername());
		return ans;
	}

	@Override
	public void registAnswer(AnswerVO answer ) throws SQLException {

		answerDAO.insertAnswer(answer);
		
	}

	@Override
	public void modifyAnswer(AnswerVO answer) throws SQLException {

		answerDAO.updateAnswer(answer);
		
	}

	@Override
	public void deleteAnswer(int answerid) throws SQLException {

		answerDAO.deleteAnswer(answerid);
		
	}
	
}
