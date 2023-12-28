package com.choongang.gb2023501.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.choongang.gb2023501.dhService.GameOrderService;
import com.choongang.gb2023501.dhutils.FileUploadDeleteUtil;
import com.choongang.gb2023501.domain.Member;
import com.choongang.gb2023501.gbService.Paging;
import com.choongang.gb2023501.jhService.MemberService;
import com.choongang.gb2023501.model.Game;
import com.choongang.gb2023501.model.GameOrder;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DhController {
	
	private final GameOrderService gos;
	private final MemberService ms;
	private final JhController jc;
	
	
	// 게임 콘텐츠 목록 조회
	@RequestMapping(value = "subscribe/gameOrderList")
	public String gameOrderList(Game game,String currentPage, Model model) {
		System.out.println("dhController gameOrderList() start..");
		int m_num = ms.selectMmNumById();
		game.setM_num(m_num);
		
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
		model.addAttribute("game", game);
		model.addAttribute("listGameOrder", listGameOrder);
		model.addAttribute("page", page);
		model.addAttribute("totalSearchGameOrder",totalSearchGameOrder);
		System.out.println("dhController gameOrderList() end..");
		
		return "dh/gameOrderList";
	}
	
	// 게임콘텐츠 구독 신청화면으로 이동
	@RequestMapping(value = "subscribe/gameOrderInsertResult")
	public String gameOrderInsertResult(@RequestParam(value="g_num") List<Integer> g_num, Model model) {
			
		System.out.println("dhController insertGameOrder start...");
			
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("g_num", g_num);
		
		List<Game> gamelist = gos.selectGameOrder(map);
		int gamesum = gos.gamesum(map);
		
		System.out.println("dhController insertGameOrder gamelist->"+gamelist.size());
		Member member = jc.aboutMember();
		String phone = phone_format(member.getPhone());
		System.out.println("phone->"+phone);
		
		model.addAttribute("gamelist", gamelist);
		model.addAttribute("member", member);
		model.addAttribute("gamesum", gamesum);
		model.addAttribute("phone",phone);
		
		
		return "dh/gameOrderForm";
	}
	
	public String phone_format(String number) {
	      String regEx = "(\\d{3})(\\d{3,4})(\\d{4})";
	      return number.replaceAll(regEx, "$1-$2-$3");
	}
		
	// 게임콘텐츠 구독신청
	@RequestMapping(value = "subscribe/gameOrderInsert")
	public String gameOrderInsert(@RequestParam(value="g_num") List<Integer> g_num,
									GameOrder gameOrder, Model model) {
		System.out.println("game");
		for (int i = 0; i < g_num.size(); i++) {
			System.out.println("g_num"+g_num.get(i));
		}
		int result = 0;
		
		int m_num = ms.selectMmNumById();
		gameOrder.setM_num(m_num);
		
		System.out.println("gameOrder"+gameOrder.getM_num());
		// 선택한 게임정보 가져오기 
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("g_num", g_num);
		List<Game> gamelist = gos.selectGameOrder(map1);
		// 게임 주문테이블에 map을 insert하기 위함
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gamelist", gamelist);
		map.put("gameOrder", gameOrder);
		
		try {
			System.out.println("dhController gameOrderInsertResult() start..");
			result = gos.insertGameOrder(map);
			System.out.println("dhController gameOrderInsertResult result -> "+result);
			
		} catch (Exception e) {
			System.out.println("dhController gameOrderInsertResult() ->"+e.getMessage());
		} finally {
			System.out.println("dhController gameOrderInsertResult() end..");
		}
		return "redirect:myGameOrderList?result="+result;
	}
		
	// 내구독 목록 조회
	@RequestMapping(value = "subscribe/myGameOrderList")
	public String myGameOrderList(Game game,String currentPage, Model model) {
		System.out.println("dhController mygameOrderList() start..");
		
		int m_num = ms.selectMmNumById();
		game.setM_num(m_num);
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
	public String gameInsertResult(Game game, MultipartFile file, Model model) {
		int m_num = ms.selectMmNumById();
		game.setM_num(m_num);
		
		String g_attach_name = null;
		String g_attach_path = null;
		
		FileUploadDeleteUtil fileUpload = new FileUploadDeleteUtil();
		
		try {
			System.out.println("gameupload File Start!!");
			String[] uploadResult = fileUpload.uploadFile(file);
			g_attach_name = uploadResult[0];
			g_attach_path = uploadResult[1];
			System.out.println("gameupload g_attach_name : {}"+ g_attach_name);
			System.out.println("gameupload g_attach_path : {}"+ g_attach_path);

		} catch (Exception e) {
			System.out.println("gameupload File upload error : {}" + e.getMessage());
		} finally {
			System.out.println("gameupload integratedboardInsert File End..");
		}
		
		game.setG_attach_name(g_attach_path+g_attach_name);
		game.setG_attach_path(g_attach_path);
		
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
	
	// 게임콘텐츠 수정
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
	public String gameUpdateResult(Game game, int g_num, MultipartFile file, Model model) {
		String g_attach_name = null;
		String g_attach_path = null;
		FileUploadDeleteUtil fileUploadDeleteUtil = new FileUploadDeleteUtil();
		int realName = file.getOriginalFilename().length();
		
		try {
			System.out.println("gameupload realName : {}"+ realName);
			
			// DB에 저장 된 파일명 조회
			Game deleteImageNameFind = gos.gameRead(game.getG_num());
			System.out.println("gameupload getG_title filePart1 : {}"+ game.getG_title());
			// DB에 저장 된 파일명 가져오기
			g_attach_name = deleteImageNameFind.getG_attach_name();
			
			// 파일 값이 있으면 저장
			if (realName > 0) {
				System.out.println("gameupload File Start!!");
				
				String[] uploadResult = fileUploadDeleteUtil.uploadFile(file);
				g_attach_name = uploadResult[0];
				g_attach_path = uploadResult[1];
				System.out.println("gameupload getG_title filePart3 : {}"+ game.getG_title());

			} else {
				System.out.println("gameupload File errer : {}"+"저장 할 파일이 없습니다.");
				System.out.println("gameupload getG_title filePart4 : {}"+ game.getG_title());
			}


			} catch (Exception e) {
				System.out.println("gameupload File upload error : {}" + e.getMessage());
			} finally {
				System.out.println("gameupload gameupload File End..");
			}
		
			game.setG_attach_name("..\\photos\\"+g_attach_name);
			game.setG_attach_path(g_attach_path);
		
		int updateGame1  = 0;
		try {
			System.out.println("dhController gameUpdateResult() start..");
			
				updateGame1 = gos.updateGame(game);
				
		} catch (Exception e) {
			System.out.println("dhController gameUpdateResult() ->"+e.getMessage());
		} finally {
			System.out.println("dhController gameUpdateResult() end..");
		}
		if (updateGame1 > 0) {
			return "redirect:gameList";
		} else {
			model.addAttribute("g_num", game.getG_num());
			model.addAttribute("m_num", game.getM_num());
			model.addAttribute("msg", "등록에 실패하였습니다.");
			return "forward:gameUpdate";
		}
	}
	
}