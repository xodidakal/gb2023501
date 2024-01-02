package com.choongang.gb2023501.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.choongang.gb2023501.domain.GameOrder;
import com.choongang.gb2023501.domain.LearnGrp;
import com.choongang.gb2023501.domain.LgJoin;
import com.choongang.gb2023501.domain.Member;
import com.choongang.gb2023501.jhService.MemberService;
import com.choongang.gb2023501.model.EduMaterials;
import com.choongang.gb2023501.model.Game;
import com.choongang.gb2023501.model.SalesInquiryDTO;
import com.choongang.gb2023501.ybService.EduMaterialsService;
import com.choongang.gb2023501.ybService.JpaEduMaterialsService;
import com.choongang.gb2023501.ybService.Paging;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
	
@Slf4j
@Controller
@RequiredArgsConstructor
public class YbController {
	private final EduMaterialsService es;
	private final JpaEduMaterialsService js;
	private final MemberService ms;
	private final JhController jh;
//	private Member aboutMember(Model model) {
//		Optional<Member> memberOptional = ms.selectUserById();
//		Member member = null;
//		if(memberOptional.isPresent()) {
//			member = memberOptional.get();
//			System.out.println("로그인 회원 정보 -> " + member);
//			System.out.println("member name -> " + member.getMmName());
//			model.addAttribute("member", member);
//		}
//		return member;
//	}
	
//	@RequestMapping(value = "/")
//	public String main(Model model) {
//		System.out.println("Main start...");
//
//		Optional<Member> memberOptional = ms.selectUserById();
//		Member member = null;		
//		if(memberOptional.isPresent()) {
//			member = memberOptional.get();
//			System.out.println("로그인 회원 정보 -> " + member);
//			System.out.println("member name -> " + member.getMmName());
//			
//		}
//		model.addAttribute("member", member);
//		return "main";
//	}
	// 학습자료 등록 페이지
	@RequestMapping(value = "/operate/eduMaterialsForm")
	public String eduResourceForm(Model model, Game game) {	
		System.out.println("ybController /operate/eduMaterialsForm start...");
		
		Member member = jh.aboutMember();
		// 게임 콘텐츠 리스트
		List<Game> selectGameList = es.selectGameList(game);
		System.out.println("ybController /operate/eduMaterialsForm  selectGameList -> " + selectGameList);
		System.out.println("model after member name -> " + member.getMmName());
		
		model.addAttribute("selectGameList", selectGameList);
		model.addAttribute("member", member);
		return "yb/eduMaterialsForm";
	}
	
	// 파일 업로드
	private String uploadFile(String originalName, byte[] fileData, String uploadPath) throws IOException {
		// universally unique identifier (UUID) --> 식별자 
		UUID uid = UUID.randomUUID();
		// requestPath = requestPath + "/resources/image";
		System.out.println("uploadPath -> " + uploadPath);
		// Directory 생성
		File fileDirectory = new File(uploadPath);
		if(!fileDirectory.exists()) {
			//신규폴더(Directory) 생성
			fileDirectory.mkdir();
			System.out.println("업로드용 폴더 생성 : " + uploadPath);
		}
		
		String savedName = uid.toString() + "_" + originalName;
		log.info("savedName : " + savedName);
		File target = new File(uploadPath, savedName);
//		File target = new File(requestPath, savedName);
		// File UpLoad ---> uploadPath / UUID +_+ originalName
		FileCopyUtils.copy(fileData, target);	//org.springframework.util.FileCopyUtils
		
		return savedName;
	}	
	
