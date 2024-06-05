package com.pds.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.pds.vo.PdsFileVO;

public class PdsFileDAOImpl implements  PdsFileDAO{
	SqlSession sqlsession;
	public void setSqlSession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}
	@Override
	public List<PdsFileVO> selectPdsFileList(int pdsid) {
		// TODO Auto-generated method stub
		return sqlsession.selectList("PdsFile-Mapper.selectPdsFileList",pdsid);
	}

	@Override
	public PdsFileVO selectPdsFileById(int pdsfileid) {
		// TODO Auto-generated method stub
		return sqlsession.selectOne("PdsFile-Mapper.selectPdsFileById", pdsfileid);
	}

	@Override
	public void insertPdsFile(PdsFileVO pdsfile) {
		// TODO Auto-generated method stub
		sqlsession.insert("PdsFile-Mapper.insertPdsFile",pdsfile);
	}

	@Override
	public void deletePdsFile(int PdsFileId) {
		// TODO Auto-generated method stub
		sqlsession.delete("PdsFile-Mapper.deletePdsFile",PdsFileId);
	}

	@Override
	public void deleteAllFile(int pdsid) {
		// TODO Auto-generated method stub
		sqlsession.delete("PdsFile-Mapper.deleteAllFile",pdsid);
	}
	

}
