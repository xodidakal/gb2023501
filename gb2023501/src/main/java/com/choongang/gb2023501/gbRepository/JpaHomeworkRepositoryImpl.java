package com.choongang.gb2023501.gbRepository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.choongang.gb2023501.domain.HwSend;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JpaHomeworkRepositoryImpl implements JpaHomeworkRepository {
	
	// EntityManager 연결
	private final EntityManager em;
	
	@Override
	public List<HwSend> selectMyHomeworkList(int m_num) {
		System.out.println("JpaHomeworkRepositoryImpl selectMyHomeworkList start...");
		
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}

}
