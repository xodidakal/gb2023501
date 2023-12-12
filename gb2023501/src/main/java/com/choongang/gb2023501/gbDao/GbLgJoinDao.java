package com.choongang.gb2023501.gbDao;

import java.util.List;

import com.choongang.gb2023501.model.Homework;
import com.choongang.gb2023501.model.HwSend;
import com.choongang.gb2023501.model.LearnGrp;
import com.choongang.gb2023501.model.LgJoin;

public interface GbLgJoinDao {

	List<LearnGrp> 	selectLgJoinList(Homework homework);
	List<LgJoin> 	selectLgJoinMemberList(HwSend hwsend);

}
