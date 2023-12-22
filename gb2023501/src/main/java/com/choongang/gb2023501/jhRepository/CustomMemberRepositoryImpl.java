package com.choongang.gb2023501.jhRepository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.choongang.gb2023501.domain.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Repository
public class CustomMemberRepositoryImpl implements CustomMemberRepository {
	
	private final EntityManager em;
	
//	//회원목록 가져오기 (조건 검색 포함)
//	public List<Member> selectMemberList(Member member) {
//		String startDate 		= member.getStartDate();
//		String endDate	 		= member.getEndDate();
//		String searchType		= member.getSearchType();
//		String SearchCriteria	= member.getSearchCriteria();
//		int	   category			= member.getCategory();
//		int    mshipType		= member.getMshipType();
//		
//		
//		String query = "SELECT m "
//		List<Member> memberList = em.createQuery(deleteQuery)
//		return memberList;
//		
//	}

}
