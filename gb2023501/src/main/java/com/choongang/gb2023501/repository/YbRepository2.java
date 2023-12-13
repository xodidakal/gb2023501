package com.choongang.gb2023501.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.choongang.gb2023501.domain.GameOrder;
import com.choongang.gb2023501.model.SalesInquiryDTO;

@Repository
public interface YbRepository2 extends JpaRepository<GameOrder, Long> {

	@Query( 
			"SELECT "
			+ "new com.choongang.gb2023501.model.SalesInquiryDTO(go.goOrderDate, count(gNum), sum(goPayment)) "
			+ "FROM GameOrder go "
			+ "WHERE go.goOrderDate BETWEEN :startDate and :endDate "
			+ "GROUP BY go.goOrderDate "
			+ "order by go.goOrderDate desc" 
			
	)
	List<SalesInquiryDTO> findSalesInquiryDtoJPQL(@Param("startDate") Date s_date, @Param("endDate") Date e_date);
	
//	@Query(
//			
//			
//			)
//	
//	
//	List<SalesInquiryDTO> findBySalesInquiryDtoOrderByGoOrderDate();




}
