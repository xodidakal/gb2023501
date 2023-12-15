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

import lombok.Data;

@Entity
@Data
@SequenceGenerator(name = "game_order_seq",				// 객체 기준
				   sequenceName   = "game_order_seq",		// DB 기준
				   initialValue   = 50021,			// 시작 번호
				   allocationSize = 1				// 증가 값
)
@Table(name = "game_order")
public class GameOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "game_order_seq"	// 객체 seq
					)
	@Column(name = "go_num")
	private int goNum;
	
	@ManyToOne	// Member 의 PK
	@JoinColumn(name = "m_num")
	private Member member;
	
	@ManyToOne	// Member 의 PK
	@JoinColumn(name = "g_num")
	private Game game;
	
	@Column(name = "go_order_type")
	private int goOrderType;

	@Column(name = "go_payment")
	private int goPayment;
	
	@Column(name = "go_order_date")
	private Date goOrderDate;
	
	@Column(name = "go_depositor")
	private String goDepositor;
}