package com.exp.dao;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;

import com.exp.vo.ExpVO;

public class ExpDAOImpl implements ExpDAO{
	private SqlSession sqlsession;
	public void setSqlsession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}
	@Override
	public ExpVO selectExpById(String expid) throws SQLException {
		// TODO Auto-generated method stub
		return sqlsession.selectOne("Exp-Mapper.selectExpById",expid);
	}
	
}
