package com.choongang.gb2023501.model;

import lombok.Data;

@Data
public class LearnGrp {
	private int 	lg_num;		// 학습그룹번호
	private int 	m_num;		// 교육자_회원번호
	private int 	g_num;		// 게임번호
	private String  lg_title;	// 학습그룹명
	private String  lg_sdate;	// 학습시작일자
	private String  lg_edate;	// 학습종료일자
	private int 	lg_to;		// 수용가능인원
	private String  lg_add1;	// 추가항목1
	private String  lg_add2;	// 추가항목2
	
	// 조회용
	private String	g_attach_name;	// 게임콘텐츠 이미지
	private String	m_name;			// 회원 이름
	private int     remainningTo;	// 잔여 인원
	private String  g_title;
	private String  mmName;
	private String  searchType;
	
	// 페이징
	private String 	pageNum;			// 페이징번호
	private int 	start;				// 페이징 시작번호
	private int 	end;				// 페이징 종료번호
}
