package com.choongang.gb2023501.dhService;

import java.util.List;

import com.choongang.gb2023501.model.GameOrder;

public interface GameOrderService {

	List<GameOrder> listGameOrder(GameOrder gameOrder);
	int             totalSearchGameOrder(GameOrder gameOrder);

}
