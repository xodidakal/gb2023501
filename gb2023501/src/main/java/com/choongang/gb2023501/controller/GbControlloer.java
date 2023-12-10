package com.choongang.gb2023501.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.choongang.gb2023501.gbService.HomeworkService;
import com.choongang.gb2023501.gbService.Paging;
import com.choongang.gb2023501.model.Homework;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class GbControlloer {
	
	private final HomeworkService hs;
	
	// 숙제 생성
	@RequestMapping("educator/homeworkForm")
	public String insertHomework(Homework homework, String currentPage, Model model) {
		System.out.println("GbControlloer insertHomework start...");
		
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
		
		return "gb/homeworkForm";
	}
}
