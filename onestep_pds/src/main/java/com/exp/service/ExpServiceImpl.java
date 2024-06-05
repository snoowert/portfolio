package com.exp.service;

import java.sql.SQLException;

import com.exp.dao.ExpDAO;
import com.exp.vo.ExpVO;

public class ExpServiceImpl implements ExpService{
	private ExpDAO expDAO;
	public void setExpDAO(ExpDAO expDAO) {
		this.expDAO = expDAO;
	}
	@Override
	public ExpVO selectExpById(String expid) throws SQLException {
		// TODO Auto-generated method stub
		return expDAO.selectExpById(expid);
	}
	
}
