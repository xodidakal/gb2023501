package com.choongang.gb2023501.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.choongang.gb2023501.domain.LearnGrp;
import com.choongang.gb2023501.domain.Member;
import com.choongang.gb2023501.hrService.LearnGrpService;
import com.choongang.gb2023501.jhService.MemberService;
import com.choongang.gb2023501.model.Game;
import com.choongang.gb2023501.model.SearchDTO;
import com.choongang.gb2023501.model.LearnGrpDTO;
import com.choongang.gb2023501.model.MemberDTO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HrController {
	// Service 연결
	private final LearnGrpService lgService;
	private final MemberService memberService;
	
	// 교육자마당 > 내학습그룹 (SELECT / JPA)
	@GetMapping("educator/learnGroupList")
	public String learnGroupList(Model model, String sort, String type, String keyword) {
		System.out.println("HrController learnGroupList() start..");
		
		List<LearnGrpDTO> learnGrps = lgService.learnGroupList(0, sort, type, keyword);
		model.addAttribute("learnGrps", learnGrps);
		model.addAttribute("type", type);
		model.addAttribute("keyword", keyword);
		System.out.println("HrController learnGroupList() learnGrps.size() -> "+learnGrps.size());
		
		System.out.println("HrController learnGroupList() end..");		
		return "/hr/learnGroupList";
	}
	
	// 교육자마당 > 내학습그룹 (DELETE / JPA)
	@ResponseBody
	@RequestMapping(value = "educator/learnGroupListDelete", method = RequestMethod.DELETE)
	public String learnGroupListDelete(Model model, int lg_num) {
		System.out.println("HrController learnGroupListDelete() start..");
		
		System.out.println("HrController learnGroupListDelete() lg_num -> "+lg_num);
		
		lgService.learnGroupListDelete(lg_num);
		
		System.out.println("HrController learnGroupListDelete() end..");
		return "1";
	}
	
	// 교육자마당 > 학습그룹 상세 (SELECT / JPA)
	@GetMapping("educator/learnGroupDetail")
	public String learnGroupDetail(Model model, int lg_num) {
		System.out.println("HrController learnGroupDetail() start..");
		
		System.out.println("HrController learnGroupDetail() lg_num -> "+lg_num);

		// 학습그룹 기본 정보
		// 기존 method 활용하여 List<Game>(multi row)으로 받은 후 Game(single row)으로 분리
		List<LearnGrpDTO> learnGrps = lgService.learnGroupList(lg_num, "", "", "");
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
	public String learnGroupForm1(Model model, SearchDTO searchDTO) {
		System.out.println("HrController learnGroupForm1() start..");
		
		System.out.println("HrController learnGroupForm1() searchDTO.getSort() -> "+searchDTO.getSort());
		System.out.println("HrController learnGroupForm1() searchDTO.getType() -> "+searchDTO.getType());
		System.out.println("HrController learnGroupForm1() searchDTO.getKeyword() -> "+searchDTO.getKeyword());
		
		// 로그인한 회원의 회원번호 도출
		int mNum = memberService.selectMmNumById();
		// m_num -> GameDTO에 저장
		searchDTO.setMNum(mNum);

		List<Game> gameList = lgService.learnGroupForm(searchDTO);
		System.out.println("HrController learnGroupForm1() gameList.size() -> "+ gameList.size());		
		
		model.addAttribute("gameList", gameList);
		model.addAttribute("type", searchDTO.getType());
		model.addAttribute("keyword", searchDTO.getKeyword());

		System.out.println("HrController learnGroupForm1() end..");
		return "/hr/learnGroupForm1";
	}
	
	// 교육자마당 > 학습그룹 등록 - 화면 2 (SELECT / MyBatis)
	@GetMapping("educator/learnGroupForm2")
	public String learnGroupForm2(Model model, int g_num) {
		System.out.println("HrController learnGroupForm2() start..");
		
		System.out.println("HrController learnGroupForm2() g_num -> "+g_num);
		
		// g_num -> GameDTO에 저장
		SearchDTO searchDTO = new SearchDTO();
		searchDTO.setNum(g_num);
		
		// 로그인한 회원의 회원번호 도출
		int mNum = memberService.selectMmNumById();
		// m_num -> GameDTO에 저장
		searchDTO.setMNum(mNum);
		
		// 기존 method 활용하여 List<Game>(multi row)으로 받은 후 Game(single row)으로 분리
		List<Game> gameList = lgService.learnGroupForm(searchDTO);
		System.out.println("HrController learnGroupForm2() gameList.size() -> "+ gameList.size());	
		
		Game game = gameList.get(0);
		
		model.addAttribute("game", game);
		model.addAttribute("g_num", g_num);
		
		System.out.println("HrController learnGroupForm2() end..");
		return "/hr/learnGroupForm2";
	}
	
	// 교육자마당 > 학습그룹 등록 - 실행 (INSERT / JPA)
	@PostMapping("educator/learnGroupFormInsert")
	public String learnGroupFormInsert(Model model, LearnGrp learnGrp, int lgPeriod) throws ParseException {
		System.out.println("HrController learnGroupFormInsert() start..");
		
		// 회원번호 임시 세팅 ------------------ 추후 뺴야 함
		// learnGrp.getMember().setMmNum(3);
		
		System.out.println("HrController learnGroupFormInsert() learnGrp 1 -> "+ learnGrp);
		
		// 시작일자(lgSdate) & 개월수(lgPeriod) -> 종료일자(lgEdate)
		System.out.println("HrController learnGroupFormInsert() lgSdate -> " + learnGrp.getLgSdate());
		System.out.println("HrController learnGroupFormInsert() lgPeriod -> " + lgPeriod);
		
		// 시작일자 데이터타입 변경 (String -> Date)
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date lgSdate = format.parse(learnGrp.getLgSdate());
		
		// 종료일자 도출 (시작일자 + 개월수)
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(lgSdate);
		calendar.add(Calendar.MONTH, lgPeriod);
		
		String lgEdate = format.format(calendar.getTime());
		
		// 종료일자 저장
		learnGrp.setLgEdate(lgEdate);
		
		System.out.println("HrController learnGroupFormInsert() learnGrp 2 -> "+ learnGrp);

		lgService.learnGroupFormInsert(learnGrp);
		
		System.out.println("HrController learnGroupFormInsert() end..");
		return "redirect:/educator/learnGroupForm1";
	}
	
	// 교육자마당 > 학습그룹 가입 승인 - 화면 (SELECT / JPA)
	@GetMapping("educator/learnGroupJoinList")
	public String learnGroupJoinList(Model model, int lg_num) {
		System.out.println("HrController learnGroupJoinList() start..");
		
		System.out.println("HrController learnGroupDetail() lg_num -> "+lg_num);

		// 학습그룹 기본 정보
		// 기존 method 활용하여 List<Game>(multi row)으로 받은 후 Game(single row)으로 분리
		List<LearnGrpDTO> learnGrps = lgService.learnGroupList(lg_num, "", "", "");
		System.out.println("HrController learnGroupList() learnGrps.size() -> "+learnGrps.size());
		
		LearnGrpDTO learnGrpDTO = learnGrps.get(0);
		
		model.addAttribute("learnGrpDTO", learnGrpDTO);
		model.addAttribute("lg_num", lg_num);
		
		// 신청자 명단
		List<MemberDTO> members = lgService.joiningMemberList(lg_num);
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
