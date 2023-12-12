package com.choongang.gb2023501.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.choongang.gb2023501.domain.EduMaterials;
import com.choongang.gb2023501.domain.GameOrder;

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
			eduMaterials2.setEmNum(eduMaterials.getEmNum());
			eduMaterials2.setEmTitle(eduMaterials.getEmTitle());
			eduMaterials2.setEmContent(eduMaterials.getEmContent());
			eduMaterials2.setEmType(eduMaterials.getEmType());
			eduMaterials2.setEmCategory(eduMaterials.getEmCategory());
			eduMaterials2.setEmDataAddr(eduMaterials.getEmDataAddr());
			eduMaterials2.setEmPayment(eduMaterials.getEmPayment());
			
			em.persist(eduMaterials2);
			log.info("YbRepositoryImpl updateByEduMaterials eduMaterials2 -> " + eduMaterials2);
			result = 1;
		
		return result;

		
	}
	// 학습자료 리스트 조회
	@Override
	public List<EduMaterials> findAll() {
		System.out.println("YbRepositoryImpl List<EduMaterials> findAll start...");

		List<EduMaterials> selectEduMaterialsList = em.createQuery("select e from EduMaterials e", EduMaterials.class)
									   .getResultList();
		return selectEduMaterialsList;
	}
	// 학습자료 키워드 리스트 조회
	@Override
	public List<EduMaterials> findByEduMaterialsContaining(String keyword, String type) {
		System.out.println("YbRepositoryImpl List<EduMaterials> findByEduContaining start...");
		EduMaterials eduMaterials = new EduMaterials();

		List<EduMaterials> findByEduMaterialsContaining = em.createQuery("select e from EduMaterials e WHERE e.type LIKE: type And e.emTitle  LIKE :keyword", EduMaterials.class)
															.setParameter("type",  "%" + type + "%")
															.setParameter("keyword", "%" + keyword + "%")
															
				   									  		.getResultList();
	

		return findByEduMaterialsContaining;
	}
	
	@Override
	public List<GameOrder> findAllSales() {
		System.out.println("YbRepositoryImpl List<GameOrder> findAll start...");
		
		 List<GameOrder> selectSaleList = em.createQuery("select s from GameOrder s", GameOrder.class)
				 							.getResultList();
		 log.info("YbRepositoryImpl List<GameOrder> findAllSales.size() -> " + selectSaleList.size());
		return selectSaleList;
	}

	
}
