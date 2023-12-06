package com.choongang.gb2023501.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class GhController {
	
	@GetMapping(value = "/boardList")
	public String BoardList() {
		
		return "/gh/boardList";
	}
	
}
