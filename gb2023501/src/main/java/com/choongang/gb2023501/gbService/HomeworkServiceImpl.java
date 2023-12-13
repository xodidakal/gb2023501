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
	public int selectHomeworkListCnt(Homework homework) {
		System.out.println("HomeworkServiceImpl selectHomeworkListCnt start...");
		
		int homeworkListCnt = hd.selectHomeworkListCnt(homework);
		System.out.println("HomeworkServiceImpl selectHomeworkListCnt homeworkListCnt->"+homeworkListCnt);
		
		return homeworkListCnt;
	}
	
	@Override
	public List<Homework> selectAllHomeworkList(Homework homework) {
		System.out.println("HomeworkServiceImpl selectAllHomeworkList start...");
		
		List<Homework> allhomeworkList = hd.selectAllHomeworkList(homework);
		System.out.println("HomeworkServiceImpl selectAllHomeworkList allhomeworkList.size()->"+allhomeworkList.size());
		
		return allhomeworkList;
	}
	
	@Override
	public List<Homework> selectHomeworkList(Homework homework) {
		System.out.println("HomeworkServiceImpl selectHomeworkList start...");
		
		List<Homework> homeworkList = hd.selectHomeworkList(homework);
		System.out.println("HomeworkServiceImpl selectHomeworkList homeworkList.size()->"+homeworkList.size());
		
		return homeworkList;
	}

	@Override
	public int insertUpdateHomework(Homework homework) {
		System.out.println("HomeworkServiceImpl insertUpdateHomework start...");
		
		int result = hd.insertUpdateHomework(homework);
		System.out.println("HomeworkServiceImpl insertUpdateHomework result -> "+result);
		
		return result;
	}

}
