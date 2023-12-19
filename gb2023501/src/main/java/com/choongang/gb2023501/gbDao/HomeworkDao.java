package com.choongang.gb2023501.gbDao;

import java.util.List;
import java.util.Map;

import com.choongang.gb2023501.model.Homework;
import com.choongang.gb2023501.model.HwRecord;
import com.choongang.gb2023501.model.HwSend;
import com.choongang.gb2023501.model.LgJoin;

public interface HomeworkDao {
	
	int 			selectHomeworkListCnt(Homework homework);
	List<Homework> 	selectHomeworkList(Homework homework);
	int 			insertUpdateHomework(Homework homework);
	List<Homework> 	selectAllHomeworkList(Homework homework);
	int 			insertHwSend(Map<String, Object> map);
	int 			insertUpdateMyHomework(HwRecord hwrecord); 

}
