package com.choongang.gb2023501.configuration.auth;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.choongang.gb2023501.domain.Member;


public class SecurityUser extends User{
	
	private Member member;
	public SecurityUser (Member member) {
		super(member.getMmId(), member.getMmPswd(), AuthorityUtils.createAuthorityList(member.getRole().toString()));
		
		this.member = member;
	}
}
