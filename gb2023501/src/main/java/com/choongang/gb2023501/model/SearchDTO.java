package com.choongang.gb2023501.model;

import lombok.Data;

@Data
public class SearchDTO {
	private int mNum;		// 회원번호
	private int num;		// 고유번호
	private String sort;	// 정렬기준
	private String type;	// 검색유형
	private String keyword;	// 검색어
}
