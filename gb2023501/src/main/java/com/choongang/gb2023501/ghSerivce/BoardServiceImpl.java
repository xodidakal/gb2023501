package com.choongang.gb2023501.ghSerivce;

import java.util.List;

import org.springframework.stereotype.Service;

import com.choongang.gb2023501.ghDao.BoardDao;
import com.choongang.gb2023501.model.Board;
import com.choongang.gb2023501.model.BoardComment;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	private final BoardDao boardDao;
	
	@Override
	public int selectBoardListCnt(Board board) {
		System.out.println("BoardServiceImpl selectBoardListCnt Start...");
		int result = 0;
		
		if(board.getSearch_keyword() != null) {
			if(!board.getSearch_keyword().equals("")) {
				System.out.println("검색어로 count");
				// 검색 건수
				result = boardDao.searchBoardListCnt(board);
				System.out.println("BoardServiceImpl selectBoardListCnt result->"+result);
				return result;
			}
		}
		//총 건수
		result = boardDao.selectBoardListCnt(board);
		
		return result;
	}

	@Override
	public List<Board> selectBoardList(Board board) {
		System.out.println("BoardServiceImpl selectBoardList Start...");
		List<Board> list = null;
		System.out.println("BoardServiceImpl board.getSearch_keyword()->"+board.getSearch_keyword());
		if(board.getSearch_keyword() != null) {
			if(!board.getSearch_keyword().equals("")) {
				System.out.println("검색어로 list 출력");
				list = boardDao.searchBoardList(board);
				System.out.println("BoardServiceImpl selectBoardList list.size->"+list.size());
				return list;
			}
		}
		System.out.println("그냥 list 출력");
		list = boardDao.selectBoardList(board);
		System.out.println("BoardServiceImpl selectBoardList list.size->"+list.size());
		return list;
	}
	
	@Override
	public Board selectBoard(int b_num) {
		System.out.println("BoardServiceImpl selectBoard Start...");
		Board board = boardDao.selectBoard(b_num);
		
		return board;
	}

	@Override
	public int updateBoardCnt(int b_num) {
		System.out.println("BoardServiceImpl updateBdCount Start...");
		int result = boardDao.updateBoardCnt(b_num);
		
		return result;
	}

	@Override
	public List<BoardComment> selectCommentList(int b_num) {
		System.out.println("BoardServiceImpl selectCommentList Start...");
		List<BoardComment> list = boardDao.selectCommentList(b_num);

		return list;
	}
	
	// ori
	@Override
	public int insertBoard(Board board) {
		System.out.println("BoardServiceImpl insertBoard Start...");
		int result = boardDao.insertBoard(board);
		
		return result;
	}
	
	@Override
	public int insertComment(BoardComment boardComment) {
		System.out.println("BoardServiceImpl insertComment Start...");
		int result = boardDao.insertComment(boardComment);
		
		return result;
	}

	@Override
	public int deleteComment(BoardComment boardComment) {
		System.out.println("BoardServiceImpl deleteComment Start...");
		int result = boardDao.deleteComment(boardComment);
		
		return result;
	}

	@Override
	public int deleteBoard(BoardComment boardComment) {
		System.out.println("BoardServiceImpl deleteBoard Start...");
		
		// 댓글 먼저 삭제
		int resultComment = boardDao.deleteBoardComment(boardComment);
		if(resultComment > 0)	{System.out.println("BoardServiceImpl deleteBoard resultComment 댓글삭제 성공->"+resultComment);}
		
		// 게시글 삭제
		int resultBoard = boardDao.deleteBoard(boardComment);
		if(resultBoard > 0 )	{System.out.println("BoardServiceImpl deleteBoard resultComment 원글삭제 성공->"+resultBoard);}
		
		return resultBoard;
	}

	@Override
	public int updateBoard(Board board) {
		System.out.println("BoardServiceImpl updateBoard Start...");
		int result = boardDao.updateBoard(board);
		
		return result;
	}

	@Override
	public int deleteFile(Board board) {
		System.out.println("BoardServiceImpl deleteFile Start...");
		int result = boardDao.deleteFile(board);
		
		return result;
	}

	@Override
	public Board selectOriBoard(int b_num) {
		System.out.println("BoardServiceImpl selectOriBoard Start...");
		Board board = boardDao.selectOriBoard(b_num);
		
		return board;
	}

	@Override
	public int selectAnswerCnt(BoardComment boardComment) {
		System.out.println("BoardServiceImpl selectAnswerCnt Start...");
		int result = boardDao.selectAnswerCnt(boardComment);

		return result;
	}
	
	// ver2
//	@Override
//	public int insertBoard(Board board) {
//		System.out.println("BoardServiceImpl insertBoard Start...");
//		int result = 0;
//		if(board.getB_ref_num() == "1") {
//			System.out.println("BoardServiceImpl update 원글 b_ref_num");
//			// 해당글의 B_ref_num만 업데이트
//			result = boardDao.updateParentNum(board.getB_num());
//			return result;
//		} else {
//			System.out.println("BoardServiceImpl 원글 등록");
//			result = boardDao.insertBoard(board);			
//		}
//		
//		return result;
//	}
	
	

//	@Override
//	public int selectBdCommentListCnt(BoardComment boardComment) {
//		System.out.println("BoardServiceImpl selectBdCommentListCnt Start...");
//		int result = boardDao.selectBdCommentListCnt(boardComment);
//		
//		return result;
//	}

}
