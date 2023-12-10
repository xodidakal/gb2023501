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
	public List<Homework> selectHomeworkList() {
		System.out.println("HomeworkDaoImpl selectHomeworkList start...");
		List<Homework> homeworkList = null;
		try {
			homeworkList = session.selectList("gbSelectHomeworkList");
			System.out.println("HomeworkDaoImpl selectHomeworkList homeworkList.size()"+homeworkList.size());
		} catch (Exception e) {
			System.out.println("HomeworkDaoImpl selectHomeworkList Exception->"+e.getMessage());
		}
		return homeworkList;
	}

}
