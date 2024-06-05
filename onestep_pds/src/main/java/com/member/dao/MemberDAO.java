package com.member.dao;

import java.util.List;

import com.member.vo.MemberVO;
import com.spring.command.PageMaker;

public interface MemberDAO {

	public List<MemberVO> selectMemberSearchList(PageMaker pageMaker);
	public MemberVO selectMemberById(int memberid);
	public MemberVO selectMemberByEmail(String email);
	public MemberVO selectMemberByUsername(String username);
	public int selectMemberSearchListCount(PageMaker pageMaker);
	public void insertMember(MemberVO member);
	public void updateMember(MemberVO member);
	public void banMember(int memberid);
	public void restoreMember(int memberid);
}
