package com.choongang.gb2023501.model;

import java.util.Date;
import lombok.Data;

@Data
public class Game {
	private int 	g_num;
	private int 	m_num;
	private String	g_title;
	private int		g_step;
	private int		g_period;
	private int		g_to;
	private int		g_price;
	private int		discount;
	private int		g_sell_price;
	private String	g_content;
	private String	g_attach_name;
	private String	g_attach_path;
	private int		g_dele_status;
	private Date	g_regi_date;
	private Date	g_modi_date;
	
	//gameOrder
	private int 	go_num;
	private int		go_order_type;
	private int		go_payment;
	private Date	go_order_date;
	private String	go_depositor;
	
	// 조회용
	private String searchType;
	private String keyword;
	private String pageNum;
	private int	   start;
	private int	   end;
	
	// 내구독 조회
	private String lg_title;
	
	// (희라)학습그룹등록 시 게임콘텐츠선택 화면
	private int	   remainingPeriod;		// 잔여 기간
	private int	   remainingTo;			// 잔여 인원
	
}
