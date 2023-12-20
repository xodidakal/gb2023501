package com.choongang.gb2023501.gbService;

import java.util.List;

import com.choongang.gb2023501.domain.Homework;
import com.choongang.gb2023501.domain.HwRecord;
import com.choongang.gb2023501.domain.HwSend;
import com.choongang.gb2023501.model.HomeworkDTO;

public interface JpaHomeworkService {

	List<HwSend> selectMyHomeworkList(int m_num);

	Long myHomeworkcountBy(HwSend hwsend);

	List<HwRecord> selectMyHomeworkDetail(int m_num, int h_num);

	Homework selectMyHomework(int h_num); 

}
