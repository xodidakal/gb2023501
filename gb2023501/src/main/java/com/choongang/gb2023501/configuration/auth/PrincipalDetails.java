package com.choongang.gb2023501.configuration.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.choongang.gb2023501.domain.Member;

import lombok.Data;
import lombok.RequiredArgsConstructor;

//직접 로그인 처리 안하고 시큐리티로 로그인 처리시 지정해야 할 정보들 -> 컨트롤러에 @PostMapping("/login") 을 만들지 않아도 됨
//로그인에 성공시 Security Session을 생성(Key값 : Security ContextHolder)후 세션에 저장할 객체(Member도 포함됨)

@Data
public class PrincipalDetails implements UserDetails{

	private Member member; //콤포지션(다른객체의 인스턴스를 자신의 인스턴스 변수로 포함해서 메서드를 호출) ->객체를 품고 있는 것
	
	//생성자
	public PrincipalDetails(Member member) {
		this.member = member;
	}
	
	//해당 유저의 권한정보를 시큐리티가 인식할 수 있는 형태로 변환하여 리턴하는 곳
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//권한 정보 담기 위해 담기 위해 GrantedAuthority를 구현한 객체를 담는 Collection 선언하고 
		//GrantedAuthority를 생성해서 add
		//원래 시큐리티에서 권한 정보(GrantedAuthority)를 문자열이므로 Role enum생성해서 가져옴
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(()->{ return"ROLE_" + member.getRole().name();});
//		collect.add(new SimpleGrantedAuthority("ROLE_" + member.getRole().name()));
		return collect;
	}

	@Override
	public String getPassword() {
		
		return member.getMmPswd();
	}

	@Override
	public String getUsername() {

		return member.getMmId();
	}

	//계정이 만료 된건지 묻는 것 true(만료안됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//계정 잠겼는가? true(아니요)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	//비밀번호가 오래되었는가? true(아니요)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//계정이 활성화 되었는가? (true: 활성화)
	@Override
	public boolean isEnabled() {
		//우리사이트 1년동안 회원이 로그인 안하면 휴면계정으로 전환할 때 사용(로그인 데이트 필요) -> 여기선 구현 안함
		//현재시간 - 로그인 시간 = 1년을 초과하면 return false; 이런 식으로 하면 됨
		return true;
	}

}
