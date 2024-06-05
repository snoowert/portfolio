package com.member.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.member.vo.MemberVO;
import com.spring.command.PageMaker;

public class MemberDAOImpl implements MemberDAO{
	private SqlSession sqlsession;
	public void setSqlsession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}
	@Override
	public List<MemberVO> selectMemberSearchList(PageMaker pageMaker) {
		// TODO Auto-generated method stub
		int offset = pageMaker.getStartRow();
		int limmit = pageMaker.getPerPageNum();
		
		RowBounds rows = new RowBounds(offset,limmit);
		return sqlsession.selectList("Member-Mapper.selectMemberSearchList",pageMaker, rows);
	}

	@Override
	public MemberVO selectMemberById(int memberid) {
		// TODO Auto-generated method stub
		return sqlsession.selectOne("Member-Mapper.selectMemberById",memberid);
	}

	@Override
	public MemberVO selectMemberByUsername(String username) {
		// TODO Auto-generated method stub
		return sqlsession.selectOne("Member-Mapper.selectMemberByUsername",username);
	}
	@Override
	public int selectMemberSearchListCount(PageMaker pageMaker) {
		// TODO Auto-generated method stub
		return sqlsession.selectOne("Member-Mapper.selectMemberSearchListCount",pageMaker);
	}

	@Override
	public void insertMember(MemberVO member) {
		// TODO Auto-generated method stub
		sqlsession.insert("Member-Mapper.insertMember",member);
	}

	@Override
	public void updateMember(MemberVO member) {
		// TODO Auto-generated method stub
		sqlsession.update("Member-Mapper.updateMember", member);
	}

	@Override
	public void banMember(int memberid) {
		// TODO Auto-generated method stub
		sqlsession.update("Member-Mapper.banMember",memberid);
	}

	@Override
	public void restoreMember(int memberid) {
		// TODO Auto-generated method stub
		sqlsession.update("Member-Mapper.restoreMember",memberid);
	}
	@Override
	public MemberVO selectMemberByEmail(String email) {
		// TODO Auto-generated method stub
		return sqlsession.selectOne("Member-Mapper.selectMemberByEmail",email);
	}

}
