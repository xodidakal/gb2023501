package com.choongang.gb2023501.hrRepository;
//package com.choongang.gb2023501.repository;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import com.choongang.gb2023501.domain.LearnGrp;
//import com.choongang.gb2023501.model.LearnGrpDTO;
//
//@Repository
//public interface HrRepository2 extends JpaRepository<LearnGrp, Long> {
//	@Query(
//	    	"SELECT "
//	        + "new com.choongang.gb2023501.model.LearnGrpDTO(lg.lgNum, lg.lgTitle, COUNT(lg)) " 
//	        + "FROM LgJoin lj "
//	        + "JOIN lj.learnGrp lg "
//	        + "WHERE lj.lgjApproval = 1 "
//	        + "GROUP BY lg.lgNum, lg.lgTitle"
//	    )
//	    List<LearnGrpDTO> findLeanGrpCountDtoJPQL();
//}
