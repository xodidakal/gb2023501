package com.choongang.gb2023501.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.choongang.gb2023501.domain.LearnGrp;
import com.choongang.gb2023501.domain.Member;
import com.choongang.gb2023501.hrService.LearnGrpService;
import com.choongang.gb2023501.model.Game;
import com.choongang.gb2023501.model.LearnGrpDTO;
import com.choongang.gb2023501.model.MemberDTO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HrController {
	// Service 연결
	private final LearnGrpService lgService;
	
	// 교육자마당 > 내학습그룹 (SELECT / JPA)
	@GetMapping("educator/learnGroupList")
	public String learnGroupList(Model model, LearnGrp learnGrp) {
		System.out.println("HrController learnGroupList() start..");
		
		List<LearnGrpDTO> learnGrps = lgService.learnGroupList(0);
		model.addAttribute("learnGrps", learnGrps);
		System.out.println("HrController learnGroupList() learnGrps.size() -> "+learnGrps.size());
		
		System.out.println("HrController learnGroupList() end..");		
		return "/hr/learnGroupList";
	}
	
	// 교육자마당 > 학습그룹 상세 (SELECT / JPA)
	@GetMapping("educator/learnGroupDetail")
	public String learnGroupDetail(Model model, int lg_num) {
		System.out.println("HrController learnGroupDetail() start..");
		
		System.out.println("HrController learnGroupDetail() lg_num -> "+lg_num);

		// 학습그룹 기본 정보
		// 기존 method 활용하여 List<Game>(multi row)으로 받은 후 Game(single row)으로 분리
		List<LearnGrpDTO> learnGrps = lgService.learnGroupList(lg_num);
		System.out.println("HrController learnGroupList() learnGrps.size() -> "+learnGrps.size());
		
		LearnGrpDTO learnGrpDTO = learnGrps.get(0);
		
		model.addAttribute("learnGrpDTO", learnGrpDTO);
		model.addAttribute("lg_num", lg_num);
		
		// 학습자 명단
		List<MemberDTO> members = lgService.joinedMemberList(lg_num);
		System.out.println("HrController learnGroupList() members.size() -> "+members.size());
		
		// 휴대전화 하이픈 추가
		for(MemberDTO member : members) {
			// Member 객체 추출
			Member memberObject = member.getMember();
			
			// 휴대전화 추출
			String ph = memberObject.getPhone();
			System.out.println("ph -> "+ph);
			
			// 하이픈 추가
			String phHyphen = ph.substring(0,3) + "-" + ph.substring(3,7) + "-" + ph.substring(7,11);
			System.out.println("phHyphen -> "+phHyphen);
			
			// 휴대전화 재설정
			memberObject.setPhone(phHyphen);
			
			// Member 재설정
			member.setMember(memberObject);
		}
		
		model.addAttribute("members", members);

		System.out.println("HrController learnGroupDetail() end..");		
		return "/hr/learnGroupDetail";
	}
	
	// 교육자마당 > 학습그룹 등록 - 화면 1 (SELECT / MyBatis)
	@GetMapping("educator/learnGroupForm1")
	public String learnGroupForm1(Model model) {
		System.out.println("HrController learnGroupForm1() start..");

		List<Game> gameList = lgService.learnGroupForm(0);
		System.out.println("HrController learnGroupForm1() gameList.size() -> "+ gameList.size());		
		
		model.addAttribute("gameList", gameList);

		System.out.println("HrController learnGroupForm1() end..");
		return "/hr/learnGroupForm1";
	}
	
	// 교육자마당 > 학습그룹 등록 - 화면 2 (SELECT / MyBatis)
	@GetMapping("educator/learnGroupForm2")
	public String learnGroupForm2(Model model, int g_num) {
		System.out.println("HrController learnGroupForm2() start..");
		
		System.out.println("HrController learnGroupForm2() g_num -> "+g_num);
		
		// 기존 method 활용하여 List<Game>(multi row)으로 받은 후 Game(single row)으로 분리
		List<Game> gameList = lgService.learnGroupForm(g_num);
		System.out.println("HrController learnGroupForm2() gameList.size() -> "+ gameList.size());	
		
		Game game = gameList.get(0);
		
		model.addAttribute("game", game);
		model.addAttribute("g_num", g_num);
		
		System.out.println("HrController learnGroupForm2() end..");
		return "/hr/learnGroupForm2";
	}
	
	// 교육자마당 > 학습그룹 등록 - 실행 (INSERT / JPA)
	@PostMapping("educator/learnGroupFormInsert")
	public String learnGroupFormInsert(Model model, LearnGrp learnGrp) {
		System.out.println("HrController learnGroupFormInsert() start..");
		
		// 회원번호 임시 세팅 ------------------ 추후 뺴야 함
		// learnGrp.getMember().setMmNum(3);
		
		System.out.println("HrController learnGroupFormInsert() learnGrp -> "+ learnGrp);
		
		lgService.learnGroupFormInsert(learnGrp);
		
		System.out.println("HrController learnGroupFormInsert() end..");
		return "redirect:/educator/learnGroupForm1";
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
