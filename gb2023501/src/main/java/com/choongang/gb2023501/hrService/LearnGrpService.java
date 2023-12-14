package com.choongang.gb2023501.hrService;

import java.util.List;

import com.choongang.gb2023501.domain.LearnGrp;
import com.choongang.gb2023501.model.LearnGrpDTO;
import com.choongang.gb2023501.model.Game;

public interface LearnGrpService {
	// 교육자마당 > 내학습그룹 (SELECT)
	 List<LearnGrpDTO> learnGroupList();
	
	// 교육자마당 > 학습그룹 등록 - 화면 (SELECT)
	List<Game> learnGroupForm(int g_num);

	// 교육자마당 > 학습그룹 등록 - 실행 (INSERT)
	LearnGrp learnGroupFormInsert(LearnGrp learnGrp);
}
