package com.choongang.gb2023501.ghDao;

import java.util.List;

import com.choongang.gb2023501.model.Board;
import com.choongang.gb2023501.model.BoardComment;

public interface BoardDao {
	

	int         		selectBoardListCnt(Board board);
	List<Board> 		selectBoardList(Board board);
	Board       		selectBoard(int b_num);
	int         		updateBoardCnt(int b_num);
	List<BoardComment> 	selectCommentList(int b_num);
	int insertBoard(Board board);
	List<Board> searchBoardList(Board board);
	int updateParentNum(int b_num);
	int insertComment(BoardComment boardComment);
	int deleteComment(BoardComment boardComment);
	int deleteBoard(BoardComment boardComment);
	int updateBoard(Board board);
	int deleteFile(Board board);
	int deleteBoardComment(BoardComment boardComment);
	Board selectOriBoard(int b_num);
	int selectAnswerCnt(BoardComment boardComment);
	int searchBoardListCnt(Board board);
	
//	int 				selectBdCommentListCnt(BoardComment boardComment);

}
