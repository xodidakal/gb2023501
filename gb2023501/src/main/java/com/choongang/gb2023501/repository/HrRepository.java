package com.choongang.gb2023501.repository;

import java.util.List;
import com.choongang.gb2023501.domain.LearnGrp;

public interface HrRepository {
	// 교육자마당 > 내학습그룹 (SELECT)
	List<LearnGrp> learnGroupList();

}
