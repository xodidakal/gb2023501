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
	public int selectBoardListCnt(Board board) {
		System.out.println("BoardDaoImpl boardCount start...");
		int result = 0;
		try {
			result = sqlSession.selectOne("mkhSelectBoardCnt", board);
			System.out.println("BoardDaoImpl selectBoardListCnt result->"+result);
		} catch (Exception e) {
			System.out.println("BoardDaoImpl boardCount Exception->"+e.getMessage());
		}
		return result;
	}

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
	public Board selectBoard(int b_num) {
		System.out.println("BoardDaoImpl selectBoard start...");
		Board board = null;
		try {
			board = sqlSession.selectOne("mkhSelectBoard", b_num);
		} catch (Exception e) {
			System.out.println("BoardDaoImpl selectBoard Exception->"+e.getMessage());
		}
		return board;
	}

	@Override
	public int updateBoardCnt(int b_num) {
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
			list = sqlSession.selectList("mkhSelectCommentList", b_num);
		} catch (Exception e) {
			System.out.println("BoardDaoImpl selectCommentList Exception->"+e.getMessage());
		}
		return list;
	}

	@Override
	public int insertBoard(Board board) {
		System.out.println("BoardDaoImpl insertBoard start...");
		int result = 0;
		try {
			if(board.getB_attach_name() == null) board.setB_attach_name("");
			if(board.getB_attach_path() == null) board.setB_attach_path("");
			
			System.out.println("board.getB_flag()->"+board.getB_flag());
			
			result = sqlSession.insert("mkhInsertBoard", board);
		} catch (Exception e) {
			System.out.println("BoardDaoImpl insertBoard Exception->"+e.getMessage());
		}
		return result;
	}

	@Override
	public List<Board> searchBoardList(Board board) {
		System.out.println("BoardDaoImpl searchBoardList start...");
		List<Board> list = null;
		try {
			list = sqlSession.selectList("mkhSearchBoardList", board);
		} catch (Exception e) {
			System.out.println("BoardDaoImpl searchBoardList Exception->"+e.getMessage());
		}
		return list;
	}

	@Override
	public int updateParentNum(int b_num) {
		System.out.println("BoardDaoImpl updateParentNum start...");
		int result = 0;
		try {
			result = sqlSession.update("mkhUpdateParentNum", b_num);
		} catch (Exception e) {
			System.out.println("BoardDaoImpl updateParentNum Exception->"+e.getMessage());
		}
		
		return result;
	}

	@Override
	public int insertComment(BoardComment boardComment) {
		System.out.println("BoardDaoImpl insertComment start...");
		int result = 0;
		try {
			result = sqlSession.insert("mkhInsertComment", boardComment);
		} catch (Exception e) {
			System.out.println("BoardDaoImpl insertComment Exception->"+e.getMessage());
		}
		
		return result;
	}

	@Override
	public int deleteComment(BoardComment boardComment) {
		System.out.println("BoardDaoImpl deleteComment start...");
		int result = 0;
		try {
			result = sqlSession.delete("mkhDeleteComment", boardComment);
			System.out.println("BoardDaoImpl deleteComment result->"+result);
		} catch (Exception e) {
			System.out.println("BoardDaoImpl deleteComment Exception->"+e.getMessage());
		}
		
		return result;
	}

	@Override
	public int deleteBoard(BoardComment boardComment) {
		System.out.println("BoardDaoImpl deleteBoard start...");
		int result = 0;
		try {
			result = sqlSession.delete("mkhDeleteBoard", boardComment);
		} catch (Exception e) {
			System.out.println("BoardDaoImpl deleteBoard Exception->"+e.getMessage());
		}
		
		return result;
	}

	@Override
	public int updateBoard(Board board) {
		System.out.println("BoardDaoImpl updateBoard start...");
		int result = 0;
		try {
			result = sqlSession.update("mkhUpdateBoard", board);
			System.out.println("BoardDaoImpl updateBoard result->"+result);
		} catch (Exception e) {
			System.out.println("BoardDaoImpl updateBoard Exception->"+e.getMessage());
		}

		return result;
	}

	@Override
	public int deleteFile(Board board) {
		System.out.println("BoardDaoImpl deleteFile start...");
		int result = 0;
		try {
			result = sqlSession.update("mkhDeleteFile", board);
		} catch (Exception e) {
			System.out.println("BoardDaoImpl deleteFile Exception->"+e.getMessage());
		}
		
		return result;
	}

	@Override
	public int deleteBoardComment(BoardComment boardComment) {
		System.out.println("BoardDaoImpl deleteBoardComment start...");
		int result = 0;
		try {
			result = sqlSession.delete("mkhDeleteBoardComment", boardComment);
			System.out.println("deleteBoardComment 결과->"+result);
		} catch (Exception e) {
			System.out.println("BoardDaoImpl deleteBoardComment Exception->"+e.getMessage());
		}
		
		return result;
	}

	@Override
	public Board selectOriBoard(int b_num) {
		System.out.println("BoardDaoImpl selectOriBoard start...");
		Board board = null;
		try {
			board = sqlSession.selectOne("mkhSelectOriBoard", b_num);
		} catch (Exception e) {
			System.out.println("BoardDaoImpl selectOriBoard Exception->"+e.getMessage());
		}
		return board;
	}

	@Override
	public int selectAnswerCnt(BoardComment boardComment) {
		System.out.println("BoardDaoImpl selectAnswerCnt start...");
		int result = 0;
		try {
			result = sqlSession.selectOne("mkhSelectAnswerCnt", boardComment);
			System.out.println("BoardDaoImpl selectAnswerCnt result->"+result);
		} catch (Exception e) {
			System.out.println("BoardDaoImpl selectAnswerCnt Exception->"+e.getMessage());
		}
		return result;
	}

	@Override
	public int searchBoardListCnt(Board board) {
		System.out.println("BoardDaoImpl searchBoardListCnt start...");
		int result = 0;
		try {
			result = sqlSession.selectOne("mkhSearchBoardListCnt", board);
			System.out.println("BoardDaoImpl searchBoardListCnt result->"+result);
		} catch (Exception e) {
			System.out.println("BoardDaoImpl searchBoardListCnt Exception->"+e.getMessage());
		}
		return result;		
	}
	

//	@Override
//	public int selectBdCommentListCnt(BoardComment boardComment) {
//		System.out.println("BoardDaoImpl selectBdCommentListCnt start...");
//		int result = 0;
//		try {
//			result = sqlSession.selectOne("mkhSelectCommentListCnt", boardComment);
//		} catch (Exception e) {
//			System.out.println("BoardDaoImpl selectBdCommentListCnt Exception->"+e.getMessage());
//		}
//		return result;
//	}

}
