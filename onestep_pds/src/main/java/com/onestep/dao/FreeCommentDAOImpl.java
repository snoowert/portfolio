package com.onestep.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.onestep.dto.FreeCommentVO;

public class FreeCommentDAOImpl implements FreeCommentDAO{
	
	private SqlSession sqlsession;
	public void setSqlSession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;}

	@Override
	public List<FreeCommentVO> selectFreeCommentList(int freeid) throws SQLException {
		return sqlsession.selectList("FreeComment-Mapper.selectFreeCommentList",freeid);
	}

	@Override
	public FreeCommentVO selectFreeByCommentId(int commentid) throws SQLException {

		return sqlsession.selectOne("FreeComment-Mapper.selectFreeByCommentId",commentid);
	}

	@Override
	public void insertFreeComment(FreeCommentVO freecomment) throws SQLException {
		sqlsession.insert("FreeComment-Mapper.insertFreeComment",freecomment);
		
	}

	@Override
	public void updateFreeComment(FreeCommentVO freecomment) throws SQLException {
		sqlsession.update("FreeComment-Mapper.updateFreeComment",freecomment);
		
	}

	@Override
	public void deleteFreeComment(int commentid) throws SQLException {
		sqlsession.insert("FreeComment-Mapper.deleteFreeComment",commentid);
		
	}

}
