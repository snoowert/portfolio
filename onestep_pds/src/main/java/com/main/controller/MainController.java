package com.main.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.main.service.MainService;
import com.onestep.command.PageMaker;
import com.onestep.dto.FreeVO;
import com.pms.dto.ProjectVO;
import com.qna.dto.QnAVO;

@Controller
@RequestMapping("/main")
public class MainController {
	@Autowired
	public MainService mainService;
	
	@GetMapping("/")
	public ModelAndView Main(ModelAndView mnv, PageMaker pagemaker) throws Exception{
		String url = "main/main";
		
		List<FreeVO> freeList = mainService.selectMain(pagemaker);
		
		List<FreeVO> topfreeFiveList = new ArrayList<>();
	    int count = 0;
	    for (FreeVO item : freeList) {
	        if (count >= 5) {
	            break;
	        }
	        topfreeFiveList.add(item);
	        count++;
	    }
	    
	    List<ProjectVO> projectList = mainService.selectProjectMain(pagemaker);
	    List<ProjectVO> topProjectFiveList = new ArrayList<>();
	    int countP = 0;
	    for (ProjectVO item : projectList) {
	        if (countP >= 5) {
	            break;
	        }
	        topProjectFiveList.add(item);
	        countP++;
	    }
	    
	    List<QnAVO> qnaList = mainService.selectQNAMain(pagemaker);
	    List<QnAVO> topQnAFiveList = new ArrayList<>();
	    int countQ = 0;
	    for (QnAVO item : qnaList) {
	        if (countQ >= 5) {
	            break;
	        }
	        topQnAFiveList.add(item);
	        countQ++;
	    }
	    
	    mnv.addObject("projectList", topProjectFiveList);
	    mnv.addObject("freeList",topfreeFiveList);
	    mnv.addObject("qnaList",topQnAFiveList);
	    mnv.setViewName(url);
		return mnv;
	}
}
