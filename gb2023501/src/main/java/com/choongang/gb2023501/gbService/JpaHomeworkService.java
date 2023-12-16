package com.choongang.gb2023501.gbService;

import java.util.List;

import com.choongang.gb2023501.domain.HwSend;

public interface JpaHomeworkService {

	List<HwSend> selectMyHomeworkList(int m_num); 

}
