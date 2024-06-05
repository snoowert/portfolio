package com.member.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.member.dao.MemberDAO;
import com.member.service.MemberService;
import com.member.vo.MemberVO;
import com.member.vo.QuizVO;
import com.spring.command.MemberModifyCommand;
import com.spring.command.MemberRegistCommand;
import com.spring.command.PageMaker;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	MemberService memberService;
	@Autowired
	MemberDAO memberDAO;
	//마이페이지(회원목록 or 내정보 메뉴확인)
	@GetMapping("/mypage")
	public ModelAndView myPage(ModelAndView mnv) {
		String url = "/member/mypage";
		
		mnv.setViewName(url);
		return mnv;
	}
	
	//회원목록
	@GetMapping("/list")
	public ModelAndView MemberList(ModelAndView mnv, PageMaker pageMaker) {
		String url = "/member/list";
		
		List<MemberVO> memberList = memberService.SearchMemberList(pageMaker);
		
		
		mnv.addObject("memberList", memberList);
		mnv.setViewName(url);
		return mnv;
	}
	
	//운영자회원조회
	@GetMapping("/Mdetail")
	public ModelAndView ManagerDetail(ModelAndView mnv, int memberid) {
		String url="/member/detail";
		String isM = "Yes";
		MemberVO member = memberService.SelectMemberById(memberid);
		mnv.addObject("isM", isM);
		mnv.addObject("member", member);
		mnv.setViewName(url);
		return mnv;
	}
	
	//회원조회
	@GetMapping("/detail")
	public ModelAndView Detail(ModelAndView mnv, int memberid) {
		String url="/member/detail";
		MemberVO member = memberService.SelectMemberById(memberid);
		
		mnv.addObject("member",member);
		mnv.setViewName(url);
		return mnv;
	}
	@GetMapping("/registSelect")
	public ModelAndView registSelect(ModelAndView mnv) {
		String url = "/member/regist_select";
		
		mnv.setViewName(url);
		return mnv;
	}
	//회원가입 폼
	@GetMapping("/registForm")
	public ModelAndView RegistForm(ModelAndView mnv, String authority) {
		String url ="/member/registForm";
		
		mnv.setViewName(url);
		mnv.addObject("authority",authority);
		return mnv;
	}
	
	//회원가입
	@PostMapping("/regist")
	public ModelAndView Regist(ModelAndView mnv, MemberRegistCommand command) {
		String url = "/member/regist_success";
		if(command.getDevlan().isEmpty()) {
			command.setDevlan("none");
		}
		MemberVO member = command.toMemberVO();
		memberService.insertMember(member);
		
		mnv.setViewName(url);
		return mnv;
	}
	@PostMapping("/checkEmail")
	@ResponseBody
	public String checkEmail(String email) {
		String data = "";
		PageMaker pageMaker = new PageMaker();
		pageMaker.setPerPageNum(Integer.MAX_VALUE);
		List<MemberVO> memberList = memberService.SearchMemberList(pageMaker);
		
		for(MemberVO member : memberList) {
			if(email.equals(member.getEmail())) {
				data = "duplicate";
				break;
			}
		}
		return data;
	}
	//로그인 폼
	@GetMapping("/loginForm")
	public ModelAndView LoginForm(ModelAndView mnv, String retUrl) {
		String url = "/member/loginForm";
		
		mnv.addObject("retUrl", retUrl);
		mnv.setViewName(url);
		return mnv;
	}
	//로그인
