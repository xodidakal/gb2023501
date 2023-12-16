package com.choongang.gb2023501.gbRepository;

import java.util.List;

import com.choongang.gb2023501.domain.HwSend;

public interface JpaHomeworkRepository {

	List<HwSend> selectMyHomeworkList(int m_num);

}
