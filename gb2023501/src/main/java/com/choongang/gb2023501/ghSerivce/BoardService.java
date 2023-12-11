package com.choongang.gb2023501.ghSerivce;

import java.util.List;

import com.choongang.gb2023501.model.Board;
import com.choongang.gb2023501.model.BoardComment;

public interface BoardService {

	List<Board> selectBoardList(Board board);
	int 		boardCount(String b_category);
	Board       selectBdDetail(int b_num);
	int       	updateBdCount(int b_num);
	List<BoardComment> selectCommentList(int b_num);
	int selectBdCommentListCnt(BoardComment boardComment);

}
