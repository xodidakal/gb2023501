package com.choongang.gb2023501.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.Getter;

@Getter
@Entity
@Data
@IdClass(LgJoinPK.class)
@Table(name = "lg_join")
public class LgJoin {
	// 학습그룹번호
	@Id
	@ManyToOne	// 1학습그룹 : M학습그룹가입
	@JoinColumn(name = "lg_num")
	private LearnGrp learnGrp;
	
	// 학습자_회원번호
	@Id
	@ManyToOne	// 1학습자 : M학습그룹가입
	@JoinColumn(name = "m_num")
	private Member member;
	
	// 신청일자
	@Column(name = "lgj_joindate")
	private Date lgjJoindate;
	
	// 승인여부
	@Column(name = "lgj_approval")
	private int lgjApproval;
	
	// 승인일자
	@Column(name = "lgj_appdate")
	private Date lgjAppdate;

	
	//----- 조회용 ----- //
	@Transient
	private int lg_num;

}
