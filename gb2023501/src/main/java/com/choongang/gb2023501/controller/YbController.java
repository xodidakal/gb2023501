package com.choongang.gb2023501.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.choongang.gb2023501.domain.GameOrder;
import com.choongang.gb2023501.model.EduMaterials;
import com.choongang.gb2023501.model.SalesInquiryDTO;
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
//	@RequestMapping(value = "/operate/eduMaterialsList")
//	public String eduResourceList(EduMaterials eduMaterials, Model model, String currentPage) {
//		System.out.println("ybController operate/eduMaterialsList start...");
//		
//		int selectEduMaterialsListCnt = es.selectEduMaterialsListCnt(eduMaterials);
//		
//		Paging page = new Paging(selectEduMaterialsListCnt, currentPage, 10);
//		eduMaterials.setStart(page.getStartRow());
//		eduMaterials.setEnd(page.getEndRow());
//		// 학습자료 리스트
//		List<EduMaterials> selectEduMaterialsList = es.selectEduMaterialsList(eduMaterials);
//		
//		model.addAttribute("selectEduMaterialsListCnt", selectEduMaterialsListCnt);
//		model.addAttribute("StartRow",page.getStartRow());
//		model.addAttribute("page", page);
//		model.addAttribute("selectEduMaterialsList", selectEduMaterialsList);
//		
//		return "yb/eduMaterialsList";
//	}
	

	// 학습자료 상세 화면 JPA
	@GetMapping(value = "/operate/eduMaterialsDetail")
	public String eduMaterialsDetail(int em_num, Model model) {
		System.out.println("ybController operate/eduMaterialsDetail start...");
		com.choongang.gb2023501.domain.EduMaterials eduMaterials = null;
		System.out.println("ybController operate/eduMaterialsDetail em_num -> " + em_num);
		Optional<com.choongang.gb2023501.domain.EduMaterials> OptiEduMaterials = js.findByEduMaterials(em_num);
		System.out.println("ybController operate/eduMaterialsDetail Optional.eduMaterials -> " + OptiEduMaterials);
		
		eduMaterials = OptiEduMaterials.get();
		
		System.out.println("edumaterials.getRegiDate -> " + eduMaterials.getEmRegiDate());

		model.addAttribute("eduMaterials", eduMaterials);
		return "yb/eduMaterialsDetail";
	}
	
	// 학습자료 수정 JPA
	@PostMapping(value = "/operate/updateEduMaterials")
	public String updateEduMaterials(com.choongang.gb2023501.domain.EduMaterials eduMaterials, int em_num, Model model,RedirectAttributes redirect) {
		log.info("ybController YbJpa/updateEduMaterials start...");
		
		log.info("em_num -> " + em_num);
		eduMaterials.setEmNum(em_num);
		log.info("eduMaterials -> " + eduMaterials);
		
		int result = js.updateByEduMaterials(eduMaterials);
		
		log.info("ybController operate/updateEduMaterials result - > " + result);
		log.info("update After -> " + eduMaterials);
		
		redirect.addAttribute("em_num", em_num);
		model.addAttribute("eduMaterials", eduMaterials);
		return "redirect:/operate/eduMaterialsDetail";
	}
	
	// 학습자료 리스트  jpa
	@RequestMapping(value = "/operate/eduMaterialsList")
	public String JpaEduResourceList(EduMaterials eduMaterials, Model model) {
		log.info("ybController operate/eduMaterialsList start...");
		Date emRegidate = eduMaterials.getEm_regi_date();
		
		// 학습자료 리스트
		List<com.choongang.gb2023501.domain.EduMaterials> selectEduMaterialsList = js.getListAllEduMaterials();

		model.addAttribute("selectEduMaterialsList", selectEduMaterialsList);
		
		return "yb/eduMaterialsList";
	}
	
	// 학습자료 검색 리스트 jpa
	@RequestMapping(value = "/operate/searchEduMaterials")
	public String searchEduMaterials(String keyword, Model model, String type) {
		
		List<com.choongang.gb2023501.domain.EduMaterials> selectEduMaterialsList = null;
		
		System.out.println("type -> " + type);
		System.out.println("keyword -> " + keyword);
		
		if(type.equals("em_title")) {
			selectEduMaterialsList = js.findByEduMaterialsContaining(keyword);
			System.out.println("selectEduMaterialsList.size() -> " + selectEduMaterialsList.size());
		}
		
		model.addAttribute("selectEduMaterialsList", selectEduMaterialsList);
		return "yb/eduMaterialsList";
	}
	
	// 매출 조회 화면 jpa
	@RequestMapping(value = "/operate/salesInquiryDetail")
	public String salesInquiryDetail(Model model, String selectDate, Pageable pageable) {
		System.out.println("ybController /operate/salesInquiryDetail start...");
		
//		List<GameOrder> selectSaleList = js.getListAllGameOrder();
//		log.info("selectSaleList -> " + selectSaleList);
//		
//		model.addAttribute("selectSaleList", selectSaleList);
		
		return "yb/salesInquiryDetail";
	}
	// 매출 조회 화면 jpa
	@RequestMapping(value = "/operate/searchSalesInquiry")
	public String selectDateList(Model model, String selectDate, String startDate, String endDate, String sMonth, String eMonth) throws ParseException {
		System.out.println("ybController /operate/selectDateList start...");
		List<SalesInquiryDTO> selectSaleList= null;
		
		Date s_date = null;
		Date e_date = null;
		
		System.out.println("ybController /operate/selectDateList smonth -> " + sMonth);
		System.out.println("ybController /operate/selectDateList emonth -> " + eMonth);
		System.out.println("ybController /operate/selectDateList s_sdate -> " + startDate);
		System.out.println("ybController /operate/selectDateList e_sdate -> " + endDate);
		
		if(selectDate.equals("date")) {
			s_date = java.sql.Date.valueOf(startDate);
			e_date = java.sql.Date.valueOf(endDate);
			
			selectSaleList = js.findBySalesContaining(s_date, e_date);	
		}
		
		if(selectDate.equals("month")) {
			String firstDay = getFirstDayOfMonth(sMonth);
	        String lastDay = getLastDayOfMonth(eMonth);
	        
	        s_date = java.sql.Date.valueOf(firstDay);
			e_date = java.sql.Date.valueOf(lastDay);
	        System.out.println("ybController /operate/selectDateList firstDay -> " + s_date);
	        System.out.println("ybController /operate/selectDateList lastDay -> " + e_date);
			/* month = java.sql.Date.valueOf(sMonth); */
		

			selectSaleList = js.findBySalesContaining(s_date, e_date);
		}
		log.info("selectSaleList -> " + selectSaleList);
		
		model.addAttribute("selectSaleList", selectSaleList);
		
		return "yb/salesInquiryDetail";
	}
	
	    public static String getFirstDayOfMonth(String dateString) {
	        YearMonth yearMonth = YearMonth.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM"));
	        LocalDate firstDay = yearMonth.atDay(1);
	        return formatDate(firstDay);
	    }

	    public static String getLastDayOfMonth(String dateString) {
	        YearMonth yearMonth = YearMonth.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM"));
	        LocalDate lastDay = yearMonth.atEndOfMonth();
	        return formatDate(lastDay);
	    }

	    private static String formatDate(LocalDate date) {
	        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	    }
	
	
}
