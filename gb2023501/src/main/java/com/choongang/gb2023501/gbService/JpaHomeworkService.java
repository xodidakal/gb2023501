package com.choongang.gb2023501.gbService;

import java.util.List;
import java.util.Map;

import com.choongang.gb2023501.domain.Homework;
import com.choongang.gb2023501.domain.HwRecord;
import com.choongang.gb2023501.domain.HwSend;
import com.choongang.gb2023501.model.HomeworkDTO;
import com.choongang.gb2023501.model.MyHomeworkDTO;

public interface JpaHomeworkService {

	List<HwSend> 		selectMyHomeworkList(HwSend hwsend); 

	Long 				myHomeworkcountBy(HwSend hwsend);

	List<HwRecord> 		selectMyHomeworkDetail(int m_num, int h_num);

	Homework 			selectMyHomework(int h_num);

	List<HomeworkDTO> 		selectHomeworkList(HwSend hwsend);

	List<HwRecord> 		selectHwrecordList(int hhNum);

	List<String> 		selectHomeworkNameList(HwSend hwsend);

	int updateHomeworkEval(Map<String, Object> map);

	int insertUpdateMyHomework(HwRecord hwrecord);

}
