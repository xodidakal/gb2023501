package com.choongang.gb2023501.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.choongang.gb2023501.domain.LearnGrp;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HrController {
	// 교육자마당 > 내학습그룹
	@GetMapping("educator/learnGroupList")
	public String learnGroupList(Model model, LearnGrp learnGrp) {
		System.out.println("HrController learnGroupList() start..");
		
		model.addAttribute("yb", "yb");
		
		System.out.println("HrController learnGroupList() end..");		
		return "/hr/learnGroupList";
	}
	
	// 교육자마당 > 내학습그룹
	@GetMapping("educator/learnGroupDetail")
	public String learnGroupDetail(Model model, LearnGrp learnGrp) {
		System.out.println("HrController learnGroupDetail() start..");
		
		model.addAttribute("yb", "yb");
		
		System.out.println("HrController learnGroupDetail() end..");		
		return "/hr/learnGroupDetail";
	}
	
}
