package com.choongang.gb2023501.dhDao;

import java.util.List;

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
	public List<GameOrder> listGameOrder(GameOrder gameOrder) {
		System.out.println("GameOrderDaoImpl listGameOrder start...");
		List<GameOrder> gameOrderList = null;
		
		try {
			gameOrderList = session.selectList("dhGameOrderList" , gameOrder);
			System.out.println("GameOrderDaoImpl listGameOrder gameOrderList->"+gameOrderList.size());
		} catch (Exception e) {
			System.out.println("GameOrderDaoImpl listGameOrder Exception->"+e.getMessage());
		}
		return gameOrderList;
	}

	@Override
	public int totalSearchGameOrder(GameOrder gameOrder) {
		int totalSearchGameOrder = 0;
		try {
			totalSearchGameOrder = session.selectOne("dhGameOrderSearchTotal",gameOrder);
			System.out.println("GameOrderDaoImpl totalSearchGameOrder()->"+totalSearchGameOrder);
		} catch (Exception e) {
			System.out.println("GameOrderDaoImpl totalSearchGameOrder->"+e.getMessage());
		}
		return totalSearchGameOrder;
	}

}
