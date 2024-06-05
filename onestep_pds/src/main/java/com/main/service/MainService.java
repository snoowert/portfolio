package com.main.service;

import java.util.List;

import com.onestep.command.PageMaker;
import com.onestep.dto.FreeVO;
import com.pms.dto.ProjectVO;
import com.qna.dto.QnAVO;

public interface MainService {
	//자유게시판 5개
	List<FreeVO> selectMain(PageMaker pageMaker) throws Exception;
	//개발자노트 5개
	List<ProjectVO> selectProjectMain(PageMaker pageMaker) throws Exception;
	//질문게시판 5개
	public List<QnAVO> selectQNAMain(PageMaker pageMaker) throws Exception;
}
