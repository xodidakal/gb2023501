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
		// insert 또는 update 결과값을 파라미터로 받아옴
		String result1 = null;
		if(result != null) {
			result1 = result;
		}
		
		// 숙제 목록은 로그인한 교육자가 생성한 목록이 조회되므로 교육자 회원번호를 담는다. (우선 임시로 추후에 변경 예정)
		homework.setM_num(3);
		
		// 생성한 숙제 총 개수
		int homeworkListCnt = hs.selectHomeworkListCnt(homework);
		
		Paging page = new Paging(homeworkListCnt, currentPage, 10);
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
		  
		  // 교육자 회원번호 임시로 넣음 (추후 변경 예정)
		  homework.setM_num(3);
		  
		  String result = String.valueOf(hs.insertUpdateHomework(homework));
		  System.out.println("GbController insertUpdateHomework result -> "+result);
		  
		  return "redirect:homeworkForm?result="+result; 
	  }
	  
	  // 숙제 전송 화면 이동
	  @RequestMapping("/educator/homeworkSend")
	  public String selectHomeworkList(Homework homework, String currentPage, Model model) {
		  System.out.println("GbController selectHomeworkList start...");
		  // 숙제 목록은 로그인한 교육자가 생성한 목록이 조회되므로 교육자 회원번호를 담는다. (우선 임시로 추후에 변경 예정)
		  homework.setM_num(3);
			
		  // 생성한 숙제 총 개수
		  int homeworkListCnt = hs.selectHomeworkListCnt(homework);
			
		  Paging page = new Paging(homeworkListCnt, currentPage, 5);
		  homework.setStart(page.getStartRow());
		  homework.setEnd(page.getEndRow());
		  System.out.println("homework.getStart ->"+homework.getStart());
		  System.out.println("homework.getEnd ->"+homework.getEnd());
		  
		  // 생성한 숙제 리스트 조회
		  List<Homework> homeworkList = hs.selectHomeworkList(homework);
		  
		  // 교육자의 학습그룹 가입 회원 조회
		  
		  
		  model.addAttribute("homeworkList", homeworkList);
		  model.addAttribute("StartRow",page.getStartRow());
		  model.addAttribute("page", page);
		  
		  return "gb/homeworkSend";
	  }
	 
}
