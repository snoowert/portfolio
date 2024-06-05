package com.pms.dao;

import java.sql.SQLException;
import java.util.List;

import com.pms.dto.CalendarVO;

public interface CalendarDAO {
	
	List<CalendarVO> selectCalendarList(int projectId) throws SQLException;
	
	void insertCalendar(CalendarVO cal) throws SQLException;
	void deleteCalendar(int id) throws SQLException;
}