	// 학습자료 등록
	@RequestMapping(value = "/operate/insertEduMaterials")
	public String insertEduMaterials(com.choongang.gb2023501.domain.EduMaterials eduMaterials,Model model, Game game,
									 HttpServletRequest request, MultipartFile file1,String g_num) throws IOException {
		System.out.println("ybController operate/insertEduMaterials start...");
		
		// 이미지 경로 
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload/yb");
		
		System.out.println("uploadForm POST Start");
		// 파일 원본 이름
		log.info("originalName" + file1.getOriginalFilename());
		log.info("size: " + file1.getSize());
		log.info("contentType : " + file1.getContentType());
		log.info("uploadPath : " + uploadPath);
		// 파일 원본 이름 저장
		String savedName = uploadFile(file1.getOriginalFilename(), file1.getBytes(), uploadPath);
		//Service -> DB CRUD
		
		log.info("Return savedName : " + savedName);
		
		Member member = jh.aboutMember();
		String mmName = member.getMmName();
		eduMaterials.setEmAttachName(savedName);
		eduMaterials.setEmAttachPath(uploadPath);
		int insertMaterials = js.insertMaterials(eduMaterials, member);
		
		System.out.println("ybController operate/insertEduMaterials result => " + insertMaterials);
		
		return "redirect:/operate/eduMaterialsList";
	}

	// 학습자료 상세 화면 JPA
	@GetMapping(value = "/operate/eduMaterialsDetail")
	public String eduMaterialsDetail(int ggNum, Model model, int em_num, Game game, String beforeName) {
		System.out.println("ybController operate/eduMaterialsDetail start...");
		com.choongang.gb2023501.domain.EduMaterials eduMaterials = null;
		System.out.println("ybController operate/eduMaterialsDetail em_num -> " + em_num);
		System.out.println("ybController operate/eduMaterialsDetail ggNum -> " + ggNum);
		
		Optional<com.choongang.gb2023501.domain.EduMaterials> OptiEduMaterials = js.findByEduMaterials(em_num);
		System.out.println("ybController operate/eduMaterialsDetail Optional.eduMaterials -> " + OptiEduMaterials);
		System.out.println("\"ybController YbJpa/updateEduMaterials beforeName -> " + beforeName);
		eduMaterials = OptiEduMaterials.get();
		
		List<Game> selectGameList = es.selectGameList(game);
		System.out.println("edumaterials.getRegiDate -> " + eduMaterials.getEmRegiDate());
		model.addAttribute("eduMaterials", eduMaterials);
		model.addAttribute("selectGameList", selectGameList);
		return "yb/eduMaterialsDetail";
	}
	
	// 학습자료 수정 JPA
	@PostMapping(value = "/operate/updateEduMaterials")
	public String updateEduMaterials(com.choongang.gb2023501.domain.EduMaterials eduMaterials, int em_num, Model model,RedirectAttributes redirect,
									 HttpServletRequest request, MultipartFile file1, int gNum, String beforeName) throws IOException {
		log.info("ybController YbJpa/updateEduMaterials start...");	

		long g_num = gNum;
		System.out.println("\"ybController YbJpa/updateEduMaterials beforeName -> " + beforeName);
		System.out.println("\"ybController YbJpa/updateEduMaterials g_num -> " + g_num);
		log.info("em_num -> " + em_num);
		eduMaterials.setEmNum(em_num);
		log.info("eduMaterials -> " + eduMaterials);
		
		if(file1.getSize() == 0) {
			eduMaterials.setEmAttachName(beforeName);
		} else {
			String uploadPath = request.getSession().getServletContext().getRealPath("/upload/yb");
			
			System.out.println("uploadForm POST Start");
			// 파일 원본 이름
			log.info("originalName" + file1.getOriginalFilename());
			log.info("size: " + file1.getSize());
			log.info("contentType : " + file1.getContentType());
			log.info("uploadPathkkk : " + uploadPath);
			// 파일 원본 이름 저장
			String savedName = uploadFile(file1.getOriginalFilename(), file1.getBytes(), uploadPath);
			System.out.println("after update eduMaterials.savedNamekkk -> " + savedName);
			eduMaterials.setEmAttachName(savedName);
			eduMaterials.setEmAttachPath(uploadPath);
		}
	
		eduMaterials.setGgNum(g_num);
		int result = js.updateByEduMaterials(eduMaterials);
		System.out.println("after update eduMaterials.getAttachName -> " + eduMaterials.getEmAttachName());
		log.info("ybController operate/updateEduMaterials result - > " + result);
		log.info("update After -> " + eduMaterials);
		
		redirect.addAttribute("ggNum", g_num);
		redirect.addAttribute("em_num", em_num);
		model.addAttribute("eduMaterials", eduMaterials);
		return "redirect:/operate/eduMaterialsDetail";
	}
	
