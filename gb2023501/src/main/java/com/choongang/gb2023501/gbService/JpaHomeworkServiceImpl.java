package com.choongang.gb2023501.gbService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import com.choongang.gb2023501.model.MyHomeworkDTO;

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
	public List<HomeworkDTO> selectHomeworkList(HwSend hwsend) {
		System.out.println("JpaHomeworkServiceImpl selectHomeworkList start...");
		
		List<HomeworkDTO> homeworkList = jhr.selectHomeworkList(hwsend);
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

	// 평가한 숙제  update
	@Override
	public int updateHomeworkEval(Map<String, Object> map) {
		System.out.println("JpaHomeworkServiceImpl updateHomeworkEval start...");
		
		int result = 0;
		
		int[] hhNumList = (int[]) map.get("hhNumList");
		int[] mmNumList = (int[]) map.get("mmNumList");
		int[] hrLevelList = (int[]) map.get("hrLevelList");
		Integer[] hrEvalList = (Integer[]) map.get("hrEvalList");
		
		try {
			for(int i=0; i<hhNumList.length; i++) {
				if(!String.valueOf(hrEvalList[i]).equals("0")) {
					// Optional 객체를 사용하면 결과값이 null일 경우 자동으로 예외처리를 한다.
					Optional<HwRecord> hwrecord = jihrr.findByHomeworkHhNumAndMemberMmNumAndHrLevel(hhNumList[i],mmNumList[i],hrLevelList[i]);
					// 숙제이력이 존재한다면
					if(hwrecord.isPresent()) {
						hwrecord.get().setHrEval(hrEvalList[i]);
						hwrecord.get().setHrEvalDate(LocalDateTime.now());
						jihrr.save(hwrecord.get());
						result = 1;
					}
				}
			}
		} catch (Exception e) {
			System.out.println("JpaHomeworkServiceImpl updateHomeworkEval Exception -> "+e.getMessage());
			result = 0;
		}
		
		return result;
	}
	
	// 내 숙제 제출하기
	@Override
	public int insertUpdateMyHomework(HwRecord hwrecord) {
		System.out.println("JpaHomeworkServiceImpl insertUpdateMyHomework start... ");
		int result = 0;
		try {
			
			int hhNum = hwrecord.getHomework().getHhNum();
			int mmNum = hwrecord.getMember().getMmNum();
			int hrLevel = hwrecord.getHrLevel();
			
			Optional<HwRecord> hwrecord1 = jihrr.findByHomeworkHhNumAndMemberMmNumAndHrLevel(hhNum, mmNum, hrLevel);
			// 이미 제출한 숙제이력이 있다면
			if(hwrecord1.isPresent()) {
				System.out.println("숙제 제출 후 수정일 경우");
				hwrecord1.get().setHrContent(hwrecord.getHrContent());
				hwrecord1.get().setHrQuestion(hwrecord.getHrQuestion());
				hwrecord1.get().setHrModiDate(LocalDateTime.now());
				jihrr.save(hwrecord1.get());
				result = 1;				
			}
			// 제출한 숙제이력이 없다면
			else {
				System.out.println("숙제 제출 처음일 경우");
				hwrecord.setHrSubmDate(LocalDateTime.now());
				jihrr.save(hwrecord);
				result = 1;
			}
		} catch (Exception e) {
			System.out.println("JpaHomeworkServiceImpl insertUpdateMyHomework Exception -> "+e.getMessage());
			result = 0;	
		}
		
		return result;
	}
	
	
}
