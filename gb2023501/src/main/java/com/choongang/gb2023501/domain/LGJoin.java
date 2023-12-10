package com.choongang.gb2023501.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@IdClass(LGJoinPK.class)
@Table(name = "lg_join")
public class LGJoin {
	// 학습그룹번호
	@Id
	@ManyToOne	// 1학습그룹 : M학습그룹가입
	@JoinColumn(name = "lg_num")
	private LearnGrp LearnGrp;
	
	// 학습자_회원번호
	@Id
	@ManyToOne	// 1학습자 : M학습그룹가입
	@JoinColumn(name = "m_num")
	private Member member;
	
	// 신청일자
	@Column(name = "lgd_joindate")
	private Date lgdJoindate;
	
	// 승인여부
	@Column(name = "lgd_approval")
	private int lgdApproval;
	
	// 승인일자
	@Column(name = "lgd_appdate")
	private Date lgdAppdate;

}
