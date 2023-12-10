package com.choongang.gb2023501.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class GhController {
	
	/* 공지사항 리스트 */
	@RequestMapping(value = "boardNotieList")
	public String boardNotieList() {
		
		return "gh/boardNotieList";
	}
	
	/* Q&A 리스트 */
	@RequestMapping(value = "boardQnaList")
	public String boardQnaList() {
		
		return "gh/boardQnaList";
	}
	
	/* FAQ 리스트 */
	@RequestMapping(value = "boardFaqList")
	public String boardFaqList() {
		
		return "gh/boardFaqList";
	}
	
	/* 공지사항 컨텐츠 */
	@RequestMapping(value = "boardNotieContent")
	public String boardContent() {
		
		return "gh/boardNotieContent";
	}
	
	/* Q&A 컨텐츠 */
	@RequestMapping(value = "boardQnaContent")
	public String boardQnaContent() {
		
		return "gh/boardQnaContent";
	}
	
	/* FAQ 컨텐츠 */
	
	/* 글 작성 */
	@RequestMapping(value = "boardForm")
	public String boardForm() {
		
		return "gh/boardForm";
	}
	
}
