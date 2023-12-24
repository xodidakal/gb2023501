package com.choongang.gb2023501.model;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import lombok.Data;

//회원 검색 조건을 담는 DTO
@Data
public class MemberSearchCriteriaDTO {
	//검색 조건
	private String searchType;	
	private String SearchCriteria;	
	
	
//	// 회원 폰번호
//	private String phone;
	
	//회원 구분(교육자/학습자/일반인/운영자)
	private Integer category;	
	
	//회원 자격(유/무료)
	private Integer mshipType;	
	
//	 // 회원명 검색 조건
//    private String mmName;
//
//    // 회원 아이디 검색 조건
//    private String mmId;

    // 검색 시작일
    //@Temporal(TemporalType.DATE)
    private String startDate;

    // 검색 종료일
    @Temporal(TemporalType.DATE)
    private String endDate;
}
