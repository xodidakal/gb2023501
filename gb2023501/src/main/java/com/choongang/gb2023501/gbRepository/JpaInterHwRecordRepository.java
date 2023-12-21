package com.choongang.gb2023501.gbRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.choongang.gb2023501.domain.HwRecord;
import com.choongang.gb2023501.domain.HwRecordPk;

public interface JpaInterHwRecordRepository extends JpaRepository<HwRecord, HwRecordPk> {

	List<HwRecord> findByHomeworkHhNumAndMemberMmNumOrderByHrLevelAsc(int h_num, int m_num);
	// 숙제 평가 메뉴에서 숙제 클릭시 조회되는 숙제 제출 이력
	List<HwRecord> findByHomeworkHhNum(int hhNum);
	// 평가해야할 숙제 제출 이력 가져오기
	Optional<HwRecord> findByHomeworkHhNumAndMemberMmNumAndHrLevel(int i, int j, int k);

}
