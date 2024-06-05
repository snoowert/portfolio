package com.qna.command;

import com.qna.dto.QnAVO;

public class QnAModifyCommand extends QnARegistCommand {
	
	private int qnaid;

	public int getQnaid() {
		return qnaid;
	}

	public void setQnaid(int qnaid) {
		this.qnaid = qnaid;
	}
	
	@Override
	public QnAVO toQnAVO() {
		QnAVO qna = super.toQnAVO();
		qna.setQnaid(this.qnaid);
		
		return qna;
	}
	
}
