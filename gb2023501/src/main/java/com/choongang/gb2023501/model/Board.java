package com.choongang.gb2023501.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Board {
	private int 		b_num;			// 글번호
	private int 		b_category;		// 게시 구분
	private int 		b_notie_type;	// 게시 분류
	private int 		b_flag;			// 공지 상단 노출
	private int 		m_num;			// 회원번호
	private String 		b_title;		// 제목
	private String 		b_content;		// 내용
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date 		b_regi_date;	// 등록일자
	private Date 		b_modi_date;	// 수정일자
	private int 		b_count;		// 조회수
	private String 		b_attach_name;	// 첨부파일명
	private String 		b_attach_path;	// 첨부파일경로
	private String 		b_ref_num;		// 답글의 원글번호
	private int			b_step;			// 답글의 순서
	
	// 조회용
	private String      m_name;			// 이름
	private int			rn;				// 목록 rownum번호
	private int			start;			// 페이지 시작번호
	private int			end;			// 페이지 끝번호
	private int			comment_count;  // 댓글 수
	private String		m_category;		// 회원 구분
	private String		search_type;	// 검색대상 필드
	private String		search_keyword;	// 검색어 키워드
	private int			count_type;		// 몇개씩 보여줄껀지
	
}
