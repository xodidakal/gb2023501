package com.choongang.gb2023501.ybService;

import java.util.List;

import com.choongang.gb2023501.model.EduMaterials;

public interface EduMaterialsService {
	// 학습자료 리스트
	List<EduMaterials> 			selectEduMaterialsList(EduMaterials eduMaterials);

	int 						selectEduMaterialsListCnt(EduMaterials eduMaterials);

}
