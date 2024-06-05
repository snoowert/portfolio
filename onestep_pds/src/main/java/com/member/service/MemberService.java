package com.member.service;

import java.sql.SQLException;
import java.util.List;

import com.member.vo.MemberVO;
import com.member.vo.QuizVO;
import com.spring.command.PageMaker;
import com.spring.exception.InvalidPasswordException;
import com.spring.exception.NotFoundIdentityException;

public interface MemberService {
	//전체조회
	public List<MemberVO> SearchMemberList(PageMaker pageMaker);
	//단일조회
	public MemberVO SelectMemberById(int memberid);
	public MemberVO login(String id, String pwd) throws NotFoundIdentityException, InvalidPasswordException, SQLException;
	//생성
	public void insertMember(MemberVO member);
	//수정
	public void updateMember(MemberVO member);
	//임시삭제
	public void banMember(int memberid);
	//복구
	public void restoreMember(int memberid);
	//가입용 퀴즈목록
	public List<QuizVO> SelectQuizByDevlan(String devlan);
	//가입용 퀴즈조회
	public QuizVO SelectQuizByQuizid(int quizid);
}
