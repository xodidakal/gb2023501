// 희라
package com.choongang.gb2023501.model;

import java.util.Date;
import com.choongang.gb2023501.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
	private Member member;
	private Date   lgjAppdate;	// 가입 승인 일자
	private Date   lgjJoindate;	// 가입 신청 일자
}