	// 학습자료 리스트  jpa
	@RequestMapping(value = "/operate/eduMaterialsList")
	public String JpaEduResourceList(@ModelAttribute("eduMaterials") com.choongang.gb2023501.domain.EduMaterials eduMaterials, Model model ){
		log.info("ybController operate/eduMaterialsList start...");

		// 학습자료 리스트
		List<com.choongang.gb2023501.domain.EduMaterials> selectEduMaterialsList = js.getListAllEduMaterials(eduMaterials);

		model.addAttribute("selectEduMaterialsList", selectEduMaterialsList);
		
		return "yb/eduMaterialsList";
	}
	
	// 학습자료 검색 리스트 jpa
	@RequestMapping(value = "/operate/searchEduMaterials")
	public String searchEduMaterials(String keyword, Model model, String type) {
		System.out.println("ybController operate/searchEduMaterials start...");
		List<com.choongang.gb2023501.domain.EduMaterials> selectEduMaterialsList = null;
		
		System.out.println("type -> " + type);
		System.out.println("keyword -> " + keyword);
		// em_title 검색 시 리스트 추출
		if(type.equals("em_title")) {
			selectEduMaterialsList = js.findByEduMaterialsContaining(keyword);
			System.out.println("selectEduMaterialsList.size() -> " + selectEduMaterialsList.size());
		}
		model.addAttribute("type", type);
		model.addAttribute("keyword", keyword);
		model.addAttribute("selectEduMaterialsList", selectEduMaterialsList);
		return "yb/eduMaterialsList";
	}
	// 학습자료 리스트 정렬
	@RequestMapping(value = "/operate/selectSearchType")
	public String selectSearchType(String typeSelect1, String typeSelect2, String typeSelect3, 
								   com.choongang.gb2023501.domain.EduMaterials eduMaterials, Model model, RedirectAttributes redirect) {
		System.out.println("ybController /operate/selectSearchType start...");
		System.out.println("typeSelect1 -> " + typeSelect1);
		System.out.println("typeSelect2 -> " + typeSelect2);
		System.out.println("typeSelect3 -> " + typeSelect3);
		int typeIntSelect1 =Integer.parseInt(typeSelect1);
		int typeIntSelect2 =Integer.parseInt(typeSelect2);
		int typeIntSelect3 =Integer.parseInt(typeSelect3);
		
		eduMaterials.setEmCategory(typeIntSelect1);
		eduMaterials.setEmType(typeIntSelect2);
		eduMaterials.setEmPayment(typeIntSelect3);
		
		System.out.println("emCategory -> " + eduMaterials.getEmCategory());
		List<com.choongang.gb2023501.domain.EduMaterials> selectEduMaterialsList = js.getListAllEduMaterials(eduMaterials);
		System.out.println("emCategory -> " + eduMaterials.getEmCategory());
		model.addAttribute("selectEduMaterialsList", selectEduMaterialsList);
		model.addAttribute("typeSelect1", typeIntSelect1);
		model.addAttribute("typeSelect2", typeIntSelect2);
		model.addAttribute("typeSelect3", typeIntSelect3);
		redirect.addFlashAttribute("eduMaterials", eduMaterials);
		
		return "redirect:/operate/eduMaterialsList";
	}
	// 학습자료 삭제
	@RequestMapping(value = "/operate/deleteEduMaterials")
	public String deleteEduMaterials(int emNum) {
		System.out.println("ybController operate/deleteEduMaterials start...");
		System.out.println("ybController operate/deleteEduMaterials emNum -> " + emNum);
		js.deleteByEmNum(emNum);
		
		return "redirect:/operate/eduMaterialsList";
	}
	
	
	// 매출 조회 화면
	@RequestMapping(value = "/operate/salesInquiryDetail")
	public String salesInquiryDetail(Model model, String selectDate, Pageable pageable) {
		System.out.println("ybController /operate/salesInquiryDetail start...");
		Date s_date = null;
		Date e_date = null;
		int selectTotal = es.findTotal(s_date, e_date);
		int selectListCnt = es.selectListCnt(s_date, e_date);
		model.addAttribute("selectListCnt", selectListCnt);
		model.addAttribute("selectTotal", selectTotal);
		return "yb/salesInquiryDetail";
	}
	// 매출 조회 화면 jpa
	@RequestMapping(value = "/operate/searchSalesInquiry")
	public String selectDateList(Model model, String selectDate, String startDate, String endDate, String sMonth, String eMonth, 
								 SalesInquiryDTO salesInquiryDTO)  throws ParseException {
		System.out.println("ybController /operate/selectDateList start...");
		List<SalesInquiryDTO> selectSaleList= null;
		int selectTotal = 0;
		int selectListCnt = 0;
		Date s_date = null;
		Date e_date = null;
		System.out.println("ybController /operate/selectDateList selectDate -> " + selectDate);
		// 일별 검색 
		if(selectDate.equals("date1")) {
			System.out.println("ybController /operate/selectDateList s_sdate -> " + startDate);
			System.out.println("ybController /operate/selectDateList e_sdate -> " + endDate);
			s_date = java.sql.Date.valueOf(startDate);
			e_date = java.sql.Date.valueOf(endDate);
			
			selectListCnt = es.selectListCnt(s_date, e_date);
			selectTotal = es.findTotal(s_date, e_date);
			selectSaleList = js.findBySalesContaining(s_date, e_date);	//, pageable
			System.out.println("ybController /operate/selectDateList selectSaleList -> " + selectSaleList);
			System.out.println("ybController /operate/selectDateList selectListCnt -> " + selectListCnt);
			log.info("selectSaleList -> " + selectSaleList.size());
			model.addAttribute("selectListCnt", selectListCnt);
			model.addAttribute("selectDate1", selectDate);
			model.addAttribute("selectTotal", selectTotal);
			model.addAttribute("selectSaleList", selectSaleList);
			model.addAttribute("s_date", s_date);
	        model.addAttribute("e_date", e_date);
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
	        selectListCnt = es.selectListCnt(s_date, e_date);
			/* month = java.sql.Date.valueOf(sMonth); */
	        selectTotal = es.findTotal(s_date, e_date);
	        selectSaleList = js.selectSaleList(s_date, e_date);
	        log.info("selectSaleList -> " + selectSaleList.size());
	        
	        model.addAttribute("selectListCnt", selectListCnt);
	        model.addAttribute("selectDate1", selectDate);
	        model.addAttribute("s_date", s_date);
	        model.addAttribute("e_date", e_date);	      
	        model.addAttribute("selectTotal", selectTotal);
	        model.addAttribute("selectSaleList1", selectSaleList);
		}
		
		
		
		
		return "yb/salesInquiryDetail";
	}

