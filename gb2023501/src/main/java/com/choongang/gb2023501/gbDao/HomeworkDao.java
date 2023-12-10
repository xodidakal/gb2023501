package com.choongang.gb2023501.gbDao;

import java.util.List;

import com.choongang.gb2023501.model.Homework;

public interface HomeworkDao {
	
	int 			selectHomeworkListCnt();
	List<Homework> 	selectHomeworkList();

}
