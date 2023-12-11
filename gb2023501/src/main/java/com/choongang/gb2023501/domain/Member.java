package com.choongang.gb2023501.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@SequenceGenerator(name = "member_seq", // 객체기준
				   sequenceName = "member_seq",	// DB 기준
				   initialValue = 21,	// 시작 번호
				   allocationSize = 1   // 증가 값
				  )
@Table(name = "member")
public class Member {
	
	//회원번호
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "member_seq")
	@Column(name = "m_num")
	private int mmNum;			
	
	
	//회원명
	@Column(name = "m_name")
	private String mmName;		
	
	
	//아이디
	@Column(name = "m_id")
	private String mmId;			
	
	
	//비밀번호
	@Column(name = "m_pswd")
	private String mmPswd;		
	
	
	//회원 구분
	@Column(name = "m_category")
	private int category;		
	
	
	//자격 유형
	@Column(name = "m_mship_type")
	private int mshipType;		
	
	
	//휴대폰 번호
	@Column(name = "m_phone")
	private String phone;		
	
	
	//연락처
	@Column(name = "m_tel")
	private String tel;		
	
	
	//생년월일
	@Column(name = "m_birth")
	private String birth;		
	
	
	//성별
	@Column(name = "m_gender")
	private int gender;		
	
	
	//이메일
	@Column(name = "m_email")
	private String email;		
	
	
	//주소
	@Column(name = "m_address")
	private String address;	
	
	
	//가입일자
	@Column(name = "m_regi_date")
	private Date regiDate;		
	
	
	//수정일자
	@Column(name = "m_modi_date")
	private Date modiDate;		
	
	
	//이메일 수신동의
	@Column(name = "m_econsent")
	private int econsent;		
	
	
	//SMS 수신동의
	@Column(name = "m_sconsent")
	private int sconsent;		
	
	
	//삭제여부
	@Column(name = "m_dele_status")
	private int deleStatus;	
	
	
	
	

}
