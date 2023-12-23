package com.choongang.gb2023501.domain;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Entity		// 엔티티 생성
@Data		
@IdClass(HwRecordPk.class)	// HwRecordPk 클래스를 통해 복합키를 사용할 수 있도록 IdClass 어노테이션을 선언
@Table(name="hw_record")
public class HwRecord {
	@Id								// PK
	@ManyToOne						// 1숙제 : N숙제배포
	@JoinColumn(name="h_num")		// h_num으로 HwSend 테이블과 조인
	private Homework homework;		// 숙제번호
	
	@Id								// PK
	@ManyToOne						// 1숙제 : N학습자번호
	@JoinColumn(name="m_num")		// m_num으로 Member 테이블과 조인
	private Member member;			// 학습자_회원번호
	
	@Id								// PK
	@Column(name="hr_level")	
	private int hrLevel;			// 현재 학습자 레벨
	
	@Column(name="hr_content")	
	private String hrContent;		// 숙제 제출 내용
	
	@Column(name="hr_question")
	private String hrQuestion;		// 추가 질의 내용
	
	@Column(name="hr_subm_date")
	private LocalDateTime hrSubmDate;		// 숙제 제출 일시
	
	@Column(name="hr_modi_date")
	private LocalDateTime hrModiDate;		// 숙제 수정 일시
	
	@Column(name="hr_eval")
	private Integer hrEval;			// 숙제 교육자 평가
	
	@Column(name="hr_eval_date")
	private LocalDateTime hrEvalDate;		// 숙제 교육자 평가일시
	
	// 조회용
	
	@Transient	
	private int hrMaxLevel;		// 학습자의 현재 최고 레벨
	

}
