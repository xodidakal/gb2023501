package com.choongang.gb2023501.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.choongang.gb2023501.domain.Member;
import com.choongang.gb2023501.jhRepository.MemberRepository;
import com.choongang.gb2023501.jhService.BoardMyBatisService;
import com.choongang.gb2023501.jhService.GameMyBatisService;
import com.choongang.gb2023501.jhService.MemberService;
import com.choongang.gb2023501.model.Board;
import com.choongang.gb2023501.model.Game;
import com.choongang.gb2023501.model.MemberSearchCriteriaDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j 
@RequiredArgsConstructor
public class JhController {

	private final MemberRepository mr;
	//서비스 -> 레파지토리 이므로 여기선 mr 안 씀 -> 사용자 정보 업데이트할 때 사용
	private final MemberService ms;
	private final GameMyBatisService gs;
	private final BoardMyBatisService bs;
	
	private final JavaMailSender mailSender;
//	private final PasswordEncoder passwordEncoder;
	
	//폰 포멧팅 -> dto에 직접 넣었음 좋았을 걸...이름도 phoneFormat로 바꿀걸
	public String phone_format(String number) {
	      String regEx = "(\\d{3})(\\d{3,4})(\\d{4})";
	      return number.replaceAll(regEx, "$1-$2-$3");
	}
	
	//회원 정보 호출
	public Member aboutMember() {
		Optional<Member> memberOptional = ms.selectUserById();
		Member member = null;
		if(memberOptional.isPresent()) {
			member = memberOptional.get();
			System.out.println("로그인 회원 정보 -> " + member);
			System.out.println("member name -> " + member.getMmName());
		}
		return member;
	}
	
	@RequestMapping(value = "/")
	public String main(Model model) {
		System.out.println("JhController Main start...");

		Optional<Member> memberOptional = ms.selectUserById();
		Member member = null;		
		if(memberOptional.isPresent()) {
			member = memberOptional.get();
			System.out.println("로그인 회원 정보 -> " + member);
			System.out.println("member name -> " + member.getMmName());
			
		}
		
		//마이바티스 활용해 게임목록 조회(최근 등록순 상위 5개)
		int totalListCnt = 5;
		List<Game> selectGameList = gs.selectGameList(totalListCnt);
		
		//공지사항 리스트 최근 5개
		int noticeCategory = 2;
		Map<String, Integer> noticeParams = new HashMap<>();
		noticeParams.put("b_category", noticeCategory);
		noticeParams.put("totalListCnt", totalListCnt);
		List<Board> selectNoticeBoardList = bs.selectBoardList(noticeParams);
		
		
		//FAQ 리스트 최근 5개
		int FAQCategory = 3;
		Map<String, Integer> FAQParams = new HashMap<>();
		FAQParams.put("b_category", FAQCategory);
		FAQParams.put("totalListCnt", totalListCnt);
		List<Board> selectFAQBoardList = bs.selectBoardList(FAQParams);
		
		
		
		
		model.addAttribute("member", member);
		model.addAttribute("NoticeBoardList", selectNoticeBoardList);
		model.addAttribute("FAQBoardList", selectFAQBoardList);
		model.addAttribute("selectGameList", selectGameList);
		return "main";
	}
	
	
	
	//로그인 화면 이동
	@RequestMapping(value = "info/loginForm")
	public String loginForm() {
		System.out.println("JhController loginForm Start...");
		return "jh/loginForm";
	}
	
	//로그인 실패시 갈 페이지 -> 페이지 내용 수정 필요 -> 없어도 될 듯
//	@RequestMapping(value = "loginFailure")
//	public String loginFailure() {
//		System.out.println("JhController loginFailure Start...");
//		return "jh/loginFailure";
//		
//	}
	
	//로그아웃
	@GetMapping("logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("JhController logout Start...");
		new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
		//SecurityConfig에서 .logoutSuccessUrl로 처리하므로 return 불필요
//		return "redirect:/info/loginForm";
	}
	
