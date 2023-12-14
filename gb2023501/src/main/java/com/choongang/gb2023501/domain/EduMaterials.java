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

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity				// jpa -> entity써야함  domain 폴더에는 entity 관련된 것들 생성
@Data
@SequenceGenerator(name 		= "em_num_seq",			// 객체 SEQ
				   sequenceName = "em_num_seq",	// DB SEQ
				   allocationSize = 1,		// -> 1씩 증가
				   initialValue = 40001			// 1부터 시작
				  )
@Table(name = "edu_Materials")
public class EduMaterials {
		@Id     // -> Primary Key
		@GeneratedValue(strategy = GenerationType.SEQUENCE,
						generator = "em_num_seq"	// 객체 seq
					   )
		@Column(name = "em_num")			// --> NUMBER 타입 길이 설정 X
		private int   emNum;
		
		// 관계 설정
		@ManyToOne	// Member 의 PK
		@JoinColumn(name = "m_num")
		private Member member;
			
		@Column(name = "em_category")
		private int emCategory;

		@Column(name = "em_type")
		private int emType;
		
		@Column(name = "em_payment")
		private int emPayment;
		
		@Column(name = "em_title")
		private String emTitle;
		
		@Column(name = "em_content")
		private String emContent;
		
		@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyyMMdd", timezone="Asia/Seoul")
		@Column(name = "em_regi_date")
		private Date emRegiDate = new Date();
		
		@Column(name = "em_data_addr")
		private String emDataAddr;
		
		@Column(name = "em_attach_name")
		private String emAttachName;
		
		@Column(name = "em_attach_path")
		private String emAttachPath;
		
		@Column(name = "g_num", nullable = true)
		private Long ggNum;

}
