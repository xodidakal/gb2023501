package com.choongang.gb2023501.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.choongang.gb2023501.domain.EduMaterials;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class YbRepositoryImpl implements YbRepository {

	private final EntityManager em;
	
	@Override
	public EduMaterials findByEduMaterials(int em_num) {
		System.out.println("YbRepositoryImpl findByEduMaterials start...");
		System.out.println("YbRepositoryImpl findByEduMaterials em_num -> " + em_num);
		
		EduMaterials eduMaterials = em.find(EduMaterials.class, em_num);
		System.out.println("YbRepositoryImpl selectEduMaterials eduMaterials -> " + eduMaterials);
		return eduMaterials;
	}
	
}
