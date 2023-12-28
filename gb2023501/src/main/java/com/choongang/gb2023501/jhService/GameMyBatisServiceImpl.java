package com.choongang.gb2023501.jhService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.choongang.gb2023501.dhDao.GameOrderDao;
import com.choongang.gb2023501.jhDao.GameDao;
import com.choongang.gb2023501.model.Game;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GameMyBatisServiceImpl implements GameMyBatisService {

	private final GameDao gd;
	

	@Override
	public List<Game> selectGameList(int totalListCnt) {
		System.out.println("GameMyBatisServiceImpl selectGameList start...");
		List<Game> selectGameList = gd.selectGameList(totalListCnt);
		
		return selectGameList;
	}

}
