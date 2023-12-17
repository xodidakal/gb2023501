package com.choongang.gb2023501.jhService;

import java.util.Optional;

import com.choongang.gb2023501.domain.Member;

public interface MemberService {
	//로그인된 아이디 가져오기
	public String getLoggedInId();

	//로그인된 회원번호 가져오기
	public int selectMmNumById();
	
	//로그인된 회원 정보 가져오기
	public Optional<Member> selectUserById();

	//휴대폰 번호와 이름으로 기존 사용자인지 찾기
	public Optional<Member> findByNameAndPhone(String name, String phone);

	//이메일과 이름으로 기존 사용자인지 찾기
	public Optional<Member> findByNameAndEmail(String name, String email);

}