	//회원약관 동의 페이지
	@RequestMapping(value = "info/joinAgreeForm")
	public String joinAgreeForm(Model model) {
		System.out.println("JhController joinAgreeForm Start...");
		
		
//		//로그인 된 아이디 가져오기
//		String mmId = ms.getLoggedInId();
//		//로그로 아이디 확인
//		log.info("로그인된 아이디:{}", mmId);
//		
//		
//		
//		//로그인된 회원번호 가져오기 / 로그인 안된 경우 0
//		int mmNum = ms.selectMmNumById();
//		System.out.println("회원번호 int " + mmNum);
//		
//		Member member = aboutMember();
//		model.addAttribute("member", member);

		//로그인 된 회원정보 전체 가져오기 
//		Optional<Member> memberOptional = ms.selectUserById();
//		if(memberOptional.isPresent()) {
//			Member member = memberOptional.get();
//			System.out.println("회원 이름" + member.getMmName());
//			System.out.println("회원 번호" + member.getMmNum());
//			model.addAttribute("member",member);
//		}
		
		return "jh/joinAgreeForm";
	}
	

	//약관 동의 후 본인인증(휴대폰은 유료라 생략 메일인증만 진행)
	@ResponseBody
	@PostMapping(value = "info/joinAgree")
	public String joinAgree(@RequestBody Map<String, String> data, HttpSession session ) {
		System.out.println("JhController joinAgree Start...");
		
	    // data에는 클라이언트가 전송한 JSON 데이터가 Map으로 변환되어 들어옴
	    // 예: {"name": "John", "phone": "123-456-7890", "email": "john@example.com"}
	    String name  = data.get("name");
	    String phone = data.get("phone");
	    String email = data.get("email");
	    System.out.println("name -> " + name);
	    System.out.println("phone -> " + phone);
	    System.out.println("email -> " + email);
	    
	    String result = null;
	    
		
		
		Optional<Member> currentUser = null;
		
		//휴대폰 인증
		if(phone != null) {
			//기존 사용자인지 비교 먼저하고 결과 반환하기 
			currentUser = ms.findByNameAndPhone(name, phone);
			
			if(currentUser.isPresent()) {
				System.out.println("JhController joinAgree currentUser -> " + currentUser);
				result = "1"; // 이미 가입된 사용자
			} else{
				session.setAttribute("phone", phone);
				session.setAttribute("name", name);
				result = "2"; // 신규 가입자, 폼 이동 필요
			}
			
//			model.addAttribute("phone",phone);
			
		//메일 인증	
		} else if (email != null) {
			//기존 사용자인지 비교 먼저하고 결과 반환하기 
			currentUser = ms.findByNameAndEmail(name, email);
			
			if(currentUser.isPresent()) {
				System.out.println("JhController joinAgree currentUser -> " + currentUser);
				result = "1";// 이미 가입된 사용자
				
			} else{
			
				int emailResult = emailVarification(email, name, session);
				
				if (emailResult > 0) {
					result = "3";
				} else {
					result = "4";
				}
			}
		}
		
		return result;
		
		
	}
	
