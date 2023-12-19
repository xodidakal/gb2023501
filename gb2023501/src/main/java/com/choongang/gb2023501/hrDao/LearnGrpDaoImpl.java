package com.choongang.gb2023501.hrDao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.choongang.gb2023501.model.Game;
import com.choongang.gb2023501.model.SearchDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class LearnGrpDaoImpl implements LearnGrpDao {
	
	// SqlSession 연결
	private final SqlSession session;
	
	// 교육자마당 > 학습그룹 등록 - 화면 (SELECT / MyBatis)
	@Override
	public List<Game> learnGroupForm(SearchDTO searchDTO) {
		System.out.println("LearnGrpDaoImpl learnGroupForm() start..");
		
		List<Game> gameList = new ArrayList<Game>();
		
		try {
			gameList = session.selectList("hrSelectGameList", searchDTO);
			System.out.println("LearnGrpDaoImpl learnGroupForm() gameList.size() -> "+ gameList.size());
		} catch (Exception e) {
			System.out.println("LearnGrpDaoImpl learnGroupForm() e.getMessage() -> "+e.getMessage());
		}
		
		System.out.println("LearnGrpDaoImpl learnGroupForm() end..");		
		return gameList;
	}

}
