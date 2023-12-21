package com.choongang.gb2023501.ybService;

import java.util.Date;
import java.util.List;

import com.choongang.gb2023501.domain.LearnGrp;
import com.choongang.gb2023501.model.EduMaterials;
import com.choongang.gb2023501.model.Game;
import com.choongang.gb2023501.model.GameOrder;

public interface EduMaterialsService {
	// 학습자료 리스트
	List<EduMaterials> 			selectEduMaterialsList(EduMaterials eduMaterials);

	int 						selectEduMaterialsListCnt(EduMaterials eduMaterials);

	List<LearnGrp> 				selecLgpListByTitle(com.choongang.gb2023501.model.LearnGrp learnGrp);

//	int 						selectLgpListByTitleCnt(String lgTitle, int mmNum);

	List<GameOrder> 			selectSalesDetailList(GameOrder gameOrder);

	int 						findTotal(Date s_date, Date e_date);

	List<Game> 					selectGameList(Game game);

	int 						insertLgJoin(int lg_num, int m_num);

	int 						selectListCnt(Date s_date, Date e_date);

	List<com.choongang.gb2023501.model.LearnGrp> 				selectMNameList(com.choongang.gb2023501.model.LearnGrp learnGrp);



}
