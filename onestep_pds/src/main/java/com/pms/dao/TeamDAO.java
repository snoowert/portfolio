package com.pms.dao;

import java.sql.SQLException;
import java.util.List;

import com.pms.dto.TeamVO;

public interface TeamDAO {
	public List<TeamVO> selectTeamList(int projectid) throws SQLException;
	public TeamVO selectTeamById(TeamVO team) throws SQLException;
	public void insertTeam(TeamVO team) throws SQLException;
	public void deleteTeam(TeamVO team) throws SQLException;
}
