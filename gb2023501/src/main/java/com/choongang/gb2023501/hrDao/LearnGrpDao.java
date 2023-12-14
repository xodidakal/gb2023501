package com.choongang.gb2023501.hrDao;

import java.util.List;
import com.choongang.gb2023501.model.Game;

public interface LearnGrpDao {

	// 교육자마당 > 학습그룹 등록 - 화면 (SELECT)
	List<Game> learnGroupForm(int g_num);

}
