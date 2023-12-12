package com.choongang.gb2023501.dhService;

import java.util.List;

import com.choongang.gb2023501.model.Game;

public interface GameOrderService {

	List<Game> listGameOrder(Game game);
	int        totalSearchGameOrder(Game game);

}
