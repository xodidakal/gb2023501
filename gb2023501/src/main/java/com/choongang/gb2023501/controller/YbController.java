package com.choongang.gb2023501.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.choongang.gb2023501.model.EduMaterials;
import com.choongang.gb2023501.ybService.EduMaterialsService;
import com.choongang.gb2023501.ybService.JpaEduMaterialsService;
import com.choongang.gb2023501.ybService.Paging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
	
@Slf4j
@Controller
@RequiredArgsConstructor
public class YbController {
	private final EduMaterialsService es;
	private final JpaEduMaterialsService js;
	
	@RequestMapping(value = "/")
	public String main() {
		System.out.println("Main start...");
		return "main";
	}
	@RequestMapping(value = "/operate/eduMaterialsForm")
	public String eduResourceForm() {
		System.out.println("ybController operate/eduResourceForm start...");
		return "yb/eduMaterialsForm";
	}
	// 학습자료 리스트
	@RequestMapping(value = "/operate/eduMaterialsList")
	public String eduResourceList(EduMaterials eduMaterials, Model model, String currentPage) {
		System.out.println("ybController operate/eduMaterialsList start...");
		
		int selectEduMaterialsListCnt = es.selectEduMaterialsListCnt(eduMaterials);
		
		Paging page = new Paging(selectEduMaterialsListCnt, currentPage);
		
		eduMaterials.setStart(page.getStart());
		eduMaterials.setEnd(page.getEnd());
		// 학습자료 리스트
		List<EduMaterials> selectEduMaterialsList = es.selectEduMaterialsList(eduMaterials);
		
		
		model.addAttribute("page", page);
		model.addAttribute("selectEduMaterialsList", selectEduMaterialsList);
		
		return "yb/eduMaterialsList";
	}
	
	// 학습자료 상세 화면 JPA
	@GetMapping(value = "/operate/eduMaterialsDetail")
	public String eduMaterialsDetail(int em_num, Model model) {
		System.out.println("ybController operate/eduMaterialsDetail start...");
		com.choongang.gb2023501.domain.EduMaterials eduMaterials = null;
		System.out.println("ybController operate/eduMaterialsDetail em_num -> " + em_num);
		Optional<com.choongang.gb2023501.domain.EduMaterials> OptiEduMaterials = js.findByEduMaterials(em_num);
		System.out.println("ybController operate/eduMaterialsDetail Optional.eduMaterials -> " + OptiEduMaterials);
		
		eduMaterials = OptiEduMaterials.get();
//		System.out.println("eduMaterials.getMember().getMNum() -> " + eduMaterials.getMember().getMmNum());
//		int m_num = eduMaterials.getMember().getMmNum();
//		System.out.println("eduMaterials.getMember().getMNum() = m_num -> " + m_num);
//		
		System.out.println("edumaterials.getRegiDate -> " + eduMaterials.getEmRegiDate());

//		model.addAttribute("m_num", m_num);
		model.addAttribute("eduMaterials", eduMaterials);
		return "yb/eduMaterialsDetail";
	}
	
	// 학습자료 수정 JPA
	@PostMapping(value = "/operate/updateEduMaterials")
	public String updateEduMaterials(com.choongang.gb2023501.domain.EduMaterials eduMaterials, int em_num) {
		log.info("ybController YbJpa/updateEduMaterials start...");
		
		
		log.info("eduMaterials -> " + eduMaterials);
		int result = js.updateByEduMaterials(eduMaterials);
		
		log.info("ybController operate/updateEduMaterials result - > " + result);
		log.info("update After -> " + eduMaterials);
		
		
		return "redirect:/operate/eduMaterialsDetail?em_num="+em_num;
	}
	
	
	// 매출 조회 화면
	@RequestMapping(value = "salesInquiryDetail")
	public String salesInquiryDetail() {
		System.out.println("ybController salesInquiryDetail start...");
		
		
		
		return "yb/salesInquiryDetail";
	}
	
	
	
}
