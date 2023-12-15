package com.choongang.gb2023501.hrRepository;

import java.util.List;

import com.choongang.gb2023501.domain.LearnGrp;
import com.choongang.gb2023501.model.LearnGrpDTO;
import com.choongang.gb2023501.model.MemberDTO;

public interface HrRepository {
	// 교육자마당 > 내학습그룹 (SELECT / JPA)
	// 교육자마당 > 학습그룹 상세 (SELECT / JPA) - 학습그룹 정보
	List<LearnGrpDTO> learnGroupList(int lg_num);

	// 교육자마당 > 학습그룹 등록 - 실행 (INSERT / JPA)
	LearnGrp learnGroupFormInsert(LearnGrp learnGrp);

	// 교육자마당 > 학습그룹 상세 (SELECT / JPA) - 학습자 명단
	List<MemberDTO> joinedMemberList(int lg_num);

}
