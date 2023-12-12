package com.choongang.gb2023501.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.choongang.gb2023501.domain.Member;

//public interface MemberRepository extends CrudRepository<Member, String>{
	//원래 첫번째 영상보고 아래처럼 JpaRepository상속 받았는데 안돼서 다른 영상보고 CrudRepository 상속으로 바꿈

//CRUD 함수를 JpaRepository가 들고 있음
//@Repository라는 어노테이션 없어도 JpaRepository를 상속했기 때문에 IoC 가능 자동으로 Bean으로 등록됨
//																Member모델의 pk타입은 Integer니까? 근데 m_id는 String이라 바꿈
	public interface MemberRepository extends JpaRepository<Member, Integer>{

	//findBy까진 네이밍 규칙 -> Username은 문법
	//select * from user where username = ? 쿼리문이 호출됨
	//?엔 파라미터인 username이 들어옴
	//EX) public Member findByEmail(String email);이면
	//select * from user where email = ?이 실행됨
	//jpa query method로 검색하면 더 알 수 있음
	Optional<Member> findByMmId(String mmId);

	//public Member findByUsername(String username); 
}
