package com.choongang.gb2023501.model;

import java.util.Date;

import lombok.Data;

@Data
public class Member {
	private int 	m_num;			//회원번호
	private String 	m_name;			//회원명
	private String 	m_id;			//아이디
	private String 	m_pswd;			//비밀번호
	private int 	m_category;		//회원 구분
	private int 	m_mship_type;	//자격 유형
	private String 	m_phone;		//휴대폰 번호
	private String 	m_tel;			//연락처
	private String 	m_birth;		//생년월일
	private int 	m_gender;		//성별
	private String 	m_email;		//이메일
	private String 	m_address;		//주소
	private Date 	m_regi_date;	//가입일자
	private Date 	m_modi_date;	//수정일자
	private int 	m_econsent;		//이메일 수신동의
	private int 	m_sconsent;		//SMS 수신동의
	private int 	m_dele_status;	//삭제여부

}
