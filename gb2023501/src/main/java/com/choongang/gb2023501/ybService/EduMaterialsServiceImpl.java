package com.choongang.gb2023501.ybService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.choongang.gb2023501.model.EduMaterials;
import com.choongang.gb2023501.ybDao.EduMaterialsDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EduMaterialsServiceImpl implements EduMaterialsService {
	private final EduMaterialsDao ed;
	@Override
	public List<EduMaterials> selectEduMaterialsList(EduMaterials eduMaterials) {
		System.out.println("YbController EduMaterialsServiceImpl selectEduMaterialsList start...");
		List<EduMaterials> selectEduMaterialsList = ed.selectEduMaterialsList(eduMaterials);
		return selectEduMaterialsList;
	}

}
