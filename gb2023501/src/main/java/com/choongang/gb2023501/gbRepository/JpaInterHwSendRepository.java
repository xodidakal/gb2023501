package com.choongang.gb2023501.gbRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.choongang.gb2023501.domain.HwSend;
import com.choongang.gb2023501.domain.HwSendPk;

//																 domain	, domain의 키값		
public interface JpaInterHwSendRepository extends JpaRepository<HwSend, HwSendPk> {
	
	// 숙제 배포 이력에 있는 내 숙제 가져오기
	// List<HwSend> findByMemberMmNum(int m_num);
	
	// 내 숙제 총 개수
	Long countByMemberMmNum(int m_num);

	// 검색에 따른 내 숙제 총 개수 가져오기
	// Long countByMemberMmNumAndSearchTypeAndSearchKeyword(int mmNum, String searchType, String searchKeyword);
}
