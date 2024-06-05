package com.member.service;

import java.sql.SQLException;
import java.util.List;

import com.member.dao.MemberDAO;
import com.member.dao.QuizDAO;
import com.member.vo.MemberVO;
import com.member.vo.QuizVO;
import com.spring.command.PageMaker;
import com.spring.exception.InvalidPasswordException;
import com.spring.exception.NotFoundIdentityException;

public class MemberServiceImpl implements MemberService{
	private MemberDAO memberDAO;
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	private QuizDAO quizDAO;
	public void setQuizDAO(QuizDAO quizDAO) {
		this.quizDAO = quizDAO;
	}
	@Override
	public List<MemberVO> SearchMemberList(PageMaker pageMaker) {
		// TODO Auto-generated method stub
		List<MemberVO> memberList = memberDAO.selectMemberSearchList(pageMaker);
		pageMaker.setTotalCount(memberDAO.selectMemberSearchListCount(pageMaker));
		return memberList;
	}

	@Override
	public MemberVO SelectMemberById(int memberid) {
		// TODO Auto-generated method stub
		MemberVO member = memberDAO.selectMemberById(memberid);
		return member;
	}
	
	@Override
	public MemberVO login(String email, String password) throws NotFoundIdentityException, InvalidPasswordException, SQLException {
		
		MemberVO member = memberDAO.selectMemberByEmail(email);
		
		if(member==null) throw new NotFoundIdentityException();
		if(!password.equals(member.getPassword())) throw new InvalidPasswordException();
		
		
		return member;
	}
	@Override
	public void insertMember(MemberVO member) {
		// TODO Auto-generated method stub
		memberDAO.insertMember(member);
	}

	@Override
	public void updateMember(MemberVO member) {
		// TODO Auto-generated method stub
		memberDAO.updateMember(member);
	}

	@Override
	public void banMember(int memberid) {
		// TODO Auto-generated method stub
		memberDAO.banMember(memberid);
	}

	@Override
	public void restoreMember(int memberid) {
		// TODO Auto-generated method stub
		memberDAO.restoreMember(memberid);
	}
	@Override
	public List<QuizVO> SelectQuizByDevlan(String devlan) {
		// TODO Auto-generated method stub
		return quizDAO.SelectQuizByDevlan(devlan);
	}
	@Override
	public QuizVO SelectQuizByQuizid(int quizid) {
		// TODO Auto-generated method stub
		return quizDAO.selectQuizByQuizid(quizid);
	}
	
}
