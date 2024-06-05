package com.pms.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.pds.vo.PdsFileVO;
import com.pms.command.ModifyCommentCommand;
import com.pms.command.ModifyFeedCommand;
import com.pms.command.ModifyProjectCommand;
import com.pms.command.RegistCalendarCommand;
import com.pms.command.RegistCommentCommand;
import com.pms.command.RegistFeedCommand;
import com.pms.command.RegistFileCommand;
import com.pms.command.RegistProjectCommand;
import com.pms.dto.CalendarVO;
import com.pms.dto.NoteCommentVO;
import com.pms.dto.NoteVO;
import com.pms.dto.ProjectFileVO;
import com.pms.dto.ProjectVO;
import com.pms.dto.TeamVO;
import com.pms.service.ProjectService;
import com.spring.command.PageMaker;
import com.spring.view.FileDownloadView;

@Controller
@RequestMapping("/devnote")
public class DevnoteController {
	
	
	@Autowired
	private ProjectService projectService;
	
	@Resource(name = "imageUploadPath")
	private String uploadDir;
	@Resource(name = "fileUploadPath")
	private String fileUploadDir;
	
	@GetMapping("/list")
	public ModelAndView list(ModelAndView mnv, @ModelAttribute PageMaker pageMaker) throws SQLException {
		String url = "/project/list";

		List<ProjectVO> projectList = projectService.list(pageMaker);
		mnv.addObject("projectList", projectList);

		mnv.setViewName(url);

		
		return mnv;		 
		
	}

	@GetMapping("/registProject")
	public ModelAndView registForm(ProjectVO pj, ModelAndView mnv) throws SQLException { 
		String url = "/project/registProject";
		
		mnv.setViewName(url);
		
		return mnv;

	}
	
	@PostMapping(value = "/regist", produces = "text/plain;charset=utf-8")
	public ModelAndView regist(RegistProjectCommand command, ModelAndView mnv) throws SQLException {
		String url  = "/project/registProject_success";
		
		
		projectService.regist(command.setProjectVO(), command.getMemberid());
		mnv.setViewName(url);
		
		return mnv;

	}
	

	@GetMapping("/main")
	public ModelAndView detail(int projectId, HttpSession session, String form, ModelAndView mnv) throws SQLException {
		String url = "/devnote/main";
		
		ProjectVO project = projectService.detail(projectId);
		List<TeamVO> teamList = projectService.teamList(projectId);
		List<Integer> checkList = new ArrayList<Integer>();
		for(TeamVO team : teamList) {
			checkList.add(team.getMemberid());
		}
		mnv.addObject("project", project);
		mnv.addObject("teamList", teamList);
		mnv.addObject("checkList", checkList);
		mnv.setViewName(url);
		return mnv;
		
	}
	
	@GetMapping("/modifyProject")
	public ModelAndView modifyForm(int projectId, ModelAndView mnv) throws SQLException {
		String url = "/project/modifyProject";
		
		ProjectVO project = projectService.detail(projectId);
		
		mnv.addObject("project", project);
		mnv.setViewName(url);
		return mnv;
	}
	
	@GetMapping("/insertTeam")
	public ModelAndView insertTeam(int memberid, int projectid, ModelAndView mnv) throws Exception{
		String url = "/project/insertTeam_success";
		TeamVO team = new TeamVO();
		team.setMemberid(memberid);
		team.setProjectid(projectid);
		
		projectService.insertTeam(team);
		
		mnv.addObject("projectId",projectid);
		mnv.setViewName(url);
		return mnv;
	}
	
