package com.choongang.gb2023501.jhService;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.choongang.gb2023501.jhDao.JhBoardDao;
import com.choongang.gb2023501.jhDao.GameDao;
import com.choongang.gb2023501.model.Board;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardMyBatisServiceImpl implements BoardMyBatisService {
	
	private final JhBoardDao bd;

	@Override
	public List<Board> selectBoardList(Map<String, Integer> noticeParams) {
		List<Board> selectBoardList = bd.selectBoardList(noticeParams);
		return selectBoardList;
	}

}
