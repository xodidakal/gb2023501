package com.choongang.gb2023501.gbRepository;

import java.util.List;

import com.choongang.gb2023501.domain.HwSend;
import com.choongang.gb2023501.model.HomeworkDTO;

public interface JpaHomeworkRepository {

	List<HomeworkDTO> selectMyHomeworkList(int m_num);

}
