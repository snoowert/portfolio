package com.pms.service;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import com.member.dao.MemberDAO;
import com.pms.dao.CalendarDAO;
import com.pms.dao.NoteCommentDAO;
import com.pms.dao.NoteDAO;
import com.pms.dao.ProjectDAO;
import com.pms.dao.ProjectFileDAO;
import com.pms.dao.TeamDAO;
import com.pms.dto.CalendarVO;
import com.pms.dto.NoteCommentVO;
import com.pms.dto.NoteVO;
import com.pms.dto.ProjectFileVO;
import com.pms.dto.ProjectVO;
import com.pms.dto.TeamVO;
import com.spring.command.PageMaker;

public class ProjectServiceImpl implements ProjectService{
	
	private ProjectDAO projectDAO;
	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}
	private ProjectFileDAO projectFileDAO;
	public void setProjectFileDAO(ProjectFileDAO projectFileDAO) {
		this.projectFileDAO = projectFileDAO;
	}
	private CalendarDAO calendarDAO;
	public void setCalendarDAO(CalendarDAO calendarDAO) {
		this.calendarDAO = calendarDAO;
	}
	
	private TeamDAO teamDAO;
	public void setTeamDAO(TeamDAO teamDAO) {
		this.teamDAO = teamDAO;
	}
	private NoteDAO noteDAO;
	public void setNoteDAO(NoteDAO noteDAO) {
		this.noteDAO = noteDAO;
	}
	private NoteCommentDAO noteCommentDAO;

    // NoteCommentDAO 프로퍼티의 setter 메서드
    public void setNoteCommentDAO(NoteCommentDAO noteCommentDAO) {
        this.noteCommentDAO = noteCommentDAO;
    }

	private MemberDAO memberDAO;
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	//프로젝트 목록
	@Override
	public List<ProjectVO> list(PageMaker pageMaker) throws SQLException {
		pageMaker.setPerPageNum(12);
		List<ProjectVO> projectList = projectDAO.selectProjectList(pageMaker); 
		pageMaker.setTotalCount(projectDAO.selectProjectListCount(pageMaker));
		
		return projectList;
	}

	//프로젝트 생성
	@Override
	public void regist(ProjectVO pj, int memberid) throws SQLException {
		TeamVO team = new TeamVO();

		pj.setProjectId(projectDAO.selectProjectSeqNext());
		team.setMemberid(memberid);
		team.setProjectid(pj.getProjectId());
		projectDAO.insertProject(pj);
		teamDAO.insertTeam(team);
		
	}
	//프로젝트 수정
	@Override
	public void modify(ProjectVO pj) throws SQLException {
		projectDAO.updateProject(pj);
		
	}
	//프로젝트 삭제
	@Override
	public void remove(int projectId) throws SQLException {
		projectDAO.deleteProject(projectId);
		
	}
	//프로젝트 상세보기
	@Override
	public ProjectVO detail(int projectId) throws SQLException {
		return projectDAO.selectProjectByProjectId(projectId);
		
	}
	//팀 목록
	@Override
	public List<TeamVO> teamList(int projectid) throws SQLException {
		// TODO Auto-generated method stub
		List<TeamVO> teamList = teamDAO.selectTeamList(projectid);
		for(TeamVO team : teamList) {
			team.setMembername(memberDAO.selectMemberById(team.getMemberid()).getUsername());
		}
		return teamList;
	}
	//팀 조회
	@Override
	public TeamVO teamOne(TeamVO team) throws SQLException {
		// TODO Auto-generated method stub
		return teamDAO.selectTeamById(team);
	}
	//팀추가
	@Override
	public void insertTeam(TeamVO team) throws SQLException {
		// TODO Auto-generated method stub
		teamDAO.insertTeam(team);
	}
	//팀 삭제
	@Override
	public void deleteTeam(TeamVO team) throws SQLException {
		// TODO Auto-generated method stub
		teamDAO.deleteTeam(team);
	}
	
	//피드목록
	@Override
	public List<NoteVO> feedList(int projectid) throws SQLException {
		// TODO Auto-generated method stub
		List<NoteVO> feedList = noteDAO.selectNoteList(projectid);
		for(NoteVO note : feedList) {
			note.setWriter(memberDAO.selectMemberById(note.getMemberId()).getUsername());
			List<NoteCommentVO> commentList = noteCommentDAO.selectNoteCommentList(note.getNoteId());
			for(NoteCommentVO comment : commentList) {
				comment.setWriter(memberDAO.selectMemberById(comment.getMemberid()).getUsername());
			}
			note.setCommentList(commentList);
		}
		return feedList;
	}
	//피드 작성
	@Override
	public void insertFeed(NoteVO note) throws SQLException {
		// TODO Auto-generated method stub
		note.setNoteId(noteDAO.selectNoteSeqNext());
		noteDAO.insertNote(note);
	}
	//피드 조회
	@Override
	public NoteVO detailFeed(int noteId) throws SQLException {
		// TODO Auto-generated method stub
		NoteVO note = noteDAO.selectNoteByNoteId(noteId);
		note.setWriter(memberDAO.selectMemberById(note.getMemberId()).getUsername());
		return note;
	}
	//피드 수정
	@Override
	public void modifyFeed(NoteVO note) throws SQLException {
		// TODO Auto-generated method stub
		noteDAO.updateNote(note);
	}
	//피드 삭제
	@Override
	public void deleteFeed(int noteid) throws SQLException {
		// TODO Auto-generated method stub
		noteDAO.deleteNote(noteid);
	}
	//댓글 조회
	public NoteCommentVO feedComment(int dncomid) throws SQLException{
		NoteCommentVO notecomment = noteCommentDAO.selectNoteCommentById(dncomid);
		notecomment.setWriter(memberDAO.selectMemberById(notecomment.getMemberid()).getUsername());
		return notecomment;
	}
	//댓글 등록
	public void insertComment(NoteCommentVO notecomment) throws SQLException{
		noteCommentDAO.insertNoteComment(notecomment);
	}
	//댓글 수정
	public void updateComment(NoteCommentVO notecomment) throws SQLException{
		noteCommentDAO.updateNoteComment(notecomment);
	}
	//댓글 삭제
	public void deleteComment(int dncomid) throws SQLException{
		noteCommentDAO.deleteNoteComment(dncomid);
	}
	
	//파일리스트
	@Override
	public List<ProjectFileVO> projectFileList(int projectid) throws Exception {
		// TODO Auto-generated method stub
		List<ProjectFileVO> projectFileList = projectFileDAO.selectProjectFileList(projectid);
		for(ProjectFileVO projectFile : projectFileList) {
			projectFile.setWriter(memberDAO.selectMemberById(projectFile.getMemberId()).getUsername());
		}
		return projectFileList;
	}

	@Override
	public ProjectFileVO projectFile(int fileid) throws Exception {
		// TODO Auto-generated method stub
		return projectFileDAO.selectProjectFileByFileId(fileid);
	}

	@Override
	public void insertProjectFile(ProjectFileVO projectFile) throws Exception {
		// TODO Auto-generated method stub
		projectFileDAO.insertProjectFile(projectFile);
	}

	@Override
	public void deleteProjectFile(int fileid) throws Exception {
		// TODO Auto-generated method stub
		projectFileDAO.deleteProjectFile(fileid);
	}

	
	@Override
	public void increaseDownCnt(int fileid) throws Exception {
		// TODO Auto-generated method stub
		projectFileDAO.increaseProjectFileDownCnt(fileid);
	}

	@Override
	public List<CalendarVO> calList(int projectId) throws SQLException {
		List<CalendarVO> cal = calendarDAO.selectCalendarList(projectId);
		
		DateFormat dateFomatter = new SimpleDateFormat("yyyy-MM-dd");
		
		for(CalendarVO calendar : cal ) {
			calendar.setStartDate(dateFomatter.format(calendar.getStartd()));
			calendar.setEndDate(dateFomatter.format(calendar.getEndd()));
		}
		return cal;
	}

	@Override
	public void calInsert(CalendarVO cal) throws SQLException {
		calendarDAO.insertCalendar(cal);
	}

	@Override
	public void calDelete(int id) throws SQLException {
		// TODO Auto-generated method stub
		calendarDAO.deleteCalendar(id);
	}

	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
