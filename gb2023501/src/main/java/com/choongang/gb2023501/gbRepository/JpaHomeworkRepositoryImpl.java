package com.choongang.gb2023501.gbRepository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.choongang.gb2023501.domain.Homework;
import com.choongang.gb2023501.domain.HwRecord;
import com.choongang.gb2023501.domain.HwSend;
import com.choongang.gb2023501.model.HomeworkDTO;
import com.choongang.gb2023501.model.MyHomeworkDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JpaHomeworkRepositoryImpl implements JpaHomeworkRepository {
	
	// EntityManager 연결
	private final EntityManager em;
	
	@Override
	public List<HwSend> selectMyHomeworkList(HwSend hwsend) {
		System.out.println("JpaHomeworkRepositoryImpl selectMyHomeworkList start...");
		List<HwSend> myHomeworkList = null;
		
		try {
			// JPA는 객체 중심 ORM이므로 쿼리의 모든 테이블명 및 컬럼명은 domain에 선언된 객체 및 변수명으로 작성한다.
			// 조인을 할 때에는 domain에서 조인 어노테이션을 걸어주었기 때문에 쿼리에는 FROM 절에 join 객체명만 해주면 된다.
//			String sql = "SELECT hs, hm.mmName "
//					   + "FROM HwSend hs join hs.homework h join hs.member hsm join h.member hm "
//					   + "WHERE hsm.mmNum = : mmNum";
//			String sql = "SELECT new com.choongang.gb2023501.model.HomeworkDTO(hs, (SELECT max(hr1.hrLevel) "
//																				 + "FROM HwRecord hr1 "
//																				 + "WHERE hr1.hwsend = hs ) as hrMaxLevel) "
//					   + "FROM HwSend hs "
//					   + "LEFT JOIN hs.hwrecord hr2 "
//					   + "WHERE hs.member.mmNum = : mmNum "
//					   + "GROUP BY hs";
			// createQuery(실행할 쿼리, 반환값을 담을 domain)
			// setParameter(쿼리에서 받을 파라미터명, 실제 파라미터값이 담겨있는 변수명)
			// getResultList() -> 쿼리 결과값을 리스트로 반환			
			
			String sql = "SELECT hs "
					   + "FROM HwSend hs "
					   + "WHERE hs.member.mmNum = : mmNum ";
			
			System.out.println("여기서 값이 있으면 검색 조건을 탄다. hwsend.getSearchType() -> "+hwsend.getSearchType());
			// 검색조건이 있을 경우
			if(hwsend.getSearchType() != null) {
				// 숙제명으로 검색했을 때
				if(hwsend.getSearchType().equals("title")) {
					System.out.println("검색 조건 숙제명 -> "+hwsend.getSearchKeyword());
					sql += "AND hs.homework.hhTitle Like '%"+ hwsend.getSearchKeyword() + "%'";
				}
				// 교육자로 검색했을 때
				else if(hwsend.getSearchType().equals("teacher")) {
					System.out.println("검색 조건 교육자 -> "+hwsend.getSearchKeyword());
					sql += "AND hs.homework.member.mmName Like '%"+ hwsend.getSearchKeyword() + "%'";
				}
			}
			
			// 제출한 숙제이력을 검색할 때
			if(hwsend.getSearchSubmit() > 0) {
				// 제출한 숙제이력이 있는 숙제만 검색
				if(hwsend.getSearchSubmit() == 1) {
					sql += "AND hs.homework.hhNum IN ( SELECT hr.homework.hhNum "
													+ "FROM HwRecord hr "
													+ "WHERE hr.member.mmNum = : mmNum "
													+ "GROUP BY hr.homework.hhNum )";
				}
				// 제출한 숙제이력이 없는 숙제만 검색
				else {
					sql += "AND hs.homework.hhNum NOT IN ( SELECT hr.homework.hhNum "
														+ "FROM HwRecord hr "
														+ "WHERE hr.member.mmNum = : mmNum "
														+ "GROUP BY hr.homework.hhNum )";
				}
			}
			// 숙제 전송일자 최신
			sql += "ORDER BY hs.homework.hhDeadline";

			myHomeworkList = em.createQuery(sql, HwSend.class).setParameter("mmNum", hwsend.getMember().getMmNum()).getResultList();
			System.out.println("JpaHomeworkRepositoryImpl selectMyHomeworkList myHomeworkList -> "+myHomeworkList.size());
			
		} catch (Exception e) {
			System.out.println("JpaHomeworkRepositoryImpl selectMyHomeworkList Exception -> "+ e.getMessage());
		}
		
		return myHomeworkList;
	}
	
	// 숙제 평가 목록 가져오기
	@Override
	public List<HomeworkDTO> selectHomeworkList(HwSend hwsend) {
		System.out.println("JpaHomeworkRepositoryImpl selectHomeworkList start...");
		List<HomeworkDTO> homeworkList = null;
		
		try {
//			String sql = "SELECT hr.homework.hhNum "
//					   + "FROM HwRecord hr "
//					   + "GROUP BY hr.homework.hhNum";
//			List<Integer> hhNumList = em.createQuery(sql, Integer.class).getResultList();
//			String sql2 = "SELECT h "
//						+ "FROM Homework h "
//						+ "WHERE h.hhNum IN :hhNumList "
//						+ "AND h.member.mmNum = :mmNum ";
//			if(hwsend.getSearchKeyword() != null) {
//				sql2 += "AND h.hhTitle LIKE '%"+hwsend.getSearchKeyword()+"%'";
//			}
//			homeworkList = em.createQuery(sql2, Homework.class)
//											.setParameter("hhNumList", hhNumList)
//											.setParameter("mmNum", hwsend.getMember().getMmNum())
//											.getResultList();
			String sql = "SELECT new com.choongang.gb2023501.model.HomeworkDTO(hr.homework, "
																			+ "count(hr.homework.hhNum) as hrTotalCount, "
																			+ "(SELECT count(hr2.homework.hhNum) "
																			 + "FROM HwRecord hr2 "
																			 + "WHERE hr2.hrEval IS NOT NULL "
																			 + "AND hr.homework.hhNum = hr2.homework.hhNum "
																			 + "GROUP BY hr2.homework.hhNum) as hrEvalCount)"
					   + "FROM HwRecord hr "
					   + "WHERE hr.homework.hhNum IN ( SELECT hr.homework.hhNum "
					   								 + "FROM HwRecord hr "
					   								 + "GROUP BY hr.homework.hhNum) "
					   + "AND hr.homework.member.mmNum = :mmNum ";
			if(hwsend.getSearchKeyword() != null) {
				sql += "AND hr.homework.hhTitle LIKE '%"+hwsend.getSearchKeyword()+"%' ";
			}
			sql += "GROUP BY hr.homework";
			
			homeworkList = em.createQuery(sql, HomeworkDTO.class)
							 .setParameter("mmNum", hwsend.getMember().getMmNum())
							 .getResultList();
		} catch (Exception e) {
			System.out.println("JpaHomeworkRepositoryImpl selectHomeworkList Exception -> "+ e.getMessage());
		}
		
		
		return homeworkList;
	}

	@Override
	public List<String> selectHomeworkNameList(HwSend hwsend) {
		System.out.println("JpaHomeworkRepositoryImpl selectHomeworkNameList start...");
		List<String> homeworkNameList = null;
		try {
			String sql = "SELECT hr.homework.hhNum "
					   + "FROM HwRecord hr "
					   + "GROUP BY hr.homework.hhNum";
			List<Integer> hhNumList = em.createQuery(sql, Integer.class).getResultList();
			String sql2 = "SELECT h.hhTitle "
						+ "FROM Homework h "
						+ "WHERE h.hhNum IN :hhNumList "
						+ "AND h.member.mmNum = :mmNum";
			homeworkNameList = em.createQuery(sql2, String.class)
											.setParameter("hhNumList", hhNumList)
											.setParameter("mmNum", hwsend.getMember().getMmNum())
											.getResultList();
		} catch (Exception e) {
			System.out.println("JpaHomeworkRepositoryImpl selectHomeworkNameList Exception -> "+ e.getMessage());
		}

		return homeworkNameList;
	}

}
