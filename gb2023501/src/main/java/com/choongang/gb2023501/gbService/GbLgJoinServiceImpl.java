package com.choongang.gb2023501.gbService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.choongang.gb2023501.gbDao.GbLgJoinDao;
import com.choongang.gb2023501.model.Homework;
import com.choongang.gb2023501.model.HwSend;
import com.choongang.gb2023501.model.LearnGrp;
import com.choongang.gb2023501.model.LgJoin;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GbLgJoinServiceImpl implements GbLgJoinService {
	
	private final GbLgJoinDao gljd;
	
	@Override
	public List<LearnGrp> selectLgJoinList(Homework homework) {
		System.out.println("GbLgjoinServiceImpl selectLgJoinList start...");
		List<LearnGrp> learnGrpList = gljd.selectLgJoinList(homework);
		System.out.println("GbLgjoinServiceImpl selectLgJoinList learnGrpList -> "+learnGrpList.size());
		
		return learnGrpList;
	}

	@Override
	public List<LgJoin> selectLgJoinMemberList(HwSend hwsend) {
		System.out.println("GbLgjoinServiceImpl selectLgJoinMemberList start...");
		List<LgJoin> lgJoinMemberList = gljd.selectLgJoinMemberList(hwsend);
		System.out.println("GbLgjoinServiceImpl selectLgJoinMemberList lgJoinMemberList -> "+lgJoinMemberList.size());
		
		return lgJoinMemberList;
	}

}
