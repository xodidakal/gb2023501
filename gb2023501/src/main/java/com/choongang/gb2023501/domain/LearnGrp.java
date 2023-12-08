package com.choongang.gb2023501.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.choongang.gb2023501.model.Member;

import lombok.Data;

@Entity
@Data
@SequenceGenerator(name = "learn_grp_seq",				// 객체 기준
				   sequenceName   = "learn_grp_seq",	// DB 기준
				   initialValue   = 10021,				// 시작 번호
				   allocationSize = 1					// 증가 값
				   )
@Table(name = "learn_grp")
public class LearnGrp {
	// 학습그룹번호
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "learn_grp_seq")
	@Column(name = "lg_num")
	private int lgNum;
	
	// 교육자_회원번호
	@ManyToOne	// 1교육자 : M학습그룹
	@JoinColumn(name = "m_num")
	private Member member;

	// 게임번호
	@Column(name = "g_num")
	private int gNum;
	
	// 학습그룹명
	@Column(name = "lg_title")
	private String lgTitle;
	
	// 학습시작일자
	@Column(name = "lg_sdate")
	private String lgSdate;
	
	// 학습종료일자
	@Column(name = "lg_edate")
	private String lgEdate;
	
	// 수용가능인원
	@Column(name = "lg_to")
	private int lgTo;
	
	// 추가항목1
	@Column(name = "lg_add1")
	private String lgAdd1;
	
	// 추가항목2
	@Column(name = "lg_add2")
	private String lgAdd2;
	
	//----- 조회용 ----- //
	@Transient
	private String mName;
}
