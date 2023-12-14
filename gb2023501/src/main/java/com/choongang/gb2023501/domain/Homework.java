package com.choongang.gb2023501.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity	// JPA 엔티티 선언
@Data	// getter/setter, constructor를 생성해주는 lombok 어노테이션
@SequenceGenerator(name 		  = "homework_seq",	// 객체 SEQ
				   sequenceName   = "homework_seq",	// DB SEQ
				   initialValue   = 30032,			// 초기값
				   allocationSize = 1				// 증가값
				  )
@Table(name="homework")	// homework의 테이블명을 가진 테이블을 매핑 (별도로 지정해주지 않으면 클래스 이름의 테이블과 매핑)
public class Homework {
	
	@Id	// PK
	@GeneratedValue(strategy  = GenerationType.SEQUENCE,
					generator = "homework_seq"
					)
	@Column(name="h_num") // h_num을 가진 컬럼과 연결
	private int hhNum;
	
	@Column(name="m_num")
	private int mmNum;
	
	@Column(name="h_title")
	private int hhTitle;
	
	@Column(name="h_content")
	private int hhContent;
	
	@Column(name="h_level")
	private int hhLevel;
	
	@Column(name="h_deadline")
	private int hhDeadline;
	
	@Column(name="h_regi_date")
	private int hhRegiDate;
	
	@Column(name="h_modi_date")
	private int hhModiDate;
}
