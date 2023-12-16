package com.choongang.gb2023501.gbService;

import java.util.List;
import org.springframework.stereotype.Service;

import com.choongang.gb2023501.domain.HwSend;
import com.choongang.gb2023501.gbRepository.JpaHomeworkRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JpaHomeworkServiceImpl implements JpaHomeworkService {
	
	private JpaHomeworkRepository jhr;
	
	@Override
	public List<HwSend> selectMyHomeworkList(int m_num) {
		System.out.println("JpaHomeworkServiceImpl selectMyHomeworkList start...");
		
		List<HwSend> myHomeworkList = jhr.selectMyHomeworkList(m_num);
		System.out.println("JpaHomeworkServiceImpl selectMyHomeworkList myHomeworkList -> "+myHomeworkList.size());
		
		return myHomeworkList;
	}
	
}
