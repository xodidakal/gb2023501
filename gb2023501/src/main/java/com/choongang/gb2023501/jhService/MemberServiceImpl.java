package com.choongang.gb2023501.jhService;

import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.choongang.gb2023501.domain.Member;
import com.choongang.gb2023501.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberRepository mr;
	
	
	//로그인된 id가져오기 /없을 경우 null반환
	@Override
	public String getLoggedInId() {
		//Authentication (인증) : 증명, 유저가 누구인지 확인하는 과정
		//로그인 성공시 인증된 사용자 정보 Authentication 호출가능
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication != null && !authentication.getName().equals("anonymousUser") ){
			return authentication.getName();
		}
		return null;
	}

	//로그인된 id로 모든 사용자 정보 가져옴
	@Override
	public Member selectUserById() {
		
		//로그인된 아이디 가져오기
		String Id = getLoggedInId();
		
		Optional<Member> memberOptional = mr.findByMmId(Id);
		
		if (!memberOptional.isPresent()) {
        	throw new BadCredentialsException("아이디 불일치 =" + Id);
        }
        
    	//Optional 객체에서 값 꺼내서 memeber에 저장
        Member member = memberOptional.get();
		
		return member;
	}
	


}
