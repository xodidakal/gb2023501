package com.choongang.gb2023501.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.choongang.gb2023501.gbService.GbLgJoinService;
import com.choongang.gb2023501.gbService.HomeworkService;
import com.choongang.gb2023501.gbService.Paging;
import com.choongang.gb2023501.model.Homework;
import com.choongang.gb2023501.model.HwSend;
import com.choongang.gb2023501.model.LearnGrp;
import com.choongang.gb2023501.model.LgJoin;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class GbController {
	
	private final HomeworkService hs;
	private final GbLgJoinService gljs;
	
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
	  public String selectHomeworkList(Homework homework, String currentPage, HwSend hwsend, String result, Model model) {
		  System.out.println("GbController selectHomeworkList start...");
		  // 숙제 목록은 로그인한 교육자가 생성한 목록이 조회되므로 교육자 회원번호를 담는다. (우선 임시로 추후에 변경 예정)
		  homework.setM_num(3);
		  System.out.println("homework h_num -> "+homework.getH_num());
		  
		  // 생성한 숙제 총 개수
		  int homeworkListCnt = hs.selectHomeworkListCnt(homework);
			
		  Paging page = new Paging(homeworkListCnt, currentPage, 5);
		  homework.setStart(page.getStartRow());
		  homework.setEnd(page.getEndRow());
		  System.out.println("homework.getStart ->"+homework.getStart());
		  System.out.println("homework.getEnd ->"+homework.getEnd());
		  
		  // 생성한 숙제 셀렉트
		  List<Homework> allhomeworkList = hs.selectAllHomeworkList(homework);
		  
		  // 생성한 숙제 목록 조회
		  List<Homework> homeworkList = hs.selectHomeworkList(homework);
		  
		  // 교육자의 학습그룹 목록
		  List<LearnGrp> learnGrpList = gljs.selectLgJoinList(homework);
		  
		  // 학습그룹의 학습자 목록 변수 선언
		  List<LgJoin> lgJoinMemberList = null;
		  
		  // 학습그룹의 학습자 목록은 교육자의 학습그룹 목록이 있을 때만 조회한다.
		  if(learnGrpList.size() > 0) {
			if(hwsend.getLg_num() > 0) {
				System.out.println("학습그룹번호 검색 -> "+hwsend.getLg_num());
			}else {
				// 처음 진입할 때 학습그룹 번호는 가장 첫번째 값을 가지고 온다.
				hwsend.setLg_num(learnGrpList.get(0).getLg_num());
				System.out.println("처음 진입 -> "+hwsend.getLg_num());
			}		
			
			// 학습그룹의 학습자 목록
			lgJoinMemberList = gljs.selectLgJoinMemberList(hwsend);
		  }else { // 학습그룹이 하나도 없을 때에는 0으로 초기화
			  hwsend.setLg_num(0);
		  }
		  
		  model.addAttribute("homework1", homework);
		  model.addAttribute("allhomeworkList", allhomeworkList);
		  model.addAttribute("homeworkList", homeworkList);
		  model.addAttribute("StartRow",page.getStartRow());
		  model.addAttribute("page", page);
		  model.addAttribute("hwsend", hwsend);
		  model.addAttribute("learnGrpList", learnGrpList);
		  model.addAttribute("lgJoinMemberList", lgJoinMemberList);
		  
		  return "gb/homeworkSend";
	  }
	 
	  // 숙제 전송하기
	  @PostMapping("/educator/homeworkSendAction")
	  public String insertHwSendList(int[] h_num, int[] m_num, Model model) {
		  System.out.println("GbController insertHwSendList start...");
		  String result = "1";
		  
		  for(int i=0; i < h_num.length; i++) {
			  System.out.println("insertHwSendList h_num -> "+h_num[i]);
		  }
		  
		  for(int i=0; i < m_num.length; i++) {
			  System.out.println("insertHwSendList m_num -> "+m_num[i]);
		  }
		  
		  return"redirect:homeworkSend?result="+result;
	  }
}