	//이메일 인증번호 전송
	private int emailVarification(String email, String name, HttpSession session) {
		
		int result = 0;
		try {
			String title = "한국바둑기원 회원가입 인증번호 입니다.";
			String toEmail = (String) email;
			String setFrom= "alphago5012@gmail.com";
			
			//Mime 전자우편 Internet 표준 Format
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			
			//messageHelper를 통해 메일정보 세팅
			messageHelper.setFrom(setFrom);		//보내는 사람 이메일
			messageHelper.setTo(toEmail);		//받는 사람 이메일
			messageHelper.setSubject(title);
			
			 // 안전한 난수 생성 (6자리의 임시 비밀번호를 생성)
            SecureRandom secureRandom = new SecureRandom();
            int tempPassword = 100000 + secureRandom.nextInt(900000);
			//String tempPassword = (int) (Math.random() * 999999) + 1 + "";
			
			
			messageHelper.setText("인증번호 입니다 : " + tempPassword); //메일내용
			System.out.println("인증번호 입니다 : " +tempPassword);
			mailSender.send(message);
			//session.setAttribute("check", 1); //정상 전달
			session.setAttribute("name", name);
			session.setAttribute("email", email);
			session.setAttribute("tempPassword", tempPassword);
			
			result = 1; // 가입 인증번호 전송 성공
			
		} catch (Exception e) {
			System.out.println("JhController joinAgree mailTransport e.getMessage() -> " + e.getMessage());
			//session.setAttribute("check", 2); //메일 전달 실패
			result = 0; // 가입 인증번호 전송 실패
		}
		return result;
	}
	
	
	//메일 인증번호 받은 후 맞는지 확인
	@ResponseBody
	@PostMapping(value = "info/varification")
	public String varification(@RequestParam int varificationNum, HttpSession session ) {
		System.out.println("JhController varification Start...");
		int tempPassword = (int) session.getAttribute("tempPassword");
		System.out.println("JhController varification tempPassword -> " + tempPassword);
		System.out.println("JhController varification varificationNum -> " + varificationNum);
		
		String result = null;
//		비밀번호나 인증 번호와 같은 값의 비교는 == 연산자보다는 equals 메서드를 사용하는 것이 안전
//		equals는 객체 간의 내용 비교를 수행하는 반면, ==는 참조 비교를 수행
		if(Integer.valueOf(varificationNum).equals(tempPassword)) {
		//	if(varificationNum == tempPassword) {
			result = "1";
		} else {
			result = "0";
		}
		//한번 응답 받고나면 인증번호 재 사용 못하게 삭제
		session.removeAttribute("tempPassword");
		return result;
	}
	
	
	//약관동의 후 회원 가입 정보 입력 페이지
	@RequestMapping(value = "info/joinForm")
	public ModelAndView joinForm(HttpSession session) {
		System.out.println("JhController joinForm Start...");
		
		// ModelAndView 객체 생성 및 모델과 뷰 이름 설정
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("jh/joinForm");
		
		String name = (String) session.getAttribute("name");
		String phone = (String) session.getAttribute("phone");
		String email = (String) session.getAttribute("email");
		System.out.println("JhController joinForm name -> " + name);
		System.out.println("JhController joinForm phone -> " + phone);
		System.out.println("JhController joinForm email -> " + email);
		modelAndView.addObject("name", name);
		
		if(phone != null) {
			modelAndView.addObject("phone", phone);
		} else {
			modelAndView.addObject("email", email);
			
		}
		
		//세션 초기화
		//안하면 뒤로가기했다 앞으로 왔을 떄 인증 다시 안해도 정보가 계속 저장되어버림
		session.invalidate();
		
		return modelAndView;
	}
	
	//아이디 중복체크
	@ResponseBody
	@PostMapping(value = "info/idDuplicateCheck")
	public String idDuplicateCheck(String id) {
		System.out.println("JhController idDuplicateCheck Start...");
		System.out.println("JhController idDuplicateCheck id -> "+id);
		
		boolean existsByMmId = ms.existsByMmId(id);
		
		//중복아이디면 1, 아니면 0
		//ajax 결과는 boolean을 받지 못하기 때문
		String result = existsByMmId ? "1" : "0";
//		String result = "2";
		return result;
	}
	
	//회원가입
	@ResponseBody
	@PostMapping(value = "info/join")
	public String join( @Valid Member member) {
		System.out.println("JhController join Start...");
		System.out.println("memeber -> " + member);
		
		member.setMshipType(1);
		
		member = encodedPassword(member);
		
		Member savedMember = ms.join(member);
		String result = null;
		
		if(savedMember != null) {
			result = "1";
		} else 
		result = "0";
		
		return result;
	}
	
	//비밀번호 암호화
	private Member encodedPassword (Member member) {
		System.out.println("JhController encodedPassword Start... 비밀번호 인코딩");
		// 비밀번호 암호화
		//member 객체에서 비번 꺼내서 인코딩후 다시 멤버객체에 셋팅 후 멤버 리턴
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode(member.getMmPswd());
	    member.setMmPswd(encodedPassword);
		
		
		return member;
		
	}
	
	
	//아이디/비번 찾기 페이지
	@RequestMapping(value = "info/idPwInquiry")
	public String idPwInquiry() {
		System.out.println("JhController idPwInquiry Start...");
		return "jh/idPwInquiry";
	}
	
