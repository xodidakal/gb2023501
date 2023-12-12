package com.choongang.gb2023501.dhService;

import java.util.List;

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

}

