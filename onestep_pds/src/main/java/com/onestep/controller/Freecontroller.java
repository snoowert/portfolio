package com.onestep.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.onestep.command.CommentModifyCommand;
import com.onestep.command.CommentRegistCommand;
import com.onestep.command.FreeModifyCommand;
import com.onestep.command.FreeRegistCommand;
import com.onestep.command.PageMaker;
import com.onestep.dto.FreeCommentVO;
import com.onestep.dto.FreeVO;
import com.onestep.service.FreeService;

@Controller
@RequestMapping("/free")
public class Freecontroller {
	@Autowired
	private FreeService freeService;
	
	
	private String uploadDir = "c:/free/image/upload";

	@GetMapping("/main")
	public ModelAndView list(@ModelAttribute PageMaker pageMaker, ModelAndView mnv) throws Exception {
		String url="/free/main";
		
		List<FreeVO> freeList = freeService.searchList(pageMaker);
		
		mnv.addObject("freeList",freeList);
		mnv.setViewName(url);
		return mnv;
	}
	

	@GetMapping("/registForm")
	public ModelAndView registForm(ModelAndView mnv) throws Exception {
		String url = "/free/regist";
		mnv.setViewName(url);
		return mnv;
	}

	@PostMapping(value = "/regist", produces = "text/plain;charset=utf-8")
	public ModelAndView regist(FreeRegistCommand regCommand, ModelAndView mnv) throws Exception {
		String url = "/free/regist_success";
	
		
		FreeVO free = regCommand.toFreeVO();

	    free.getFreeupdatedate();
		
		freeService.regist(free);
		mnv.setViewName(url);

		return mnv;
	}

	@GetMapping("/detail")
	public ModelAndView detail(int freeid, HttpSession session, String from, ModelAndView mnv) throws Exception {
	    String url = "/free/detail";

	    FreeVO free = null;
	    
	    if(from != null && from.equals("list")) {
	    	freeService.increaseFreeViewPoint(freeid);
			url = "redirect:/free/detail?freeid="+freeid;
		}
		else {
			free = freeService.detail(freeid);
		}
	  
	    mnv.addObject("free", free);
	    mnv.setViewName(url);
	    return mnv;
	}
	
	@GetMapping("/modifyForm")
	public ModelAndView modifyForm(int freeid,ModelAndView mnv)throws Exception{
		String url = "/free/modify";

		FreeVO free = freeService.detail(freeid);

		mnv.addObject("free", free);
		mnv.setViewName(url);
		return mnv;
	}
	
	@PostMapping("/modify")
	public ModelAndView modify(FreeModifyCommand modifyCommand, ModelAndView mnv)throws Exception{
			String url = "/free/modify_success";

			FreeVO free = modifyCommand.toFreeVO();

			freeService.modify(free);
			
			mnv.setViewName(url);
			
			return mnv;
	}
	
	@GetMapping("/remove")
	public ModelAndView remove(int freeid, ModelAndView mnv)throws Exception{
		String url="/free/remove_success";
		
	    ModelAndView free = new ModelAndView();
		freeService.remove(freeid);
		
		mnv.setViewName(url);
		return mnv;
	}
	@PostMapping(value="/commentregist", produces="text/plain;charset=utf-8")
	public ModelAndView Commentregist(CommentRegistCommand command, ModelAndView mnv) throws Exception{
		String url = "/free/registComment_success";
		FreeCommentVO comment = command.toFreeCommentVO();
		int freeid = command.getFreeid();
		
		freeService.registcomment(comment, freeid);
		
		mnv.addObject("freeid",freeid);
		mnv.setViewName(url);
		return mnv;
	}
	@PostMapping("/commentremove")
	public ModelAndView Commentremove(int freeid, int commentid, ModelAndView mnv) throws Exception {
	    String url = "/free/removeComment_success";
	    ModelAndView comment = new ModelAndView();
	    freeService.removecomment(commentid);
	    mnv.addObject("freeid", freeid);
	    mnv.setViewName(url);
	    return mnv;
	}
	@GetMapping("/modifycommentForm")
	public ModelAndView modifycommentForm(int commentid, ModelAndView mnv)throws Exception{
		String url = "/free/modifyComment";

		FreeCommentVO comment = freeService.readcomment(commentid);
		int id = comment.getFreeid();
		String name = freeService.detail(id).getWriter();
		comment.setWriter(name);
		mnv.addObject("comment", comment);
		mnv.setViewName(url);
		return mnv;
	}
	
	@PostMapping(value="/commentmodify", produces="text/plain;charset=utf-8")
	public ModelAndView Commentmodify(CommentModifyCommand command, ModelAndView mnv) throws Exception{
		String url = "/free/modifyComment_success";
		FreeCommentVO comment = command.toFreeCommentVO();
		int freeid = command.getFreeid();
		
		freeService.modifycomment(comment);
		
		mnv.addObject("freeid",freeid);
		mnv.setViewName(url);
		return mnv;
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