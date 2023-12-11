package com.choongang.gb2023501.ghDao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.choongang.gb2023501.model.Board;
import com.choongang.gb2023501.model.BoardComment;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BoardDaoImpl implements BoardDao {
	
	private final SqlSession sqlSession;

	@Override
	public List<Board> selectBoardList(Board board) {
		System.out.println("BoardDaoImpl selectBoardList start...");
		List<Board> list = null;
		try {
			list = sqlSession.selectList("mkhSelectBoardList", board);
		} catch (Exception e) {
			System.out.println("BoardDaoImpl selectBoardList Exception->"+e.getMessage());
		}
		return list;
	}

	@Override
	public int boardCount(String b_category) {
		System.out.println("BoardDaoImpl boardCount start...");
		int result = 0;
		try {
			result = sqlSession.selectOne("mkhBoardCount", b_category);
		} catch (Exception e) {
			System.out.println("BoardDaoImpl boardCount Exception->"+e.getMessage());
		}
		return result;
	}

	@Override
	public Board selectBdDetail(int b_num) {
		System.out.println("BoardDaoImpl selectBdDetail start...");
		Board board = null;
		try {
			board = sqlSession.selectOne("mkhSelectBoard", b_num);
		} catch (Exception e) {
			System.out.println("BoardDaoImpl selectBdDetail Exception->"+e.getMessage());
		}
		return board;
	}

	@Override
	public int updateBdCount(int b_num) {
		System.out.println("BoardDaoImpl updateBdCount start...");
		int result = 0;
		try {
			result = sqlSession.update("mkhUpdateBdCount", b_num);
		} catch (Exception e) {
			System.out.println("BoardDaoImpl updateBdCount Exception->"+e.getMessage());
		}
		return result;
	}

	@Override
	public List<BoardComment> selectCommentList(int b_num) {
		System.out.println("BoardDaoImpl selectCommentList start...");
		List<BoardComment> list = null;
		try {
			list = sqlSession.selectList("mkhSelectComment", b_num);
		} catch (Exception e) {
			System.out.println("BoardDaoImpl selectCommentList Exception->"+e.getMessage());
		}
		return list;
	}

	@Override
	public int selectBdCommentListCnt(BoardComment boardComment) {
		System.out.println("BoardDaoImpl selectBdCommentListCnt start...");
		int result = 0;
		try {
			result = sqlSession.selectOne("mkhSelectCommentListCnt", boardComment);
		} catch (Exception e) {
			System.out.println("BoardDaoImpl selectBdCommentListCnt Exception->"+e.getMessage());
		}
		return result;
	}

}
