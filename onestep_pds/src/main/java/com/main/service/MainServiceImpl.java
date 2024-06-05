package com.main.service;

import java.util.List;

import com.onestep.command.PageMaker;
import com.onestep.dao.FreeDAO;
import com.onestep.dto.FreeVO;
import com.pms.dao.ProjectDAO;
import com.pms.dto.ProjectVO;
import com.qna.dao.QnADAO;
import com.qna.dto.QnAVO;

public class MainServiceImpl implements MainService{
	private FreeDAO freeDAO;
	public void setFreeDAO(FreeDAO freeDAO) {
		this.freeDAO = freeDAO;
	}
	private ProjectDAO projectDAO;
	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}
	private QnADAO qnaDAO;
	public void setQnADAO(QnADAO qnaDAO) {
		this.qnaDAO = qnaDAO;
	}
	@Override
	public List<FreeVO> selectMain(PageMaker pageMaker) throws Exception {
		// TODO Auto-generated method stub
		return freeDAO.selectFreeList(pageMaker);
	}
	@Override
	public List<ProjectVO> selectProjectMain(PageMaker pageMaker) throws Exception {
		// TODO Auto-generated method stub
		return projectDAO.selectPJList(pageMaker);
	}
	@Override
	public List<QnAVO> selectQNAMain(PageMaker pageMaker) throws Exception {
		// TODO Auto-generated method stub
		return qnaDAO.selectSearchQnAList(pageMaker);
	}
	
}
