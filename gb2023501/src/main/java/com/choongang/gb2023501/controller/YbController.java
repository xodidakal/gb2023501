package com.choongang.gb2023501.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.choongang.gb2023501.model.EduMaterials;
import com.choongang.gb2023501.ybService.EduMaterialsService;

import lombok.RequiredArgsConstructor;
	

@Controller
@RequiredArgsConstructor
public class YbController {
	private final EduMaterialsService es;
	
	@RequestMapping(value = "/")
	public String main() {
		System.out.println("Main start...");
		return "main";
	}
	@RequestMapping(value = "eduResourceForm")
	public String eduResourceForm() {
		System.out.println("ybController eduResourceForm start...");
		return "yb/eduResourceForm";
	}
	// 학습자료 리스트
	@RequestMapping(value = "eduResourceList")
	public String eduResourceList(EduMaterials eduMaterials, Model model) {
		System.out.println("ybController eduResourceList start...");
		// 학습자료 리스트
		List<EduMaterials> selectEduMaterialsList = es.selectEduMaterialsList(eduMaterials);
		
		model.addAttribute("selectEduMaterialsList", selectEduMaterialsList);
		return "yb/eduResourceList";
	}
	
	// 매출 조회 화면
	@RequestMapping(value = "salesInquiryDetail")
	public String salesInquiryDetail() {
		System.out.println("ybController salesInquiryDetail start...");
		
		
		
		return "yb/salesInquiryDetail";
	}
}
