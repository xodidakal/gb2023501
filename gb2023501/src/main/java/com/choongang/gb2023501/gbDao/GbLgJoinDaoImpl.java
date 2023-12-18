package com.choongang.gb2023501.gbDao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.choongang.gb2023501.model.Homework;
import com.choongang.gb2023501.model.HwSend;
import com.choongang.gb2023501.model.LearnGrp;
import com.choongang.gb2023501.model.LgJoin;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class GbLgJoinDaoImpl implements GbLgJoinDao {

	private final SqlSession session;
	
	@Override
	public List<LearnGrp> selectLgJoinList(Homework homework) {
		System.out.println("GbLgJoinDaoImpl selectLgJoinList start...");
		List<LearnGrp> learnGrpList = null;
		try {
			learnGrpList = session.selectList("gbSelectLgJoinList", homework);
			System.out.println("GbLgJoinDaoImpl selectLgJoinList learnGrpList ->"+learnGrpList.size());
		} catch (Exception e) {
			System.out.println("GbLgJoinDaoImpl selectLgJoinList Exception -> "+e.getMessage());
		}
		
		return learnGrpList;
	}

	@Override
	public List<LgJoin> selectLgJoinMemberList(HwSend hwsend) {
		System.out.println("GbLgJoinDaoImpl selectLgJoinList start...");
		List<LgJoin> lgJoinMemberList = null;
		
		try {
			lgJoinMemberList = session.selectList("gbSelectLgJoinMemberList", hwsend);
			System.out.println("GbLgJoinDaoImpl selectLgJoinList lgJoinMemberList -> "+lgJoinMemberList.size());
		} catch (Exception e) {
			System.out.println("GbLgJoinDaoImpl selectLgJoinList Exception -> "+e.getMessage());
		}
		
		return lgJoinMemberList;
	}
	
	@Override
	public List<LgJoin> selectLgHwSendMemberList(HwSend hwsend) {
		System.out.println("GbLgJoinDaoImpl selectLgHwSendMemberList start...");
		List<LgJoin> hwSendMemberList = null;
		
		try {
			hwSendMemberList = session.selectList("gbSelectLgHwSendMemberList", hwsend);
			System.out.println("GbLgJoinDaoImpl selectLgHwSendMemberList hwSendMemberList -> "+hwSendMemberList.size());
		} catch (Exception e) {
			System.out.println("GbLgJoinDaoImpl selectLgHwSendMemberList Exception->"+e.getMessage());
		}
		
		return hwSendMemberList;
	}

}
