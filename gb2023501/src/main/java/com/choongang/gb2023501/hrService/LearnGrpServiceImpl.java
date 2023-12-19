package com.choongang.gb2023501.hrService;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.choongang.gb2023501.domain.LearnGrp;
import com.choongang.gb2023501.hrDao.LearnGrpDao;
import com.choongang.gb2023501.hrRepository.HrRepository;
import com.choongang.gb2023501.model.Game;
import com.choongang.gb2023501.model.SearchDTO;
import com.choongang.gb2023501.model.LearnGrpDTO;
import com.choongang.gb2023501.model.MemberDTO;

//import com.choongang.gb2023501.repository.HrRepository2;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class LearnGrpServiceImpl implements LearnGrpService {
	// Repository 연결
	private final HrRepository hrRepository;
	
	// Dao 연결
	private final LearnGrpDao lgDao;

	// 교육자마당 > 내학습그룹 (SELECT / JPA)
	// 교육자마당 > 학습그룹 상세 (SELECT / JPA) - 학습그룹 정보
	@Override
	public List<LearnGrpDTO> learnGroupList(int mNum, int lg_num, String sort, String type, String keyword) {
		System.out.println("LearnGrpServiceImpl learnGroupList() start..");
		
		List<LearnGrpDTO> learnGrps = hrRepository.learnGroupList(mNum, lg_num, sort, type, keyword);
		System.out.println("LearnGrpServiceImpl learnGroupList() learnGrps.size() -> "+learnGrps.size());
		
		System.out.println("LearnGrpServiceImpl learnGroupList() end..");
		return learnGrps;
	}
	
	// 교육자마당 > 내학습그룹 (DELETE / JPA)
	@Override
	public void learnGroupListDelete(int lg_num) {
		System.out.println("LearnGrpServiceImpl learnGroupListDelete() start..");
		
		hrRepository.learnGroupListDelete(lg_num);
		
		System.out.println("LearnGrpServiceImpl learnGroupListDelete() end..");
	}
		
	// 교육자마당 > 학습그룹 등록 - 화면 (SELECT / MyBatis)
	@Override
	public List<Game> learnGroupForm(SearchDTO searchDTO) {
		System.out.println("LearnGrpServiceImpl learnGroupForm() start..");
		
		List<Game> gameList = lgDao.learnGroupForm(searchDTO);
		System.out.println("LearnGrpServiceImpl learnGroupForm() gameList.size() -> "+ gameList.size());		
		
		System.out.println("LearnGrpServiceImpl learnGroupForm() end..");		
		return gameList;
	}
	
	// 교육자마당 > 학습그룹 등록 - 실행 (INSERT / JPA)
	@Override
	public LearnGrp learnGroupFormInsert(LearnGrp learnGrp) {
		System.out.println("LearnGrpServiceImpl learnGroupFormInsert() start..");
		
		hrRepository.learnGroupFormInsert(learnGrp);
		
		System.out.println("LearnGrpServiceImpl learnGroupFormInsert() end..");
		return learnGrp;
	}

	// 교육자마당 > 학습그룹 상세 (SELECT / JPA) - 학습자 명단
	@Override
	public List<MemberDTO> joinedMemberList(int lg_num) {
		System.out.println("LearnGrpServiceImpl joinedMemberList() start..");
		
		List<MemberDTO> members = hrRepository.joinedMemberList(lg_num);
		System.out.println("LearnGrpServiceImpl joinedMemberList() members.size() -> "+members.size());
		
		System.out.println("LearnGrpServiceImpl joinedMemberList() end..");
		return members;
	}

	// 교육자마당 > 학습그룹 가입 승인 - 화면 (SELECT / JPA) - 신청자 명단
	@Override
	public List<MemberDTO> joiningMemberList(int lg_num) {
		System.out.println("LearnGrpServiceImpl joiningMemberList() start..");
		
		List<MemberDTO> members = hrRepository.joiningMemberList(lg_num);
		System.out.println("LearnGrpServiceImpl joiningMemberList() members.size() -> "+members.size());
		
		System.out.println("LearnGrpServiceImpl joiningMemberList() end..");
		return members;
	}

	// 교육자마당 > 학습그룹 가입 승인 - 실행 (UPDATE / JPA)
	@Override
	public void learnGroupJoinApproval(int lg_num, int m_num) {
		System.out.println("LearnGrpServiceImpl learnGroupJoinApproval() start..");
		
		hrRepository.learnGroupJoinApproval(lg_num, m_num);
		
		System.out.println("LearnGrpServiceImpl learnGroupJoinApproval() end..");
	}

}
