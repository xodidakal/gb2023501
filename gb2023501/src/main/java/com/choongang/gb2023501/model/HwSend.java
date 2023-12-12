package com.choongang.gb2023501.model;

import java.sql.Date;

import lombok.Data;

@Data
public class HwSend {
	private int h_num;		// 숙제번호
	private int m_num;		// 학습자_회원번호
	private Date hs_date;	// 교육자 전송일자
	
	// 조회용
	private int lg_num;		// 학습그룹번호
}
