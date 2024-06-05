package com.pms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.pms.dto.CalendarVO;

public class CalendarDAOImpl implements CalendarDAO{

	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public List<CalendarVO> selectCalendarList(int projectId) throws SQLException {
		CalendarVO cal = new CalendarVO();
		return sqlSession.selectList("Calendar-Mapper.selectCalendarList", projectId);

	}

	@Override
	public void insertCalendar(CalendarVO cal) throws SQLException {
		sqlSession.insert("Calendar-Mapper.insertCalendar", cal);
	}

	@Override
	public void deleteCalendar(int id) throws SQLException {
		// TODO Auto-generated method stub
		sqlSession.delete("Calendar-Mapper.deleteCalendar",id);
	}


}