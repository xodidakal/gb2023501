package com.choongang.gb2023501.gbDao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.choongang.gb2023501.model.Homework;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class HomeworkDaoImpl implements HomeworkDao {
	
	private final SqlSession session;

	@Override
	public int selectHomeworkListCnt(Homework homework) {
		System.out.println("HomeworkDaoImpl selectHomeworkListCnt start...");
		
		int homeworkListCnt = 0;
		try {
			homeworkListCnt = session.selectOne("gbSelectHomeworkListCnt", homework);
			System.out.println("HomeworkDaoImpl selectHomeworkListCnt homeworkListCnt -> "+homeworkListCnt);
		} catch (Exception e) {
			System.out.println("HomeworkDaoImpl selectHomeworkListCnt Exception->"+e.getMessage());
		}
		return homeworkListCnt;
	}
	
	@Override
	public List<Homework> selectAllHomeworkList(Homework homework) {
		System.out.println("HomeworkDaoImpl selectAllHomeworkList start...");
		List<Homework> allhomeworkList = null;
		try {
			allhomeworkList = session.selectList("gbAllSelectHomeworkList", homework);
		} catch (Exception e) {
			System.out.println("HomeworkDaoImpl selectAllHomeworkList Exception->"+e.getMessage());
		}
		
		return allhomeworkList;
	}
	
	@Override
	public List<Homework> selectHomeworkList(Homework homework) {
		System.out.println("HomeworkDaoImpl selectHomeworkList start...");
		List<Homework> homeworkList = null;
		try {
			homeworkList = session.selectList("gbSelectHomeworkList", homework);
			System.out.println("HomeworkDaoImpl selectHomeworkList homeworkList.size()"+homeworkList.size());
		} catch (Exception e) {
			System.out.println("HomeworkDaoImpl selectHomeworkList Exception->"+e.getMessage());
		}
		return homeworkList;
	}

	@Override
	public int insertUpdateHomework(Homework homework) {
		System.out.println("HomeworkDaoImpl insertUpdateHomework start...");
		int result = 0;
		try {
			result = session.insert("gbInsertUpdateHomework", homework);
			System.out.println("HomeworkDaoImpl insertUpdateHomework result -> "+result);
		} catch (Exception e) {
			System.out.println("HomeworkDaoImpl insertUpdateHomework Exception->"+e.getMessage());
		}
		
		return result;
	}
}
