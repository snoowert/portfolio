package com.qna.command;

import com.qna.dto.QnAVO;

public class QnARegistCommand {
	
	private int memberid;
	private int qnaid;
	private String qnatitle;
	private String qnacontent;
	
	public int getMemberid() {
		return memberid;
	}
	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}
	public int getQnaid() {
		return qnaid;
	}
	public void setQnaid(int qnaid) {
		this.qnaid = qnaid;
	}
	public String getQnatitle() {
		return qnatitle;
	}
	public void setQnatitle(String qnatitle) {
		this.qnatitle = qnatitle;
	}
	public String getQnacontent() {
		return qnacontent;
	}
	public void setQnacontent(String qnacontent) {
		this.qnacontent = qnacontent;
	}

	public QnAVO toQnAVO() {
		QnAVO qna = new QnAVO();
		qna.setMemberid(this.memberid);
		qna.setQnaid(this.qnaid);
		qna.setQnatitle(this.qnatitle);
		qna.setQnacontent(this.qnacontent);
		
		return qna;
	}
	
}
