package com.choongang.gb2023501.configuration.auth;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.choongang.gb2023501.domain.Member;
import com.choongang.gb2023501.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
	private final MemberRepository mr;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//nullPoint 방지 위해 Optional사용
		Optional<Member> op = mr.findById(username);
//		Optional<Member> op = mr.findByMmId(mmId);
		if(!op.isPresent()) {
			throw new UsernameNotFoundException("사용자 없음");
		}
		//패스워드는 알아서 검증해줌
		//op.get();으로 member가져옴
		Member member = op.get();
		
		//첫번째 영상에선 UserDetails상속받은 PrincipalDetails로 리턴했는데 
		//지금은 User상속받은 SecurityUser로 리턴함
		return new SecurityUser(member);
	}
}
