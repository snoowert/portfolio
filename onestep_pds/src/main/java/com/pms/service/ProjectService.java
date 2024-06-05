package com.pms.service;

import java.sql.SQLException;
import java.util.List;

import com.pms.dto.CalendarVO;
import com.pms.dto.NoteCommentVO;
import com.pms.dto.NoteVO;
import com.pms.dto.ProjectFileVO;
import com.pms.dto.ProjectVO;
import com.pms.dto.TeamVO;
import com.spring.command.PageMaker;

public interface ProjectService {
	//프로젝트 목록
	List<ProjectVO> list(PageMaker pageMaker) throws SQLException;

	//프로젝트 메인페이지
	ProjectVO detail(int projectId) throws SQLException;
	//프로젝트 등록
	void regist(ProjectVO pj, int memberid) throws SQLException;
	//프로젝트 수정
	void modify(ProjectVO pj) throws SQLException;
	//프로젝트 삭제
	void remove(int projectId) throws SQLException;
	
	//팀 목록
	List<TeamVO> teamList(int projectid) throws SQLException;
	//팀 조회
	TeamVO teamOne(TeamVO team) throws SQLException;
	//팀 등록
	void insertTeam(TeamVO team) throws SQLException;
	//팀 삭제
	void deleteTeam(TeamVO team) throws SQLException;
	
	//피드조회
	public List<NoteVO> feedList(int projectid) throws SQLException;
	//피드등록
	public void insertFeed(NoteVO note) throws SQLException;
	//피드단일조회
	public NoteVO detailFeed(int noteId) throws SQLException;
	//피드수정
	public void modifyFeed(NoteVO note) throws SQLException;
	//피드삭제
	public void deleteFeed(int noteid) throws SQLException;
	
	//댓글 조회
	public NoteCommentVO feedComment(int dncomid) throws SQLException;
	//댓글 등록
	public void insertComment(NoteCommentVO notecomment) throws SQLException;
	//댓글 수정
	public void updateComment(NoteCommentVO notecomment) throws SQLException;
	//댓글 삭제
	public void deleteComment(int dncomid) throws SQLException;
	
	//파일 리스트
	public List<ProjectFileVO> projectFileList(int projectid) throws Exception;
	//파일 조회
	public ProjectFileVO projectFile(int fileid) throws Exception;
	//파일 등록
	public void insertProjectFile(ProjectFileVO projectFile) throws Exception;
	//파일 삭제
	public void deleteProjectFile(int fileid) throws Exception;
	//파일 다운수 증가
	public void increaseDownCnt(int fileid) throws Exception;
	// 달력
	List<CalendarVO> calList(int projectId) throws SQLException;
	void calInsert(CalendarVO cal) throws SQLException;
	void calDelete(int id) throws SQLException;

}
