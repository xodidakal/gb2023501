package com.choongang.gb2023501.gbService;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.choongang.gb2023501.domain.HwSend;
import com.choongang.gb2023501.gbRepository.JpaHomeworkRepository;
import com.choongang.gb2023501.model.HomeworkDTO;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class JpaHomeworkServiceImpl implements JpaHomeworkService {
	
	private final JpaHomeworkRepository jhr;
	
	@Override
	public List<HomeworkDTO> selectMyHomeworkList(int m_num) {
		System.out.println("JpaHomeworkServiceImpl selectMyHomeworkList start...");
		
		List<HomeworkDTO> myHomeworkList = jhr.selectMyHomeworkList(m_num);
		System.out.println("JpaHomeworkServiceImpl selectMyHomeworkList myHomeworkList -> "+myHomeworkList.size());
		
		return myHomeworkList;
	}
	
}
