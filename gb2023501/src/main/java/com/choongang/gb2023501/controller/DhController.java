package com.choongang.gb2023501.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.choongang.gb2023501.dhService.GameOrderService;
import com.choongang.gb2023501.gbService.Paging;
import com.choongang.gb2023501.model.Game;

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
	
	// 게임콘텐츠 관리
	@RequestMapping(value = "operate/gameList")
	public String gameList(Game game,String currentPage, Model model) {
		System.out.println("dhController gameList() start..");
		// 게임콘텐츠 총 개수
		int totalSearchGame = gos.totalSearchGame(game);
		// 키워드 검색
		String keyword = game.getKeyword();
		System.out.println("totalSearchGame -> "+totalSearchGame);
					
		Paging page = new Paging(totalSearchGame, currentPage, 10);
		game.setStart(page.getStartRow());
		game.setEnd(page.getEndRow());
						
		List<Game> listGame = gos.listGame(game);
						
		model.addAttribute("keyword", keyword);
		model.addAttribute("listGame", listGame);
		model.addAttribute("page", page);
		model.addAttribute("totalSearchGame",totalSearchGame);
					
		System.out.println("dhController gameList() end..");
						
		return "dh/gameList";

	}
	// 게임콘텐츠 등록
	@RequestMapping(value = "operate/gameInsertResult")
	public String gameInsertResult(Game game, Model model) {
		int result = 0;
		try {
			System.out.println("dhController gameInsertResult() start..");
			result = gos.insertGame(game);
			
		} catch (Exception e) {
			System.out.println("dhController gameInsertResult() ->"+e.getMessage());
		} finally {
			System.out.println("dhController gameInsertResult() end..");
		}
			if (result > 0) {
				return "redirect:gameList";
			} else {
				model.addAttribute("msg", "등록에 실패하였습니다.");
				return "forward:gameInsertForm";
			}
		}
	
	@RequestMapping(value = "operate/gameInsertForm")
	public String gameinsertForm(Model model) {
		try {
			System.out.println("dhController gameinsertForm() start..");
		} catch (Exception e) {
			System.out.println("dhController gameinsertForm() ->"+e.getMessage());
		} finally {
			System.out.println("dhController gameinsertForm() end..");
		}
		return "dh/gameForm";
	}
	
	@RequestMapping(value = "operate/gameUpdate")
	public String spotUpdate(int g_num, int m_num, Model model) {
		try {
			System.out.println("dhController gameUpdate() start..");
			Game game = gos.selectGame(g_num, m_num);
			model.addAttribute("game", game);
		} catch (Exception e) {
			System.out.println("dhController gameUpdate() ->"+e.getMessage());
		} finally {
			System.out.println("dhController gameUpdate() end..");
		}
			return "dh/gameUpdate";
	}
	
	@RequestMapping(value = "operate/gameUpdateResult")
	public String gameUpdateResult(Game game, Model model) {
		int result = 0;
		try {
			System.out.println("dhController gameUpdateResult() start..");
			result = gos.updateGame(game);
		} catch (Exception e) {
			System.out.println("dhController gameUpdateResult() ->"+e.getMessage());
		} finally {
			System.out.println("dhController gameUpdateResult() end..");
		}
		if (result > 0) {
			return "redirect:gameList";
		} else {
			model.addAttribute("g_num", game.getG_num());
			model.addAttribute("m_num", game.getM_num());
			model.addAttribute("msg", "등록에 실패하였습니다.");
			return "forward:gameUpdate";
		}
	}
	
}
 