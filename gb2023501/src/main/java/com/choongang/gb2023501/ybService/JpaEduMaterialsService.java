package com.choongang.gb2023501.ybService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.choongang.gb2023501.domain.EduMaterials;
import com.choongang.gb2023501.domain.GameOrder;
import com.choongang.gb2023501.model.SalesInquiryDTO;
import com.choongang.gb2023501.repository.YbRepository2;
import com.choongang.gb2023501.repository.YbRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional  // -> 원자성,commit or rollback이 되어야함
@RequiredArgsConstructor
public class JpaEduMaterialsService {
	private final YbRepository yr;
	private final YbRepository2 yr2;
	// 학습자료 상세조회
	public Optional<EduMaterials> findByEduMaterials(int em_num) {
		System.out.println("YbJpaEduMaterialsService findByEduMaterials start...");
		System.out.println("YbJpaEduMaterialsService findByEduMaterials em_num -> " + em_num);
			
		Optional<EduMaterials> findByEduMaterials =  yr.findByEduMaterials(em_num);
		return findByEduMaterials;
	}
	// 학습자료 수정
	public int updateByEduMaterials(EduMaterials eduMaterials) {
		System.out.println("YbJpaEduMaterialsService updateByEduMaterials start...");
		System.out.println("YbJpaEduMaterialsService updateByEduMaterials eduMaterials -> " + eduMaterials);
		
		int result = yr.updateByEduMaterials(eduMaterials);
		return result;
	}
	// 학습자료 리스트
	public List<EduMaterials> getListAllEduMaterials() {
		log.info("YbJpaEduMaterialsService getListAllEduMaterials start...");
		List<EduMaterials> listEduMaterials = yr.findAll();
		log.info("YbJpaEduMaterialsService getListAllEduMaterials listEduMaterials.size() -> " + listEduMaterials.size());

		return listEduMaterials;
	}
	// 매출 조회 화면
	public List<GameOrder> getListAllGameOrder() {
		log.info("YbJpaEduMaterialsService getListAllEduMaterials start...");
		List<GameOrder>	listSales = yr.findAllSales();
		
		return listSales;
	}
	// 학습자료 검색 리스트
	public List<EduMaterials> findByEduMaterialsContaining(String keyword) {
		log.info("YbJpaEduMaterialsService findByEduContaining start...");
		
		List<EduMaterials> findByEduContaining = yr.findByEduMaterialsContaining(keyword);
		return findByEduContaining;
	}
	// 매출 조회 검색 리스트
	public List<SalesInquiryDTO> findBySalesContaining(Date s_date, Date e_date) {
		log.info("YbJpaEduMaterialsService findByEduContaining start...");
		List<SalesInquiryDTO> gameOrder = yr2.findSalesInquiryDtoJPQL(s_date, e_date);
		//List<SalesInquiryDTO> findBySalesContaining = yr.findBySalesContaining(startDate, endDate);
		return gameOrder;
	}
//	public List<SalesInquiryDTO> findBySalesInquiryDtoOrderByGoOrderDate() {
//		List<SalesInquiryDTO> findBySalesInquiryDtoOrderByGoOrderDate = yr2.findBySalesInquiryDtoOrderByGoOrderDate();
//		return findBySalesInquiryDtoOrderByGoOrderDate;
//	}

	



}
