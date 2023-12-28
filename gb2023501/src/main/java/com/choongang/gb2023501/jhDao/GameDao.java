package com.choongang.gb2023501.jhDao;

import java.util.List;

import com.choongang.gb2023501.model.Game;

public interface GameDao {

	List<Game> selectGameList(int totalListCnt);

}
