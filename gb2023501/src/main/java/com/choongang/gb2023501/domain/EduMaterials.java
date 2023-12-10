package com.choongang.gb2023501.domain;

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
import lombok.Data;

@Entity				// jpa -> entity써야함  domain 폴더에는 entity 관련된 것들 생성
@Data
@SequenceGenerator(name 		= "em_num_seq",			// 객체 SEQ
				   sequenceName = "em_num_seq",	// DB SEQ
				   allocationSize = 1,		// -> 1씩 증가
				   initialValue = 40021			// 1부터 시작
				  )
@Table(name = "edu_Materials")
public class EduMaterials {
		@Id     // -> Primary Key
		@GeneratedValue(strategy = GenerationType.SEQUENCE,
						generator = "em_num_seq"	// 객체 seq
					   )
		@Column(name = "em_num")			// --> NUMBER 타입 길이 설정 X
		private Long   emNum;
		
//		// 관계 설정
//		@ManyToOne	// team의 PK
//		@JoinColumn(name = "m_num")
//		private Member member;
//		private Long   mNum;
//		
		@Column(name = "em_category")
		private Long emCategory;

		@Column(name = "em_type")
		private Long emType;
		
		@Column(name = "em_payment")
		private Long emPayment;
		
		@Column(name = "em_title")
		private Long emTitle;
		
		@Column(name = "em_regi_date")
		private Long emRegiDate;
		
		@Column(name = "em_data_addr")
		private Long emDataAddr;
		
		@Column(name = "em_attach_name")
		private Long emAttachName;
		
		@Column(name = "em_attach_path")
		private Long emAttachPath;
		
		@Column(name = "g_num")
		private Long gNum;
		


}
