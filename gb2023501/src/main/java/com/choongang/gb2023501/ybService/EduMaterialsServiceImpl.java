package com.choongang.gb2023501.ybService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.choongang.gb2023501.domain.LearnGrp;
import com.choongang.gb2023501.model.EduMaterials;
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
	@Override
	public int selectLgpListByTitleCnt(String lgTitle) {
		System.out.println("YbController EduMaterialsServiceImpl selectLgpListByTitleCnt start...");
		int selectEduMaterialsListCnt = ed.selectLgpListByTitleCnt(lgTitle);
		return selectEduMaterialsListCnt;
	}


}
