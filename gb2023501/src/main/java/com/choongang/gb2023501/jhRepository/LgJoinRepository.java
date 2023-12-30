package com.choongang.gb2023501.jhRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.choongang.gb2023501.domain.LgJoin;
import com.choongang.gb2023501.domain.LgJoinPK;
import com.choongang.gb2023501.domain.Member;

//LgJoin 엔터티의 기본 키가 LearnGrp와 Member 엔터티의 조합이라 복합 키인 LgJoinPK 사용
public interface LgJoinRepository extends JpaRepository<LgJoin, LgJoinPK> {
	
	List<LgJoin> findByMember(Member member);
}
