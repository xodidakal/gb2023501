package com.choongang.gb2023501.gbRepository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.choongang.gb2023501.domain.HwSend;
import com.choongang.gb2023501.model.HomeworkDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JpaHomeworkRepositoryImpl implements JpaHomeworkRepository {
	
	// EntityManager 연결
	private final EntityManager em;
	
	@Override
	public List<HwSend> selectMyHomeworkList(int m_num) {
		System.out.println("JpaHomeworkRepositoryImpl selectMyHomeworkList start...");
		List<HwSend> myHomeworkList = null;
		
		try {
			// JPA는 객체 중심 ORM이므로 쿼리의 모든 테이블명 및 컬럼명은 domain에 선언된 객체 및 변수명으로 작성한다.
			// 조인을 할 때에는 domain에서 조인 어노테이션을 걸어주었기 때문에 쿼리에는 FROM 절에 join 객체명만 해주면 된다.
//			String sql = "SELECT hs, hm.mmName "
//					   + "FROM HwSend hs join hs.homework h join hs.member hsm join h.member hm "
//					   + "WHERE hsm.mmNum = : mmNum";
			String sql = "SELECT hs "
					   + "FROM HwSend hs "
					   + "WHERE hs.member.mmNum = : mmNum";
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
			myHomeworkList = em.createQuery(sql, HwSend.class).setParameter("mmNum", m_num).getResultList();
			System.out.println("JpaHomeworkRepositoryImpl selectMyHomeworkList myHomeworkList -> "+myHomeworkList.size());
			
		} catch (Exception e) {
			System.out.println("JpaHomeworkRepositoryImpl selectMyHomeworkList Exception -> "+ e.getMessage());
		}
		
		return myHomeworkList;
	}

}
