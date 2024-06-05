package com.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.member.vo.QuizVO;

public class QuizDAOImpl implements QuizDAO{
	private SqlSession sqlsession;
	public void setSqlSession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}
	@Override
	public List<QuizVO> SelectQuizByDevlan(String devlan) {
		// TODO Auto-generated method stub
		return sqlsession.selectList("Quiz-Mapper.selectQuizByDevlan",devlan);
	}

	@Override
	public QuizVO selectQuizByQuizid(int quizid) {
		// TODO Auto-generated method stub
		return sqlsession.selectOne("Quiz-Mapper.selectQuizByQuizid",quizid);
	}
	
}
