package com.exp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.exp.service.ExpService;
import com.exp.vo.ExpVO;

@Controller
@RequestMapping("/exp")
public class ExpController {
	@Autowired
	ExpService expService;
	
	@GetMapping("/main")
	public ModelAndView main(ModelAndView mnv) throws Exception{
		String url = "/exp/main";
		ExpVO exp = expService.selectExpById("main");
		mnv.addObject("exp", exp);
		mnv.setViewName(url);
		return mnv;
	}
	
	@GetMapping("/backup")
	public ModelAndView backup(ModelAndView mnv) throws Exception{
		String url = "/exp/backup";
		ExpVO exp = expService.selectExpById("main");
		mnv.addObject("exp", exp);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping("/getVideo")
	@ResponseBody
	public String getVideo(@RequestParam String videoName) throws Exception{
	    // videoName을 이용하여 DB에서 동영상 파일명을 가져옵니다.
	    ExpVO exp = expService.selectExpById(videoName);
	    return exp.getExpname();
	}
}
