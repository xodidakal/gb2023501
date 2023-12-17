package com.choongang.gb2023501.jhRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.choongang.gb2023501.domain.Member;

	public interface MemberRepository extends JpaRepository<Member, Integer>{

	//로그인된 아이디로 회원 정보 가져오기
	Member findByMmId(String mmId);

	//휴대폰 번호와 이름으로 기존 사용자인지 찾기
	Optional<Member> findBymmNameAndPhone(String name, String phone);

	//이메일과 이름으로 기존 사용자인지 찾기
	Optional<Member> findBymmNameAndEmail(String name, String email);

	
	

	//public Member findByUsername(String username); 
}