	//아이디 찾기
	@PostMapping(value = "info/idInquiry")
	@ResponseBody
	public String idInquiry(@RequestBody Map<String, String> data, HttpSession session ) {
		System.out.println("JhController idInquiry Start...");
		
		String name = data.get("name");
		String phone = data.get("phone");
		String email = data.get("email");
	    System.out.println("name -> " + name);
	    System.out.println("phone -> " + phone);
	    System.out.println("email -> " + email);
	    
		
		String result = null;
		
		Optional<Member> currentUser = null;
		Member user = null;
		
		//휴대폰 인증
		if(phone != null) {
			//기존 사용자인지 비교 먼저하고 결과 반환하기 
			currentUser = ms.findByNameAndPhone(name, phone);
			
			if(currentUser.isPresent()) {
				System.out.println("JhController joinAgree currentUser -> " + currentUser);
				user = currentUser.get();
				String id = user.getMmId();
				result = id;
			} else{
				System.out.println("JhController 유저 없음 ");
				result = "0"; // 신규 가입자, 폼 이동 필요
			}
			
//			model.addAttribute("phone",phone);
			
		//메일 인증	
		} else if (email != null) {
			//기존 사용자인지 비교 먼저하고 결과 반환하기 
			currentUser = ms.findByNameAndEmail(name, email);
			
			if(currentUser.isPresent()) {
				System.out.println("JhController joinAgree currentUser -> " + currentUser);
				int emailResult = emailVarification(email, name, session);
				if (emailResult > 0) {
					result = "1"; //메일전송 성공
				} else {
					result = ""; //전송 실패
				}
				
			} else{
				result = "0"; // 신규 가입자, 폼 이동 필요
				
			}
		}
		
		
		return result;
	}
	
