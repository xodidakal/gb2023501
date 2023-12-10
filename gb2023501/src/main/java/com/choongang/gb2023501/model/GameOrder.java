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
	
}
