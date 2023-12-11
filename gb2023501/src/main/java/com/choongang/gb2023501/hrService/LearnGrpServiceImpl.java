package com.choongang.gb2023501.hrService;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.choongang.gb2023501.domain.LearnGrp;
import com.choongang.gb2023501.repository.HrRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class LearnGrpServiceImpl implements LearnGrpService {
	// Repository 연결
	private final HrRepository hrRepository;

	// 교육자마당 > 내학습그룹 (SELECT)
	@Override
	public List<LearnGrp> learnGroupList() {
		System.out.println("LearnGrpServiceImpl learnGroupList() start..");
		
		List<LearnGrp> learnGrps = hrRepository.learnGroupList();
		System.out.println("LearnGrpServiceImpl learnGroupList() learnGrps.size() -> "+learnGrps.size());
		
		System.out.println("LearnGrpServiceImpl learnGroupList() end..");
		return learnGrps;
	}

}
