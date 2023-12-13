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
	public List<Game> listGameOrder(Game game) {
		System.out.println("GameOrderDaoImpl listGameOrder start...");
		List<Game> gameOrderList = null;
		
		try {
			gameOrderList = session.selectList("dhGameOrderList" , game);
			System.out.println("GameOrderDaoImpl listGameOrder gameOrderList->"+gameOrderList.size());
		} catch (Exception e) {
			System.out.println("GameOrderDaoImpl listGameOrder Exception->"+e.getMessage());
		}
		return gameOrderList;
	}

	@Override
	public int totalSearchGameOrder(Game game) {
		int totalSearchGameOrder = 0;
		try {
			totalSearchGameOrder = session.selectOne("dhGameOrderSearchTotal",game);
			System.out.println("GameOrderDaoImpl totalSearchGameOrder()->"+totalSearchGameOrder);
		} catch (Exception e) {
			System.out.println("GameOrderDaoImpl totalSearchGameOrder->"+e.getMessage());
		}
		return totalSearchGameOrder;
	}

	@Override
	public List<Game> listGameOrder2(Game game) {
		System.out.println("GameOrderDaoImpl listGameOrder2 start...");
		List<Game> gameOrderList = null;
		
		try {
			gameOrderList = session.selectList("dhGameOrderList2" , game);
			System.out.println("GameOrderDaoImpl listGameOrder2 gameOrderList->"+gameOrderList.size());
		} catch (Exception e) {
			System.out.println("GameOrderDaoImpl listGameOrder2 Exception->"+e.getMessage());
		}
		return gameOrderList;
	}

	@Override
	public int totalSearchGameOrder2(Game game) {
		int totalSearchGameOrder = 0;
		try {
			totalSearchGameOrder = session.selectOne("dhGameOrderSearchTotal2",game);
			System.out.println("GameOrderDaoImpl totalSearchGameOrder2()->"+totalSearchGameOrder);
		} catch (Exception e) {
			System.out.println("GameOrderDaoImpl totalSearchGameOrder2->"+e.getMessage());
		}
		return totalSearchGameOrder;
	}

}
