package com.choongang.gb2023501.hrService;

import java.util.List;

import com.choongang.gb2023501.domain.LearnGrp;
//import com.choongang.gb2023501.model.LearnGrpDTO;
import com.choongang.gb2023501.model.Game;

public interface LearnGrpService {
//	// 교육자마당 > 내학습그룹 (SELECT)
//	 List<LearnGrpDTO> learnGroupList();
	
	// 교육자마당 > 학습그룹 등록 - 화면 1
	List<Game> learnGroupForm1();

}
