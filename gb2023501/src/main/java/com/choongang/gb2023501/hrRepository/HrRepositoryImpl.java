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
	public List<LearnGrpDTO> learnGroupList(int mNum, int lg_num, String sort, String type, String keyword) {
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
			
			// QUERY 개별
			String queryCommon = "SELECT new com.choongang.gb2023501.model.LearnGrpDTO(learnGrp, (SELECT count(lj2) " +
																							     "FROM   LgJoin lj2 " +
																							     "WHERE  lj2.learnGrp = learnGrp " +
																							     "AND    lj2.lgjApproval = 1) as mmNumCnt) " +
								 "FROM LearnGrp learnGrp " +
								 "LEFT JOIN learnGrp.lgJoin lj ";
			String queryWhere1 = "WHERE learnGrp.member.mmNum = "+mNum;
			String queryWhere2 = "";
			String queryGroupBy = "GROUP BY learnGrp ";
			String queryOrderBy = "";

			System.out.println("HrRepositoryImpl learnGroupList() sort -> "+sort);
			System.out.println("HrRepositoryImpl learnGroupList() type -> "+type);
			System.out.println("HrRepositoryImpl learnGroupList() keyword -> "+keyword);
			
			// 정렬 : 미선택
			if(sort == null) {
				System.out.println("정렬 : 미선택");
				queryOrderBy = "ORDER BY MIN(learnGrp.lgTitle), MIN(learnGrp.game.ggTitle) ";
				
			// 정렬 : 학습그룹명 ㄱㄴㄷ
			} else if(sort.equals("sortLgTitle")) {
				System.out.println("정렬 : 학습그룹명 ㄱㄴㄷ");
				queryOrderBy = "ORDER BY MIN(learnGrp.lgTitle), MIN(learnGrp.game.ggTitle) ";
				
			// 정렬 : 게임콘텐츠명 ㄱㄴㄷ
			} else if(sort.equals("sortGgTitle")) {
				System.out.println("정렬 : 게임콘텐츠명 ㄱㄴㄷ");
				queryOrderBy = "ORDER BY MIN(learnGrp.game.ggTitle), MIN(learnGrp.lgTitle) ";
			}
			
			// 검색어 : X
			if(keyword == null || type == null) {
				System.out.println("검색어 : X");
				queryWhere2 = "";
				
			// 검색어 : O
			} else {
				System.out.println("검색어 : "+keyword);
				
				// 검색유형 : 학습그룹명
				if(type.equals("typeLgTitle")) {
					System.out.println("검색유형 : 학습그룹명");
					queryWhere2 = "AND learnGrp.lgTitle LIKE '%"+keyword+"%' ";
					
				// 검색유형 : 게임콘텐츠명
				} else if(type.equals("typeGgTitle")) {
					System.out.println("검색유형 : 게임콘텐츠명");
					queryWhere2 = "AND learnGrp.game.ggTitle LIKE '%"+keyword+"%' ";
				}
			}

			// QUERY 통합
			String query = queryCommon + queryWhere1 + queryWhere2 + queryGroupBy + queryOrderBy;
			System.out.println("HrRepositoryImpl learnGroupList() query -> "+query);
						
			learnGrps = em.createQuery(query, LearnGrpDTO.class)
					.getResultList();
//			
//			learnGrps = em.createQuery("SELECT new com.choongang.gb2023501.model.LearnGrpDTO(learnGrp, (SELECT count(lj2) " +
//																									   "FROM   LgJoin lj2 " +
//																									   "WHERE  lj2.learnGrp = learnGrp " +
//																									   "AND    lj2.lgjApproval = 1) as mmNumCnt) " +
//										"FROM LearnGrp learnGrp " +
//										"LEFT JOIN learnGrp.lgJoin lj " +
//										"WHERE learnGrp.lgTitle = '강남 A반' " +
//										"GROUP BY learnGrp " + 
//										"ORDER BY MIN(learnGrp.lgTitle) ", LearnGrpDTO.class)
//					      .getResultList();
		
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
										"AND learnGrp.member.mmNum = " + mNum +
										"GROUP BY learnGrp ", LearnGrpDTO.class)
						  .getResultList();		
		}
		
		System.out.println("HrRepositoryImpl learnGroupList() learnGrps.size() -> "+learnGrps.size());
		
		System.out.println("HrRepositoryImpl learnGroupList() end..");
		return learnGrps;
	}
	
	// 교육자마당 > 내학습그룹 (DELETE / JPA)
	@Override
	public void learnGroupListDelete(int lg_num) {
		System.out.println("HrRepositoryImpl learnGroupListDelete() start..");
		
		em.createQuery("DELETE FROM LearnGrp lg " + 
					   "WHERE lg.lgNum = :lgNum "
					   )
		  .setParameter("lgNum", lg_num)
		  .executeUpdate();
		
		System.out.println("HrRepositoryImpl learnGroupListDelete() end..");
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
		
		List<MemberDTO> members = em.createQuery("SELECT new com.choongang.gb2023501.model.MemberDTO(m, lj.lgjAppdate, lj.lgjJoindate) " + 
												 "FROM   LgJoin lj " + 
												 "JOIN   lj.member m " + 
												 "WHERE  lj.learnGrp.lgNum = :lgNum " + 
												 "AND    lj.lgjApproval = 1 "
												 , MemberDTO.class)
									.setParameter("lgNum", lg_num)
									.getResultList();
		
		System.out.println("HrRepositoryImpl joinedMemberList() members.size() -> "+members.size());

		System.out.println("HrRepositoryImpl joinedMemberList() end..");
		return members;
	}

	// 교육자마당 > 학습그룹 가입 승인 - 화면 (SELECT / JPA) - 신청자 명단
	@Override
	public List<MemberDTO> joiningMemberList(int lg_num) {
		System.out.println("HrRepositoryImpl joiningMemberList() start..");
		
		List<MemberDTO> members = em.createQuery("SELECT new com.choongang.gb2023501.model.MemberDTO(m, lj.lgjAppdate, lj.lgjJoindate) " + 
												 "FROM   LgJoin lj " + 
												 "JOIN   lj.member m " + 
												 "WHERE  lj.learnGrp.lgNum = :lgNum " +
												 "AND    lj.lgjApproval = 0 "
												 , MemberDTO.class)
									.setParameter("lgNum", lg_num)
									.getResultList();
		
		System.out.println("HrRepositoryImpl learnGroupList() members.size() -> "+members.size());

		System.out.println("HrRepositoryImpl joiningMemberList() end..");
		return members;
	}

	// 교육자마당 > 학습그룹 가입 승인 - 실행 (UPDATE / JPA)
	@Override
	public void learnGroupJoinApproval(int lg_num, int m_num) {
		System.out.println("HrRepositoryImpl learnGroupJoinApproval() start..");
		
		em.createQuery("UPDATE LgJoin lj " + 
					   "SET    lgjApproval = 1, lgjAppdate = sysdate " + 
					   "WHERE  lj.learnGrp.lgNum = :lg_num AND lj.member.mmNum = :m_num "
					   )
		  .setParameter("lg_num", lg_num)
		  .setParameter("m_num", m_num)
		  .executeUpdate();
		
		System.out.println("HrRepositoryImpl learnGroupJoinApproval() end..");
	}
	
}
