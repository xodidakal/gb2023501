package com.choongang.gb2023501.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor		// 파라미터가 없는 기본 생성자를 생성 (Board board = new Board();)
@AllArgsConstructor		// 모든 필드 값을 파라미터로 받는 생성자 생성 (Board board = new Board(2L, "content value"))
@Data
public class HwRecordPk implements Serializable {
	private int homework;
	private int member;
	private int hrLevel;
}
