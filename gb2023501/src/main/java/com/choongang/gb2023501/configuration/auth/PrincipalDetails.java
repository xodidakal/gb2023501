package com.choongang.gb2023501.configuration.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.choongang.gb2023501.domain.Member;

import lombok.RequiredArgsConstructor;

//직접 로그인 처리 안하고 시큐리티로 로그인 처리시 지정해야 할 정보들 
//따라서 컨트롤러에 @PostMapping("/login") 을 만들지 않아도 됨
//시큐리티가 /login 요청에 대해 로그인 진행 시키고 
//로그인에 성공하면 Security Session을 생성해 줌 (Key값 : Security ContextHolder)
//Key값 : Security ContextHolder에 세션정보 저장
//이 세션에 들어갈 수 있는 오브젝트는 정해져 있음
//오브젝트 타입 -> 	Authentication 타입 객체
//				그 객체 안의 유저 정보 있어야됨
//User 오브텍트 타입 -> UserDetails 타입객체

//즉 시큐리티가 가지는 세션 영역(Security Session)에 세션 정보 저장하는데 여기 들어갈 수 있는 객체 타입은
//Authentication 타입 이고, 이 안에 유저정보 저장할 땐 UserDetails타입인 것
//따라서 get을 통해 Session -> Authentication -> UserDetails(PrincipalDetails)순으로 꺼내다보면
//user오브젝트에 접근 가능 
//PrincipalDetails이 UserDetails를 구현했으므로 PrincipalDetails는 UserDetails와 같은 타입됨
@RequiredArgsConstructor
public class PrincipalDetails implements UserDetails{

	private Member member; //콤포지션
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
