package com.choongang.gb2023501.model;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;

//회원 검색 조건을 담는 DTO
@Data
public class MemberSearchCriteriaDTO {
	//검색 조건
	private String searchType;	
	private String searchValue;	
	
	
//	// 회원 폰번호
//	private String phone;
	
	//회원 구분(교육자/학습자/일반인/운영자)
	private Integer searchCategory;	
	
	//회원 자격(유/무료)
	private Integer searchMshipType;	
	
//	 // 회원명 검색 조건
//    private String mmName;
//
//    // 회원 아이디 검색 조건
//    private String mmId;

    // 검색 시작일
    //@Temporal(TemporalType.DATE)
    private String startDate;

    // 검색 종료일
//    @Temporal(TemporalType.DATE)
    private String endDate;
    
    private int page;
    
    public String getCriteriaListLink() {
    	UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
    																	.queryParam("searchType", this.searchType)
    																	.queryParam("searchValue", this.searchValue)
    																	.queryParam("searchCategory", this.searchCategory)
    																	.queryParam("searchMshipType", this.searchMshipType)
    																	.queryParam("startDate", this.startDate)
    																	.queryParam("endDate", this.endDate)
    																	.queryParam("page", this.page);
    	return builder.toUriString();
		
	}
    
    
    
}
