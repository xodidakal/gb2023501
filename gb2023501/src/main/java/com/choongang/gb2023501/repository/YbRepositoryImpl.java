package com.choongang.gb2023501.repository;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.choongang.gb2023501.domain.EduMaterials;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Repository
public class YbRepositoryImpl implements YbRepository {

	private final EntityManager em;
	
	// 학습자료 상세
	@Override
	public Optional<EduMaterials> findByEduMaterials(int em_num) {
		System.out.println("YbRepositoryImpl findByEduMaterials start...");
		System.out.println("YbRepositoryImpl findByEduMaterials em_num -> " + em_num);
		
		EduMaterials eduMaterials = em.find(EduMaterials.class, em_num);
		System.out.println("YbRepositoryImpl selectEduMaterials eduMaterials -> " + eduMaterials);
		return Optional.ofNullable(eduMaterials);
	}
	// 학습자료 수정
	@Override
	public int updateByEduMaterials(EduMaterials eduMaterials) {
		System.out.println("YbRepositoryImpl updateByEduMaterials start...");
		int result = 0;
		
		EduMaterials eduMaterials2 = em.find(EduMaterials.class, eduMaterials.getEmNum());
		
		if(eduMaterials2 != null) {
			eduMaterials2.setEmTitle(eduMaterials.getEmTitle());
			eduMaterials2.setEmContent(eduMaterials.getEmContent());
			eduMaterials2.setEmType(eduMaterials.getEmType());
			eduMaterials2.setEmCategory(eduMaterials.getEmCategory());
			eduMaterials2.setEmDataAddr(eduMaterials.getEmDataAddr());
			eduMaterials2.setEmPayment(eduMaterials.getEmPayment());
			
			em.persist(eduMaterials2);
			System.out.println("YbRepositoryImpl updateByEduMaterials eduMaterials2 -> " + eduMaterials2);
			result = 1;
		}
		return result;

		
	}
	
}
