package com.choongang.gb2023501.jhDao;

import java.util.List;
import java.util.Map;

import com.choongang.gb2023501.model.Board;

public interface JhBoardDao {

	List<Board> selectBoardList(Map<String, Integer> noticeParams);

}
