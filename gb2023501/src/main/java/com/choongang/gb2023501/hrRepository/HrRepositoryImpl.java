package com.choongang.gb2023501.hrRepository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import com.choongang.gb2023501.domain.Game;
import com.choongang.gb2023501.domain.LearnGrp;
import com.choongang.gb2023501.domain.Member;
import com.choongang.gb2023501.model.LearnGrpDTO;
import com.choongang.gb2023501.model.MemberDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class HrRepositoryImpl implements HrRepository {
	// EntityManager 연결
	private final EntityManager em;

	// 교육자마당 > 내학습그룹 (SELECT / JPA)
	// 교육자마당 > 학습그룹 상세 (SELECT / JPA) - 학습그룹 정보
	@Override
	public List<LearnGrpDTO> learnGroupList(int lg_num) {
		System.out.println("HrRepositoryImpl learnGroupList() start..");
		
////	기본값 조회
//		List<LearnGrp> learnGrps = em.createQuery("SELECT lg FROM LearnGrp lg", LearnGrp.class)
//									 .getResultList();
//		
////	전용 DTO 활용 (지정된 lg 컬럼 받기 - 성공)
////							   but 개선 필요 (필요한 컬럼을 매번 지정해주어야 하기 때문)
//		List<LearnGrpDTO> learnGrps = em.createQuery("SELECT   new com.choongang.gb2023501.model.LearnGrpDTO(lg.lgNum, lg.lgTitle, COUNT(lg)) " + 
//												  "FROM     LgJoin lj " +
//												  "JOIN 	lj.learnGrp lg " +
//												  "WHERE    lj.lgjApproval = 1 	 " + 
//												  "GROUP BY lg.lgNum, lg.lgTitle  "
//												  , LearnGrpDTO.class)
//										.getResultList();
//		
////	전용 DTO 활용 (lg 전체 컬럼 받기 시도 - 실패)
//		List<LearnGrpDTO> learnGrps = em.createQuery("SELECT new com.choongang.gb2023501.model.LearnGrpDTO(learnGrp, (SELECT   COUNT(lj.m_num)  " +
//			"FROM     LgJoin lj " +
//			"JOIN 	  lj.learnGrp learnGrp1 " +
//			"WHERE    lj.lgj_approval = 1 " +
//			"GROUP BY lj.lg_num " +
//			") mmNumCnt "+
//			"FROM  LearnGrp learnGrp", LearnGrpDTO.class)
//			.getResultList();
//		
////	전용 DTO 활용 (lg 전체 컬럼 받기 시도 - 실패)
////								 서브쿼리 결과가 multiRow
//		List<LearnGrpDTO> learnGrps = em.createQuery("SELECT new com.choongang.gb2023501.model.LearnGrpDTO(learnGrp, (SELECT   COUNT(lj.member)  " +
//																												     "FROM     LgJoin lj " +
//																												     "JOIN 	 lj.learnGrp learnGrp1 " +
//																												     "WHERE    lj.lgjApproval = 1 " +
//																												     "GROUP BY lj.learnGrp " +
//																												     ") as mmNumCnt) "+
//												     "FROM  LearnGrp learnGrp", LearnGrpDTO.class)
//									    .getResultList();
//		
//	전용 DTO 활용 (lg 전체 컬럼 받기 시도 - 성공!)
////							 서브쿼리 없는 구조로 변경
////							but 개선 필요 (LgJoin 값 없을 경우 누락되어 OUTER JOIN 필요)
//		List<LearnGrpDTO> learnGrps = em.createQuery("SELECT   new com.choongang.gb2023501.model.LearnGrpDTO(learnGrp, COUNT(lj.member)) " +
//													 "FROM     LgJoin lj " +
//													 "JOIN 	   lj.learnGrp learnGrp " +
//													 "WHERE    lj.lgjApproval = 1 " +
//													 "GROUP BY learnGrp "
//													 , LearnGrpDTO.class)
//			    						.getResultList();
//		
//	전용 DTO 활용 (lg 전체 컬럼 받기 시도 - 성공!)
////							 OUTER JOIN으로 변경
		List<LearnGrpDTO> learnGrps = new ArrayList<LearnGrpDTO>();
		
		// LIST
		if (lg_num == 0) {
			System.out.println("HrRepositoryImpl learnGroupList() lg_num == 0");
			learnGrps = em.createQuery("SELECT new com.choongang.gb2023501.model.LearnGrpDTO(learnGrp, (SELECT count(lj2) " +
																									   "FROM   LgJoin lj2 " +
																									   "WHERE  lj2.learnGrp = learnGrp " +
																									   "AND    lj2.lgjApproval = 1) as mmNumCnt) " +
										"FROM LearnGrp learnGrp " +
										"LEFT JOIN learnGrp.lgJoin lj " +
					"WHERE learnGrp.lgTitle = '강남 A반' " +
					"GROUP BY learnGrp " /*
											 * + "ORDER BY MIN(learnGrp.lgTitle) "
											 */, LearnGrpDTO.class)
					      .getResultList();
		
		// DETAIL (lg_num 존재 -> single row)
		} else {
			System.out.println("HrRepositoryImpl learnGroupList() lg_num != 0");
			learnGrps = em.createQuery("SELECT new com.choongang.gb2023501.model.LearnGrpDTO(learnGrp, (SELECT count(lj2) " +
																									   "FROM   LgJoin lj2 " +
																									   "WHERE  lj2.learnGrp = learnGrp " +
																									   "AND    lj2.lgjApproval = 1) as mmNumCnt) " +
										"FROM LearnGrp learnGrp " +
										"LEFT JOIN learnGrp.lgJoin lj " +
										"WHERE learnGrp.lgNum = " + lg_num + 
										"GROUP BY learnGrp ", LearnGrpDTO.class)
						  .getResultList();		
		}
		
		System.out.println("HrRepositoryImpl learnGroupList() learnGrps.size() -> "+learnGrps.size());
		
		System.out.println("HrRepositoryImpl learnGroupList() end..");
		return learnGrps;
	}

	// 교육자마당 > 학습그룹 등록 - 실행 (INSERT / JPA)
	@Override
	public LearnGrp learnGroupFormInsert(LearnGrp learnGrp) {
		System.out.println("HrRepositoryImpl learnGroupFormInsert() start..");
		
		// Game 객체 세팅
		Game game = new Game();
		game.setGNum(learnGrp.getG_num());
		learnGrp.setGame(game);
		
		// Member 객체 세팅
		Member member = new Member();
		member.setMmNum(learnGrp.getM_num());
		learnGrp.setMember(member);
		
		// INSERT
		em.persist(learnGrp);
		
		System.out.println("HrRepositoryImpl learnGroupFormInsert() end..");
		return learnGrp;
	}

	// 교육자마당 > 학습그룹 상세 (SELECT / JPA) - 학습자 명단
	@Override
	public List<MemberDTO> joinedMemberList(int lg_num) {
		System.out.println("HrRepositoryImpl joinedMemberList() start..");
		
		List<MemberDTO> members = em.createQuery("SELECT new com.choongang.gb2023501.model.MemberDTO(m, lj.lgjAppdate) " + 
												 "FROM   LgJoin lj " + 
												 "JOIN   lj.member m " + 
												 "WHERE  lj.learnGrp.lgNum = :lgNum "
												 , MemberDTO.class)
									.setParameter("lgNum", lg_num)
									.getResultList();
		
		System.out.println("HrRepositoryImpl learnGroupList() members.size() -> "+members.size());

		System.out.println("HrRepositoryImpl joinedMemberList() end..");
		return members;
	}
	
}
