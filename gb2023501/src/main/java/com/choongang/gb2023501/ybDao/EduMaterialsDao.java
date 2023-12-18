package com.choongang.gb2023501.ybDao;

import java.util.Date;
import java.util.List;

import com.choongang.gb2023501.domain.LearnGrp;
import com.choongang.gb2023501.model.EduMaterials;

public interface EduMaterialsDao {
	// 학습자료 리스트
	List<EduMaterials> 			selectEduMaterialsList(EduMaterials eduMaterials);

	int 						selectEduMaterialsListCnt(EduMaterials eduMaterials);

	List<LearnGrp> 				selectLgpListByTitle(com.choongang.gb2023501.model.LearnGrp learnGrp);

	int 						selectLgpListByTitleCnt(String lgTitle);

	int							findTotal(Date s_date, Date e_date);

}
