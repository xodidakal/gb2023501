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
	public List<GameOrder> listGameOrder(GameOrder gameOrder) {
		System.out.println("GameOrderServiceImpl listGameOrder Start...");
		List<GameOrder> gameOrderList = god.listGameOrder(gameOrder);
		System.out.println("GameOrderServiceImpl listGameOrder list.size->"+gameOrderList.size());
		
		return gameOrderList;
	}

	@Override
	public int totalSearchGameOrder(GameOrder gameOrder) {
		int totalSearchGameOrder = god.totalSearchGameOrder(gameOrder);
		return totalSearchGameOrder;
	}

}

