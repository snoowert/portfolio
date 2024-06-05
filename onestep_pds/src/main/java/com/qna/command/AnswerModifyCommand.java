package com.qna.command;

import com.qna.dto.AnswerVO;

public class AnswerModifyCommand extends AnswerRegistCommand {
	
	private int answerid;

	public int getAnswerid() {
		return answerid;
	}

	public void setAnswerid(int answerid) {
		this.answerid = answerid;
	}
	
	@Override
	public AnswerVO toAnswerVO() {
		AnswerVO answer = super.toAnswerVO();
		answer.setAnswerid(this.answerid);
		
		return answer;
	}
	
}
