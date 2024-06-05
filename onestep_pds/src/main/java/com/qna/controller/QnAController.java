package com.qna.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.qna.command.AnswerModifyCommand;
import com.qna.command.AnswerRegistCommand;
import com.qna.command.QnAModifyCommand;
import com.qna.command.QnARegistCommand;
import com.qna.dto.AnswerVO;
import com.qna.dto.QnAVO;
import com.qna.service.QnAService;
import com.spring.command.PageMaker;

@Controller
@RequestMapping("/qna")
public class QnAController {
	
	@Autowired
	private QnAService qnaService;
	
	@Resource(name = "imageUploadPath")
	private String uploadDir;
	@Resource(name = "fileUploadPath")
	private String fileUploadDir;
	//질문글 리스트
	@GetMapping("main")
	public ModelAndView list(@ModelAttribute PageMaker pageMaker, ModelAndView mnv) throws Exception{
		String url="/qna/main";
		List<QnAVO> qnaList = qnaService.searchList(pageMaker);
		mnv.addObject("qnaList",qnaList);
		mnv.setViewName(url);
		return mnv;
	}
	//질문글 작성폼
	@GetMapping("/registForm")
	public ModelAndView registForm(ModelAndView mnv) {
		String url = "/qna/regist";
		
		mnv.setViewName(url);
		return mnv;
	}
	//질문글 작성
	@PostMapping(value = "/regist", produces ="text/plain;charset=utf-8")
	public ModelAndView regist(QnARegistCommand regCommand,ModelAndView mnv) throws Exception{
		String url ="/qna/regist_success";
		QnAVO qna = regCommand.toQnAVO();
		
		qna.getQnaregdate();
		qnaService.regist(qna);
		mnv.setViewName(url);
		
		return mnv;
	}
	//질문글 조회
	@GetMapping("/detail")
	public ModelAndView detail(int qnaid, HttpSession session, String from, ModelAndView mnv) throws Exception{
		
		String url = "/qna/detail";
		
		QnAVO qna = null;

		if(from != null && from.equals("list")) {
			qnaService.increaseViewCnt(qnaid);
			url = "redirect:/qna/detail?qnaid="+qnaid;
		}
		else {
			qna = qnaService.getQnA(qnaid);
		}
		
		mnv.addObject("qna", qna);
		mnv.setViewName(url);
		return mnv;
	}
	
	@GetMapping("/modifyForm")
	public ModelAndView modifyForm(int qnaid, ModelAndView mnv) throws Exception{
		String url ="/qna/modify";
		
		QnAVO qna = qnaService.detail(qnaid);
		
		mnv.addObject("qna", qna);
		mnv.setViewName(url);
		return mnv;
	}
	
	@PostMapping("/modify")
	public ModelAndView modify(QnAModifyCommand modifyCommand, ModelAndView mnv) throws Exception{
		String url = "/qna/modify_success";
		
		QnAVO qna = modifyCommand.toQnAVO();
		qnaService.modify(qna);
		mnv.setViewName(url);
		
		return mnv;
	}
	//질문글 삭제
	@GetMapping("/remove")
	public ModelAndView remove(int qnaid, ModelAndView mnv) throws Exception{
		String url ="/qna/remove_success";
		
		ModelAndView qna = new ModelAndView();
		qnaService.remove(qnaid);
		mnv.setViewName(url);
		return mnv;
	}
	
	
	// ANSWER
	
	@GetMapping("/regist_answerForm")
	public ModelAndView ansregistForm(int qnaid, ModelAndView mnv) throws Exception{
		String url = "/qna/regist_answer";
		
		QnAVO qna = qnaService.detail(qnaid);
		mnv.addObject("qna", qna);
		mnv.setViewName(url);
		return mnv;
	}
	
	@PostMapping(value="/regist_answer", produces="text/plain;charset=utf-8")
	public ModelAndView ansregist(AnswerRegistCommand answerReg, ModelAndView mnv) throws Exception{
		String url = "/qna/answer_regist_success";
		
		AnswerVO answer = answerReg.toAnswerVO();
		
		qnaService.registAnswer(answer);
		
		int qnaid = answer.getQnaid();
		
		mnv.addObject("qnaid", qnaid);
		mnv.setViewName(url);
		return mnv;
	}
	//답변삭제
	@PostMapping("/remove_answer")
	public ModelAndView removeAnswer(int qnaid, int answerid, ModelAndView mnv) throws Exception{
		String url = "/qna/answer_remove_success";
		qnaService.deleteAnswer(answerid);
		
		mnv.addObject("qnaid", qnaid);
		mnv.setViewName(url);
		return mnv;
	}
	
	@GetMapping("/modify_answerForm")
	public ModelAndView AnswerModifyForm(int answerid, ModelAndView mnv) throws Exception{
		String url = "/qna/modify_answer";
		
		AnswerVO answer = qnaService.readanswer(answerid);
		
		mnv.addObject("answer", answer);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@PostMapping(value="modify_answer", produces="text/plain;charset=utf-8")
	public ModelAndView modifyAnswer(AnswerModifyCommand answerMod, ModelAndView mnv) throws Exception{
		String url = "/qna/answer_modify_success";
		AnswerVO answer = answerMod.toAnswerVO();
		
		qnaService.modifyAnswer(answer);
		
		mnv.addObject("qnaid", answer.getQnaid());
		mnv.setViewName(url);
		return mnv;
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