	@PostMapping(value = "info/idInquiryByEmail")
	@ResponseBody
	public String idInquiryByEmail(@RequestParam int varificationNum, HttpSession session) {
		System.out.println("JhController idInquiryByEmail Start...");
		
		String result = null;
		try {
			//인증번호 검증 메서드 호출
			result = varification(varificationNum, session);
			if(result != "0") {
				String name = (String) session.getAttribute("name");
				String email = (String) session.getAttribute("email");
				Optional<Member> currentUser =  ms.findByNameAndEmail(name, email);
				Member user = currentUser.get();
				result = user.getMmId();
			} else {
				result = "0";
			} 
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}
	
	@PostMapping(value = "info/pswdInquiry")
	@ResponseBody
	public String pswdInquiry(@RequestBody Map<String, String> data, HttpSession session ) {
		System.out.println("JhController pswdInquiry Start...");
		String name = data.get("name");
		String id = data.get("id");
		String phone = data.get("phone");
		String email = data.get("email");

		String result = null;
		
		Optional<Member> currentMember = null;
		Member member = null;
		
		//휴대폰 인증
		if(phone != null) {
			//기존 사용자인지 비교 먼저하고 결과 반환하기 
			currentMember = ms.findByMmIdAndPhoneAndName(id, phone, name);
			
			if(currentMember.isPresent()) {
				System.out.println("JhController joinAgree currentUser -> " + currentMember);
				member = currentMember.get();
				//임시 비번 생성
				String temporaryPswd = generateTemporaryPassword();
				//임시비번 멤버에 셋팅
				member.setMmPswd(temporaryPswd);
				//세팅된 임시 비번 암호화
				member = encodedPassword(member);
				//암호화된 임시비번으로 디비에 업데이트
				mr.save(member);
				result = temporaryPswd; //사용자에게 보여주기 위해 해시 하지 않음
//				String pswd = member.getMmPswd(); 예전에 쓰던거
//				result = pswd;
			} else{
				System.out.println("JhController 유저 없음 ");
				result = "0"; // 신규 가입자, 폼 이동 필요
			}
			
//			model.addAttribute("phone",phone);
			
		//메일 인증	
		} else if (email != null) {
			//기존 사용자인지 비교 먼저하고 결과 반환하기 
			currentMember = ms.findByMmIdAndEmailAndName(id, email, name);
			
			if(currentMember.isPresent()) {
				System.out.println("JhController joinAgree currentUser -> " + currentMember);
				int emailResult = emailVarification(email, name, session);
				if (emailResult > 0) {
					session.setAttribute("id", id);
					result = "1"; //메일전송 성공
				} else {
					result = ""; //전송 실패
				}
				
			} else{
				result = "0"; // 신규 가입자, 폼 이동 필요
				
			}
		}
		
		
		return result;
	}
	
	@PostMapping(value = "info/pswdInquiryByEmail")
	@ResponseBody
	public String pswdInquiryByEmail(@RequestParam int varificationNum, HttpSession session) {
		System.out.println("JhController idInquiryByEmail Start...");
		
		String result = null;
		try {
			//인증번호 검증 메서드 호출
			result = varification(varificationNum, session);
			if(result != "0") {
				System.out.println("임시비번 발급 Start...");
				String name = (String) session.getAttribute("name");
				String email = (String) session.getAttribute("email");
				String id = (String) session.getAttribute("id");
				System.out.println("JhController pswdInquiryByEmail 유저정보..." + name+ email+ id);
				
				Optional<Member> currentUser = ms.findByMmIdAndEmailAndName(id, email, name);
				Member member = currentUser.get();
				System.out.println("JhController pswdInquiryByEmail 유저..." + member);
				
				String temporaryPswd = generateTemporaryPassword();
				
				//임시비번 멤버에 셋팅
				member.setMmPswd(temporaryPswd);
				//세팅된 임시 비번 암호화
				member = encodedPassword(member);
				//암호화된 임시비번으로 디비에 업데이트
				mr.save(member);
				result = temporaryPswd;
				System.out.println("JhController pswdInquiryByEmail 임시비번..." + temporaryPswd);
			} else {
				result = "0";
			} 
			
		} catch (Exception e) {
			System.out.println("JhController pswdInquiryByEmail e.getMessage()-> " + e.getMessage());
		} 
		
		return result;
	}
	
	// 임시비밀번호 생성
	private String generateTemporaryPassword() {
	    System.out.println("JhController 임시비번 generateTemporaryPassword Start...");
	    String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";

	    Random random = new SecureRandom();
	    StringBuilder password = new StringBuilder();

	    for (int i = 0; i < 8; i++) {
	        int randomIndex = random.nextInt(CHARACTERS.length());
	        password.append(CHARACTERS.charAt(randomIndex));
	    }

	    // 생성된 임시 비밀번호 반환 (해시하지 않음)사용자에게 보여주기 위해 해시 하지 않음
	    return password.toString();
	}
	
	
	//회원 전체 목록 조회 - 회원 관리 메인 페이지
	@GetMapping(value = "operate/memberList")
	public String memberList(
							 @RequestParam(name = "page", defaultValue = "1") int page
							, Model model) {
		System.out.println("JhController memberList Start...");
		
		
		int pageSize = 10; // 페이지당 아이템 수
		Sort sort = Sort.by("regiDate").descending(); // 정렬 조건: regiDate 필드를 기준으로 내림차순 정렬
		// 직접 Pageable 객체 생성하여 시작 페이지 번호 조정
		//Pageable의 기본 시작번호는 0부터지만 웹에선 1부터 시작이므로 조정
		Pageable pageable = PageRequest.of(page - 1, pageSize, sort);
		
		//회원 리스트
		Page<Member> memberList = ms.findAll(pageable);
		System.out.println("memberList.size() -> " + memberList.getSize());
		
		//폰번호 포멧팅
		String phone = null;
				
				
		// 전체 회원 수
	    long totalMembers = memberList.getTotalElements();

	    // 현재 페이지의 첫 번째 회원 번호 계산
	    //long startNumber = totalMembers - (adjustedPageable.getPageNumber() * adjustedPageable.getPageSize());
//	    int startNumber = (page - 1) * pageSize + 1;
	    long startNumber = pageable.getOffset() + 1;
	    
	    // 페이지 블록 계산
	    int pageBlock = 10;
	    int startPage = (int) (Math.floor((double) (page - 1) / pageBlock) * pageBlock) + 1; //시작 페이지 번호
	    int endPage = Math.min(startPage + pageBlock - 1, memberList.getTotalPages());		 //끝 페이지 번호
	    int totalPage = memberList.getTotalPages();	//전체 페이지 수

	    

		model.addAttribute("memberList", memberList);
		 model.addAttribute("startNumber", startNumber);
		model.addAttribute("totalMembers", totalMembers);
		model.addAttribute("pageBlock", pageBlock);
	    model.addAttribute("startPage", startPage);
	    model.addAttribute("endPage", endPage);
	    model.addAttribute("totalPage", totalPage);
	    model.addAttribute("page", page);
		
		return "jh/memberList";
	}
	
	@GetMapping(value = "operate/SearchMemberList")
	public String searchMemberList(MemberSearchCriteriaDTO searchCriteria
								 //, @PageableDefault(size = 10, sort = "regiDate", direction = Sort.Direction.DESC ) Pageable pageable
								 , @RequestParam(name = "page", defaultValue = "1") int page
								 , Model model
								 , HttpSession session) {
		//Pageable adjustedPageable = PageRequest.of(page - 1 , pageable.getPageSize());
		
		System.out.println("JhController searchMemberList Start...");
		System.out.println("조건 기간 시작 -> " + searchCriteria.getStartDate());
		System.out.println("조건 기간 끝 -> " + searchCriteria.getEndDate());
//		System.out.println("조건 아이디 -> " + searchCriteria.);
//		System.out.println("조건 이름 -> " + searchCriteria.);
//		System.out.println("조건 폰 -> " + searchCriteria.);
		System.out.println("조건 타입 -> " + searchCriteria.getSearchType());
		System.out.println("조건 값 -> " + searchCriteria.getSearchValue());
//		System.out.println("조건 회원구분 -> " + searchCriteria.getCategory());
//		System.out.println("조건 자격 -> " + searchCriteria.getMshipType());
		System.out.println("페이지 번호-> " + page);
		System.out.println("searchCriteria -> " + searchCriteria);
		
		
		int pageSize = 10; // 페이지당 아이템 수
		Sort sort = Sort.by("regiDate").descending(); // 정렬 조건: regiDate 필드를 기준으로 내림차순 정렬
		Pageable pageable = PageRequest.of(page - 1, pageSize, sort);

		//회원 리스트
		Page<Member> memberList = ms.SearchMemberList(searchCriteria, pageable);
		
		// 전체 회원 수
	    long totalMembers = memberList.getTotalElements();
	    
	    // 현재 페이지의 첫 번째 회원 번호 계산
//	    long startNumber = totalMembers - (pageable.getPageNumber() * pageable.getPageSize());
	    //int startNumber = (page - 1) * pageSize + 1;
	    long startNumber = pageable.getOffset()+1; //시작행 번호
	    
	    // 페이지 블록 계산
	    int pageBlock = 10;
	    int startPage = (int) (Math.floor((double) (page - 1) / pageBlock) * pageBlock) + 1;
	    int endPage = Math.min(startPage + pageBlock - 1, memberList.getTotalPages());
	    int totalPage = memberList.getTotalPages();

		//검색조건 리스트
		String searchCriteriaList = searchCriteria.getCriteriaListLink();
		model.addAttribute("criteriaList", searchCriteriaList);
	    
		model.addAttribute("memberList", memberList);
		model.addAttribute("startNumber", startNumber);
		model.addAttribute("totalMembers", totalMembers);
		model.addAttribute("searchCriteria", searchCriteria);
		model.addAttribute("pageBlock", pageBlock);
	    model.addAttribute("startPage", startPage);
	    model.addAttribute("endPage", endPage);
	    model.addAttribute("totalPage", totalPage);
	    model.addAttribute("page", page);
		
//		long tototalMembers = memberList.
		
		return "jh/memberList";
	}
	
	@GetMapping(value = "operate/memberDetail")
	public String memberDetail(@RequestParam(name = "mmNum", required = true) int mmNum
							 , Integer page
							 , MemberSearchCriteriaDTO searchCriteria
							 , Model model
							 ) {
		System.out.println("JhController memberDetail Start...");
		System.out.println("mmNum -> " + mmNum);
		System.out.println("page -> " + page);
		System.out.println("searchCriteria -> " + searchCriteria);
		
		Member member = ms.findByMmNum(mmNum);
		System.out.println("회원정보 member -> " + member);
		
		//검색조건 리스트
		String searchCriteriaList = searchCriteria.getCriteriaListLink();
		model.addAttribute("criteriaList", searchCriteriaList);
		
		model.addAttribute("page", page);
		model.addAttribute("criteria", searchCriteria);
		model.addAttribute("member", member);
		model.addAttribute("mmNum", mmNum);
		return "jh/memberDetail";
		
	}
	
	@GetMapping(value = "operate/memberUpdateForm")
	public String memberUpdateForm(@RequestParam(name = "mmNum", required = true) int mmNum
								 , Integer page
								 , MemberSearchCriteriaDTO searchCriteria
								 , Model model) {
		System.out.println("JhController memberUpdateForm Start...");
		System.out.println("mmNum -> " + mmNum);
		System.out.println("page -> " + page);
		System.out.println("searchCriteria -> " + searchCriteria);
		
		Member member = ms.findByMmNum(mmNum);
		System.out.println("회원정보 member -> " + member);
		
		//검색조건 리스트
		String searchCriteriaList = searchCriteria.getCriteriaListLink();
		model.addAttribute("criteriaList", searchCriteriaList);
		
		model.addAttribute("page", page);
		model.addAttribute("criteria", searchCriteria);
		model.addAttribute("member", member);
		model.addAttribute("mmNum", mmNum);
		
		return "jh/memberUpdateForm";
		
	}
	
	@PostMapping(value = "operate/memberUpdate")
	public String memberUpdate(Member member
							 , MemberSearchCriteriaDTO searchCriteria
//							 , Integer page
							 , Model model) {
		System.out.println("JhController memberUpdate Start...");
		
		int mmNum = member.getMmNum();
		System.out.println("member -> " + member);
//		System.out.println("page -> " + page);
		int page = searchCriteria.getPage();
//		System.out.println("pageNum -> " + pageNum);
		System.out.println("searchCriteria -> " + searchCriteria);
		System.out.println("가입일 -> " + member.getRegiDate());
		
		//회원정보 수정
		ms.join(member);


		//검색조건 리스트
		String searchCriteriaList = searchCriteria.getCriteriaListLink();
		model.addAttribute("criteriaList", searchCriteriaList);
		
		model.addAttribute("criteria", searchCriteria);
		
		String result = null;
		if(searchCriteria == null) {
			result = "redirect:/operate/memberDetail?mmNum=" + mmNum + "&page=" + page; 
		} else if (searchCriteria != null) {
			try {
	            // MemberSearchCriteriaDTO의 각 필드값 가져오기
//	            String searchType = searchCriteria.getSearchType();
//	            String searchValue = URLEncoder.encode(searchCriteria.getSearchValue(), "UTF-8");
//	            Integer category = searchCriteria.getCategory();
//	            Integer mshipType = searchCriteria.getMshipType();
//	            String startDate = searchCriteria.getStartDate();
//	            String endDate = searchCriteria.getEndDate();

	            result = "redirect:/operate/memberDetail"+ searchCriteriaList + "&mmNum=" + mmNum;
//	            result = "redirect:/operate/memberDetail?startDate=" + startDate +
//	            		"&endDate=" + endDate +
//	            		"&searchType=" + searchType +
//	            		"&searchValue=" + searchValue +
//	            		"&category=" + category +
//	            		"&mshipType=" + mshipType +
//	            		"&mmNum=" + mmNum +
//	            		"&page=" + page;
	        } catch (Exception e) {
	            // 예외 처리
	            e.printStackTrace();
	        }
		}
		
		return result;
	}
	
	@GetMapping(value = "info/myDetail")
	public String myDetail(String mmId , Model model){
		System.out.println("JhController myDetail Start...");

		Optional<Member> memberOptional = ms.selectUserById();
		Member member = memberOptional.get();
		
		String my = "1";
		System.out.println("회원정보 member -> " + member);
		model.addAttribute("member", member);
		model.addAttribute("my", my);
		return "jh/memberDetail";
	}
	
	@GetMapping(value = "info/myUpdateForm")
	public String myUpdateForm(@RequestParam(name = "mmNum", required = true) int mmNum, Model model) {
		System.out.println("JhController myUpdateForm Start...");
		Optional<Member> memberOptional = ms.selectUserById();
		Member member = memberOptional.get();
		
		String my = "1";
		System.out.println("회원정보 member -> " + member);
		model.addAttribute("member", member);
		model.addAttribute("my", my);
		return "jh/memberUpdateForm";
	}
	
	
	@PostMapping(value = "info/myUpdate")
	public String memberUpdate(Member member
							 , Model model) {
		System.out.println("JhController memberUpdate Start...");
		System.out.println("member 암호화 전 비번-> " + member.getMmPswd());
		member = encodedPassword(member);
		System.out.println("member 암호화 후 비번-> " + member.getMmPswd());
		String mmId = member.getMmId();
		System.out.println("member -> " + member);
//		System.out.println("mmId -> " + mmId);
		System.out.println("가입일 -> " + member.getRegiDate());
		
		//회원정보 수정
		ms.join(member);
		
		return "redirect:/info/myDetail?mmId="+mmId;
	}

}
