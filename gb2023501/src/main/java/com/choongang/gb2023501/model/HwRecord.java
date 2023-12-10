package com.choongang.gb2023501.model;

import java.sql.Date;

import lombok.Data;

@Data
public class HwRecord {
	private int h_num;				// 숙제번호
	private int m_num;				// 학습자_회원번호
	private int hr_level;			// 숙제 진도
	private String hr_content;		// 학습자_학습제출내용
	private String hr_question;		// 학습자_추가질의내용
	private Date hr_subm_date;		// 학습자_제출일자
	private Date hr_modi_date;		// 숙제 수정일자
	private int hr_eval;			// 교육자 숙제평가
	private int hr_eval_date;		// 교육자 숙제평가일자
}
