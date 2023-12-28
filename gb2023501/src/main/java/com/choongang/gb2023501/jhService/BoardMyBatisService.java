package com.choongang.gb2023501.jhService;

import java.util.List;
import java.util.Map;

import com.choongang.gb2023501.model.Board;

public interface BoardMyBatisService {

	List<Board> selectBoardList(Map<String, Integer> noticeParams);

}
