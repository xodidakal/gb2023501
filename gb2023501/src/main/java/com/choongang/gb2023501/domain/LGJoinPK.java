package com.choongang.gb2023501.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class LGJoinPK implements Serializable {
	private int LearnGrp;
	private int member;
}
