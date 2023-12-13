package com.choongang.gb2023501.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.choongang.gb2023501.domain.Member;

	public interface MemberRepository extends JpaRepository<Member, Integer>{

	Optional<Member> findByMmId(String mmId);
	
	

	//public Member findByUsername(String username); 
}
