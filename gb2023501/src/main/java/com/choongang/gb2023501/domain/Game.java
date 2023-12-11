package com.choongang.gb2023501.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@SequenceGenerator(name = "game_seq",				// 객체 기준
				   sequenceName   = "game_seq",		// DB 기준
				   initialValue   = 20021,			// 시작 번호
				   allocationSize = 1				// 증가 값
)
@Table(name = "game")
public class Game {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "game_seq"	// 객체 seq
					)
	@Column(name = "g_num")
	private int gNum;
	
	@Column(name = "m_num")
	private int mNum;
	
	@Column(name = "g_title")
	private String ggTitle;
	
	@Column(name = "g_step")
	private int gStep;

	@Column(name = "g_period")
	private int gPeriod;
	
	@Column(name = "g_to")
	private int gTo;
	
	@Column(name = "g_price")
	private int gPrice;
	
	@Column(name = "discount")
	private int discount;
	
	@Column(name = "g_sell_price")
	private int gSellPrice;
	
	@Column(name = "g_content")
	private String gContent;
	
	@Column(name = "g_attach_name")
	private String gAttachName;

	@Column(name = "g_attach_path")
	private String gAttachPath;
	
	@Column(name = "g_dele_status")
	private int gDeleStatus;
	
	@Column(name = "g_regi_date")
	private Date gRegiDate;
	
	@Column(name = "g_modi_date")
	private Date gModiDate;
}