	@GetMapping("/deleteTeam")
	public ModelAndView deleteTeam(int memberid, int projectid, ModelAndView mnv) throws Exception{
		String url = "/project/deleteTeam_success";
		TeamVO team = new TeamVO();
		team.setMemberid(memberid);
		team.setProjectid(projectid);
		
		projectService.deleteTeam(team);
		
		mnv.addObject("projectId", projectid);
		mnv.setViewName(url);
		return mnv;
	}
	@PostMapping(value= "/modifyPj", produces = "text/plain; charset=utf-8")
	public ModelAndView modifyPj(ModifyProjectCommand command, ModelAndView mnv) throws SQLException {
		String url = "/project/modifyProject_success";
		
		ProjectVO project = command.toProjectVO();
		
		System.out.println(project.getProjectId());
		projectService.modify(project);
		
		mnv.addObject("projectId", project.getProjectId());
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@GetMapping("/deleteProject")
	public ModelAndView deleteProject(ModelAndView mnv, int projectId) throws SQLException{
		String url = "/project/deleteProject";
		
		projectService.remove(projectId);
		
		mnv.setViewName(url);
		return mnv;
	}
	
	
	@GetMapping("/feed")
	public ModelAndView feedList(int projectId, ModelAndView mnv) throws SQLException {
		String url = "/devnote/feed";
		
		List<NoteVO> noteList = projectService.feedList(projectId);
		
		ProjectVO project = projectService.detail(projectId);
		
		mnv.addObject("project", project);
		
		mnv.addObject("noteList", noteList);
		mnv.setViewName(url);
		return mnv;
	}
	
	
	@PostMapping(value="/feedRegist", produces="text/plain; charset=utf-8")
	public ModelAndView feedRegist(RegistFeedCommand regCommand, ModelAndView mnv) throws SQLException {
		String url = "/devnote/registFeed_success";
		
		NoteVO note = regCommand.toNoteVO();
		
		projectService.insertFeed(note);
		
		
		mnv.addObject("projectId",note.getProjectId());
		mnv.setViewName(url);
		return mnv;	
		
	}
	
	@GetMapping("/modifyFeedForm")
	public ModelAndView feedModifyForm(ModelAndView mnv, int noteId) throws SQLException{
		String url = "/devnote/modifyFeedForm";
		
		NoteVO note = projectService.detailFeed(noteId);
		
		mnv.addObject("note", note);
		mnv.setViewName(url);
		return mnv;
	}

	@PostMapping("/modifyFeed")
	public ModelAndView feedModify(ModelAndView mnv, ModifyFeedCommand command) throws Exception {
		String url = "/devnote/modifyFeed_success";
		NoteVO note = command.toNoteVO();
		
		projectService.modifyFeed(note);
		
		
		mnv.addObject("projectId", note.getProjectId());
		mnv.setViewName(url);
		return mnv;
	}
	
	@GetMapping("/deleteFeed")
	public ModelAndView feedDelete(ModelAndView mnv, int noteId, int projectId) throws SQLException{
		String url = "/devnote/deleteFeed_success";
		
		projectService.deleteFeed(noteId);
		
		mnv.addObject("projectId", projectId);
		mnv.setViewName(url);
		return mnv;
	}

	@PostMapping("/registComment")
	public ModelAndView insertNoteComment(ModelAndView mnv, RegistCommentCommand command) throws SQLException{
		String url = "/devnote/insertComment_success";
		
		NoteCommentVO notecomment = command.toNoteCommentVO();
		projectService.insertComment(notecomment);
		
		mnv.addObject("projectId", notecomment.getProjectid());
		mnv.setViewName(url);
		return mnv;
	}
	
	@PostMapping("/modifyComment")
	public ModelAndView modifyNoteComment(ModelAndView mnv, ModifyCommentCommand command) throws SQLException{
		String url = "/devnote/modifyComment_success";
		
		NoteCommentVO notecomment = command.toNoteCommentVO();
		
		projectService.updateComment(notecomment);
		
		mnv.addObject("projectId", notecomment.getProjectid());
		mnv.setViewName(url);
		return mnv;
	}
	
	@GetMapping("/deleteComment")
	public ModelAndView deleteNoteComment(ModelAndView mnv, int dncomid, int projectId) throws SQLException{
		String url = "/devnote/deleteComment_success";
		
		projectService.deleteComment(dncomid);
		
		mnv.addObject("projectId", projectId);
		mnv.setViewName(url);
		return mnv;
	}
	
	@GetMapping("/calendar")
	public ModelAndView calendar(int projectId,  ModelAndView mnv) throws SQLException {
		String url = "/devnote/calendar";
		
		
		ProjectVO project = projectService.detail(projectId);
		List<CalendarVO> cal = projectService.calList(projectId);
		
		mnv.addObject("calendar",cal);
		mnv.addObject("projectId", projectId);
		mnv.addObject("project", project);
		mnv.setViewName(url);
		return mnv;
		
	}
	
	
 	@PostMapping("/save-event")
    @ResponseBody
    public String saveEventToDB(RegistCalendarCommand regCalCommand) throws Exception{
        // eventData 객체로부터 데이터를 추출하여 CalendarVO 객체에 설정
        CalendarVO calendarVO = regCalCommand.toVO();
        

        // CalendarService를 사용하여 DB에 일정 저장
        projectService.calInsert(calendarVO);

        return "일정이 성공적으로 저장되었습니다.";
    }
	
 	@PostMapping("/deleteCalendar")
    @ResponseBody
    public String deleteCalendar(@RequestParam("id") int eventId) throws Exception{

 		projectService.calDelete(eventId);
        return "이벤트가 성공적으로 삭제되었습니다.";
    }
	
 	
 	
 	
 	@GetMapping("/projectFile")
 	public ModelAndView projectFileList(ModelAndView mnv, int projectId) throws Exception {
 		String url = "/devnote/projectFile";
 		
 		List<ProjectFileVO> projectFileList = projectService.projectFileList(projectId);
 		ProjectVO project = projectService.detail(projectId);
 		
 		mnv.addObject("project",project);
 		mnv.addObject("projectFileList",projectFileList);
 		mnv.setViewName(url);
 		return mnv;
 	}
 	
 	@GetMapping("/deleteProjectFile")
 	public ModelAndView deleteProjectFile(ModelAndView mnv, int fileid) throws Exception{
 		String url = "/devnote/deleteProjectFile";
 		ProjectFileVO pjFile = projectService.projectFile(fileid);
 		projectService.deleteProjectFile(fileid);
 		
 		mnv.addObject("projectId", pjFile.getProjectId());
 		mnv.setViewName(url);
 		return mnv;
 	}
 	
 	@GetMapping("/projectFile_upload")
 	public ModelAndView projectFile_uploadForm(ModelAndView mnv, int projectId) throws Exception{
 		String url = "/devnote/projectFile_upload";
 		
 		
 		mnv.addObject("projectId", projectId);
 		mnv.setViewName(url);
 		return mnv;
 	}
 	@PostMapping("/projectFileRegist")
 	public ModelAndView projectFileRegist(ModelAndView mnv, RegistFileCommand command) throws Exception{
 		String url = "/devnote/projectFileRegist_success";
 		
 		MultipartFile File = command.getFileName();
 		ProjectFileVO projectFile = command.toProjectFileVO();
 		projectFile = saveFileToAttaches(File, fileUploadDir, projectFile);
 		
 		projectService.insertProjectFile(projectFile);
 		
 		mnv.addObject("projectId", command.getProjectId());
 		mnv.setViewName(url);
 		return mnv;
 	}
 	//파일다운로드
	@GetMapping("/getFile")
	public ModelAndView getFile(int fileId,  ModelAndView mnv)throws Exception{
		
		ProjectFileVO File = projectService.projectFile(fileId);
		projectService.increaseDownCnt(fileId);
		mnv.setView(new FileDownloadView());
		mnv.addObject("savedPath", fileUploadDir);
		mnv.addObject("fileName", File.getFileName());		
		
		return mnv;
	}
	//파일저장
 	private ProjectFileVO saveFileToAttaches(MultipartFile File, String savePath, ProjectFileVO FileVO )throws Exception{
		
		if (File == null) return null;
		
		//저장
		String uuid = UUID.randomUUID().toString().replace("-", "");
		String fileName = uuid+"$$"+File.getOriginalFilename();
			
		//파일저장
		File target = new File(savePath, fileName);
		target.mkdirs();
		File.transferTo(target);
			
		FileVO.setFileName(fileName);
		return FileVO;
	}
 	//toastUI
 		@PostMapping("/tui-editor/image-upload")
 		public ResponseEntity<String> uploadEditorImage(HttpServletRequest request, @RequestParam("image") MultipartFile image) {
 	     try {

 	            // 디렉토리가 존재하지 않으면 생성
 	            File directory = new File(uploadDir);
 	            if (!directory.exists()) {
 	                directory.mkdirs();
 	            }

 	            // 파일명 생성 (랜덤 UUID + 확장자)
 	            String filename = UUID.randomUUID().toString() + "." + getFileExtension(image.getOriginalFilename());

 	            // 업로드할 파일 생성
 	            File file = new File(uploadDir + File.separator + filename);

 	            // 이미지 파일 저장
 	            FileOutputStream outputStream = new FileOutputStream(file);
 	            outputStream.write(image.getBytes());
 	            outputStream.close();
 	            // 파일명 반환
 	            return ResponseEntity.ok(filename);
 	        } catch (IOException e) {
 	            e.printStackTrace();
 	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image upload failed.");
 	        }
 	    }

 	    // 파일명에서 확장자 추출
 	    private String getFileExtension(String filename) {
 	        int dotIndex = filename.lastIndexOf(".");
 	        if (dotIndex == -1) {
 	            return ""; // 확장자 없음
 	        }
 	        return filename.substring(dotIndex + 1);
 	    }
 		
 	    @GetMapping("/tui-editor/image-print")
 	    public ResponseEntity<byte[]> serveFile(@RequestParam("filename") String filename) {
 	        Path filePath = Paths.get(uploadDir).resolve(filename);
 	        try {
 	            if (Files.exists(filePath) && Files.isReadable(filePath)) {
 	                byte[] data = Files.readAllBytes(filePath);
 	                HttpHeaders headers = new HttpHeaders();
 	                headers.setContentType(MediaType.IMAGE_JPEG);
 	                return new ResponseEntity<>(data, headers, HttpStatus.OK);
 	            } else {
 	                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
 	            }
 	        } catch (IOException e) {
 	            e.printStackTrace();
 	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
 	        }
 	    }
}
