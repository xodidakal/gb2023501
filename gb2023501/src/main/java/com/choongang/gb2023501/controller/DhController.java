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
	
	@RequestMapping(value = "subscribe/gameOrderList")
	public String gameOrderList(GameOrder gameOrder,String currentPage, Model model) {
		System.out.println("dhController gameOrderList() start..");
		
		int totalSearchGameOrder = gos.totalSearchGameOrder(gameOrder);
		
		Paging page = new Paging(totalSearchGameOrder, currentPage);
		gameOrder.setStart(page.getStartPage());
		gameOrder.setEnd(page.getEndPage());
		
		List<GameOrder> listGameOrder = gos.listGameOrder(gameOrder);
		
		model.addAttribute("listGameOrder", listGameOrder);
		model.addAttribute("page", page);
		model.addAttribute("totalSearchGameOrder",totalSearchGameOrder);
		System.out.println("dhController gameOrderList() end..");
		
		return "dh/gameOrderList";
	}
}
	
 