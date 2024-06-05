package com.spring.pds;

import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pds.dao.PdsDAO;
import com.pds.vo.PdsVO;
import com.spring.command.PageMaker;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/spring/context/root-context.xml")
public class PdsTest {
	@Autowired
	PdsDAO pdsDAO;
	
	@Test
	public void selectListTest() throws SQLException{
		PageMaker pagemaker = new PageMaker();
		List<PdsVO> pdsList = pdsDAO.selectSearchPdsList(pagemaker);
		System.out.println(pdsList.isEmpty());
		Assert.assertEquals(5, pdsList.size());
	}
	@Test
	public void selectIdTest() throws SQLException{
		PdsVO pds = pdsDAO.selectPdsByPdsid(6);
		System.out.println(pds);
		Assert.assertNotNull(pds);
	}
	@Test
	public void selectcntTest() throws SQLException{
		int cnt = pdsDAO.selectSearchPdsListCount(new PageMaker());
		Assert.assertEquals(16, cnt);
	}
//	@Test
//	public void insertTest() throws SQLException{
//		PdsVO pds = new PdsVO();
//		pds.setMemberid(2);
//		pds.setPdstitle("ㅈ디ㅗㅁ기주");
//		pds.setPdscontent("가단ㄷㅁㅈㄷㅈㅁ");
//		
//		pdsDAO.insertPds(pds);
//		String t = pdsDAO.selectPdsByPdsid(12).getPdstitle();
//		Assert.assertEquals(pds.getPdstitle(), t);
//	}
//	@Test
//	public void updateTest() throws SQLException{
//		PdsVO pds = pdsDAO.selectPdsByPdsid(15);
//		pds.setPdstitle("새로쓴제목");
//		pds.setPdscontent("새로쓴 내용");
//		
//		pdsDAO.updatePds(pds);
//		Assert.assertEquals(pds.getPdstitle(), pdsDAO.selectPdsByPdsid(15).getPdstitle());
//	}
//	@Test
//	public void deleteTest() throws SQLException{
//		pdsDAO.deletePds(16);
//		PdsVO pds = pdsDAO.selectPdsByPdsid(16);
//		
//		Assert.assertNull(pds);
//	}
}
