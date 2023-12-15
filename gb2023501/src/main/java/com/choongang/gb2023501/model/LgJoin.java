package com.choongang.gb2023501.model;

import java.util.Date;
import lombok.Data;

@Data
public class LgJoin {
	private int  lg_num;		// 학습그룹번호
	private int  m_num;			// 학습자_회원번호
	private Date lgj_joindate;	// 신청일자
	private int  lgj_approval;	// 승인여부
	private Date lgj_appdate;	// 승인일자
	
	// 조회용
	private String lg_title;	// 학습그룹명
	private int	   h_num;		// 숙제번호
	private String m_name;		// 회원명
	private String m_phone;		// 휴대폰 번호
	private int hr_level;		// 학습자 레벨
	private int	existence;		// 숙제 배포 이력에 존재여부
}