package com.pds.service;

import java.sql.SQLException;
import java.util.List;

import com.member.dao.MemberDAO;
import com.pds.dao.PdsDAO;
import com.pds.dao.PdsFileDAO;
import com.pds.vo.PdsFileVO;
import com.pds.vo.PdsVO;
import com.spring.command.PageMaker;

public class PdsServiceImpl implements PdsService{
	PdsDAO pdsDAO;
	public void setPdsDAO(PdsDAO pdsdao) {
		this.pdsDAO = pdsdao;
	}
	PdsFileDAO pdsfileDAO;
	public void setPdsFileDAO(PdsFileDAO pdsfiledao) {
		this.pdsfileDAO = pdsfiledao;
	}
	MemberDAO memberDAO;
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	@Override
	public List<PdsVO> searchList(PageMaker pageMaker) throws SQLException {
		// TODO Auto-generated method stub
		List<PdsVO> PdsList = pdsDAO.selectSearchPdsList(pageMaker);
		if(PdsList != null) for(PdsVO pds : PdsList) {
			int pdsid = pds.getPdsid();
			int memberid = pds.getMemberid();
			List<PdsFileVO> pdsFileList = pdsfileDAO.selectPdsFileList(pdsid);
			String username = memberDAO.selectMemberById(memberid).getUsername();
			pds.setPdsfilelist(pdsFileList);
			pds.setWriter(username);
		}
		pageMaker.setTotalCount(pdsDAO.selectSearchPdsListCount(pageMaker));
		return PdsList;
	}

	@Override
	public void regist(PdsVO pds) throws SQLException {
		// TODO Auto-generated method stub
		List<PdsFileVO> pdsFileList = pds.getPdsfilelist();
		pds.setPdsid(pdsDAO.selectPdsidSeqNext());
		pdsDAO.insertPds(pds);
		
		if(pdsFileList != null) for(PdsFileVO pdsFile : pdsFileList) {
			pdsFile.setPdsid(pds.getPdsid());
			pdsfileDAO.insertPdsFile(pdsFile);
		}
	}

	@Override
	public void increaseViewCnt(int pdsid) throws SQLException {
		// TODO Auto-generated method stub
		pdsDAO.increaseViewPoint(pdsid);
	}

	@Override
	public PdsVO getPds(int pdsid) throws SQLException {
		// TODO Auto-generated method stub
		PdsVO pds = pdsDAO.selectPdsByPdsid(pdsid);
		List<PdsFileVO> pdsFileList = pdsfileDAO.selectPdsFileList(pdsid);
		String writer = memberDAO.selectMemberById(pds.getMemberid()).getUsername();
		pds.setPdsfilelist(pdsFileList);
		pds.setWriter(writer);
		return pds;
	}

	@Override
	public void modify(PdsVO pds) throws SQLException {
		// TODO Auto-generated method stub
		pdsDAO.updatePds(pds);
		
		int pdsid = pds.getPdsid();
		
		List<PdsFileVO> pdsFileList = pds.getPdsfilelist();
		if(pdsFileList != null) for(PdsFileVO pdsFile : pdsFileList) {
			pdsFile.setPdsid(pdsid);
			pdsfileDAO.insertPdsFile(pdsFile);
		}
	}

	@Override
	public void remove(int pdsid) throws SQLException {
		// TODO Auto-generated method stub
		pdsDAO.deletePds(pdsid);
	}

	@Override
	public PdsFileVO getAttachByAno(int pdsfileid) throws SQLException {
		// TODO Auto-generated method stub
		return pdsfileDAO.selectPdsFileById(pdsfileid);
	}

	@Override
	public void removeAttachByAno(int pdsfileid) throws SQLException {
		// TODO Auto-generated method stub
		pdsfileDAO.deletePdsFile(pdsfileid);
	}

}
