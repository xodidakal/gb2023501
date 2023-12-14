package com.choongang.gb2023501.ybDao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.choongang.gb2023501.model.LearnGrp;
import com.choongang.gb2023501.model.EduMaterials;

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
	@Override
	public int selectLgpListByTitleCnt(String lgTitle) {
		System.out.println("YbController EduMaterialsDaoImpl selectLgpListByTitleCnt start...");
		int selectLgpListByTitleCnt = 0;
		try {
			selectLgpListByTitleCnt = session.selectOne("selectLgpListByTitleCnt", lgTitle);
		} catch (Exception e) {
			System.out.println("YbController EduMaterialsDaoImpl selectLgpListByTitleCnt Exception -> " + e.getMessage());
		}
		return selectLgpListByTitleCnt;
	}
}
