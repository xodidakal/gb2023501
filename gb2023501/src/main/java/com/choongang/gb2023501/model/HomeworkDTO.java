package com.choongang.gb2023501.model;


import java.sql.Date;

import com.choongang.gb2023501.domain.LearnGrp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HomeworkDTO {
	private int hhNum;				// 숙제번호
    private String hhTitle;			// 숙제명
    private int hhLevel;			// 숙제 진도
    private String hhDeadline;		// 제출기한
    private String mmName;			// 교육자명
}
