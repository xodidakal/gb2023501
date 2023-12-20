package com.choongang.gb2023501.ybRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.choongang.gb2023501.domain.LearnGrp;
import com.choongang.gb2023501.domain.LgJoin;

public interface LearnGrpRepository extends JpaRepository<LearnGrp, Long> {

	void save(LgJoin lgJoin);


}
