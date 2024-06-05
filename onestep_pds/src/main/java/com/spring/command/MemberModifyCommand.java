package com.spring.command;

import com.member.vo.MemberVO;

public class MemberModifyCommand extends MemberRegistCommand{
	int memberid;
	
	
	public int getMemberid() {
		return memberid;
	}


	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}


	public MemberVO toMemberVO() {
		MemberVO member = new MemberVO();
		member = super.toMemberVO();
		member.setMemberid(this.memberid);
		return member;
	}
}
