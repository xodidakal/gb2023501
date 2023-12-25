package com.choongang.gb2023501.gbRepository;

import java.util.List;

import com.choongang.gb2023501.domain.Homework;
import com.choongang.gb2023501.domain.HwRecord;
import com.choongang.gb2023501.domain.HwSend;
import com.choongang.gb2023501.model.HomeworkDTO;
import com.choongang.gb2023501.model.MyHomeworkDTO;

public interface JpaHomeworkRepository {

	List<HwSend> 		selectMyHomeworkList(HwSend hwsend);

	List<HomeworkDTO> 		selectHomeworkList(HwSend hwsend);

	List<String> selectHomeworkNameList(HwSend hwsend);

}
