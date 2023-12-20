package com.choongang.gb2023501.gbRepository;

import java.util.List;

import com.choongang.gb2023501.domain.Homework;
import com.choongang.gb2023501.domain.HwSend;
import com.choongang.gb2023501.model.HomeworkDTO;

public interface JpaHomeworkRepository {

	List<HwSend> 		selectMyHomeworkList(HwSend hwsend);

	List<Homework> 		selectHomeworkList(HwSend hwsend);

}
