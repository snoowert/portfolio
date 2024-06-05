package com.pds.controller;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.pds.service.PdsService;
import com.pds.vo.PdsFileVO;
import com.pds.vo.PdsVO;
import com.spring.command.PageMaker;
import com.spring.command.PdsModifyCommand;
import com.spring.command.PdsRegistCommand;
import com.spring.view.FileDownloadView;

@Controller
@RequestMapping("/pds")
public class PdsController {
	@Autowired
	PdsService pdsService;
	
	@Resource(name = "imageUploadPath")
	private String uploadDir;
	@Resource(name = "fileUploadPath")
	private String fileUploadDir;
	//목록
	@GetMapping("/list")
	public ModelAndView list(ModelAndView mnv, @ModelAttribute PageMaker pagemaker) throws SQLException{
		String url = "/pds/list";
		
		List<PdsVO> pdsList = pdsService.searchList(pagemaker);
		mnv.addObject("pdsList", pdsList);
		mnv.setViewName(url);
		return mnv;
	}
	@GetMapping("/detail")
	public ModelAndView detail(ModelAndView mnv, int pdsid, String from) throws Exception{
		String url = "/pds/detail";
		PdsVO pds = pdsService.getPds(pdsid);
		if(from != null && from.equals("list")) {
			pdsService.increaseViewCnt(pdsid);
			url = "redirect:/pds/detail?pdsid="+pdsid;
		}
		else {
			pds = pdsService.getPds(pdsid);
		}
		
		mnv.addObject("pds",pds);
		mnv.setViewName(url);
		return mnv;
	}
	
	//글작성폼
	@GetMapping("/registForm")
	public ModelAndView registForm(ModelAndView mnv) throws SQLException{
		String url = "/pds/regist";
		
		mnv.setViewName(url);
		return mnv;
	}
	//글작성
	@PostMapping("/regist")
	public ModelAndView regist(ModelAndView mnv, PdsRegistCommand command) throws Exception{
		String url = "/pds/regist_success";
		
		List<MultipartFile> pdsFile = command.getUploadFile();
		String savePath = fileUploadDir;
		List<PdsFileVO> pdsFileList = saveFileToAttaches(pdsFile, savePath);
		
		PdsVO pds = command.toPdsVO();
		pds.setPdsfilelist(pdsFileList);
		
		pdsService.regist(pds);
		
		mnv.setViewName(url);
		return mnv;
	}
	//글수정폼
	@GetMapping("/modifyForm")
	public ModelAndView modifyForm(ModelAndView mnv, int pdsid) throws SQLException{
		String url = "/pds/modify";
		PdsVO pds = pdsService.getPds(pdsid);
		
		mnv.addObject("pds", pds);
		mnv.setViewName(url);
		return mnv;
	}
	//글수정
	@PostMapping("/modify")
	public ModelAndView modify(PdsModifyCommand command, ModelAndView mnv) throws Exception{
		String url = "/pds/modify_success";
		if(command.getDeleteFile() != null && command.getDeleteFile().length > 0) {
			for(int pdsFileid : command.getDeleteFile()) {
				PdsFileVO pdsFile = pdsService.getAttachByAno(pdsFileid);
				
				String savedPath = fileUploadDir;
				
				File deleteFile = new File(savedPath, pdsFile.getPdsfilename());
				
				if(deleteFile.exists()) {
					deleteFile.delete(); //파일삭제
				}
				pdsService.removeAttachByAno(pdsFileid); //DB삭제
			}
		}
		
		List<PdsFileVO> pdsFileList = saveFileToAttaches(command.getUploadFile(), fileUploadDir);
		
		PdsVO pds = command.toPdsVO();
		pds.setPdsfilelist(pdsFileList);
		pdsService.modify(pds);
		
		mnv.setViewName(url);
		return mnv;
	}
	//글삭제
	@GetMapping("/delete")
	public ModelAndView delete(ModelAndView mnv, int pdsid) throws Exception{
		String url = "/pds/delete_success";
		
		List<PdsFileVO> pdsFileList = pdsService.getPds(pdsid).getPdsfilelist();
		if(pdsFileList!= null) for(PdsFileVO pdsFile : pdsFileList) {
			File target = new File(fileUploadDir, pdsFile.getPdsfilename());
			if(target.exists()) {
				target.delete();
			}
			pdsService.removeAttachByAno(pdsFile.getPdsfileid());
		}
		pdsService.remove(pdsid);
		
		mnv.setViewName(url);
		return mnv;
	}
	//파일불러오기
	@GetMapping("/getFile")
	public ModelAndView getFile(int pdsfileid,  ModelAndView mnv)throws Exception{
		
		PdsFileVO pdsFile = pdsService.getAttachByAno(pdsfileid);
		
		mnv.setView(new FileDownloadView());
		mnv.addObject("savedPath", fileUploadDir);
		mnv.addObject("fileName", pdsFile.getPdsfilename());		
		
		return mnv;
	}
	//파일업로드
	private List<PdsFileVO> saveFileToAttaches(List<MultipartFile> multiFiles,
			  String savePath )throws Exception{
		
		if (multiFiles == null) return null;
		
		//저장 -> attachVO -> list.add
		List<PdsFileVO> pdsFileList = new ArrayList<PdsFileVO>();
		for (MultipartFile multi : multiFiles) {
			String uuid = UUID.randomUUID().toString().replace("-", "");
			String fileName = uuid+"$$"+multi.getOriginalFilename();
			
			//파일저장
			File target = new File(savePath, fileName);
			target.mkdirs();
			multi.transferTo(target);
			
			//attachVO
			PdsFileVO pdsFile = new PdsFileVO();
			pdsFile.setPdsfilename(fileName);
			pdsFile.setPdsfiletype(fileName.substring(fileName.lastIndexOf('.') + 1)
					.toUpperCase());
			
			pdsFileList.add(pdsFile);
		}
		return pdsFileList;
	}
	
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
