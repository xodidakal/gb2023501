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
				   sequenceName = "member_seq",	// DB 기
				   initialValue = 21,	// 시작 번호
				   allocationSize = 1   // 증가 값
				  )
@Table(name = "member_seq")
public class Member {
	
	//회원번호
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "member_seq")
	@Column(name = "m_num")
	private int mNum;			
	
	
	//회원명
	@Column(name = "m_name")
	private String mName;		
	
	
	//아이디
	@Column(name = "m_id")
	private String mId;			
	
	
	//비밀번호
	@Column(name = "m_pswd")
	private String mPswd;		
	
	
	//회원 구분
	@Column(name = "m_category")
	private int mCategory;		
	
	
	//자격 유형
	@Column(name = "m_mship_type")
	private int mMshipType;		
	
	
	//휴대폰 번호
	@Column(name = "m_phone")
	private String mPhone;		
	
	
	//연락처
	@Column(name = "m_tel")
	private String mTel;		
	
	
	//생년월일
	@Column(name = "m_birth")
	private String mBirth;		
	
	
	//성별
	@Column(name = "m_gender")
	private int mGender;		
	
	
	//이메일
	@Column(name = "m_email")
	private String mEmail;		
	
	
	//주소
	@Column(name = "m_address")
	private String mAddress;	
	
	
	//가입일자
	@Column(name = "m_regi_date")
	private Date mRegiDate;		
	
	
	//수정일자
	@Column(name = "m_modi_date")
	private Date mModiDate;		
	
	
	//이메일 수신동의
	@Column(name = "m_econsent")
	private int mEconsent;		
	
	
	//SMS 수신동의
	@Column(name = "m_sconsent")
	private int mSconsent;		
	
	
	//삭제여부
	@Column(name = "m_dele_status")
	private int mDeleStatus;	
	
	
	
	

}
