package com.choongang.gb2023501.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.choongang.gb2023501.gbService.HomeworkService;
import com.choongang.gb2023501.gbService.Paging;
import com.choongang.gb2023501.model.Homework;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class GbController {
	
	private final HomeworkService hs;
	
	// 숙제 생성 목록
	@RequestMapping("educator/homeworkForm")
	public String selectHomeworkList(Homework homework, String currentPage, String result, Model model) {
		System.out.println("GbController selectHomework start...");
		String result1 = null;
		if(result != null) {
			result1 = result;
		}
		
		System.out.println("result1 -> "+ result1);
		
		// 생성한 숙제 총 개수
		int homeworkListCnt = hs.selectHomeworkListCnt();
		
		Paging page = new Paging(homeworkListCnt, currentPage);
		homework.setStart(page.getStartRow());
		homework.setEnd(page.getEndRow());
		System.out.println("homework.getStart ->"+homework.getStart());
		System.out.println("homework.getEnd ->"+homework.getEnd());
		
		// 생성한 숙제 리스트 조회
		List<Homework> homeworkList = hs.selectHomeworkList(homework);
		
		
		model.addAttribute("homeworkList", homeworkList);
		model.addAttribute("homeworkListCnt", homeworkListCnt);
		model.addAttribute("StartRow",page.getStartRow());
		model.addAttribute("page", page);
		model.addAttribute("result", result1);
		
		return "gb/homeworkForm";
	}
	
	  // 숙제 생성 insert 또는 update
	  @PostMapping("educator/homeworkInsertUdpate") 
	  public String insertUpdateHomework(Homework homework) {
		  System.out.println("GbController insertUpdateHomework start...");
		  // insert 일 경우 h_num을 0으로 초기화한다.
		  int h_num = 0;
		  // 파라미터 값으로 h_num 값이 있으면 그 값을 h_num 변수에 넣는다. (update일 경우)
		  if(homework.getH_num() > 0) {
			  h_num = homework.getH_num();
		  }
		  homework.setH_num(h_num);
		  homework.setM_num(3);	// 임시로 3번 넣었다.
		  
		  String result = String.valueOf(hs.insertUpdateHomework(homework));
		  System.out.println("GbController insertUpdateHomework result -> "+result);
		  
		  return "redirect:homeworkForm?result="+result; 
	  }
	 
}
