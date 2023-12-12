package com.choongang.gb2023501.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.choongang.gb2023501.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class JhController {

	private final MemberRepository mr;
	@RequestMapping(value = "loginForm")
	public String login() {
		
		return "jh/loginForm";
	}
	
	@RequestMapping(value = "joinAgreeForm")
	public String joinAgreeForm() {
		
		return "jh/joinAgreeForm";
	}
	
	@PostMapping(value = "joinAgree")
	@ResponseBody
	public String joinAgree() {
		
		return "jh/joinAgree";
	}
	
	@RequestMapping(value = "joinForm")
	public String joinForm() {
		
		return "jh/joinForm";
	}
	
	
}
