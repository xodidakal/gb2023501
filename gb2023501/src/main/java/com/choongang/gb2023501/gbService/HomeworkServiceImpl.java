package com.choongang.gb2023501.gbService;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.choongang.gb2023501.gbDao.HomeworkDao;
import com.choongang.gb2023501.model.Homework;
import com.choongang.gb2023501.model.HwRecord;
import com.choongang.gb2023501.model.HwSend;
import com.choongang.gb2023501.model.LgJoin;

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

	@Override
	public int insertHwSend(Map<String, Object> map) {
		System.out.println("HomeworkServiceImpl insertHwSend start...");
		
		int count = hd.insertHwSend(map);
		System.out.println("HomeworkServiceImpl insertHwSend result -> "+count);
		
		return count;
	}

	// 내 숙제 제출하기
	@Override
	public int insertUpdateMyHomework(HwRecord hwrecord) {
		System.out.println("HomeworkServiceImpl insertUpdateMyHomework start...");
		
		int result = hd.insertUpdateMyHomework(hwrecord);
		System.out.println("HomeworkServiceImpl insertUpdateMyHomework result ->"+result);
		
		
		return result;
	}

}
