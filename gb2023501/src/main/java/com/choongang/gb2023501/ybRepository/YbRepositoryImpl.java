package com.choongang.gb2023501.ybRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.choongang.gb2023501.domain.EduMaterials;
import com.choongang.gb2023501.domain.GameOrder;
import com.choongang.gb2023501.domain.LearnGrp;
import com.choongang.gb2023501.model.SalesInquiryDTO;

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
			eduMaterials2.setEmAttachName(eduMaterials.getEmAttachName());
			eduMaterials2.setEmAttachPath(eduMaterials.getEmAttachPath());
			eduMaterials2.setEmDataAddr(eduMaterials.getEmDataAddr());
			eduMaterials2.setGgNum(eduMaterials.getGgNum());
			
			em.persist(eduMaterials2);
			log.info("YbRepositoryImpl updateByEduMaterials eduMaterials2 -> " + eduMaterials2);
			result = 1;
		
		return result;

		
	}
	// 학습자료 리스트 조회
	@Override
	public List<EduMaterials> findAll(EduMaterials eduMaterials) {
		int category = eduMaterials.getEmCategory();
		int type = eduMaterials.getEmType();
		int payment = eduMaterials.getEmPayment();
		
		List<EduMaterials> selectEduMaterialsList = em.createQuery("select e from EduMaterials e "
																  + "WHERE (:category = 0 OR e.emCategory =: category) "
																  + "AND (:type = 0 OR e.emType =: type) "
																  + "AND (:payment = 0 OR e.emPayment =: payment) "
																  + "order by e.emRegiDate desc", EduMaterials.class)
													  .setParameter("category", category)
													  .setParameter("type", type)
													  .setParameter("payment", payment)
													  .getResultList();
		return selectEduMaterialsList;
	}
	// 학습자료 키워드 리스트 조회
	@Override
	public List<EduMaterials> findByEduMaterialsContaining(String keyword) {
		System.out.println("YbRepositoryImpl List<EduMaterials> findByEduContaining start...");

		List<EduMaterials> findByEduMaterialsContaining = em.createQuery("select e from EduMaterials e WHERE e.emTitle LIKE :keyword", EduMaterials.class)
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
//	@Override
//	public List<GameOrder> findBySalesContaining(String s_sdate, String s_edate) {
//		System.out.println("YbRepositoryImpl findBySalesContaining start...");
//		
//		 List<GameOrder> selectDateList = em.createQuery("select g from GameOrder g where g.goOrderDate between :s_sdate and :s_edate order by g.goOrderDate desc", GameOrder.class)
//				 							.setParameter("s_sdate", java.sql.Date.valueOf(s_sdate))
//				 							.setParameter("s_edate", java.sql.Date.valueOf(s_edate))
//				 							.getResultList();
//		 log.info("YbRepositoryImpl List<GameOrder> findAllSales.size() -> " + selectDateList.size());
//		return selectDateList;
//	}
	// 매출 조회 검색 리스트 -> 일별 	
	@Override
	public List<SalesInquiryDTO> findBySalesContaining(String s_sdate, String s_edate) {
		
		System.out.println("YbRepositoryImpl findBySalesContaining start...");
		
		 List<SalesInquiryDTO> selectDateList = em.createQuery("select s from SalesInquiryDTO s where s.goOrderDate "
		 											   + "between :s_sdate and :s_edate GROUP BY s.goOrderDate order by s.goOrderDate desc", SalesInquiryDTO.class)
				 							.setParameter("s_sdate", java.sql.Date.valueOf(s_sdate))
				 							.setParameter("s_edate", java.sql.Date.valueOf(s_edate))
				 							.getResultList();
		 log.info("YbRepositoryImpl List<GameOrder> findAllSales.size() -> " + selectDateList.size());
		return selectDateList;
	}
	// 학습그룹 리스트 조회
	@Override
	public List<LearnGrp> selectLGpList() {
		
		System.out.println("YbRepositoryImpl selectLGpList start...");
		List<LearnGrp> selectLGpList = em.createQuery("select DISTINCT l from LearnGrp l where l.lgSdate <= sysdate", LearnGrp.class)
				   						 .getResultList();	
		
		return selectLGpList;
	}
	
	
	@Override
	public List<GameOrder> findByGoOrderDateAtBetween(Date s_date, Date e_date) {
		System.out.println("YbRepositoryImpl findByGoOrderDateAtBetween start...");
		List<GameOrder> findByGoOrderDateAtBetween = em.createQuery("select g from GameOrder g where to_date(goOrderDate, 'yy-MM-dd') "
																  + "between :s_date and :e_date order by to_date(goOrderDate, 'yy-MM-dd') desc", GameOrder.class)
													   .setParameter("s_date", s_date)
													   .setParameter("e_date", e_date)
				   						 			   .getResultList();	
		return findByGoOrderDateAtBetween;
	}
	@Override
	public List<GameOrder> findByGoOrderDate(Date orderDate) {
		System.out.println("YbRepositoryImpl findByGoOrderDate start...");
		List<GameOrder> findByGoOrderDate = em.createQuery("select g from GameOrder g where to_date(goOrderDate, 'yy-MM-dd') =:orderDate "
																  + "order by to_date(goOrderDate, 'yy-MM-dd') desc", GameOrder.class)
													   .setParameter("orderDate", orderDate)
				   						 			   .getResultList();	
		return findByGoOrderDate;
	}


	
}
