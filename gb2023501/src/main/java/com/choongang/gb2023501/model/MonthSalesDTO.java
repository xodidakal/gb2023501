package com.choongang.gb2023501.model;

import java.util.Date;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MonthSalesDTO {

	private int  goOrderDate;

	private long  salesCnt;
	private long  salesSum;
}

