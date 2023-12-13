package com.choongang.gb2023501.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.choongang.gb2023501.dhService.GameOrderService;
import com.choongang.gb2023501.gbService.Paging;
import com.choongang.gb2023501.model.Game;
import com.choongang.gb2023501.model.GameOrder;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DhController {
	
	private final GameOrderService gos;
	
	// 게임 콘텐츠 목록 조회
	@RequestMapping(value = "subscribe/gameOrderList")
	public String gameOrderList(Game game,String currentPage, Model model) {
		System.out.println("dhController gameOrderList() start..");
		// 게임 콘텐츠 총 개수
		int totalSearchGameOrder = gos.totalSearchGameOrder(game);
		// 키워드 검색
		String keyword = game.getKeyword();
		System.out.println("totalSearchGameOrder -> "+totalSearchGameOrder);
		
		Paging page = new Paging(totalSearchGameOrder, currentPage, 10);
		game.setStart(page.getStartRow());
		game.setEnd(page.getEndRow());
		
		List<Game> listGameOrder = gos.listGameOrder(game);
		
		model.addAttribute("keyword", keyword);
		model.addAttribute("listGameOrder", listGameOrder);
		model.addAttribute("page", page);
		model.addAttribute("totalSearchGameOrder",totalSearchGameOrder);
		System.out.println("dhController gameOrderList() end..");
		
		return "dh/gameOrderList";
	}
	
	// 내구독 목록 조회
	@RequestMapping(value = "subscribe/myGameOrderList")
	public String myGameOrderList(Game game,String currentPage, Model model) {
		System.out.println("dhController mygameOrderList() start..");
		// 구독 콘텐츠 총 개수
		int totalSearchGameOrder = gos.totalSearchGameOrder2(game);
		// 키워드 검색
		String keyword = game.getKeyword();
		System.out.println("totalSearchGameOrder2 -> "+totalSearchGameOrder);
			
		Paging page = new Paging(totalSearchGameOrder, currentPage, 10);
		game.setStart(page.getStartRow());
		game.setEnd(page.getEndRow());
			
		List<Game> listGameOrder = gos.listGameOrder2(game);
			
		model.addAttribute("keyword", keyword);
		model.addAttribute("listGameOrder", listGameOrder);
		model.addAttribute("page", page);
		model.addAttribute("totalSearchGameOrder",totalSearchGameOrder);
		
		System.out.println("dhController mygameOrderList() end..");
			
		return "dh/myGameOrderList";

	}
}
 