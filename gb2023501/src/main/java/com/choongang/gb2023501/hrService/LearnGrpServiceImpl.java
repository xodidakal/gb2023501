package com.choongang.gb2023501.hrService;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.choongang.gb2023501.domain.LearnGrp;
import com.choongang.gb2023501.hrDao.LearnGrpDao;
import com.choongang.gb2023501.model.Game;
//import com.choongang.gb2023501.model.LearnGrpDTO;
import com.choongang.gb2023501.repository.HrRepository;
//import com.choongang.gb2023501.repository.HrRepository2;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class LearnGrpServiceImpl implements LearnGrpService {
//	// Repository 연결
	private final HrRepository hrRepository;
//	// private final HrRepository2 hrRepository2;
//
//	// 교육자마당 > 내학습그룹 (SELECT)
//	@Override
//	public List<LearnGrpDTO> learnGroupList() {
//		System.out.println("LearnGrpServiceImpl learnGroupList() start..");
//		
//		List<LearnGrpDTO> learnGrps = hrRepository.learnGroupList();
//		// List<LearnGrpDTO> learnGrps = hrRepository2.findLeanGrpCountDtoJPQL();
//		System.out.println("LearnGrpServiceImpl learnGroupList() learnGrps.size() -> "+learnGrps.size());
//		
//		System.out.println("LearnGrpServiceImpl learnGroupList() end..");
//		return learnGrps;
//	}
	
	// Dao 연결
	private final LearnGrpDao lgDao;
	
	// 교육자마당 > 학습그룹 등록 - 화면 (SELECT)
	@Override
	public List<Game> learnGroupForm(int g_num) {
		System.out.println("LearnGrpServiceImpl learnGroupForm() start..");
		
		List<Game> gameList = lgDao.learnGroupForm(g_num);
		System.out.println("LearnGrpServiceImpl learnGroupForm() gameList.size() -> "+ gameList.size());		
		
		System.out.println("LearnGrpServiceImpl learnGroupForm() end..");		
		return gameList;
	}
	
	// 교육자마당 > 학습그룹 등록 - 실행 (INSERT)
	@Override
	public int learnGroupFormInsert(LearnGrp learnGrp) {
		System.out.println("LearnGrpServiceImpl learnGroupFormInsert() start..");
		
		//int result = hrRepository.learnGroupFormInsert(learnGrp);
		
		System.out.println("LearnGrpServiceImpl learnGroupFormInsert() start..");
		return 0;
	}

}
