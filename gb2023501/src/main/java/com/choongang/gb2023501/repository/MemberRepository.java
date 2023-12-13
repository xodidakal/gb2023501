package com.choongang.gb2023501.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.choongang.gb2023501.domain.Member;

	public interface MemberRepository extends JpaRepository<Member, Integer>{

	//로그인된 아이디로 회원 정보 가져오기
	Member findByMmId(String mmId);
	
	

	//public Member findByUsername(String username); 
}
