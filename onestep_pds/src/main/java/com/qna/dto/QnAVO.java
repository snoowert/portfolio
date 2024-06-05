package com.qna.dto;

import java.util.Date;
import java.util.List;

public class QnAVO {
	
	private int memberid; 			// 회원 번호
	private int qnaid;	 			// 글 번호
	private String qnatitle; 	// 질문 제목
	private String qnacontent; 	// 질문 내용
	private Date qnaregdate;		// 질문 등록 일자
	private int qnaviewpoint;		// 질문 조회 수
	private Date qnaupdatedate;		// 질문 수정 일자
	
	List<AnswerVO> answerlist;
	String writer;
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
	public Date getQnaregdate() {
		return qnaregdate;
	}
	public void setQnaregdate(Date qnaregdate) {
		this.qnaregdate = qnaregdate;
	}
	public int getQnaviewpoint() {
		return qnaviewpoint;
	}
	public void setQnaviewpoint(int qnaviewpoint) {
		this.qnaviewpoint = qnaviewpoint;
	}
	public Date getQnaupdatedate() {
		return qnaupdatedate;
	}
	public void setQnaupdatedate(Date qnaupdatedate) {
		this.qnaupdatedate = qnaupdatedate;
	}
	public List<AnswerVO> getAnswerlist() {
		return answerlist;
	}
	public void setAnswerlist(List<AnswerVO> answerlist) {
		this.answerlist = answerlist;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	
}
