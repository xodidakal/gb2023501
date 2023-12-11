package com.choongang.gb2023501.model;

import java.util.Date;
import lombok.Data;

@Data
public class GameOrder {
	private int 	go_num;
	private int 	m_num;
	private int 	g_num;
	private int		go_order_type;
	private int		go_payment;
	private Date	go_order_date;
	private String	go_depositor;
	
	// game
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
	
	// 조회용
	private String searchType;
	private String keyword;
	private String pageNum;
	private int	   start;
	private int	   end;
}
