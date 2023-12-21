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
		
		// 로그인한 회원의 회원번호 도출
		int mNum = memberService.selectMmNumById();

		// SELECT
		List<LearnGrpDTO> learnGrps = lgService.learnGroupList(mNum, 0, sort, type, keyword);
		System.out.println("HrController learnGroupList() learnGrps.size() -> "+learnGrps.size());

		// model 저장
		model.addAttribute("learnGrps", learnGrps);
		model.addAttribute("sort", sort);
		model.addAttribute("type", type);
		model.addAttribute("keyword", keyword);
		model.addAttribute("size", learnGrps.size());
		
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

		// 로그인한 회원의 회원번호 도출
		int mNum = memberService.selectMmNumById();

		// 학습그룹 기본 정보
		// 기존 method 활용하여 List<Game>(multi row)으로 받은 후 Game(single row)으로 분리
		List<LearnGrpDTO> learnGrps = lgService.learnGroupList(mNum, lg_num, "", "", "");
		System.out.println("HrController learnGroupList() learnGrps.size() -> "+learnGrps.size());
		
		// learnGrps.size()가 0일 경우 -> 에러페이지
		if(learnGrps.size() == 0) {
			System.out.println("HrController learnGroupDetail() error!");
			return "/hr/error";
		} else {
			LearnGrpDTO learnGrpDTO = learnGrps.get(0);
			
			model.addAttribute("learnGrpDTO", learnGrpDTO);
			model.addAttribute("lg_num", learnGrpDTO.getLearnGrp().getLgNum());	// 대표값(index=0)의 lg_num
			
			// 학습그룹 목록 (for ComboBox)
			List<LearnGrpDTO> learnGrpsAll = lgService.learnGroupList(mNum, 0, "", "", "");
			model.addAttribute("learnGrpsAll", learnGrpsAll);
			
			// 학습자 명단											// 대표값(index=0)의 lg_num
			List<MemberDTO> members = lgService.joinedMemberList(learnGrpDTO.getLearnGrp().getLgNum());
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
		model.addAttribute("sort", searchDTO.getSort());
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
		
		// gameList.size()가 0일 경우 -> 에러페이지
		if(gameList.size() == 0) {
			System.out.println("HrController learnGroupForm2() error!");
			return "/hr/error";
		} else {
			Game game = gameList.get(0);
			
			model.addAttribute("game", game);
			model.addAttribute("g_num", g_num);
			model.addAttribute("m_num", mNum);
			
			System.out.println("HrController learnGroupForm2() end..");
			return "/hr/learnGroupForm2";
		}
	}
	
	// 교육자마당 > 학습그룹 등록 - 실행 (INSERT / JPA)
	@PostMapping("educator/learnGroupFormInsert")
	public String learnGroupFormInsert(Model model, LearnGrp learnGrp, int lgPeriod) throws ParseException {
		System.out.println("HrController learnGroupFormInsert() start..");

		// 로그인한 회원의 회원번호 도출 -> NullPointerException 발생 -> jsp 화면에서 hidden으로 받아오는 것으로 수정
//		int mNum = memberService.selectMmNumById();
//		Member member = learnGrp.getMember();
//		member.setMmNum(mNum);
		
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

		// INSERT
		lgService.learnGroupFormInsert(learnGrp);
		
		System.out.println("HrController learnGroupFormInsert() end..");
		return "redirect:/educator/learnGroupList";
	}
	
	// 교육자마당 > 학습그룹 가입 승인 - 화면 (SELECT / JPA)
	@GetMapping("educator/learnGroupJoinList")
	public String learnGroupJoinList(Model model, int lg_num) {
		System.out.println("HrController learnGroupJoinList() start..");
		
		System.out.println("HrController learnGroupJoinList() lg_num -> "+lg_num);

		// 로그인한 회원의 회원번호 도출
		int mNum = memberService.selectMmNumById();

		// 학습그룹 기본 정보
		// 기존 method 활용하여 List<Game>(multi row)으로 받은 후 Game(single row)으로 분리
		List<LearnGrpDTO> learnGrps = lgService.learnGroupList(mNum, lg_num, "", "", "");
		System.out.println("HrController learnGroupJoinList() learnGrps.size() -> "+learnGrps.size());
		
		// learnGrps.size()가 0일 경우 -> 에러페이지
		if(learnGrps.size() == 0) {
			System.out.println("HrController learnGroupDetail() error!");
			return "/hr/error";
		} else {
			LearnGrpDTO learnGrpDTO = learnGrps.get(0);
			
			model.addAttribute("learnGrpDTO", learnGrpDTO);
			model.addAttribute("lg_num", learnGrpDTO.getLearnGrp().getLgNum());	// 대표값(index=0)의 lg_num
			
			// 학습그룹 목록 (for ComboBox)
			List<LearnGrpDTO> learnGrpsAll = lgService.learnGroupList(mNum, 0, "", "", "");
			model.addAttribute("learnGrpsAll", learnGrpsAll);

			// 신청자 명단											  // 대표값(index=0)의 lg_num
			List<MemberDTO> members = lgService.joiningMemberList(learnGrpDTO.getLearnGrp().getLgNum());
			System.out.println("HrController learnGroupJoinList() members.size() -> "+members.size());
			
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
	}

	// 교육자마당 > 학습그룹 가입 승인 - 실행 (UPDATE / JPA)
	@ResponseBody
	@RequestMapping("educator/learnGroupJoinApproval")
	public String learnGroupJoinApproval(int lg_num, int m_num) {
		System.out.println("HrController learnGroupJoinApproval() start..");
		
		System.out.println("HrController learnGroupJoinApproval() lg_num -> "+lg_num);
		System.out.println("HrController learnGroupJoinApproval() m_num -> "+m_num);
		
		lgService.learnGroupJoinApproval(lg_num, m_num);
		
		System.out.println("HrController learnGroupJoinApproval() end..");
		return "1";
	}
	
}
