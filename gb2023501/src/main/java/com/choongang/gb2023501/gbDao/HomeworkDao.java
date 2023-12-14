package com.choongang.gb2023501.gbDao;

import java.util.List;
import java.util.Map;

import com.choongang.gb2023501.model.Homework;

public interface HomeworkDao {
	
	int 			selectHomeworkListCnt(Homework homework);
	List<Homework> 	selectHomeworkList(Homework homework);
	int 			insertUpdateHomework(Homework homework);
	List<Homework> 	selectAllHomeworkList(Homework homework);
	int 			insertHwSend(Map<String, Object> map);  

}
