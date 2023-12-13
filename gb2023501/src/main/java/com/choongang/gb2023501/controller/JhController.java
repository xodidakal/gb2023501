package com.choongang.gb2023501.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public String login() {
		String mmId = ms.getLoggedInId();
		log.info("getLoggedInId:{}", mmId);
		return "jh/loginForm";
	}
	
	//회원약관 동의 페이지
	@RequestMapping(value = "info/joinAgreeForm")
	public String joinAgreeForm() {
		
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
		
		return "jh/memberList";
	}
	//회원 목록 관리 페이지
	@RequestMapping(value = "loginFailure")
	public String loginFailure() {
		
		return "jh/loginFailure";
	}
	
	
}
