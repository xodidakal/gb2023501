package com.choongang.gb2023501.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class JhController {

	@RequestMapping(value = "login")
	public String login() {
		
		return "jh/login";
	}
}
