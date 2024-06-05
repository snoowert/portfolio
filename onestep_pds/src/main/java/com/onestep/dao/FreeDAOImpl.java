package com.onestep.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.onestep.command.PageMaker;
import com.onestep.dto.FreeVO;

public class FreeDAOImpl implements FreeDAO{

	private SqlSession sqlsession;
	public void setSqlSession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;}
	
	@Override
	public List<FreeVO> selectFreeList(PageMaker pageMaker) throws SQLException {
	    if (sqlsession == null) {
	        throw new SQLException("SqlSession is not initialized.");
	    }

	    int offset = (pageMaker.getPage() - 1) * pageMaker.getPerPageNum();
	    int limit = pageMaker.getPerPageNum();

	    RowBounds rows = new RowBounds(offset, limit);

	    return sqlsession.selectList("Free-Mapper.selectFreeList", pageMaker, rows);
	}


	@Override
	public int selectFreeListCount(PageMaker pageMaker) throws SQLException {
		return sqlsession.selectOne("Free-Mapper.selectFreeListCount",pageMaker);
	}

	@Override
	public FreeVO selectFreeByFreeId(int freeid) throws SQLException {
		return sqlsession.selectOne("Free-Mapper.selectFreeByFreeId",freeid);
	}

	@Override
	public int selectFreeSeqNext() throws SQLException {
		int seq = sqlsession.selectOne("Free-Mapper.selectFreeSeqNext");
		return seq;
	}

	@Override
	public void insertFree(FreeVO free) throws SQLException {
		sqlsession.insert("Free-Mapper.insertFree",free);
		
	}

	@Override
	public void updateFree(FreeVO free) throws SQLException {
		sqlsession.update("Free-Mapper.updateFree",free);
		
	}

	@Override
	public void deleteFree(int freeid) throws SQLException {
		sqlsession.insert("Free-Mapper.deleteFree",freeid);
		
	}

	@Override
	public void increaseFreeViewPoint(int freeid) throws SQLException {
		sqlsession.update("Free-Mapper.increaseFreeViewPoint",freeid);
		
	}
	
}
