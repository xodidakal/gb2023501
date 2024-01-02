package com.choongang.gb2023501.jhDao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.choongang.gb2023501.model.Game;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class GameDaoImpl implements GameDao {
	
	
	
	private final SqlSession session;

	@Override
	public List<Game> selectGameList(int totalListCnt) {
		System.out.println("GameDaoImpl selectGameList start...");
		System.out.println("GameDaoImpl selectGameList selectGameList..." + totalListCnt);
		List<Game> selectGameList = null;
	
		try {
			
			selectGameList = session.selectList("jhSelectGameList", totalListCnt );
			
		} catch (Exception e) {
			System.out.println("GameDaoImpl Exception " + e.getMessage());
			
		}
		
		
		System.out.println("GameDaoImpl selectGameList " + selectGameList);
			return selectGameList;
		}


}
