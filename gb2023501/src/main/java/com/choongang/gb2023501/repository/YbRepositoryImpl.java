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
	public void updateByEduMaterials(EduMaterials eduMaterials) {
		System.out.println("YbRepositoryImpl updateByEduMaterials start...");

		EduMaterials eduMaterials2 = em.find(EduMaterials.class, eduMaterials.getEmNum());
		
		System.out.println("YbRepositoryImpl updateByEduMaterials eduMaterials2 -> " + eduMaterials2);
		
		em.persist(eduMaterials2);

		
	}
	
}
