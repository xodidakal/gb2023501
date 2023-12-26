package com.choongang.gb2023501.dhDao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.choongang.gb2023501.model.Game;
import com.choongang.gb2023501.model.GameOrder;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class GameOrderDaoImpl implements GameOrderDao {

	private final SqlSession session;
	
	@Override
	public List<Game> listGameOrder(Game game) {
		System.out.println("GameOrderDaoImpl listGameOrder start...");
		List<Game> gameOrderList = null;
		
		try {
			gameOrderList = session.selectList("dhGameOrderList" , game);
			System.out.println("GameOrderDaoImpl listGameOrder gameOrderList->"+gameOrderList.size());
		} catch (Exception e) {
			System.out.println("GameOrderDaoImpl listGameOrder Exception->"+e.getMessage());
		}
		return gameOrderList;
	}

	@Override
	public int totalSearchGameOrder(Game game) {
		int totalSearchGameOrder = 0;
		try {
			totalSearchGameOrder = session.selectOne("dhGameOrderSearchTotal",game);
			System.out.println("GameOrderDaoImpl totalSearchGameOrder()->"+totalSearchGameOrder);
		} catch (Exception e) {
			System.out.println("GameOrderDaoImpl totalSearchGameOrder->"+e.getMessage());
		}
		return totalSearchGameOrder;
	}

	@Override
	public List<Game> listGameOrder2(Game game) {
		System.out.println("GameOrderDaoImpl listGameOrder2 start...");
		List<Game> gameOrderList = null;
		
		try {
			gameOrderList = session.selectList("dhGameOrderList2" , game);
			System.out.println("GameOrderDaoImpl listGameOrder2 gameOrderList->"+gameOrderList.size());
		} catch (Exception e) {
			System.out.println("GameOrderDaoImpl listGameOrder2 Exception->"+e.getMessage());
		}
		return gameOrderList;
	}

	@Override
	public int totalSearchGameOrder2(Game game) {
		int totalSearchGameOrder = 0;
		try {
			totalSearchGameOrder = session.selectOne("dhGameOrderSearchTotal2",game);
			System.out.println("GameOrderDaoImpl totalSearchGameOrder2()->"+totalSearchGameOrder);
		} catch (Exception e) {
			System.out.println("GameOrderDaoImpl totalSearchGameOrder2->"+e.getMessage());
		}
		return totalSearchGameOrder;
	}

	@Override
	public List<Game> listGame(Game game) {
		System.out.println("GameOrderDaoImpl listGame start...");
		List<Game> gameList = null;
		
		try {
			gameList = session.selectList("dhGameList" , game);
			System.out.println("GameOrderDaoImpl listGame gameList->"+gameList.size());
		} catch (Exception e) {
			System.out.println("GameOrderDaoImpl listGame Exception->"+e.getMessage());
		}
		return gameList;
	}

	@Override
	public int totalSearchGame(Game game) {
		int totalSearchGame = 0;
		try {
			totalSearchGame = session.selectOne("dhGameSearchTotal",game);
			System.out.println("GameOrderDaoImpl totalSearchGame()->"+totalSearchGame);
		} catch (Exception e) {
			System.out.println("GameOrderDaoImpl totalSearchGame->"+e.getMessage());
		}
		return totalSearchGame;
	}

	@Override
	public int insertGame(Game game) {
		int result = 0;
		try {
			result = session.insert("dhGameInsert", game);
		} catch (Exception e) {
			System.out.println("GameOrderDaoImpl insertGame->"+e.getMessage());
		}
		return result;
	}

	@Override
	public Game selectGame(int g_num, int m_num) {
		Game gameselect = new Game();
		Game game = new Game();
		game.setG_num(g_num);
		game.setM_num(m_num);
		try {
			gameselect = session.selectOne("dhGameSelect", game);
		}catch (Exception e) {
			System.out.println("GameOrderDaoImpl selectGame->"+e.getMessage());
		}
		return gameselect;
	}

	@Override
	public int updateGame(Game game) {
		int updateGame1 = 0;
		try {
				System.out.println("GameOrderDaoImpl updateGame normal Start!!");				
				updateGame1 = session.update("updateGame2", game);
		} catch (Exception e) {
			System.out.println("GameOrderDaoImpl  updateGame->"+e.getMessage());
		}
		return updateGame1;
		}

	@Override
	public List<Game> selectGameOrder(Map<String, Object> map) {
		List<Game> gameorderselect = null;
		try {
			gameorderselect = session.selectList("dhGameOrderSelect", map);
		}catch (Exception e) {
			System.out.println("GameOrderDaoImpl selectGame->"+e.getMessage());
		}
		return gameorderselect;
	}

	@Override
	public int gamesum(Map<String, Object> map) {
		int result = 0;
		try {
			result = session.selectOne("dhGameSum",map);
		} catch (Exception e) {
			System.out.println("GameOrderDaoImpl  gamesum->"+e.getMessage());
		}
		return result;
	}

	@Override
	public int insertGameOrder(Map<String, Object> map) {
		int result = 0;
		try {
			result = session.insert("dhGameOrderInsert", map);
		} catch (Exception e) {
			System.out.println("GameOrderDaoImpl insertGameOrder->"+e.getMessage());
		}
		return result;
	}

	@Override
	public Game gameRead(int g_num) {
		Game game = null;
		
		try {
			System.out.println("GameOrderDaoImpl gameRead Start!!");
			
			game = session.selectOne("gameImageRead", g_num);
			System.out.println("GameOrderDaoImpl gameRead getG_title : {}"+ game.getG_title());
			
		} catch (Exception e) {
			System.out.println("GameOrderDaoImpl gameReadException : {}"+ e.getMessage());
		} finally {
			System.out.println("GameOrderDaoImpl gameRead End..");			
		}
		
		return game;
	}

}