	// 매출 상세 리스트 
	   @RequestMapping(value = "/operate/searchSalesInquiryDetail")
	   public String searchSalesInquiryDetail(@Param("go_order_date") String go_order_date, GameOrder gameOrder, Model model) throws ParseException {
	      System.out.println("ybController /operate/searchSalesInquiryDetail go_order_date -> " + go_order_date);
	      System.out.println("go_order_date -> " + go_order_date);
	      Date orderDate = null;
	      String stringDate = null;
	      List<GameOrder> selectSaleList = null;
	      // 일별 상세 리스트
	      if(go_order_date.length() == 8) {
	         
	         orderDate = stringMathToDate(go_order_date);
	         
	         System.out.println("orderDate -> " + orderDate);
	         gameOrder.setGoOrderDate(orderDate);

	         selectSaleList = js.getListAllGameOrder(orderDate);
	        
	         log.info("selectSaleList -> " + selectSaleList);
	      // 월별 상세 리스트
	      } else if(go_order_date.length() == 6){
	         stringDate = go_order_date.substring(0,4) + "-" +go_order_date.substring(4,6);
	         String firstDay = getFirstDayOfMonth(stringDate);
	         String lastDay = getLastDayOfMonth(stringDate);
	         Date s_date = java.sql.Date.valueOf(firstDay);
	         Date e_date = java.sql.Date.valueOf(lastDay);
	         
	         System.out.println("go_order_date.length() != 8 -- s_date -> " + s_date);
	         System.out.println("go_order_date.length() != 8 -- e_date -> " + e_date);
	         
	         selectSaleList = js.getListAllGameOrder1(s_date, e_date);
	         
	      }
	      System.out.println("selectSaleList.size() -> " + selectSaleList.size());
	      System.out.println("orderDate -> " + orderDate);
//	      Date go_order_date1 = java.sql.Date.valueOf(go_order_date);
	      model.addAttribute("stringDate", stringDate);
	      model.addAttribute("selectSaleList", selectSaleList);
	      model.addAttribute("date", orderDate);
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

    // yyyymmdd 형식 String -> yyyy-mm-dd 형식 Date
    public static Date stringMathToDate(String date) {
		String stringDate = date.substring(0,4) + "-" +date.substring(4,6) + "-" +date.substring(6,8);
		Date typeDate = java.sql.Date.valueOf(stringDate);
		return typeDate;
	}
 
    // 학습 그룹 가입 신청 페이지
    @RequestMapping(value = "/learning/learnGrpJoinForm")
    public String learnGrpJoin(Model model,  com.choongang.gb2023501.model.LearnGrp learnGrp, String lgTitle, String mmName, String searchType) {
    	System.out.println("ybController /learning/learnGrpJoin start...");
    	System.out.println("ybController /learning/learnGrpJoin searchType -> " + searchType);
    	Member member = jh.aboutMember();
    	List<LearnGrp> selectLGpList = js.selectLGpList();
    	List<com.choongang.gb2023501.model.LearnGrp> selectMNameList = es.selectMNameList(learnGrp);
    	System.out.println("ybController /learning/learnGrpJoin selectLGpList.size() -> " + selectLGpList.size());
    	int mmNum = ms.selectMmNumById();
    	learnGrp.setM_num(mmNum);
    	List<LearnGrp> selectLgpListByTitle = es.selecLgpListByTitle(learnGrp);
    	System.out.println("ybController /learning/learnGrpJoin selectLgpListByTitle.size() -> " + selectLgpListByTitle.size());
    	
    	
    	model.addAttribute("selectLgpListByTitle", selectLgpListByTitle);
    	model.addAttribute("selectLGpList", selectLGpList);
    	model.addAttribute("selectMNameList", selectMNameList);

    	return "yb/learnGrpJoinForm";
    }
    // 학습 그룹 조건 검색
	@RequestMapping(value = "/learning/searchGrpList")
	public String searchGrpList(Model model, String searchType, com.choongang.gb2023501.model.LearnGrp learnGrp, String lgTitle,String mmName) {
		System.out.println("ybController /learning/searchGrpList start...");
		
		System.out.println("ybController /learning/searchGrpList searchType -> " + searchType);
		
		List<LearnGrp> selectLGpList = js.selectLGpList();
		List<com.choongang.gb2023501.model.LearnGrp> selectMNameList = es.selectMNameList(learnGrp);
		int mmNum = ms.selectMmNumById();
    	
		List<LearnGrp> selectLgpListByTitle = null;
		if(searchType.equals("lgTitle")) {
			System.out.println("ybController /learning/searchGrpList lgTitle -> " + lgTitle);
			learnGrp.setLg_title(lgTitle);
			learnGrp.setM_num(mmNum);
			selectLgpListByTitle = es.selecLgpListByTitle(learnGrp);
		} else {
			System.out.println("ybController /learning/searchGrpList mmName -> " + mmName);
			learnGrp.setM_num(mmNum);
			learnGrp.setM_name(mmName);
			
			selectLgpListByTitle = es.selecLgpListByTitle(learnGrp);
		} 
		model.addAttribute("selectMNameList",selectMNameList);
		model.addAttribute("selectLGpList", selectLGpList);
		model.addAttribute("selectLgpListByTitle", selectLgpListByTitle);
    	return "yb/learnGrpJoinForm";
	}
	// 학습 그룹 가입 신청 전송
	@RequestMapping(value = "/learning/learnGrpJoinDo")
	public String learnGrpJoinDo(@Param("lg_num") int lg_num, LgJoin lgJoin, Model model) {
		System.out.println("ybController /learning/learnGrpJoinDo start...");
		System.out.println("ybController /learning/learnGrpJoinDo lg_num -> " + lg_num);
		
		
		Member member = jh.aboutMember();
		int m_num = member.getMmNum();
		System.out.println("m_num -> " + m_num);
		System.out.println("ybController /learning/learnGrpJoinDo` start... -> " + lgJoin.getMember());
		int insertLgJoin = es.insertLgJoin(lg_num, m_num);
		
		model.addAttribute("insertLgJoin", insertLgJoin);
		return "redirect:/learning/learnGrpJoinForm";
	}
	// 매출 그래프 조회
	@RequestMapping(value = "/operate/saleInquiryChart")
	public String saleInquiryChart(Model model, String sDate, String eDate, String s, String date) throws JsonProcessingException {
		 System.out.println("ybController /operate/saleInquiryChart Start...");
		 List<String> dateList = new ArrayList<>();
		 
		 System.out.println("ybController /operate/saleInquiryChart sDate -> " + sDate);
		 System.out.println("ybController /operate/saleInquiryChart eDate -> " + eDate);
		 Date s_date =  stringMathToDate(sDate);
		 Date e_date =  stringMathToDate(eDate);
		 System.out.println("ybController /operate/saleInquiryChart selectDate ->" + date);
		 System.out.println("ybController /operate/saleInquiryChart s_date -> " + s_date);
		 System.out.println("ybController /operate/saleInquiryChart e_date -> " + e_date);
		
		List<SalesInquiryDTO> selectSaleList = null;
		SimpleDateFormat dateFormat = null;
		// 월 검색일 때
		if(date.equals("month")) {
			selectSaleList = js.selectSaleList(s_date, e_date);
			dateFormat = new SimpleDateFormat("yyyy-MM");
		} 
		// 일 별 검색
		else {
			selectSaleList = js.findBySalesContaining(s_date, e_date);
			dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		}
		System.out.println("ybController /operate/saleInquiryChart selectSaleList.get(0).toString() -> " + selectSaleList.get(0).getGoOrderDate());
		System.out.println("ybController /operate/saleInquiryChart selectSaleList.get(0).toString() -> " + selectSaleList.get(0).getSalesSum());

		// selectSaleList에서 날짜 뽑아서 형식 변환
		for(int i=0; i<selectSaleList.size(); i++) {
			Date  goOrderDate1 = selectSaleList.get(i).getGoOrderDate();
			
            String formattedDate = dateFormat.format(goOrderDate1);

			dateList.add(formattedDate);
		}
		
		System.out.println("ybController /operate/saleInquiryChart dateList -> " + dateList);
		System.out.println("ybController /operate/saleInquiryChart selectSaleList -> " + selectSaleList.toString());
		
		ObjectMapper objectMapper = new ObjectMapper();
		String selectSaleListJson = objectMapper.writeValueAsString(selectSaleList);
		String selectDateList = objectMapper.writeValueAsString(dateList);
		
		System.out.println("ybController /operate/saleInquiryChart selectSaleListJson -> " + dateList.get(0));
		System.out.println("ybController /operate/saleInquiryChart selectSaleListJson -> " + selectSaleListJson.toString());
		System.out.println("ybController /operate/saleInquiryChart selectSaleList.size() -> " + selectSaleList.size());
		
		model.addAttribute("selectDateList", selectDateList);
		model.addAttribute("selectSaleListJson", selectSaleListJson);
		model.addAttribute("s_date", s_date);
		model.addAttribute("e_date", e_date);
		model.addAttribute("selectSaleList", selectSaleList);
		
		return "yb/saleInquiryChart";
	}

	

	
	
}
