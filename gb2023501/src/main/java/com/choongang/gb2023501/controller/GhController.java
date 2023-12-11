package com.choongang.gb2023501.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.choongang.gb2023501.ghSerivce.BoardService;
import com.choongang.gb2023501.model.Board;
import com.choongang.gb2023501.model.BoardComment;
import com.choongang.gb2023501.model.Paging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class GhController {
	
	private final BoardService boardService;
	
	/* 게시판 리스트 */
	@RequestMapping(value = "boardList")
	public String boardList(Model model, String b_category, Board board, BoardComment boardComment ,@RequestParam(defaultValue = "1") String currentPage) {
		System.out.println("GhController boardList Start...");
//		System.out.println("b_category->"+b_category); 
//		System.out.println("board.getB_category();->"+board.getB_category());

//		System.out.println("rowPage->"+rowPage);
		
		// 게시물 count
		int bdCount = boardService.boardCount(b_category);
		System.out.println("GhController boardCount bdCount->"+bdCount);
		
		// 페이징 작업
		Paging page = new Paging(bdCount, currentPage);
		board.setStart(page.getStart());
		board.setEnd(page.getEnd());
		model.addAttribute("page", page);
		
		// 게시판 list
		// -----------------------------------------------------
		List<Board> list = boardService.selectBoardList(board);
		// -----------------------------------------------------
		System.out.println("GhController boardList list.size->"+list.size());
		model.addAttribute("Boardlist", list);
		model.addAttribute("BoardCategory", b_category);
		// 게시판 카테고리 별 count
		model.addAttribute("BoardCount", bdCount);
		
		// 검색
		System.out.println("board.getB_num()->"+board.getB_num());
		System.out.println("boardComment.getB_num()->"+boardComment.getB_num());
		
		// 댓글 count
		int comtCount = boardService.selectBdCommentListCnt(boardComment);
		System.out.println("GhController comtCount->"+comtCount);
		model.addAttribute("comtCount", comtCount);
		
		return "gh/boardList";
	}
	
	/* 게시물 상세 */
	@RequestMapping(value = "boardDetail")
	public String boardDetail(Model model, int b_num) {
		System.out.println("GhController boardDetail Start...");
		System.out.println("board.getB_num()->"+b_num);
		
		// 게시물 상세
		// -----------------------------------------------------
		Board boardDetail = boardService.selectBdDetail(b_num);
		// -----------------------------------------------------
		model.addAttribute("BdDetail",boardDetail);
		
		// 조회수 증가
		// -----------------------------------------------------
		int boardCount = boardService.updateBdCount(b_num);
		// -----------------------------------------------------
		System.out.println("boardCount->"+boardCount);
		
		// 댓글 조회
		List<BoardComment> commentList = boardService.selectCommentList(b_num);
		model.addAttribute("commentList",commentList);
		
		return "gh/boardDetail";
	}
	
	/* 댓글 목록 조회 */
	@RequestMapping(value = "selectBdCommentList")
	public String commentList(Model model, int b_num) {
		System.out.println("GhController commentList Start...");
		System.out.println("board.getB_num()->"+b_num);
		
		// -----------------------------------------------------
		Board boardDetail = boardService.selectBdDetail(b_num);
		// -----------------------------------------------------
		model.addAttribute("BdDetail",boardDetail);
		
		// 댓글 Count
		
		return "gh/boardDetail";
	}
	
	
	/* 글 작성 */
	@RequestMapping(value = "boardForm")
	public String boardForm() {
		System.out.println("GhController boardList Start...");

		return "gh/boardForm";
	}
	
	
//	/* 게시판 카운트 */
//	@RequestMapping(value = "boardList")
//	public String boardCount(Model model, String b_category) {
//		System.out.println("GhController boardCount Start...");
//		int result = boardService.boardCount(b_category);
//		System.out.println("GhController boardCount result->"+result);
//		
//		model.addAttribute("BoardCount", result);
//		
//		return "gh/boardList";
//	}
	
	
//	/* 공지사항 리스트 */
//	@RequestMapping(value = "boardNotieList")
//	public String boardNotieList() {
//		
//		return "gh/boardNotieList";
//	}
//	
//	/* Q&A 리스트 */
//	@RequestMapping(value = "boardQnaList")
//	public String boardQnaList() {
//		
//		return "gh/boardQnaList";
//	}
//	
//	/* FAQ 리스트 */
//	@RequestMapping(value = "boardFaqList")
//	public String boardFaqList() {
//		
//		return "gh/boardFaqList";
//	}
//	
//	/* 공지사항 컨텐츠 */
//	@RequestMapping(value = "boardNotieContent")
//	public String boardContent() {
//		
//		return "gh/boardNotieContent";
//	}
//	
//	/* Q&A 컨텐츠 */
//	@RequestMapping(value = "boardQnaContent")
//	public String boardQnaContent() {
//		
//		return "gh/boardQnaContent";
//	}
//	
//	/* FAQ 컨텐츠 */
//	
//	/* 글 작성 */
//	@RequestMapping(value = "boardForm")
//	public String boardForm() {
//		
//		return "gh/boardForm";
//	}
	
}
