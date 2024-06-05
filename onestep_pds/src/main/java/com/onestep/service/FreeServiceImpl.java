package com.onestep.service;

import java.sql.SQLException;
import java.util.List;

import com.member.dao.MemberDAO;
import com.onestep.command.PageMaker;
import com.onestep.dao.FreeCommentDAO;
import com.onestep.dao.FreeDAO;
import com.onestep.dto.FreeCommentVO;
import com.onestep.dto.FreeVO;

public class FreeServiceImpl implements FreeService{
	
	private FreeDAO FreeDAO;
	public void setFreeDAO(FreeDAO freeDAO) {
		this.FreeDAO = freeDAO;
	}
	private FreeCommentDAO FreeCommentDAO;
	public void setFreeCommentDAO(FreeCommentDAO freeCommentDAO) {
		this.FreeCommentDAO = freeCommentDAO;
	}
	private MemberDAO memberDAO;
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	@Override
	public List<FreeVO> searchList(PageMaker pageMaker) throws SQLException {
		List<FreeVO> freeList = FreeDAO.selectFreeList(pageMaker);
		if (freeList.size() > 0) {
			for (FreeVO free : freeList) {
				int freeid = free.getFreeid();
				List<FreeCommentVO> commentList = FreeCommentDAO.selectFreeCommentList(freeid);
				free.setCommentlist(commentList);
				free.setWriter(memberDAO.selectMemberById(free.getMemberid()).getUsername());
			}
		}
		pageMaker.setTotalCount(FreeDAO.selectFreeListCount(pageMaker));
		
		return freeList;
	}

	@Override
	public void increaseFreeViewPoint(int freeid) throws SQLException {
		FreeDAO.increaseFreeViewPoint(freeid);
	}

	@Override
	public FreeVO detail(int freeid) throws SQLException {
		
		FreeVO free = FreeDAO.selectFreeByFreeId(freeid);
		List<FreeCommentVO> commentList = FreeCommentDAO.selectFreeCommentList(freeid);
		if(commentList != null)for(FreeCommentVO comment : commentList) {
			comment.setWriter(memberDAO.selectMemberById(comment.getMemberid()).getUsername());
		}
		free.setCommentlist(commentList);
		free.setWriter(memberDAO.selectMemberById(free.getMemberid()).getUsername());
		return free;
	}

	@Override
	public void regist(FreeVO free) throws SQLException {
		int freeid = FreeDAO.selectFreeSeqNext();
		free.setFreeid(freeid);
		FreeDAO.insertFree(free);		
	}

	@Override
	public void modify(FreeVO free) throws SQLException {
		FreeDAO.updateFree(free);
		
	}

	@Override
	public void remove(int freeid) throws SQLException {
		FreeDAO.deleteFree(freeid);
		
	}

	@Override
	public FreeCommentVO readcomment(int commentid) throws SQLException {
		return FreeCommentDAO.selectFreeByCommentId(commentid);
	}

	@Override
	public void registcomment(FreeCommentVO freecomment, int freeid) throws SQLException {
		freecomment.setFreeid(freeid);
		FreeCommentDAO.insertFreeComment(freecomment);
		
	}

	@Override
	public void modifycomment(FreeCommentVO freecomment) throws SQLException {
		FreeCommentDAO.updateFreeComment(freecomment);
				
	}

	@Override
	public void removecomment(int commentid) throws SQLException {
		FreeCommentDAO.deleteFreeComment(commentid);
		
	}

}
