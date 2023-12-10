package com.choongang.gb2023501.model;

import java.sql.Date;

import lombok.Data;

@Data
public class Homework {
	private int 	h_num;			// 숙제번호
	private int 	m_num;			// 교육자_회원번호
	private String 	h_title;		// 숙제명
	private String 	h_content;		// 숙제내용
	private int 	h_level;		// 숙제 최종진도
	private String 	h_deadline;		// 제출기한
	private Date	h_regi_date;	// 등록일자
	private Date	h_modi_date;	// 수정일자
	
	// 조회용
	private String 	pageNum;			// 페이징번호
	private int 	start;				// 페이징 시작번호
	private int 	end;				// 페이징 종료번호
}
