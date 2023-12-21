package com.choongang.gb2023501.gbService;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.choongang.gb2023501.domain.Homework;
import com.choongang.gb2023501.domain.HwRecord;
import com.choongang.gb2023501.domain.HwSend;
import com.choongang.gb2023501.gbRepository.JpaHomeworkRepository;
import com.choongang.gb2023501.gbRepository.JpaInterHomeworkRepository;
import com.choongang.gb2023501.gbRepository.JpaInterHwSendRepository;
import com.choongang.gb2023501.gbRepository.JpaInterHwRecordRepository;
import com.choongang.gb2023501.model.HomeworkDTO;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class JpaHomeworkServiceImpl implements JpaHomeworkService {
	
	private final JpaHomeworkRepository jhr; 		// JPQL 사용 Repository
	private final JpaInterHwSendRepository jihsr;	// JPA에서 제공하는 메소드 사용 HwSend Repository
	private final JpaInterHwRecordRepository jihrr; // JPA에서 제공하는 메소드 사용 HwRecord Repository
	private final JpaInterHomeworkRepository jihr; 	// JPA에서 제공하는 메소드 사용 Homework Repository
	
	// 내 숙제 목록 가져오기
	@Override
	public List<HwSend> selectMyHomeworkList(HwSend hwsend) {
		System.out.println("JpaHomeworkServiceImpl selectMyHomeworkList start...");
		// JPQL 사용
		List<HwSend> myHomeworkList = jhr.selectMyHomeworkList(hwsend);
		
		// JPA에서 제공하는 메소드 사용
		// List<HwSend> myHomeworkList = jihr.findByMemberMmNum(m_num);
		System.out.println("JpaHomeworkServiceImpl selectMyHomeworkList myHomeworkList -> "+myHomeworkList.size());
		
		return myHomeworkList;
	}
	
	// 내 숙제 총 건 가져오기
	@Override
	public Long myHomeworkcountBy(HwSend hwsend) {
		System.out.println("JpaHomeworkServiceImpl myHomeworkcountBy start...");
		System.out.println("hwsend.getMember().getMmNum() -> "+hwsend.getMember().getMmNum());
		
		int mmNum = hwsend.getMember().getMmNum();
		
		Long myHomeworkCnt = jihsr.countByMemberMmNum(mmNum);
		
//		if(hwsend.getSearchType() != null) {
//			myHomeworkCnt = jihsr.countByMemberMmNumAndSearchTypeAndSearchKeyword(mmNum, hwsend.getSearchType(), hwsend.getSearchKeyword());
//		}else {
//			myHomeworkCnt = jihsr.countByMemberMmNum(mmNum);
//		}
		
		return myHomeworkCnt;
	}

	// 내 숙제 상세 화면 이동
	@Override
	public List<HwRecord> selectMyHomeworkDetail(int m_num, int h_num) {
		System.out.println("JpaHomeworkServiceImpl selectMyHomeworkDetail start...");
		// 숙제 제출 현황
		List<HwRecord> myHomeworkDetailList = jihrr.findByHomeworkHhNumAndMemberMmNumOrderByHrLevelAsc (h_num, m_num);
		
		return myHomeworkDetailList;
	}
	
	// 내숙제 메뉴에서 클릭한 숙제정보 가져오기
	@Override
	public Homework selectMyHomework(int h_num) {
		System.out.println("JpaHomeworkServiceImpl selectMyHomework start...");
		
		Homework myHomework = jihr.findByHhNum(h_num);
		
		return myHomework;
	}
	
	// 숙제평가에서 교육자가 생성한 숙제 중에 학습자가 제출이력이 있는 숙제만 조회
	@Override
	public List<Homework> selectHomeworkList(HwSend hwsend) {
		System.out.println("JpaHomeworkServiceImpl selectHomeworkList start...");
		
		List<Homework> homeworkList = jhr.selectHomeworkList(hwsend);
		System.out.println("JpaHomeworkServiceImpl selectHomeworkList homeworkList -> "+homeworkList.size());
		
		return homeworkList;
	}
	
	// 숙제평가에서 교육자가 생성한 숙제 중에 학습자가 제출이력이 있는 숙제명 리스트 조회
	@Override
	public List<String> selectHomeworkNameList(HwSend hwsend) {
		System.out.println("JpaHomeworkServiceImpl selectHomeworkNameList start...");
		
		List<String> homeworkNameList = jhr.selectHomeworkNameList(hwsend);
		System.out.println("JpaHomeworkServiceImpl selectHomeworkNameList homeworkNameList -> "+homeworkNameList.size());
		
		return homeworkNameList;
	}
	
		// 숙제평가에서 숙제를 클릭하면 조회되는 학습 제출 이력
	@Override
	public List<HwRecord> selectHwrecordList(int hhNum) {
		System.out.println("JpaHomeworkServiceImpl selectHwrecordList start...");
		
		List<HwRecord> hwrecordList = jihrr.findByHomeworkHhNum(hhNum);
		System.out.println("JpaHomeworkServiceImpl selectHwrecordList hwrecordList -> "+hwrecordList.size());
		
		return hwrecordList;
	}
	
	
	
	
}
