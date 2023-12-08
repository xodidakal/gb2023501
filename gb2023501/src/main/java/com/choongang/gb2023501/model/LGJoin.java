package com.choongang.gb2023501.model;

import java.util.Date;
import lombok.Data;

@Data
public class LGJoin {
	private int  lg_num;		// 학습그룹번호
	private int  m_num;			// 학습자_회원번호
	private Date lgd_joindate;	// 신청일자
	private int  lgd_approval;	// 승인여부
	private Date lgd_appdate;	// 승인일자
}
