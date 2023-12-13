package com.choongang.gb2023501.gbService;

import java.util.List;

import com.choongang.gb2023501.model.Homework;
import com.choongang.gb2023501.model.LgJoin;

public interface HomeworkService {
	int 			selectHomeworkListCnt(Homework homework);
	List<Homework> 	selectHomeworkList(Homework homework);
	int 			insertUpdateHomework(Homework homework);
	List<Homework> 	selectAllHomeworkList(Homework homework);
}
