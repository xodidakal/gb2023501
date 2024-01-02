package com.choongang.gb2023501.jhDao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.choongang.gb2023501.model.Board;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JhBoardDaoImpl implements JhBoardDao {

	private final SqlSession session;

	@Override
	public List<Board> selectBoardList(Map<String, Integer> noticeParams) {
		
		List<Board> selectBoardList = null;
		try {
			
			selectBoardList = session.selectList("jhSelectBoardList", noticeParams );
			
		} catch (Exception e) {
			System.out.println("BoardDaoImpl Exception " + e.getMessage());
			
		}
		
		
		System.out.println("BoardDaoImpl selectBoardList " + selectBoardList);
			return selectBoardList;
	}
}
