package com.choongang.gb2023501.controller;

import java.util.Map;
import java.util.Optional;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.choongang.gb2023501.domain.Member;
import com.choongang.gb2023501.jhRepository.MemberRepository;
import com.choongang.gb2023501.jhService.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j 
@RequiredArgsConstructor
public class JhController {

	private final MemberRepository mr;
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
	
	@RequestMapping(value = "info/loginForm")
	public String loginForm() {
		System.out.println("JhController loginForm Start...");
		return "jh/loginForm";
	}
	
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
	
//	//회원약관 동의 후?
//	@PostMapping(value = "joinAgree")
//	@ResponseBody
//	public String joinAgree() {
//		System.out.println("JhController joinAgree Start...");
//		
//		return "jh/joinAgree";
//	}
	
	//약관동의 후 회원 가입 정보 입력 페이지
	@RequestMapping(value = "joinForm")
	public String joinForm() {
		System.out.println("JhController joinForm Start...");
		
		return "jh/joinForm";
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
	
	
	//로그인 실패시 갈 페이지 -> 페이지 내용 수정 필요
	@RequestMapping(value = "loginFailure")
	public String loginFailure() {
		System.out.println("JhController loginFailure Start...");
		return "jh/loginFailure";
		
	}
	
	@ResponseBody
	@PostMapping(value = "joinAgree")
	public void joinAgree(@RequestBody Map<String, String> data, Model model, HttpSession session ) {
	    // data에는 클라이언트가 전송한 JSON 데이터가 Map으로 변환되어 들어옴
	    // 예: {"name": "John", "phone": "123-456-7890", "email": "john@example.com"}
	    String name = data.get("name");
	    String phone = data.get("phone");
	    String email = data.get("email");
	    
//	public void joinAgree(HttpServletRequest request, Model model) {
//		System.out.println("JhController joinAgree Start...");
//		
//		Object name = request.getAttribute("name");
//		Object phone = request.getAttribute("phone");
//		Object email = request.getAttribute("email");
		
		String title = null;
		String toEmail = null;
		String setFrom= null;
		
		
		//기존 사용자인지 비교 먼저하고 결과 반환하기 
		if(phone != null) {
			System.out.println("phone -> " + phone);
			model.addAttribute("phone",phone);
			
			
			
			
		} else if (email != null) {
			System.out.println("email -> " + email);
			toEmail = (String) email;
			title = "한국바둑기원 회원가입 인증번호 입니다.";
			
			try {
				//Mime 전자우편 Internet 표준 Format
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
				
				//messageHelper를 통해 메일정보 세팅
				messageHelper.setFrom(setFrom);		//보내는 사람 이메일
				messageHelper.setTo(toEmail);		//받는 사람 이메일
				messageHelper.setSubject(title);
				
				String tempPassword = (int) (Math.random() * 999999) + 1 + "";
				messageHelper.setText("인증번호 입니다 : " + tempPassword); //메일내용
				System.out.println("인증번호 입니다 : " +tempPassword);
				mailSender.send(message);
				session.setAttribute("check", 1); //정상 전달
				
			} catch (Exception e) {
				System.out.println("JhController joinAgree mailTransport e.getMessage() -> " + e.getMessage());
				session.setAttribute("check", 2); //메일 전달 실패
			}
			
		}
		
		session.setAttribute("name", name);
		
		
//		return "";
	}
	
}
