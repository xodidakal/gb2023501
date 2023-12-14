package com.choongang.gb2023501.dhDao;

import java.util.List;

import com.choongang.gb2023501.model.Game;

public interface GameOrderDao {

	List<Game> 		listGameOrder(Game game);
	int             totalSearchGameOrder(Game game);
	List<Game>      listGameOrder2(Game game);
	int             totalSearchGameOrder2(Game game);

}
