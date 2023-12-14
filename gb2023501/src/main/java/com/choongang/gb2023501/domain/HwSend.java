package com.choongang.gb2023501.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity		// 엔티티 생성
@Data		
@IdClass(HwSendPk.class)	// HwSendPk 클래스를 통해 복합키를 사용할 수 있도록 IdClass 어노테이션을 선언
@Table(name="hw_send")
public class HwSend {
	
	@Id							// PK
	@ManyToOne					// 1숙제 : N숙제배포
	@JoinColumn(name="h_num")	// h_num으로 Homework 테이블과 조인
	private Homework homework;	// 숙제번호
	
	@Id							// PK
	@ManyToOne					// 1숙제 : N학습자번호
	@JoinColumn(name="m_num")	// m_num으로 Member 테이블과 조인
	private Member member;		// 학습자_회원번호
	
	@Column(name="hs_date")
	private Date hsDate;		// 전송일자
	
}
