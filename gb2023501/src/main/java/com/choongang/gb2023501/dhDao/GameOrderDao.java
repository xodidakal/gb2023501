package com.choongang.gb2023501.dhDao;

import java.util.List;
import java.util.Map;

import com.choongang.gb2023501.model.Game;
import com.choongang.gb2023501.model.GameOrder;

public interface GameOrderDao {

	List<Game> 		listGameOrder(Game game);
	int         	totalSearchGameOrder(Game game);
	List<Game>  	listGameOrder2(Game game);
	int         	totalSearchGameOrder2(Game game);
	List<Game>  	listGame(Game game);
	int         	totalSearchGame(Game game);
	int         	insertGame(Game game);
	Game        	selectGame(int g_num, int m_num);
	int         	updateGame(Game game);
	List<Game> 		selectGameOrder(Map<String, Object> map);
	int             gamesum(Map<String, Object> map);
	int             insertGameOrder(Map<String, Object> map);
	Game            gameRead(int g_num);

}
