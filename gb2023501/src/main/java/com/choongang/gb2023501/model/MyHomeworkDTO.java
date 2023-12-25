package com.choongang.gb2023501.model;


import java.sql.Date;

import com.choongang.gb2023501.domain.HwSend;
import com.choongang.gb2023501.domain.LearnGrp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyHomeworkDTO {
	private HwSend hwsend;	// 배포된 숙제이력
	private Long hrMaxLevel;	// 학습자의 현재 최고 레벨
}
