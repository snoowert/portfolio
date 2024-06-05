package com.exp.service;

import com.exp.vo.ExpVO;

import java.sql.SQLException;

public interface ExpService {
	public ExpVO selectExpById(String expid) throws SQLException;
}
