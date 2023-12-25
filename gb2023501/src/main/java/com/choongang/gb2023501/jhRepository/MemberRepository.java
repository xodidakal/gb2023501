package com.choongang.gb2023501.jhRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.choongang.gb2023501.domain.Member;

	//MemberRepository구현하는 클래스 안만든 이유 : Spring Data JPA가 해당 인터페이스를 분석하여 실행 시간에 관련된 구현체를 동적으로 생성하기 때문
	//개발자가 Repository 구현체를 별도로 작성하지 않아도 인터페이스만으로도 기본적인 CRUD 작업 및 특정한 쿼리 메소드들을 자동으로 생성하는 것이 Spring Data JPA의 목적
	//따라서 JpaRepository를 상속받은 인터페이스의 메소드 네이밍 규칙에 따라 조건에 맞는 JPQL 쿼리 자동 생성 및 실행
	public interface MemberRepository extends JpaRepository<Member, Integer>, JpaSpecificationExecutor<Member>{
//		public interface MemberRepository extends JpaRepository<Member, Integer>, CustomMemberRepository{

	//로그인된 아이디로 회원 정보 가져오기
	Member findByMmId(String mmId);

	//휴대폰 번호와 이름으로 기존 사용자인지 찾기
	Optional<Member> findBymmNameAndPhone(String name, String phone);

	//이메일과 이름으로 기존 사용자인지 찾기
	Optional<Member> findBymmNameAndEmail(String name, String email);

	// 입력한 ID와 같은 아이디가 있는지 확인
    boolean existsByMmId(String mmId);

    //아이디, 이름,폰으로 유저 찾기
	Optional<Member> findByMmIdAndPhoneAndMmName(String id, String phone, String name);

	//아이디, 이름,이메일로 유저 찾기
	Optional<Member> findByMmIdAndEmailAndMmName(String id, String email, String name);

	//회원번호로 회원정보 가져오기
	Optional<Member> findByMmNum(int mmNum);

	//CustomMemberRepository 상속받을 때 사용하려 했던 것
//	List<Member> selectMemberList(Member member);
	
	
	
	//public Member findByUsername(String username); 
}
