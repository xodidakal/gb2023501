package com.choongang.gb2023501.hrService;

import java.util.List;

import com.choongang.gb2023501.domain.LearnGrp;
import com.choongang.gb2023501.model.LearnGrpDTO;
import com.choongang.gb2023501.model.MemberDTO;
import com.choongang.gb2023501.model.Game;

public interface LearnGrpService {
	// 교육자마당 > 내학습그룹 (SELECT / JPA)
	 List<LearnGrpDTO> learnGroupList(int lg_num);
	
	// 교육자마당 > 학습그룹 등록 - 화면 (SELECT / MyBatis)
	List<Game> learnGroupForm(int g_num);

	// 교육자마당 > 학습그룹 등록 - 실행 (INSERT / JPA)
	LearnGrp learnGroupFormInsert(LearnGrp learnGrp);

	// 교육자마당 > 학습그룹 상세 (SELECT / JPA)
	List<MemberDTO> joinedMemberList(int lg_num);
}
