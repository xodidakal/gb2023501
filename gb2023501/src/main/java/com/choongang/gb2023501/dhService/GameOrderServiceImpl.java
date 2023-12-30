package com.choongang.gb2023501.dhService;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.choongang.gb2023501.dhDao.GameOrderDao;
import com.choongang.gb2023501.model.Game;
import com.choongang.gb2023501.model.GameOrder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GameOrderServiceImpl implements GameOrderService {

	private final GameOrderDao god;
	
	@Override
	public List<Game> listGameOrder(Game game) {
		System.out.println("GameOrderServiceImpl listGameOrder Start...");
		List<Game> gameOrderList = god.listGameOrder(game);
		System.out.println("GameOrderServiceImpl listGameOrder list.size->"+gameOrderList.size());
		
		return gameOrderList;
	}

	@Override
	public int totalSearchGameOrder(Game game) {
		System.out.println("GameOrderServiceImpl totalSearchGameOrder Start...");
		int totalSearchGameOrder = god.totalSearchGameOrder(game);
		return totalSearchGameOrder;
	}

	@Override
	public List<Game> listGameOrder2(Game game) {
		System.out.println("GameOrderServiceImpl listGameOrder2 Start...");
		List<Game> gameOrderList = god.listGameOrder2(game);
		System.out.println("GameOrderServiceImpl listGameOrder2 list.size->"+gameOrderList.size());
		
		return gameOrderList;
	}

	@Override
	public int totalSearchGameOrder2(Game game) {
		System.out.println("GameOrderServiceImpl totalSearchGameOrder2 Start...");
		int totalSearchGameOrder = god.totalSearchGameOrder2(game);
		return totalSearchGameOrder;
	}

	@Override
	public List<Game> listGame(Game game) {
		System.out.println("GameOrderServiceImpl listGame Start...");
		List<Game> gameList = god.listGame(game);
		System.out.println("GameOrderServiceImpl listGame list.size->"+gameList.size());
		
		return gameList;
	}

	@Override
	public int totalSearchGame(Game game) {
		System.out.println("GameOrderServiceImpl totalSearchGame Start...");
		int totalSearchGame = god.totalSearchGame(game);
		return totalSearchGame;
	}

	@Override
	public int insertGame(Game game) {
		int result = 0;
		result = god.insertGame(game);
		
		return result;
	}

	@Override
	public Game selectGame(int g_num, int m_num) {
		Game game = god.selectGame(g_num, m_num);
		return game;
	}

	@Override
	public int updateGame(Game game) {
		int updateGame1 = god.updateGame(game);
		return updateGame1;
	}

	@Override
	public List<Game> selectGameOrder(Map<String, Object> map) {
		List<Game> game = god.selectGameOrder(map);
		return game;
	}

	@Override
	public int gamesum(Map<String, Object> map) {
		int result = 0;
		result = god.gamesum(map);
		return result;
	}

	@Override
	public int insertGameOrder(Map<String, Object> map) {
		int result = 0;
		result = god.insertGameOrder(map);
		
		return result;
	}

	@Override
	public Game gameRead(int g_num) {
		Game game = god.gameRead(g_num);

		return game;
	}

}

