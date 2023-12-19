package com.choongang.gb2023501.dhService;

import java.util.List;


import com.choongang.gb2023501.model.Game;

public interface GameOrderService {

	List<Game> listGameOrder(Game game);
	int        totalSearchGameOrder(Game game);
	List<Game> listGameOrder2(Game game);
	int        totalSearchGameOrder2(Game game);
	List<Game> listGame(Game game);
	int        totalSearchGame(Game game);
	int        insertGame(Game game);
	Game       selectGame(int g_num, int m_num);
	int        updateGame(Game game);
	int        insertGameOrder(Game game);

}
