package com.choongang.gb2023501.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.choongang.gb2023501.domain.Member;
import com.choongang.gb2023501.jhService.MemberService;
import com.choongang.gb2023501.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j 
@RequiredArgsConstructor
public class JhController {

	private final MemberRepository mr;
	private final MemberService ms;
	
	@RequestMapping(value = "info/loginForm")
	public String loginForm() {
		return "jh/loginForm";
	}
	
	//회원약관 동의 페이지
	@RequestMapping(value = "info/joinAgreeForm")
	public String joinAgreeForm(Model model) {
		
		
		
		//로그인 된 아이디 가져오기
		String mmId = ms.getLoggedInId();
		//로그로 아이디 확인
		log.info("getLoggedInId:{}", mmId);
		
		
		
		//로그인된 회원번호 가져오기 / 로그인 안된 경우 0
		int mmNum = ms.selectMmNumById();
		System.out.println("회원번호 int " + mmNum);
		
		
		
		//로그인 된 회원정보 전체 가져오기 
		Optional<Member> memberOptional = ms.selectUserById();
		if(memberOptional.isPresent()) {
			Member member = memberOptional.get();
			System.out.println("회원 이름" + member.getMmName());
			System.out.println("회원 번호" + member.getMmNum());
			model.addAttribute("member",member);
		}
		
		return "jh/joinAgreeForm";
	}
	
	//회원약관 동의 후?
	@PostMapping(value = "joinAgree")
	@ResponseBody
	public String joinAgree() {
		
		return "jh/joinAgree";
	}
	
	//약관동의 후 회원 가입 정보 입력 페이지
	@RequestMapping(value = "joinForm")
	public String joinForm() {
		
		return "jh/joinForm";
	}
	
	//회원 목록 관리 페이지
	@RequestMapping(value = "operate/memberList")
	public String memberList() {
		//테스트 삼아 찍어본 것
//		int mmNum = ms.selectMmNumById();
//		System.out.println("회원번호 int " + mmNum);
//		String mmId = ms.getLoggedInId();
//		log.info("getLoggedInId:{}", mmId);
//		Optional<Member> memberOptional = ms.selectUserById();
//		if(memberOptional.isPresent()) {
//			Member member = memberOptional.get();
//			System.out.println("회원 이름" + member.getMmName());
//			System.out.println("회원 번호" + member.getMmNum());
//		}
		return "jh/memberList";
	}
	
	
	//로그인 실패시 갈 페이지 -> 페이지 내용 수정 필요
	@RequestMapping(value = "loginFailure")
	public String loginFailure() {
		
		return "jh/loginFailure";
	}
	
	
}
