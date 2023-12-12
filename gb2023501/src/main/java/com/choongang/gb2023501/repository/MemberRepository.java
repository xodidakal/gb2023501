package com.choongang.gb2023501.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.choongang.gb2023501.domain.Member;

//CRUD 함수를 JpaRepository가 들고 있음
//@Repository라는 어노테이션 없어도 JpaRepository를 상속했기 때문에 IoC 가능 자동으로 Bean으로 등록됨
//																Member모델의 pk타입은 Integer니까
public interface MemberRepository extends JpaRepository<Member, Integer>{
}
