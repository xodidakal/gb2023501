package com.choongang.gb2023501.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.choongang.gb2023501.ghSerivce.BoardService;
import com.choongang.gb2023501.ghSerivce.Paging;
import com.choongang.gb2023501.model.Board;
import com.choongang.gb2023501.model.BoardComment;
import com.choongang.gb2023501.model.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class GhController {
	
	private final BoardService boardService;
	
	/* 게시판 리스트 */
	@RequestMapping(value = "customer/boardList")
	public String boardList(Model model, String b_category, Board board, BoardComment boardComment ,@RequestParam(defaultValue = "1") String currentPage) {
		System.out.println("GhController boardList Start...");
//		System.out.println("b_category->"+b_category); 
//		System.out.println("board.getB_category();->"+board.getB_category());
//		System.out.println("rowPage->"+rowPage);
		
		// 게시물 count
		int bdCount = boardService.selectBoardListCnt(b_category);
		System.out.println("GhController selectBoardListCnt bdCount->"+bdCount);
		
		// 페이징 작업
		Paging page = new Paging(bdCount, currentPage);
		board.setStart(page.getStart());
		board.setEnd(page.getEnd());
		model.addAttribute("page", page);
		
		// 게시판 list + 댓글 숫자
		// -----------------------------------------------------
		List<Board> list = boardService.selectBoardList(board);
		// -----------------------------------------------------
		System.out.println("GhController selectBoardList list.size->"+list.size());
		model.addAttribute("Boardlist", list);
		// 게시판 카테고리
		model.addAttribute("BoardCategory", b_category);
		// 게시판 카테고리 별 count
		model.addAttribute("BoardCount", bdCount);
		
		model.addAttribute("StartRow",page.getStart());
		// 댓글 count
//		int comtCount = boardService.selectBdCommentListCnt(boardComment);
//		System.out.println("GhController comtCount->"+comtCount);
//		model.addAttribute("comtCount", comtCount);
		
		return "gh/boardList";
	}
	
	/* 게시물 상세 */
	@RequestMapping(value = "customer/boardDetail")
	public String boardDetail(Model model, int b_num) {
		System.out.println("GhController boardDetail Start...");
		System.out.println("board.getB_num()->"+b_num);
		
		// 게시물 상세
		// -----------------------------------------------------
		Board boardDetail = boardService.selectBoard(b_num);
		// -----------------------------------------------------
		model.addAttribute("BdDetail",boardDetail);
		
		// 조회수 증가
		// -----------------------------------------------------
		int boardCount = boardService.updateBoardCnt(b_num);
		// -----------------------------------------------------
		System.out.println("boardCount->"+boardCount);
		
		// 댓글 조회
		List<BoardComment> commentList = boardService.selectCommentList(b_num);
		model.addAttribute("commentList",commentList);
		commentList.size();
		
		return "gh/boardDetail";
	}
	
	/* 댓글 목록 조회 */
//	@RequestMapping(value = "selectBdCommentList")
//	public String commentList(Model model, int b_num) {
//		System.out.println("GhController commentList Start...");
//		System.out.println("board.getB_num()->"+b_num);
//		
//		// -----------------------------------------------------
//		Board boardDetail = boardService.selectComment(b_num);
//		// -----------------------------------------------------
//		model.addAttribute("BdDetail",boardDetail);
//		
//		return "gh/boardDetail";
//	}
	
	
	/* 글 작성 폼 */
	@RequestMapping(value = "customer/boardForm")
	public String boardForm() {
		System.out.println("GhController boardForm Start...");
		
		return "gh/boardForm";
	}
	
	/* 글 작성 */
	@PostMapping(value = "customer/insertBoard")
	public String insertBoard(Model model, Board board, 
							  @RequestParam(value = "file1", required = false) 
							  MultipartFile file1, HttpServletRequest request
							  ) throws IOException {
		System.out.println("GhController insertBoard Start...");
		
		// file Upload
		String attach_path = "upload/gh"; // 실제 파일이 저장되는 폴더명, uploadPath의 이름과 동일하게 해야 오류 X
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload/gh"); // 저장 위치 지정 
		
		System.out.println("GhController File Upload Post Start"); 
		
		log.info("originalName : " + file1.getOriginalFilename());		// 원본 파일명
		log.info("size : "         + file1.getSize());					// 파일 사이즈
		log.info("contextType : "  + file1.getContentType());			// 파일 타입
		log.info("uploadPath : "   + uploadPath);						// 파일 저장되는 주소
		
		String saveName = uploadFile(file1.getOriginalFilename(), file1.getBytes(), uploadPath);  // 저장되는 파일명 
		log.info("saveName: " + saveName);
		
		if(!file1.isEmpty()) {
			log.info("파일o");
			board.setB_attach_name(file1.getOriginalFilename());
			board.setB_attach_path(attach_path);
		}
		
		// 입력값 DB에 Insert
		// -----------------------------------------------------
		int insertForm = boardService.insertBoard(board);
		// -----------------------------------------------------
		
		return "redirect:/customer/boardList?b_category="+board.getB_category();
	}
	
	// file upload method
	private String uploadFile(String originalName, byte[] bytes, String uploadPath) throws IOException {
		// universally unique identifier (UUID)
		UUID uid = UUID.randomUUID();
		System.out.println("uploadPath-> " + uploadPath);
		
		// 신규 폴더(Directory) 생성
		File fileDirectory = new File(uploadPath);
		if(!fileDirectory.exists()) {
			fileDirectory.mkdirs();
			System.out.println("업로드용 폴더 생성 : " + uploadPath);
		}
		
		String savedName = uid.toString() + "_" + originalName;
		System.out.println("savedName: " + savedName);
		File target = new File(uploadPath, savedName);
		FileCopyUtils.copy(bytes, target);  
		
		return savedName;
	}

	/* 게시판 수정 폼*/
	@RequestMapping(value = "customer/boardUpdate")
	public String boardUpdate(Model model, Board board, int b_num) {
		System.out.println("GhController boardUpdate Start...");
		// 게시물 상세
		// -----------------------------------------------------
		Board boardDetail = boardService.selectBoard(b_num);
		// -----------------------------------------------------
		model.addAttribute("BdDetail",boardDetail);
		

		return "gh/boardUpdate";
	}
	
	/* 게시판 수정  */
	@RequestMapping(value = "customer/updateBoard")
	public String updateBoard() {
		System.out.println("GhController updateBoard Start...");

//		return "redirect:/customer/boardList?b_category="+board.getB_category();
		return "gh/boardUpdate";
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
