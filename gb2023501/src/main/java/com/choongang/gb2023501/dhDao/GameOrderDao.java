package com.choongang.gb2023501.dhDao;

import java.util.List;

import com.choongang.gb2023501.model.GameOrder;

public interface GameOrderDao {

	List<GameOrder> listGameOrder(GameOrder gameOrder);
	int             totalSearchGameOrder(GameOrder gameOrder);

}
