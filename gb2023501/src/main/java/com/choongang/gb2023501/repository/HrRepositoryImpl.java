//package com.choongang.gb2023501.repository;
//
//import java.util.List;
//import javax.persistence.EntityManager;
//import org.springframework.stereotype.Repository;
//import com.choongang.gb2023501.domain.LearnGrp;
//import com.choongang.gb2023501.model.LearnGrpDTO;
//
//import lombok.RequiredArgsConstructor;
//
//@Repository
//@RequiredArgsConstructor
//public class HrRepositoryImpl implements HrRepository {
//	// EntityManager 연결
//	private final EntityManager em;
//
//	// 교육자마당 > 내학습그룹 (SELECT)
//	@Override
//	public List<LearnGrpDTO> learnGroupList() {
//		System.out.println("HrRepositoryImpl learnGroupList() start..");
//		
////		기본값 조회
////		List<LearnGrp> learnGrps = em.createQuery("SELECT lg FROM LearnGrp lg", LearnGrp.class)
////									 .getResultList();
////		
////		전용 DTO 활용 (lg 전체 컬럼 받기 시도 - 실패)
//		List<LearnGrpDTO> learnGrps = em.createQuery("SELECT new com.choongang.gb2023501.model.LearnGrpDTO(learnGrp, (SELECT   COUNT(lj.m_num)  " +
//																											   "FROM     LgJoin lj " +
//																											   "JOIN 	 lj.learnGrp learnGrp1 " +
//																											   "AND      lj.lgj_approval = 1 " +
//																											   "GROUP BY lj.lg_num " +
//																											   ") mmNumCnt "+
//												     "FROM  LearnGrp learnGrp", LearnGrpDTO.class)
//									    .getResultList();
////		전용 DTO 활용 (지정된 lg 컬럼 받기)
////		List<LearnGrpDTO> learnGrps = em.createQuery("SELECT   new com.choongang.gb2023501.model.LearnGrpDTO(lg.lgNum, lg.lgTitle, COUNT(lg)) " + 
////												  "FROM     LgJoin lj " +
////												  "JOIN 	lj.learnGrp lg " +
////												  "WHERE    lj.lgjApproval = 1 	 " + 
////												  "GROUP BY lg.lgNum, lg.lgTitle  "
////												  , LearnGrpDTO.class)
////										.getResultList();
//		System.out.println("HrRepositoryImpl learnGroupList() learnGrps.size() -> "+learnGrps.size());
//		
//		System.out.println("HrRepositoryImpl learnGroupList() end..");
//		return learnGrps;
//	}
//}
