package com.choongang.gb2023501.controller;

import java.security.SecureRandom;
import java.util.Map;
import java.util.Optional;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.choongang.gb2023501.jhService.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j 
@RequiredArgsConstructor
public class JhController {

	//private final MemberRepository mr;
	//서비스 -> 레파지토리 이므로 여기선 mr 안 씀
	private final MemberService ms;
	private final JavaMailSender mailSender;
	
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
		
		
		//로그인 된 아이디 가져오기
		String mmId = ms.getLoggedInId();
		//로그로 아이디 확인
		log.info("로그인된 아이디:{}", mmId);
		
		
		
		//로그인된 회원번호 가져오기 / 로그인 안된 경우 0
		int mmNum = ms.selectMmNumById();
		System.out.println("회원번호 int " + mmNum);
		
		Member member = aboutMember();
		model.addAttribute("member", member);

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
	    String name = data.get("name");
	    String phone = data.get("phone");
	    String email = data.get("email");
	    System.out.println("name -> " + name);
	    System.out.println("phone -> " + phone);
	    System.out.println("email -> " + email);
	    
	    String result = null;
	    
//	public void joinAgree(HttpServletRequest request, Model model) {
		
//		Object name = request.getAttribute("name");
//		Object phone = request.getAttribute("phone");
//		Object email = request.getAttribute("email");
		
		
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
					
					result = "3"; // 가입 인증번호 전송 성공
					
				} catch (Exception e) {
					System.out.println("JhController joinAgree mailTransport e.getMessage() -> " + e.getMessage());
					//session.setAttribute("check", 2); //메일 전달 실패
					result = "4"; // 가입 인증번호 전송 실패
				}
			}
		}
		
		return result;
		
		
	}
	
	
	//메일 인증번호 받은 후 맞는지 확인
	@ResponseBody
	@PostMapping(value = "info/varification")
	public String varification(@RequestParam int verificationNum, HttpSession session ) {
		System.out.println("JhController varification Start...");
		int tempPassword = (int) session.getAttribute("tempPassword");
		System.out.println("JhController varification tempPassword -> " + tempPassword);
		System.out.println("JhController varification verificationNum -> " + verificationNum);
		
		String result = null;
//		비밀번호나 인증 번호와 같은 값의 비교는 == 연산자보다는 equals 메서드를 사용하는 것이 안전
//		equals는 객체 간의 내용 비교를 수행하는 반면, ==는 참조 비교를 수행
		if(Integer.valueOf(verificationNum).equals(tempPassword)) {
		//	if(verificationNum == tempPassword) {
			result = "1";
		} else {
			result = "2";
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
		Member savedMember = null;
		savedMember =  ms.join(member);
		String result = null;
		
		if(savedMember != null) {
			result = "1";
		} else 
		result = "0";
		
		return result;
	}
	
	
	
	//회원 목록 관리 페이지
	@RequestMapping(value = "operate/memberList")
	public String memberList() {
		System.out.println("JhController memberList Start...");
		//테스트 삼아 찍어본 것
		int mmNum = ms.selectMmNumById();
		System.out.println("회원번호 int " + mmNum);
		String mmId = ms.getLoggedInId();
		log.info("getLoggedInId:{}", mmId);
		Optional<Member> memberOptional = ms.selectUserById();
		if(memberOptional.isPresent()) {
			Member member = memberOptional.get();
			System.out.println("회원 이름" + member.getMmName());
			System.out.println("회원 번호" + member.getMmNum());
		}
		return "jh/memberList";
	}
	
	
}
