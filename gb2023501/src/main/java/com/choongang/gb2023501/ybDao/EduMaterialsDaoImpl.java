package com.choongang.gb2023501.ybDao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.choongang.gb2023501.model.LearnGrp;
import com.choongang.gb2023501.model.EduMaterials;
import com.choongang.gb2023501.model.Game;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EduMaterialsDaoImpl implements EduMaterialsDao {
	private final SqlSession session;
	
	// 학습자료 리스트
	@Override
	public List<EduMaterials> selectEduMaterialsList(EduMaterials eduMaterials) {
		System.out.println("YbController EduMaterialsDaoImpl selectEduMaterialsList start...");
		List<EduMaterials> selectEduMaterialsList =  new ArrayList<EduMaterials>();
		try {
			selectEduMaterialsList = session.selectList("ybEduList", eduMaterials);
			System.out.println("YbController EduMaterialsDaoImpl selectEduMaterialsList size() -> " + selectEduMaterialsList.size());
		} catch (Exception e) {
			System.out.println("YbController EduMaterialsDaoImpl selectEduMaterialsList Exception -> " + e.getMessage());
		}
		return selectEduMaterialsList;
	}
	// 학습자료 갯수
	@Override
	public int selectEduMaterialsListCnt(EduMaterials eduMaterials) {
		System.out.println("YbController EduMaterialsDaoImpl selectEduMaterialsListCnt start...");
		
		int selectEduMaterialsListCnt = 0; 
		try {
			selectEduMaterialsListCnt = session.selectOne("ybEduListCnt", eduMaterials);
			System.out.println("YbController EduMaterialsDaoImpl selectEduMaterialsListCnt -> " + selectEduMaterialsListCnt);
		} catch (Exception e) {
			System.out.println("YbController EduMaterialsDaoImpl selectEduMaterialsListCnt Exception -> " + e.getMessage());
		}
		return selectEduMaterialsListCnt;
	}
	// 학습 그룹 검색 리스트
	@Override
	public List<com.choongang.gb2023501.domain.LearnGrp> selectLgpListByTitle(LearnGrp learnGrp) {
		System.out.println("YbController EduMaterialsDaoImpl selectLgpListByTitle start...");
		List<com.choongang.gb2023501.domain.LearnGrp> selectLgpListByTitle = null;
		try {
				selectLgpListByTitle = session.selectList("selectLgpListByTitle", learnGrp);
		} catch (Exception e) {
			System.out.println("YbController EduMaterialsDaoImpl selectLgpListByTitle Exception -> " + e.getMessage());
		}
		return selectLgpListByTitle;
	}
//	// 학습그룹 검색 리스트 개수
//	@Override
//	public int selectLgpListByTitleCnt(String lgTitle, int mmNum) {
//		System.out.println("YbController EduMaterialsDaoImpl selectLgpListByTitleCnt start...");
//		int selectLgpListByTitleCnt = 0;
//		HashMap<String, Object> setAbout = new HashMap<>();
//		try {			
//			setAbout.put("lgTitle", lgTitle);
//			setAbout.put("mmNum", mmNum);
//			selectLgpListByTitleCnt = session.selectOne("selectLgpListByTitleCnt", lgTitle);
//		} catch (Exception e) {
//			System.out.println("YbController EduMaterialsDaoImpl selectLgpListByTitleCnt Exception -> " + e.getMessage());
//		}
//		return selectLgpListByTitleCnt;
//	}
	// 매출 검색 총 액
	@Override
	public int findTotal(Date s_date, Date e_date) {
		
		System.out.println("YbController EduMaterialsDaoImpl findTotal start...");
		int findTotal = 0;
		
		try {
			System.out.println("YbController EduMaterialsDaoImpl findTotal s_date -> " + s_date);
			System.out.println("YbController EduMaterialsDaoImpl findTotal e_date -> " + e_date);
			
			HashMap<String, Object> findSaleTotal = new HashMap<>();
			findSaleTotal.put("s_date", s_date);
			findSaleTotal.put("e_date", e_date);
			findTotal = session.selectOne("findSaleTotal", findSaleTotal);
		} catch (Exception e) {
			System.out.println("YbController EduMaterialsDaoImpl findTotal Exception -> " + e.getMessage());
		} 
		return findTotal;
	}
	// 학습 자료 선택 시 게임 콘텐츠 선택
	@Override
	public List<Game> selectGameList(Game game) {
		System.out.println("YbController EduMaterialsDaoImpl selectGameList start...");
		List<Game> selectGameList = null;
		try {
			selectGameList = session.selectList("selectGameList", game);
		} catch (Exception e) {
			System.out.println("YbController EduMaterialsDaoImpl selectGameList Exception -> " + e.getMessage());
		}
		return selectGameList;
	}
	@Override
	public int insertLgJoin(int lg_num, int m_num) {
		System.out.println("YbController EduMaterialsDaoImpl insertLgJoin start...");
		int insertLgJoin = 0;
		try {
			HashMap<String, Object> setLgJoin = new HashMap<>();
			setLgJoin.put("lg_num", lg_num);
			setLgJoin.put("m_num", m_num);
			insertLgJoin = session.insert("ybInsertLgJoin", setLgJoin);
		} catch (Exception e) {
			System.out.println("YbController EduMaterialsDaoImpl insertLgJoin Exception -> " + e.getMessage());
		}
		return insertLgJoin;
	}
	@Override
	public int selectListCnt(Date s_date, Date e_date) {
		System.out.println("YbController EduMaterialsDaoImpl selectListCnt start...");
		System.out.println("YbController EduMaterialsDaoImpl selectListCnt s_date -> " + s_date);
		System.out.println("YbController EduMaterialsDaoImpl selectListCnt e_date -> " + e_date);
		int selectListCnt = 0;
		try {
			HashMap<String, Object> setCnt = new HashMap<>();
			setCnt.put("s_date", s_date);
			setCnt.put("e_date", e_date);
			selectListCnt = session.selectOne("selectListCnt", setCnt);
		} catch (Exception e) {
			System.out.println("YbController EduMaterialsDaoImpl selectListCnt Exception -> " + e.getMessage());
		}
		return selectListCnt;
		
	}
	@Override
	public List<LearnGrp> selectMNameList(LearnGrp learnGrp) {
		System.out.println("YbController EduMaterialsDaoImpl selectMNameList start...");		
		List<LearnGrp> selectMNameList = null;
		try {
			selectMNameList = session.selectList("selectMNameList", learnGrp);
		} catch (Exception e) {
			System.out.println("YbController EduMaterialsDaoImpl selectListCnt Exception -> " + e.getMessage());
		}
		return selectMNameList;
	}

}
