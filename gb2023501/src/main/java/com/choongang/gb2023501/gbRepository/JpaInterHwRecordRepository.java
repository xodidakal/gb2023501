package com.choongang.gb2023501.gbRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.choongang.gb2023501.domain.HwRecord;
import com.choongang.gb2023501.domain.HwRecordPk;

public interface JpaInterHwRecordRepository extends JpaRepository<HwRecord, HwRecordPk> {

	List<HwRecord> findByHomeworkHhNumAndMemberMmNumOrderByHrLevelAsc(int h_num, int m_num);

}
