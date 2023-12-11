package com.choongang.gb2023501.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.choongang.gb2023501.domain.LearnGrp;
import com.choongang.gb2023501.hrService.LearnGrpService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HrController {
	// Service 연결
	private final LearnGrpService lgService;
	
	// 교육자마당 > 내학습그룹 (SELECT)
	@GetMapping("educator/learnGroupList")
	public String learnGroupList(Model model, LearnGrp learnGrp) {
		System.out.println("HrController learnGroupList() start..");
		
		List<LearnGrp> learnGrps = lgService.learnGroupList();
		model.addAttribute("learnGrps", learnGrps);
		System.out.println("HrController learnGroupList() learnGrps.size() -> "+learnGrps.size());
		
		System.out.println("HrController learnGroupList() end..");		
		return "/hr/learnGroupList";
	}
	
	// 교육자마당 > 학습그룹 상세
	@GetMapping("educator/learnGroupDetail")
	public String learnGroupDetail(Model model, LearnGrp learnGrp) {
		System.out.println("HrController learnGroupDetail() start..");
		
		model.addAttribute("yb", "yb");
		
		System.out.println("HrController learnGroupDetail() end..");		
		return "/hr/learnGroupDetail";
	}
	
}
