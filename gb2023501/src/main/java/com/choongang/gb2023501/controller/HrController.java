package com.choongang.gb2023501.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.choongang.gb2023501.domain.LearnGrp;
import com.choongang.gb2023501.hrService.LearnGrpService;
import com.choongang.gb2023501.model.Game;
//import com.choongang.gb2023501.model.LearnGrpDTO;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HrController {
	// Service 연결
	private final LearnGrpService lgService;
	
	// 교육자마당 > 내학습그룹 (SELECT)
//	@GetMapping("educator/learnGroupList")
//	public String learnGroupList(Model model, LearnGrp learnGrp) {
//		System.out.println("HrController learnGroupList() start..");
//		
//		List<LearnGrpDTO> learnGrps = lgService.learnGroupList();
//		model.addAttribute("learnGrps", learnGrps);
//		System.out.println("HrController learnGroupList() learnGrps.size() -> "+learnGrps.size());
//		
//		System.out.println("HrController learnGroupList() end..");		
//		return "/hr/learnGroupList";
//	}
	
	// 교육자마당 > 학습그룹 상세 (SELECT)
	@GetMapping("educator/learnGroupDetail")
	public String learnGroupDetail(Model model) {
		System.out.println("HrController learnGroupDetail() start..");
		
		
		System.out.println("HrController learnGroupDetail() end..");		
		return "/hr/learnGroupDetail";
	}
	
	// 교육자마당 > 학습그룹 등록 - 화면 1
	@GetMapping("educator/learnGroupForm1")
	public String learnGroupForm1(Model model) {
		System.out.println("HrController learnGroupForm1() start..");

		List<Game> gameList = lgService.learnGroupForm1();
		System.out.println("HrController learnGroupForm1() gameList.size() -> "+ gameList.size());		
		
		model.addAttribute("gameList", gameList);

		System.out.println("HrController learnGroupForm1() end..");
		return "/hr/learnGroupForm1";
	}
	
	// 교육자마당 > 학습그룹 등록 - 화면 2
	@GetMapping("educator/learnGroupForm2")
	public String learnGroupForm2() {
		System.out.println("HrController learnGroupForm2() start..");
		
		System.out.println("HrController learnGroupForm2() end..");
		return "/hr/learnGroupForm2";
	}
	
	// 교육자마당 > 학습그룹 등록 - 실행
	@PostMapping("educator/learnGroupFormInsert")
	public String learnGroupFormInsert() {
		System.out.println("HrController learnGroupFormInsert() start..");
		
		System.out.println("HrController learnGroupFormInsert() end..");
		return "";
	}
	
	// 교육자마당 > 학습그룹 가입 승인 - 화면
	@GetMapping("educator/learnGroupJoinList")
	public String learnGroupJoinList() {
		System.out.println("HrController learnGroupJoinList() start..");
		
		System.out.println("HrController learnGroupJoinList() end..");
		return "/hr/learnGroupJoinList";
	}

	// 교육자마당 > 학습그룹 가입 승인 - 실행
	@PostMapping("educator/learnGroupJoinApproval")
	public String learnGroupJoinApproval() {
		System.out.println("HrController learnGroupJoinApproval() start..");
		
		System.out.println("HrController learnGroupJoinApproval() end..");
		return "";
	}
	
}
