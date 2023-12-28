package com.choongang.gb2023501.jhService;

import java.util.List;

import com.choongang.gb2023501.model.Game;

public interface GameMyBatisService {

	List<Game> selectGameList(int totalListCnt);

}
