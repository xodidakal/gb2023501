package com.choongang.gb2023501.model;

import java.util.Date;

import lombok.Data;

//회원 검색 조건을 담는 DTO
@Data
public class MemberSearchCriteriaDTO {
	 // 회원명 검색 조건
    private String mmName;

    // 회원 아이디 검색 조건
    private String mmId;

    // 검색 시작일
    private Date startDate;

    // 검색 종료일
    private Date endDate;
}
