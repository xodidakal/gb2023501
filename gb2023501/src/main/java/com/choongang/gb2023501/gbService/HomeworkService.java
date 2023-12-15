package com.choongang.gb2023501.gbService;

import java.util.List;
import java.util.Map;

import com.choongang.gb2023501.model.Homework;
import com.choongang.gb2023501.model.HwSend;
import com.choongang.gb2023501.model.LgJoin;

public interface HomeworkService {
	int 			selectHomeworkListCnt(Homework homework);
	List<Homework> 	selectHomeworkList(Homework homework);
	int 			insertUpdateHomework(Homework homework);
	List<Homework> 	selectAllHomeworkList(Homework homework);
	int 			insertHwSend(Map<String, Object> map);
	List<LgJoin> 	selectLgHwSendMemberList(HwSend hwsend);
} 
