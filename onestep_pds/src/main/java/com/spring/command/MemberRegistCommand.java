package com.spring.command;

import com.member.vo.MemberVO;

public class MemberRegistCommand {
	private String email;
	private String username;
	private String authority;
	private String password;
	private String devlan;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getDevlan() {
		return devlan;
	}
	public void setDevlan(String devlan) {
		this.devlan = devlan;
	}
	public MemberVO toMemberVO() {
		MemberVO member = new MemberVO();
		member.setEmail(this.email);
		member.setAuthority(this.authority);
		member.setUsername(this.username);
		member.setPassword(this.password);
		member.setDevlan(this.devlan);
		return member;
	}
}
