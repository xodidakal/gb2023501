package com.choongang.gb2023501.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class LGJoin {
	// 학습그룹번호
	@Id
	@Column(name = "lg_num")
	private int  lgNum;
	
	// 학습자_회원번호
	@Id
	@Column(name = "lg_num")
	private int  mNum;
	
	// 신청일자
	@Column(name = "lg_num")
	private Date lgdJoindate;
	
	// 승인여부
	@Column(name = "lg_num")
	private int  lgdApproval;
	
	// 승인일자
	@Column(name = "lg_num")
	private Date lgdAppdate;

}
