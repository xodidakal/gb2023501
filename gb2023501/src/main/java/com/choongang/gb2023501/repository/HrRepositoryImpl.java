package com.choongang.gb2023501.repository;

import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import com.choongang.gb2023501.domain.LearnGrp;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class HrRepositoryImpl implements HrRepository {
	// EntityManager 연결
	private final EntityManager em;

	// 교육자마당 > 내학습그룹 (SELECT)
	@Override
	public List<LearnGrp> learnGroupList() {
		System.out.println("HrRepositoryImpl learnGroupList() start..");
		
//		List<LearnGrp> learnGrps = em.createQuery("SELECT lg FROM LearnGrp lg", LearnGrp.class)
//									 .getResultList();
		
//		List<LearnGrp> learnGrps = em.createQuery("SELECT lg, (SELECT   COUNT(lj.mmNum) " + 
//												  "            FROM     LGJoin lj  "+
//												  "            WHERE    lj.lg_num = lg.lg_num " + 
//												  "            AND      lj.lgdApproval = 1 " + 
//												  "            GROUP BY lj.lg_num " + 
//												  "            ) mmNumCnt " + 
//												  "FROM   LearnGrp lg", LearnGrp.class)
//									 .getResultList();
//		SELECT lg.LG_num , lg.lg_title  , COUNT(lj.m_num)
//		FROM   learn_grp lg, lg_join lj
//		WHERE  lg.lg_num = lj.lg_num 
//		AND    lj.lgd_approval = 1
//		GROUP BY lg.LG_num , lg.lg_title
//		
//		List<LearnGrp> learnGrps = em.createQuery("SELECT count(lg) " + 
//									  "            FROM     learn_grp lg ,   lg_join lj "+
//									  "            WHERE    lg.lg_num = lj.lg_num      " + 
//									  "            AND      lj.lgd_approval = 1 " + 
//									  "            GROUP BY lg.LG_num , lg.lg_title " + 
//									  "            ) mmNumCnt " + 
//									  "FROM   LearnGrp lg", LearnGrp.class)
//	 .getResultList();
//		System.out.println("HrRepositoryImpl learnGroupList() learnGrps.size() -> "+learnGrps.size());
		
		System.out.println("HrRepositoryImpl learnGroupList() end..");
		return null;
	}

}
