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
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.choongang.gb2023501.domain.GameOrder;
import com.choongang.gb2023501.domain.LearnGrp;
import com.choongang.gb2023501.domain.LgJoin;
import com.choongang.gb2023501.domain.Member;
import com.choongang.gb2023501.jhService.MemberService;
import com.choongang.gb2023501.model.EduMaterials;
import com.choongang.gb2023501.model.MonthSalesDTO;
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
	private final MemberService ms;
	
	private Member aboutMember(Model model) {
		Optional<Member> memberOptional = ms.selectUserById();
		Member member = null;
		if(memberOptional.isPresent()) {
			member = memberOptional.get();
			System.out.println("로그인 회원 정보 -> " + member);
			System.out.println("member name -> " + member.getMmName());
			model.addAttribute("member", member);
		}
		return member;
	}
	
	@RequestMapping(value = "/")
	public String main(Model model) {
		System.out.println("Main start...");

		aboutMember(model);
		
		return "main";
	}
	// 학습자료 등록 페이지
	@RequestMapping(value = "/operate/eduMaterialsForm")
	public String eduResourceForm(Model model) {	
//		Optional<Member> memberOptional = ms.selectUserById();
//		Member member = null;		
//		if(memberOptional.isPresent()) {
//			member = memberOptional.get();
//			System.out.println("로그인 회원 정보 -> " + member);
//			System.out.println("member name -> " + member.getMmName());
//			
//		}
		Member member = aboutMember(model);
		
		System.out.println("model after member name -> " + member.getMmName());
		
//		model.addAttribute("member", member);
		return "yb/eduMaterialsForm";
	}
	// 학습자료 등록
	@RequestMapping(value = "/operate/insertEduMaterials")
	public String insertEduMaterials(com.choongang.gb2023501.domain.EduMaterials eduMaterials,Model model) {
		System.out.println("ybController operate/insertEduMaterials start...");
		// 로그인 회원 정보 가져오기
		
		Optional<Member> memberOptional = ms.selectUserById();
		Member member = null;		
		if(memberOptional.isPresent()) {
			member = memberOptional.get();
			System.out.println("로그인 회원 정보 -> " + member);
			System.out.println("member name -> " + member.getMmName());
			
		}
		String mmName = member.getMmName();
		int insertMaterials = js.insertMaterials(eduMaterials, member);

		System.out.println("ybController operate/insertEduMaterials result => " + insertMaterials);
		
		return "redirect:/operate/eduMaterialsList";
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
		// em_title 검색 시 리스트 추출
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

		return "yb/salesInquiryDetail";
	}
	// 매출 조회 화면 jpa
	@RequestMapping(value = "/operate/searchSalesInquiry")
	public String selectDateList(Model model, String selectDate, String startDate, String endDate, String sMonth, String eMonth, 
								 SalesInquiryDTO salesInquiryDTO)  throws ParseException {
		System.out.println("ybController /operate/selectDateList start...");
		List<SalesInquiryDTO> selectSaleList= null;
		List<MonthSalesDTO> selectSaleList1 = null;
		Date s_date = null;
		Date e_date = null;
		
		System.out.println("ybController /operate/selectDateList selectDate -> " + selectDate);
		// 일별 검색 
		if(selectDate.equals("date")) {
			System.out.println("ybController /operate/selectDateList s_sdate -> " + startDate);
			System.out.println("ybController /operate/selectDateList e_sdate -> " + endDate);
			s_date = java.sql.Date.valueOf(startDate);
			e_date = java.sql.Date.valueOf(endDate);
			
			selectSaleList = js.findBySalesContaining(s_date, e_date);	//, pageable
			System.out.println("ybController /operate/selectDateList selectSaleList -> " + selectSaleList);
			//System.out.println(pageable.getPageSize());
			//System.out.println(pageable.getPageNumber());
			
			System.out.println(selectSaleList.get(0)); 
			System.out.println(selectSaleList.get(1)); 
			System.out.println(selectSaleList.get(2)); 
			
			log.info("selectSaleList -> " + selectSaleList.size());
		}
		// 월별 검색
		else if(selectDate.equals("month")) {

			System.out.println("ybController /operate/selectDateList smonth -> " + sMonth);
			System.out.println("ybController /operate/selectDateList emonth -> " + eMonth);
			// 해당 월 1일 구하기
			String firstDay = getFirstDayOfMonth(sMonth);
	        // 해당 월 마지막 날 구하기
			String lastDay = getLastDayOfMonth(eMonth);
	        
	        s_date = java.sql.Date.valueOf(firstDay);
			e_date = java.sql.Date.valueOf(lastDay);
	        System.out.println("ybController /operate/selectDateList firstDay -> " + s_date);
	        System.out.println("ybController /operate/selectDateList lastDay -> " + e_date);
			/* month = java.sql.Date.valueOf(sMonth); */
	        
	        selectSaleList1 = js.selectSaleList(s_date, e_date);
		}

		model.addAttribute("selectSaleList", selectSaleList);
		model.addAttribute("selectSaleList1", selectSaleList1);
		return "yb/salesInquiryDetail";
	}
	// 매출 상세 리스트 
	@RequestMapping(value = "/operate/searchSalesInquiryDetail")
	public String searchSalesInquiryDetail(@Param("go_order_date") String go_order_date, GameOrder gameOrder, Model model) throws ParseException {
		System.out.println("ybController /operate/searchSalesInquiryDetail go_order_date -> " + go_order_date);
		System.out.println("go_order_date -> " + go_order_date);
		String stringDate = go_order_date.substring(0,4) + "-" +go_order_date.substring(4,6) + "-" +go_order_date.substring(6,8);
		Date orderDate = java.sql.Date.valueOf(stringDate);
		
		System.out.println("orderDate -> " + orderDate);
		gameOrder.setGoOrderDate(orderDate);
//		List<com.choongang.gb2023501.model.GameOrder> selectSalesDetailList = es.selectSalesDetailList(gameOrder);
//		
		
		
		List<GameOrder> selectSaleList = js.getListAllGameOrder(orderDate);
		log.info("selectSaleList -> " + selectSaleList);
		
		model.addAttribute("selectSaleList", selectSaleList);
		
		return "yb/searchSalesInquiryDetail";
	}
	
	//  해당 월의 첫 번째 날 (atDay(1)) 얻기
    public static String getFirstDayOfMonth(String dateString) {
        YearMonth yearMonth = YearMonth.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM"));
        LocalDate firstDay = yearMonth.atDay(1);
        return formatDate(firstDay);
    }
    // 해당 월의 마지막 날 (atEndOfMonth()) 얻기
    public static String getLastDayOfMonth(String dateString) {
        YearMonth yearMonth = YearMonth.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM"));
        LocalDate lastDay = yearMonth.atEndOfMonth();
        return formatDate(lastDay);
    }
    // LocalDate 객체를 'yyyy-MM-dd' 형식으로 포맷팅
    private static String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
    
 
    // 학습 그룹 가입 신청 페이지
    @RequestMapping(value = "/learning/learnGrpJoinForm")
    public String learnGrpJoin(Model model,  com.choongang.gb2023501.model.LearnGrp learnGrp, String lgTitle) {
    	System.out.println("ybController /learning/learnGrpJoin start...");
    	
    	List<LearnGrp> selectLGpList = js.selectLGpList();
    	
    	System.out.println("ybController /learning/learnGrpJoin selectLGpList.size() -> " + selectLGpList.size());
    	List<LearnGrp> selectLgpListByTitle = es.selecLgpListByTitle(learnGrp);
    	int selectLgpListByTitleCnt = es.selectLgpListByTitleCnt(lgTitle);
    	model.addAttribute("selectLgpListByTitleCnt", selectLgpListByTitleCnt);
    	model.addAttribute("selectLgpListByTitle", selectLgpListByTitle);
    	model.addAttribute("selectLGpList", selectLGpList);	    	
    	return "yb/learnGrpJoinForm";
    }
    // 학습 그룹 조건 검색
	@RequestMapping(value = "/learning/searchGrpList")
	public String searchGrpList(Model model, String searchType, com.choongang.gb2023501.model.LearnGrp learnGrp, String lgTitle,String mmName) {
		System.out.println("ybController /learning/searchGrpList start...");
		
		System.out.println("ybController /learning/searchGrpList searchType -> " + searchType);
		System.out.println("ybController /learning/searchGrpList lgTitle -> " + lgTitle);
		List<LearnGrp> selectLGpList = js.selectLGpList();
		int selectLgpListByTitleCnt = es.selectLgpListByTitleCnt(lgTitle);
		List<LearnGrp> selectLgpListByTitle = null;
		if(searchType.equals("lgTitle")) {
			System.out.println("ybController /learning/searchGrpList lgTitle -> " + lgTitle);
			learnGrp.setLg_title(lgTitle);
			selectLgpListByTitle = es.selecLgpListByTitle(learnGrp);
		} else {
			learnGrp.setM_name(mmName);
			selectLgpListByTitle = es.selecLgpListByTitle(learnGrp);
		} 
		model.addAttribute("selectLGpList", selectLGpList);
		model.addAttribute("selectLgpListByTitleCnt", selectLgpListByTitleCnt);
		model.addAttribute("selectLgpListByTitle", selectLgpListByTitle);
    	return "yb/learnGrpJoinForm";
	}
	// 학습 그룹 가입 신청 전송
	@RequestMapping(value = "/learning/learnGrpJoinDo")
	public String learnGrpJoinDo(@Param("lg_num") int lg_num, LgJoin lgJoin) {
		System.out.println("ybController /learning/learnGrpJoinDo start...");
		System.out.println("ybController /learning/learnGrpJoinDo lg_num -> " + lg_num);
		
		Optional<Member> memberOptional = ms.selectUserById();
		Member member = null;		
		if(memberOptional.isPresent()) {
			member = memberOptional.get();
			System.out.println("로그인 회원 정보 -> " + member);
			System.out.println("member name -> " + member.getMmName());
			
		}
		lgJoin.setMember(member);
		lgJoin.setLg_num(lg_num);
		js.insertJoin(lgJoin);
		
		return "yb/learnGrpJoinForm";
	}
}
