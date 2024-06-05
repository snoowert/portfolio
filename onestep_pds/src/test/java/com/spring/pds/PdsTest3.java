package com.spring.pds;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qna.dao.AnswerDAO;
import com.qna.dao.QnADAO;
import com.qna.dto.AnswerVO;
import com.qna.dto.QnAVO;
import com.spring.command.PageMaker;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/spring/context/root-context.xml")
public class PdsTest3 {
	@Autowired
	QnADAO qnaDAO;
	@Autowired
	AnswerDAO ansDAO;
	@Test
	public void selectListTest() throws SQLException{
		PageMaker pageMaker = new PageMaker();
		List<QnAVO> qnaList = qnaDAO.selectSearchQnAList(pageMaker);
		List<AnswerVO> ansList = ansDAO.selectAnswerList(1);
		assertEquals(3, ansList.size());
	}
	@Test
	public void selectIdTest() throws SQLException{
		QnAVO qna = qnaDAO.selectQnAByQnAId(2);
		String qnaTitle = "aa12";
		AnswerVO ans = ansDAO.selectAnswerByAnswerId(2);
		assertEquals(qnaTitle, ans.getAnswercontent());
	}
	@Test
	public void selectcntTest() throws SQLException{
		PageMaker pageMaker = new PageMaker();
		int len = qnaDAO.selectSearchQnAListCount(pageMaker);
		
		assertEquals(2, len);
	}
	@Test
	@Rollback
	public void insertTest() throws SQLException{
		AnswerVO ans = new AnswerVO();
		ans.setQnaid(4);
		ans.setMemberid(3);
		ans.setAnswercontent("ansansansans122132");
		ansDAO.insertAnswer(ans);
		String t = ansDAO.selectAnswerByAnswerId(7).getAnswercontent();
		assertEquals(t, ans.getAnswercontent());
	}
//	@Test
//	@Rollback
//	public void updateTest() throws SQLException{
//		AnswerVO ans = ansDAO.selectAnswerByAnswerId(3);
//		ans.setAnswercontent("신규이름2");
//		
//		ansDAO.updateAnswer(ans);
//		assertEquals(ans.getAnswercontent(), ansDAO.selectAnswerByAnswerId(3).getAnswercontent());
//	}
//	@Test
//	public void deleteTest() throws SQLException{
//		ansDAO.deleteAnswer(2);
//		assertNotNull(ansDAO.selectAnswerByAnswerId(2));
//	}
	@Test
	public void redeleteTest() throws SQLException{
		
	}
}
