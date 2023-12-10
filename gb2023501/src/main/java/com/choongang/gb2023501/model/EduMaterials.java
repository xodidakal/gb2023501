package com.choongang.gb2023501.model;

import java.util.Date;
import lombok.Data;

@Data
public class EduMaterials {
	private int 	em_num;				// 학습자료번호
	private int		m_num;				// 운영자_회원번호
	private int		em_category;		// 자료구분
	private int		em_type;			// 자료유형
	private int		em_payment;			// 서비스구분 
	private String 	em_title;			// 학습자료명
	private String	em_content;			// 내용
	private Date	em_regi_date;		// 등록일자
	private String	em_data_addr;		// 자료 주소
	private String	em_attach_name;		// 첨부파일명
	private String 	em_attach_path;		// 첨부파일경로
	private int		g_num;				// 게임번호

	// paging
	private String  pageNum;		// 페이지	
	private int     start;			// 첫 페이지
	private int 	end;			// 마지막 페이지
	

}