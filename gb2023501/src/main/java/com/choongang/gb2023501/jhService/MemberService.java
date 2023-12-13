package com.choongang.gb2023501.jhService;

import com.choongang.gb2023501.domain.Member;

public interface MemberService {
	public String getLoggedInId();

	public Member selectUserById();
}
