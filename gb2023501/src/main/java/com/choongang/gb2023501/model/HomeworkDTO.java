package com.choongang.gb2023501.model;

import com.choongang.gb2023501.domain.Homework;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HomeworkDTO {
	private Homework homework;		// 학습자가 제출한 숙제 이력
	private Long hrTotalCount;		// 제출한 숙제별 개수
	private Long hrEvalCount;		// 제출한 숙제에서 평가가 완료된 숙제 개수
}
