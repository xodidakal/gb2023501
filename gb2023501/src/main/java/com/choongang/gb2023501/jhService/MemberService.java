package com.choongang.gb2023501.jhService;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.choongang.gb2023501.domain.LgJoin;
import com.choongang.gb2023501.domain.Member;
import com.choongang.gb2023501.model.MemberSearchCriteriaDTO;

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

	// 입력한 ID와 같은 아이디가 있는지 확인
	public boolean existsByMmId(String id);

	//회원가입
	public Member join(@Valid Member member);

	//아이디, 이름,폰으로 유저 찾기
	public Optional<Member> findByMmIdAndPhoneAndName(String id, String phone, String name);

	//아이디, 이름,이메일로 유저 찾기
	public Optional<Member> findByMmIdAndEmailAndName(String id, String email, String name);

	//회원목록 전체 조회 ->삭제예정
	public List<Member> findAll();

	//회원목록 전체 조회(페이지네이션)
	public Page<Member> findAll(Pageable pageable);

	//검색조건 있는 회원 목록 조회
	public Page<Member> SearchMemberList(MemberSearchCriteriaDTO searchCriteria, Pageable pageable);

	//회원번호로 회원정보 가져오기
	public Member findByMmNum(int mmNum);

	//회원번호로 가입된 학습그룹 리스트 가져오기
	public List<LgJoin> selectJoinedLearnGroupList(Member member);



}
