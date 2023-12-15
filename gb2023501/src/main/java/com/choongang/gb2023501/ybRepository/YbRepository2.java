package com.choongang.gb2023501.ybRepository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.choongang.gb2023501.domain.EduMaterials;
import com.choongang.gb2023501.domain.GameOrder;
import com.choongang.gb2023501.model.MonthSalesDTO;
import com.choongang.gb2023501.model.SalesInquiryDTO;

@Repository
public interface YbRepository2 extends JpaRepository<GameOrder, Long> {
	
	@Query( 
			"SELECT "
			+ "new com.choongang.gb2023501.model.SalesInquiryDTO(go.goOrderDate, count(go.game), sum(goPayment)) "
			+ "FROM GameOrder go "
			+ "WHERE go.goOrderDate BETWEEN :startDate and :endDate "
			+ "GROUP BY go.goOrderDate "
			+ "order by go.goOrderDate desc" 
	)
	List<SalesInquiryDTO> findSalesInquiryDtoJPQL(@Param("startDate") Date s_date, @Param("endDate") Date e_date);
	
//	@Query(
//		    "SELECT NEW com.choongang.gb2023501.model.MonthSalesDTO(FUNCTION('MONTH', go.goOrderDate), COUNT(go.game), SUM(goPayment)) " +
//		    "FROM GameOrder go " +
//		    "WHERE go.goOrderDate BETWEEN :sDate AND :eDate " +
//		    "GROUP BY FUNCTION('MONTH', go.goOrderDate) " +
//		    "ORDER BY FUNCTION('MONTH', go.goOrderDate) DESC"
//		)
//	
//	List<MonthSalesDTO> findSalesInquiryDtoJPQL1(@Param("sDate") Date s_date, @Param("eDate") Date e_date);
	
	@Query(
			"SELECT NEW com.choongang.gb2023501.model.SalesInquiryDTO(TRUNC(go.goOrderDate, 'MM') AS monthStart, COUNT(go.game) AS orderCount, SUM(goPayment) AS totalPayment) " +
					"FROM GameOrder go " +
					"WHERE go.goOrderDate BETWEEN :sDate AND :eDate " +
					"GROUP BY TRUNC(go.goOrderDate, 'MM') " +
					"ORDER BY monthStart DESC"
		)
	
	List<SalesInquiryDTO> findSalesInquiryDtoJPQL1(@Param("sDate") Date s_date, @Param("eDate") Date e_date);

	List<GameOrder> findByGoOrderDate(Date orderDate);


}



