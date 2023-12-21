package com.choongang.gb2023501.ybService;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.choongang.gb2023501.domain.LearnGrp;
import com.choongang.gb2023501.model.EduMaterials;
import com.choongang.gb2023501.model.Game;
import com.choongang.gb2023501.model.GameOrder;
import com.choongang.gb2023501.ybDao.EduMaterialsDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EduMaterialsServiceImpl implements EduMaterialsService {
	private final EduMaterialsDao ed;
	// 학습 자료 리스트
	@Override
	public List<EduMaterials> selectEduMaterialsList(EduMaterials eduMaterials) {
		System.out.println("YbController EduMaterialsServiceImpl selectEduMaterialsList start...");
		List<EduMaterials> selectEduMaterialsList = ed.selectEduMaterialsList(eduMaterials);
		return selectEduMaterialsList;
	}
	// 학습자료 갯수
	@Override
	public int selectEduMaterialsListCnt(EduMaterials eduMaterials) {
		System.out.println("YbController EduMaterialsServiceImpl selectEduMaterialsListCnt start...");
		int selectEduMaterialsListCnt = ed.selectEduMaterialsListCnt(eduMaterials);
		return selectEduMaterialsListCnt;
	}
	// 학습그룹 검색 리스트
	@Override
	public List<LearnGrp> selecLgpListByTitle(com.choongang.gb2023501.model.LearnGrp learnGrp) {
		System.out.println("YbController EduMaterialsServiceImpl selectLgpListByTitle start...");
		
		List<LearnGrp> selecLgpListByTitle = ed.selectLgpListByTitle(learnGrp);
		return selecLgpListByTitle;
	}
	// 학습그룹 검색 리스트 개수
//	@Override
//	public int selectLgpListByTitleCnt(String lgTitle, int mmNum) {
//		System.out.println("YbController EduMaterialsServiceImpl selectLgpListByTitleCnt start...");
//		int selectEduMaterialsListCnt = ed.selectLgpListByTitleCnt(lgTitle, mmNum);
//		return selectEduMaterialsListCnt;
//	}
	@Override
	public List<GameOrder> selectSalesDetailList(GameOrder gameOrder) {
		System.out.println("YbController EduMaterialsServiceImpl selectSalesDetailList start...");
		return null;
	}
	@Override
	public int findTotal(Date s_date, Date e_date) {
		System.out.println("YbController EduMaterialsServiceImpl findTotal start...");
		int findTotal = ed.findTotal(s_date, e_date);
		return findTotal;
	}
	// 학습 등록 시 게임 콘텐츠 선택
	@Override
	public List<Game> selectGameList(Game game) {
		System.out.println("YbController EduMaterialsServiceImpl selectGameList start...");
		List<Game> selectGameList = ed.selectGameList(game);
		return selectGameList;
	}
	// 학습그룹 가입
	@Override
	public int insertLgJoin(int lg_num, int m_num) {
		System.out.println("YbController EduMaterialsServiceImpl insertLgJoin start...");
		int insertLgJoin = ed.insertLgJoin(lg_num, m_num);
		return insertLgJoin;
	}
	// 매출 조건 검색 후 리스트 개수 
	@Override
	public int selectListCnt(Date s_date, Date e_date) {
		System.out.println("YbController EduMaterialsServiceImpl insertLgJoin start...");
		int selectListCnt = ed.selectListCnt(s_date, e_date);
		return selectListCnt;
	}
	@Override
	public List<com.choongang.gb2023501.model.LearnGrp> selectMNameList(
			com.choongang.gb2023501.model.LearnGrp learnGrp) {
		System.out.println("YbController EduMaterialsServiceImpl selectMNameList start...");
		List<com.choongang.gb2023501.model.LearnGrp> selectMNameList = ed.selectMNameList(learnGrp);
		return selectMNameList;
	}


}
