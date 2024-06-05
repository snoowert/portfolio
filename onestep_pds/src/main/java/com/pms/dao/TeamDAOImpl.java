package com.pms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.pms.dto.TeamVO;

public class TeamDAOImpl implements TeamDAO{
	private SqlSession sqlsession;
	public void setSqlSession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}
	@Override
	public List<TeamVO> selectTeamList(int projectid) throws SQLException {
		// TODO Auto-generated method stub
		return sqlsession.selectList("Team-Mapper.selectTeamList",projectid);
	}

	@Override
	public TeamVO selectTeamById(TeamVO team) throws SQLException {
		// TODO Auto-generated method stub
		return sqlsession.selectOne("Team-Mapper.selectTeamById",team);
	}

	@Override
	public void insertTeam(TeamVO team) throws SQLException {
		// TODO Auto-generated method stub
		sqlsession.insert("Team-Mapper.insertTeam",team);
	}

	@Override
	public void deleteTeam(TeamVO team) throws SQLException {
		// TODO Auto-generated method stub
		sqlsession.delete("Team-Mapper.deleteTeam",team);
	}
	
}
