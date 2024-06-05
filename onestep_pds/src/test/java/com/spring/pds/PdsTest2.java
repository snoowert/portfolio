package com.spring.pds;

import java.sql.SQLException;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.member.dao.MemberDAO;
import com.member.vo.MemberVO;
import com.pds.vo.PdsVO;
import com.spring.command.PageMaker;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/spring/context/root-context.xml")
public class PdsTest2 {
	@Autowired
	MemberDAO memberDAO;
	
	@Test
	public void selectListTest() throws SQLException{
		PageMaker pagemaker = new PageMaker();
		List<MemberVO> memberList = memberDAO.selectMemberSearchList(pagemaker);
		Assert.assertEquals(5, memberList.size());
	}
	@Test
	public void selectIdTest() throws SQLException{
		MemberVO member = memberDAO.selectMemberById(2);
		Assert.assertNotNull(member);
	}
	@Test
	public void selectcntTest() throws SQLException{
		int cnt = memberDAO.selectMemberSearchListCount(new PageMaker());
		Assert.assertEquals(4, cnt);
	}
//	@Test
//	@Rollback
//	public void insertTest() throws SQLException{
//		MemberVO member = new MemberVO();
//		member.setUsername("가나다");
//		member.setPassword("abc");
//		member.setEmail("abc@abc.abc");
//		member.setAuthority("developer");
//		
//		memberDAO.insertMember(member);
//		String t = memberDAO.selectMemberById(4).getUsername();
//		Assert.assertEquals(member.getUsername(), t);
//	}
//	@Test
//	@Rollback
//	public void updateTest() throws SQLException{
//		MemberVO member = memberDAO.selectMemberById(4);
//		member.setUsername("신규이름");
//		
//		memberDAO.updateMember(member);
//		Assert.assertEquals(member.getUsername(), memberDAO.selectMemberById(2).getUsername());
//	}
	@Test
	public void deleteTest() throws SQLException{
		memberDAO.banMember(5);
		MemberVO member = memberDAO.selectMemberById(5);
		
		Assert.assertThat(member.getIsDelete(), CoreMatchers.is('N'));
	}
	@Test
	public void redeleteTest() throws SQLException{
		memberDAO.restoreMember(5);
		MemberVO member = memberDAO.selectMemberById(5);
		
		Assert.assertEquals('N', member.getIsDelete());
	}
}
