package com.choongang.gb2023501.hrService;

import java.util.List;

import com.choongang.gb2023501.domain.LearnGrp;

public interface LearnGrpService {
	// 교육자마당 > 내학습그룹 (SELECT)
	List<LearnGrp> learnGroupList();

}
