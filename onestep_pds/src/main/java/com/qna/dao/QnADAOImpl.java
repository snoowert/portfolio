package com.qna.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.qna.dto.QnAVO;
import com.spring.command.PageMaker;

public class QnADAOImpl implements QnADAO{

	private SqlSession session;
	public void setSqlSession(SqlSession session) {
		this.session = session;
	}
	@Override
	public List<QnAVO> selectSearchQnAList(PageMaker pageMaker) throws SQLException {
		int offset = pageMaker.getStartRow();
		int limit = pageMaker.getPerPageNum();
		
		RowBounds rows = new RowBounds(offset, limit);
		return session.selectList("QnA-Mapper.selectSearchQnAList", pageMaker, rows);
	}
	@Override
	public List<QnAVO> selectSearchQnAList(com.onestep.command.PageMaker pageMaker) throws SQLException {
		int offset = pageMaker.getStartRow();
		int limit = pageMaker.getPerPageNum();
		
		RowBounds rows = new RowBounds(offset, limit);
		return session.selectList("QnA-Mapper.selectSearchQnAList", pageMaker, rows);
	}
	@Override
	public int selectSearchQnAListCount(PageMaker pageMaker) throws SQLException {
		
		return session.selectOne("QnA-Mapper.selectSearchQnAListCount", pageMaker);
	}
	@Override
	public QnAVO selectQnAByQnAId(int qnaid) throws SQLException {
		
		return session.selectOne("QnA-Mapper.selectQnAByQnAId", qnaid);
	}
	@Override
	public int selectQnASeqNext() throws SQLException {
		
		return session.selectOne("QnA-Mapper.selectQnASeqNext");
	}
	@Override
	public void insertQnA(QnAVO qna) throws SQLException {
		session.insert("QnA-Mapper.insertQnA",qna);
		
	}
	@Override
	public void updateQnA(QnAVO qna) throws SQLException {
		session.update("QnA-Mapper.updateQnA",qna);
		
	}
	@Override
	public void increaseViewCnt(int qnaid) throws SQLException {
		session.update("QnA-Mapper.increaseViewCnt",qnaid);
		
	}
	@Override
	public void deleteQnA(int qnaid) throws SQLException {
		session.delete("QnA-Mapper.deleteQnA",qnaid);
		
	}
	


}
