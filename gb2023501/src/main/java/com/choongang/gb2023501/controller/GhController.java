package com.choongang.gb2023501.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.choongang.gb2023501.domain.Member;
import com.choongang.gb2023501.ghSerivce.BoardService;
import com.choongang.gb2023501.ghSerivce.Paging;
import com.choongang.gb2023501.jhRepository.MemberRepository;
import com.choongang.gb2023501.jhService.MemberService;
import com.choongang.gb2023501.model.Board;
import com.choongang.gb2023501.model.BoardComment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class GhController {
	
	private final BoardService boardService;
	private final MemberService ms;
	
	public Member aboutMember() {
		Optional<Member> memberOptional = ms.selectUserById();
		Member member = null;
		if(memberOptional.isPresent()) {
			member = memberOptional.get();
			System.out.println("로그인 회원 정보 -> " + member);
			System.out.println("member name -> " + member.getMmName());
		}
		return member;
	}
	
	/* 게시판 리스트 */
	@RequestMapping(value = "customer/boardList")
	public String boardList(Model model, String b_category, 	Board board 
							,@RequestParam(defaultValue = "1") 	String currentPage
							,@RequestParam(defaultValue = "10") int rowPage
							,String search_type, String search_keyword
							,BoardComment boardComment) {
		System.out.println("GhController boardList Start...");
//		System.out.println("b_category->"+b_category); 
//		System.out.println("board.getB_category();->"+board.getB_category());
//		System.out.println("rowPage->"+rowPage);
		System.out.println("search_type->"+search_type);
		System.out.println("search_keyword->"+search_keyword);
		System.out.println("rowPage->"+rowPage);
		
		// 회원정보
		Member member = aboutMember();
		model.addAttribute("member", member);
		
		// 게시물 count
		// -----------------------------------------------------
		int bdCount = boardService.selectBoardListCnt(board);
		// -----------------------------------------------------
		System.out.println("GhController selectBoardListCnt bdCount->"+bdCount);
		
		// 페이징 작업
		// bdCount를 list.size로 바꿀방법?
		Paging page = new Paging(bdCount, currentPage, rowPage);
		board.setStart(page.getStart());
		board.setEnd(page.getEnd());
		model.addAttribute("page", page);
		
		// 검색작업
		board.setSearch_type(search_type);
		board.setSearch_keyword(search_keyword);
		
		// 답변 여부 (1이면 답변 없음 / 2면 답변 있음)
		// -----------------------------------------------------
		int answerCheck = boardService.selectAnswerCnt(boardComment);
		// -----------------------------------------------------
		model.addAttribute("answerCheck", answerCheck);
		
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
		// 게시판 b_num 정렬
		model.addAttribute("StartRow",page.getStart());
		// 게시판 숫자표시
		model.addAttribute("rowPage", rowPage);
		// 검색분류 + 검색어 보냄
		model.addAttribute("search_type", search_type);
		model.addAttribute("search_keyword", search_keyword);
		
		// 댓글 count
//		int comtCount = boardService.selectBdCommentListCnt(boardComment);
//		System.out.println("GhController comtCount->"+comtCount);
//		model.addAttribute("comtCount", comtCount);
		
		return "gh/boardList";
	}
	
	/* 게시물 상세 */
	@RequestMapping(value = "customer/boardDetail")
	public String boardDetail(Model model, int b_num, BoardComment boardComment) {
		System.out.println("GhController boardDetail Start...");
		System.out.println("board.getB_num()->"+b_num);
		
		// 회원정보
		Member member = aboutMember();
		model.addAttribute("member", member);
		
		// 게시물 상세(board, memeber 모든정보)
		// -----------------------------------------------------
		Board BdDetail = boardService.selectBoard(b_num);
		// -----------------------------------------------------
		model.addAttribute("BdDetail", BdDetail);
		
		// 답글 눌렀을 때 원글 게시물 상세(board 모든정보)
		// -----------------------------------------------------
		Board BdOriDetail = boardService.selectOriBoard(b_num);
		// -----------------------------------------------------
		model.addAttribute("BdOriDetail", BdOriDetail);
		
		// 조회수 증가
		// -----------------------------------------------------
		int boardCount = boardService.updateBoardCnt(b_num);
		// -----------------------------------------------------
		System.out.println("boardCount->"+boardCount);
		
		// 답변 여부 (1이면 답변 없음 / 2면 답변 있음)
		// -----------------------------------------------------
		int answerCheck = boardService.selectAnswerCnt(boardComment);
		// -----------------------------------------------------
		model.addAttribute("answerCheck", answerCheck);
		
		return "gh/boardDetail";
	}
	
	/* 글 작성 폼 */
	@RequestMapping(value = "customer/boardForm")
	public String boardForm(Model model, String b_category) {
		System.out.println("GhController boardForm Start...");
		
		// 회원정보
		Member member = aboutMember();
		model.addAttribute("member", member);
		
		// 글 작성 할때 목록 -> 카테고리 목록으로 가기
		model.addAttribute("BoardCategory", b_category);
		
		return "gh/boardForm";
	}
	
	/* 글 작성 */
	@PostMapping(value = "customer/insertBoard")
	public String insertBoard(Model model, Board board, 
							  @RequestParam(value = "file1", required = false) 
							  MultipartFile file1, HttpServletRequest request, String b_regi_date
							  ) throws IOException {
		System.out.println("GhController insertBoard Start...");
		System.out.println("GhController insertBoard b_regi_date->"+b_regi_date);
		System.out.println("GhController insertBoard board.getB_flag()->"+board.getB_flag());
		
		if(!file1.isEmpty()) {
			// file Upload
			log.info("파일o");
			String attach_path = "upload/gh"; // 실제 파일이 저장되는 폴더명, uploadPath의 이름과 동일하게 해야 오류 X
			String uploadPath = request.getSession().getServletContext().getRealPath("/upload/gh"); // 저장 위치 지정 
			
			System.out.println("GhController File Upload Post Start"); 
			
			log.info("originalName : " + file1.getOriginalFilename());		// 원본 파일명
			log.info("size : "         + file1.getSize());					// 파일 사이즈
			log.info("contextType : "  + file1.getContentType());			// 파일 타입
			log.info("uploadPath : "   + uploadPath);						// 파일 저장되는 주소
			
			String saveName = uploadFile(file1.getOriginalFilename(), file1.getBytes(), uploadPath);  // 저장되는 파일명 
			log.info("saveName: " + saveName);
			
			board.setB_attach_name(saveName);	
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

		// 회원정보
		Member member = aboutMember();
		model.addAttribute("member", member);
		
		// 수정 정보 불러오기
		// -----------------------------------------------------
		Board BdDetail = boardService.selectBoard(b_num);
		// -----------------------------------------------------
		model.addAttribute("BdDetail",BdDetail);
		
		
		return "gh/boardUpdate";
	}
	
	/* 게시판 수정  */
	@RequestMapping(value = "customer/updateBoard")
	public String updateBoard(Board board, @RequestParam(value = "file1", required = false) 
	  						  MultipartFile file1, HttpServletRequest request) throws IOException {
		System.out.println("GhController updateBoard Start...");
		System.out.println("GhController updateBoard b_title->"+board.getB_title());
		System.out.println("GhController updateBoard board.getB_num()->"+board.getB_num());
		System.out.println("GhController updateBoard board.getB_category()->"+board.getB_category());
		
		if(!file1.isEmpty()) {
			// file Upload
			log.info("파일o");
			String attach_path = "upload/gh"; // 실제 파일이 저장되는 폴더명, uploadPath의 이름과 동일하게 해야 오류 X
			String uploadPath = request.getSession().getServletContext().getRealPath("/upload/gh"); // 저장 위치 지정 
			
			System.out.println("GhController File Upload Post Start"); 
			
			log.info("originalName : " + file1.getOriginalFilename());		// 원본 파일명
			log.info("size : "         + file1.getSize());					// 파일 사이즈
			log.info("contextType : "  + file1.getContentType());			// 파일 타입
			log.info("uploadPath : "   + uploadPath);						// 파일 저장되는 주소
			
			String saveName = uploadFile(file1.getOriginalFilename(), file1.getBytes(), uploadPath);  // 저장되는 파일명 
			log.info("saveName: " + saveName);
			
			board.setB_attach_name(saveName);	
			board.setB_attach_path(attach_path);
		}

		// 정보 업데이트
		// -----------------------------------------------------
		int BdUpdate = boardService.updateBoard(board);
		// -----------------------------------------------------
		if(BdUpdate < 0) {
			System.out.println("수정실패");
		}
		System.out.println("수정성공 BdUpdate->"+BdUpdate);
		
		return "redirect:/customer/boardDetail?b_num="+board.getB_num();
	}
	
	/* 파일 삭제 */
	@RequestMapping(value = "/customer/deleteFile", method = RequestMethod.POST)
	@ResponseBody
	public String deleteFile(@RequestBody Board board) {
		System.out.println("GhController deleteFile Start...");
		String resultStatus = null;
		
		System.out.println("board.getB_num()->"+board.getB_num());
		
		board.setB_attach_name(null);
		board.setB_attach_path(null);

		// 파일 삭제
		// -----------------------------------------------------
		int deleteFile = boardService.deleteFile(board);
		// -----------------------------------------------------

		if(deleteFile > 0) {
			System.out.println("삭제 성공");
			resultStatus = "success";
		} else {
			System.out.println("삭제 실패");
			resultStatus = "fail";
		}
		
		return resultStatus;
	}
	
	/* 게시글 삭제 */
	@RequestMapping(value = "customer/boardDelete")
	public String deleteBoard(Board board, BoardComment boardComment, HttpServletResponse response) throws IOException {
		System.out.println("GhController deleteBoard Start...");
		System.out.println("GhController deleteBoard boardComment.getBc_num()->"+boardComment.getBc_num());
		System.out.println("GhController deleteBoard boardComment.getB_num()->"+boardComment.getB_num());
		
		// 답글 여부 확인
		int answerCheck = boardService.selectAnswerCnt(boardComment);
		
		if(answerCheck > 1) {
			System.out.println("답글 있음 -> 삭제불가");
//			model.addAttribute("msg", "삭제가 불가능 합니다. 답변글이 존재합니다.");
//    		model.addAttribute("url", "/customer/boardDetail?b_num="+board.getB_num());
//    		return "alert";
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제가 불가능합니다. 답변글이 존재합니다.');");
			out.println("window.location.href='/customer/boardDetail?b_num=" + board.getB_num() + "';");
			out.println("</script>");
			out.close();
		} else {
			System.out.println("답글 없음 -> 바로 삭제");
			// 게시글 삭제
			// -----------------------------------------------------
			int BdDelete = boardService.deleteBoard(boardComment);
			// -----------------------------------------------------
			if(BdDelete < 0) {
				System.out.println("삭제 실패");
			}
			System.out.println("삭제 성공");
		}
		return "redirect:/customer/boardList?b_category="+board.getB_category();
	}
	
	/* 게시글 답변 삭제용 */
	@RequestMapping(value = "customer/boardAnswerDelete")
	public String deleteAnswerBoard(Board board, BoardComment boardComment) {
		System.out.println("GhController deleteAnswerBoard Start...");
		
		// 게시글 삭제
		// -----------------------------------------------------
		int BdDelete = boardService.deleteBoard(boardComment);
		// -----------------------------------------------------
		if(BdDelete < 0) {
			System.out.println("삭제 실패");
		}
		System.out.println("삭제 성공");
		
		return "redirect:/customer/boardList?b_category="+board.getB_category();
	}
	
	/* 댓글 목록 조회 */
	@ResponseBody
	@RequestMapping(value = "/customer/selectCommentList", method = RequestMethod.POST)
	public List<BoardComment> selectCommentList(Model model, @RequestBody BoardComment boardComment) {
		System.out.println("GhController commentList Start...");
		System.out.println("selectCommentList board.getB_num()->"+boardComment.getB_num());
		System.out.println("boardComment.getBc_regi_date();->"+boardComment.getBc_regi_date());
		
		// 댓글 조회
		// -----------------------------------------------------
		List<BoardComment> commentList = boardService.selectCommentList(boardComment.getB_num());
		// -----------------------------------------------------
		
		return commentList;
	}
	
	/* 댓글작성  */
	@RequestMapping(value = "/customer/insertComment", method = RequestMethod.POST)
	@ResponseBody
	public String insertComment(@RequestBody BoardComment boardComment, Model model) {
		System.out.println("GhController insertComment Start...");

		String resultStatus = null;
		
		System.out.println("insertComment getB_num->"+boardComment.getB_num());
		System.out.println("insertComment getBc_content->"+boardComment.getBc_content());
		System.out.println("insertComment getM_num->"+boardComment.getM_num());
		
		// 댓글 작성
		// -----------------------------------------------------
		int insertComment = boardService.insertComment(boardComment);
		// -----------------------------------------------------

		if(insertComment > 0) {
			System.out.println("입력 성공");
			resultStatus = "success";
		} else {
			System.out.println("입력 실패");
			resultStatus = "fail";
		}
		
		return resultStatus;
	}

	/* 댓글삭제 */
	@RequestMapping(value = "/customer/deleteComment", method = RequestMethod.POST)
	@ResponseBody
	public String deleteComment(@RequestBody BoardComment boardComment) {
		System.out.println("GhController deleteComment Start...");
		
		String resultStatus = null;
		
		System.out.println("boardComment.getB_num()->"+boardComment.getB_num());
		System.out.println("boardComment.getM_num()->"+boardComment.getM_num());
		System.out.println("boardComment.getBc_num()->"+boardComment.getBc_num());
		
		// 댓글 삭제
		// -----------------------------------------------------
		int deleteComment = boardService.deleteComment(boardComment);
		// -----------------------------------------------------

		if(deleteComment > 0) {
			System.out.println("입력 성공");
			resultStatus = "success";
		} else {
			System.out.println("입력 실패");
			resultStatus = "fail";
		}
		
		return resultStatus;
	}
	
	
	/* 이용안내 */
	@RequestMapping(value = "userGuide")
	public String userGuide() {
		System.out.println("GhController userGuide Start...");
		
		return "gh/userGuide";
	}
	
	

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
