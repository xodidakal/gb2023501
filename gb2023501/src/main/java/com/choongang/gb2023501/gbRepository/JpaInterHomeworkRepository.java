package com.choongang.gb2023501.gbRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.choongang.gb2023501.domain.Homework;

public interface JpaInterHomeworkRepository extends JpaRepository<Homework, Integer> {

	Homework findByHhNum(int h_num);
	
}
