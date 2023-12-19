package com.choongang.gb2023501.hrRepository;

import java.util.List;

import com.choongang.gb2023501.domain.LearnGrp;
import com.choongang.gb2023501.model.LearnGrpDTO;
import com.choongang.gb2023501.model.MemberDTO;

public interface HrRepository {
	// 교육자마당 > 내학습그룹 (SELECT / JPA)
	// 교육자마당 > 학습그룹 상세 (SELECT / JPA) - 학습그룹 정보
	List<LearnGrpDTO> learnGroupList(int mNum, int lg_num, String sort, String type, String keyword);

	// 교육자마당 > 내학습그룹 (DELETE / JPA)
	void learnGroupListDelete(int lg_num);

	// 교육자마당 > 학습그룹 등록 - 실행 (INSERT / JPA)
	LearnGrp learnGroupFormInsert(LearnGrp learnGrp);

	// 교육자마당 > 학습그룹 상세 (SELECT / JPA) - 학습자 명단
	List<MemberDTO> joinedMemberList(int lg_num);

	// 교육자마당 > 학습그룹 가입 승인 - 화면 (SELECT / JPA) - 신청자 명단
	List<MemberDTO> joiningMemberList(int lg_num);

	// 교육자마당 > 학습그룹 가입 승인 - 실행 (UPDATE / JPA)
	void learnGroupJoinApproval(int lg_num, int m_num);

}
