package com.choongang.gb2023501.hrService;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.choongang.gb2023501.domain.LearnGrp;
import com.choongang.gb2023501.hrDao.LearnGrpDao;
import com.choongang.gb2023501.model.Game;

//import com.choongang.gb2023501.model.LearnGrpDTO;
//import com.choongang.gb2023501.repository.HrRepository;
//import com.choongang.gb2023501.repository.HrRepository2;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class LearnGrpServiceImpl implements LearnGrpService {
//	// Repository 연결
//	private final HrRepository hrRepository;
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
	
	// 교육자마당 > 학습그룹 등록 - 화면 1
	@Override
	public List<Game> learnGroupForm1() {
		System.out.println("LearnGrpServiceImpl learnGroupForm1() start..");
		
		List<Game> gameList = lgDao.learnGroupForm1();
		System.out.println("LearnGrpServiceImpl learnGroupForm1() gameList.size() -> "+ gameList.size());		
		
		System.out.println("LearnGrpServiceImpl learnGroupForm1() end..");		
		return gameList;
	}

}
