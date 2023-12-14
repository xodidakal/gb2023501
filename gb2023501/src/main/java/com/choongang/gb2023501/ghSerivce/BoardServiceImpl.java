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
	public int selectBoardListCnt(String b_category) {
		System.out.println("BoardServiceImpl selectBoardListCnt Start...");
		int result = boardDao.selectBoardListCnt(b_category);
		
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

	@Override
	public int insertBoard(Board board) {
		System.out.println("BoardServiceImpl insertBoard Start...");
		int result = boardDao.insertBoard(board);
		
		return result;
	}

//	@Override
//	public int selectBdCommentListCnt(BoardComment boardComment) {
//		System.out.println("BoardServiceImpl selectBdCommentListCnt Start...");
//		int result = boardDao.selectBdCommentListCnt(boardComment);
//		
//		return result;
//	}

}
