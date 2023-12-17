package com.choongang.gb2023501.gbRepository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.choongang.gb2023501.domain.HwSend;
import com.choongang.gb2023501.model.HomeworkDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JpaHomeworkRepositoryImpl implements JpaHomeworkRepository {
	
	// EntityManager 연결
	private final EntityManager em;
	
	@Override
	public List<HomeworkDTO> selectMyHomeworkList(int m_num) {
		System.out.println("JpaHomeworkRepositoryImpl selectMyHomeworkList start...");
		List<HomeworkDTO> myHomeworkList = null;
		
		try {
			
			String sql = "SELECT new com.choongang.gb2023501.model.HomeworkDTO(h.hhNum, h.hhTitle, h.hhLevel, h.hhDeadline, hm.mmName) "
					   + "FROM HwSend hs join hs.homework h join hs.member hsm join h.member hm "
					   + "WHERE hsm.mmNum = : mmNum";
			myHomeworkList = em.createQuery(sql, HomeworkDTO.class).setParameter("mmNum", m_num).getResultList();
			System.out.println("JpaHomeworkRepositoryImpl selectMyHomeworkList myHomeworkList -> "+myHomeworkList.size());
			
		} catch (Exception e) {
			System.out.println("JpaHomeworkRepositoryImpl selectMyHomeworkList Exception -> "+ e.getMessage());
		}
		
		return myHomeworkList;
	}

}
