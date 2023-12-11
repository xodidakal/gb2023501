package com.choongang.gb2023501.model;

import java.util.Date;

import lombok.Data;

@Data
public class BoardComment {
	private int 		bc_num;				// 댓글번호
	private int 		b_num;				// 글번호
	private int 		m_num;				// 회원번호
	private Date 		bc_regi_date;		// 등록일자
	private Date 		bc_modi_date;		// 수정일자
	private String		bc_content;			// 댓글내용
	
	// 조회용
	private String		m_name;				// 이름
}
