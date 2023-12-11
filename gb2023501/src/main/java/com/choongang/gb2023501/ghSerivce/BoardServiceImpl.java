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
	public List<Board> selectBoardList(Board board) {
		System.out.println("BoardServiceImpl selectBoardList Start...");
		List<Board> list = boardDao.selectBoardList(board);
		System.out.println("BoardServiceImpl selectBoardList list.size->"+list.size());
		return list;
	}

	@Override
	public int boardCount(String b_category) {
		System.out.println("BoardServiceImpl BoardCount Start...");
		int result = boardDao.boardCount(b_category);
		
		return result;
	}

	@Override
	public Board selectBdDetail(int b_num) {
		System.out.println("BoardServiceImpl selectBdDetail Start...");
		Board board = boardDao.selectBdDetail(b_num);
		
		return board;
	}

	@Override
	public int updateBdCount(int b_num) {
		System.out.println("BoardServiceImpl updateBdCount Start...");
		int result = boardDao.updateBdCount(b_num);
		
		return result;
	}

	@Override
	public List<BoardComment> selectCommentList(int b_num) {
		System.out.println("BoardServiceImpl selectCommentList Start...");
		List<BoardComment> list = boardDao.selectCommentList(b_num);

		return list;
	}

	@Override
	public int selectBdCommentListCnt(BoardComment boardComment) {
		System.out.println("BoardServiceImpl selectBdCommentListCnt Start...");
		int result = boardDao.selectBdCommentListCnt(boardComment);
		
		return result;
	}

}
