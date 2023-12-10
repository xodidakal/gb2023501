package com.choongang.gb2023501.gbService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.choongang.gb2023501.gbDao.HomeworkDao;
import com.choongang.gb2023501.model.Homework;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HomeworkServiceImpl implements HomeworkService {

	private final HomeworkDao hd;
	
	@Override
	public List<Homework> selectHomeworkList() {
		System.out.println("HomeworkServiceImpl selectHomeworkList start...");
		
		List<Homework> homeworkList = hd.selectHomeworkList();
		System.out.println("HomeworkServiceImpl selectHomeworkList homeworkList.size()"+homeworkList.size());
		
		return homeworkList;
	}

}
