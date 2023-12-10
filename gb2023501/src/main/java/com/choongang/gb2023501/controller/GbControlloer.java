package com.choongang.gb2023501.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.choongang.gb2023501.gbService.HomeworkService;
import com.choongang.gb2023501.model.Homework;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class GbControlloer {
	
	private final HomeworkService hs;
	
	@RequestMapping("educator/homeworkForm")
	public String insertHomework(Homework homework, Model model) {
		System.out.println("GbControlloer insertHomework start...");
		
		List<Homework> homeworkList = hs.selectHomeworkList();
		System.out.println("GbControlloer insertHomework homeworkList->"+homeworkList);
		
		model.addAttribute("homeworkList", homeworkList);
		
		return "gb/homeworkForm";
	}
}