//	@PostMapping("/login")
//	public ModelAndView loginPost(String email, String password, String retUrl,
//								  HttpSession session, 
//								  RedirectAttributes rttr,
//								  ModelAndView mnv)throws Exception{
//		String url="redirect:/pds/list.do";
//		
//		try {
//			memberService.login(email, password);
//			
//			MemberVO member = memberDAO.selectMemberByEmail(email);
//			
//			session.setAttribute("loginUser", member);
//			session.setMaxInactiveInterval(60*6);
//			
//			session.getServletContext().setAttribute("loginUser", member.getMemberid());
//			
//			if(retUrl!=null && !retUrl.isEmpty()) {
//				url="redirect:"+retUrl;
//			}
//			
//		}catch(NotFoundIdentityException | InvalidPasswordException e) {
//			url="redirect:/member/loginForm?retUrl="+retUrl;
//			// rttr.addAttribute(attributeValue) : 주소줄 데이터 전달
//			rttr.addFlashAttribute("message",e.getMessage()); //requset 전달방식
//		}
//		
//		mnv.setViewName(url);
//		return mnv;
//	}

	//로그아웃
	@GetMapping("/loginTimeOut")
	public String loginTimeOut(Model model) throws Exception {

		String url = "/security/sessionOut";

		model.addAttribute("message", "세션이 만료되었습니다.\\n다시 로그인 하세요!");
		return url;
	}
	//회원수정 폼
	@GetMapping("/modifyForm")
	public ModelAndView ModifyForm(ModelAndView mnv, int memberid) {
		String url ="/member/modifyForm";
		
		MemberVO member = memberService.SelectMemberById(memberid);
		
		mnv.addObject("member",member);
		mnv.setViewName(url);
		return mnv;
	}
	//회원수정
	@PostMapping("/modify")
	public ModelAndView Modify(ModelAndView mnv, MemberModifyCommand command, HttpServletRequest request ) {
		String url = "/member/modify_success";
		
		MemberVO member = command.toMemberVO();
		memberService.updateMember(member);
		
		HttpSession session = request.getSession();
	    MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
	    
	    if(loginUser != null) {
	    	loginUser.setUsername(member.getUsername());
	    	loginUser.setPassword(member.getPassword());
	    	
	    	session.setAttribute("loginUser", loginUser);
	    }
		mnv.addObject("memberid", member.getMemberid());
		mnv.setViewName(url);
		return mnv;
	}
	//비밀번호 확인폼
	@GetMapping("/passwordCheckForm")
	public ModelAndView PWcheckForm(ModelAndView mnv, int memberid, String error) {
		String url = "/member/password_check";
		
		mnv.setViewName(url);
		mnv.addObject("memberid", memberid);
		mnv.addObject("error", error);
		return mnv;
	}
	//비밀번호 확인
	@PostMapping("/PWCheck")
	public ModelAndView PWCheck(ModelAndView mnv, String password, int memberid) {
		MemberVO member = memberService.SelectMemberById(memberid);
		mnv.addObject("memberid", memberid);
		if(password.equals(member.getPassword())) {
			mnv.setViewName("redirect:/member/detail");
		}
		else {
			mnv.addObject("error", "비밀번호가 맞지 않습니다.");
			mnv.setViewName("redirect:/member/passwordCheckForm");
		}
		return mnv;
	}
	//회원탈퇴
	@PostMapping("/ban")
    public ResponseEntity<String> banMember(@RequestParam("memberid") int memberid) {
        memberService.banMember(memberid);
        return ResponseEntity.ok("Member banned successfully.");
    }
	@GetMapping("/banSelf")
	public ModelAndView banSelf(int memberid, ModelAndView mnv) {
		String url ="/member/banSelf_success";
		
		memberService.banMember(memberid);
		
		mnv.setViewName(url);
		return mnv;
	}
	//회원복구
    @PostMapping("/restore")
    public ResponseEntity<String> restoreMember(@RequestParam("memberid") int memberid) {
        memberService.restoreMember(memberid);
        return ResponseEntity.ok("Member restored successfully.");
    }
    //가입용 퀴즈 호출
    @GetMapping("/quiz")
    @ResponseBody
    public Map<String, String> getQuizData(String devlan) {
        // 퀴즈 데이터를 생성하거나 DB에서 가져와서 QuizData 객체로 반환합니다.
        // 여기서는 간단한 예시로 고정된 데이터를 반환합니다.
    	Map<String, String> quizData = new HashMap<>();
        List<QuizVO> quizList = memberService.SelectQuizByDevlan(devlan);
    	QuizVO quiz = quizList.get((int)(Math.random()*5));
    	quizData.put("question", quiz.getQuizcontent());
    	quizData.put("answer", quiz.getQuizanswer());
        
        return quizData;
    }
}
