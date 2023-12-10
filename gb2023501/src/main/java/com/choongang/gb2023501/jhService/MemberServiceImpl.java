package com.choongang.gb2023501.jhService;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements UserDetailsService, MemberService {

	
	@Override
	public UserDetails loadUserByUsername(String m_id) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
