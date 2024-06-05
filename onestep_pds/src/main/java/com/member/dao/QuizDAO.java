package com.member.dao;

import java.util.List;

import com.member.vo.QuizVO;

public interface QuizDAO {
	public List<QuizVO> SelectQuizByDevlan(String devlan);
	public QuizVO selectQuizByQuizid(int quizid);
}
