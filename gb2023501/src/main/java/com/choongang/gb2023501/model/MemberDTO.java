package com.choongang.gb2023501.model;

import java.util.Date;
import com.choongang.gb2023501.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
	private Member member;
	private Date   lgjAppdate;
}
