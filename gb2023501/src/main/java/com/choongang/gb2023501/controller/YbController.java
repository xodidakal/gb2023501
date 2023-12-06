package com.choongang.gb2023501.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class YbController {
	@RequestMapping(value = "/")
	public String main() {
		System.out.println("Main start...");
		return "main";
	}
	@RequestMapping(value = "eduResourceForm")
	public String eduResourceForm() {
		System.out.println("ybController eduResourceForm start...");
		return "yb/eduResourceForm";
	}
	@RequestMapping(value = "eduResourceList")
	public String eduResourceList() {
		System.out.println("ybController eduResourceList start...");
		return "yb/eduResourceList";
	}
}
