// 희라
package com.choongang.gb2023501.model;

import com.choongang.gb2023501.domain.LearnGrp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 교육자마당 > 내학습그룹 (SELECT / JPA)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LearnGrpDTO {
	private LearnGrp learnGrp;
	private Long   	 mmCnt;		// 가입승인인원
}
