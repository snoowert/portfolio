package com.exp.dao;

import java.sql.SQLException;

import com.exp.vo.ExpVO;

public interface ExpDAO {
	public ExpVO selectExpById(String expid) throws SQLException;
}
