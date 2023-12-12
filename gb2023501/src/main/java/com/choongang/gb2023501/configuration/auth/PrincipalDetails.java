package com.choongang.gb2023501.configuration.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.choongang.gb2023501.domain.Member;

import lombok.Data;
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
//PrincipalDetails이 UserDetails를 구현했으므로 PrincipalDetails는 UserDetails와 같은 것인 셈
//따라서 PrincipalDetails을 Authentication객체에 넣고 Authentication객체를 만들어서 Session에 담는 것
@Data
public class PrincipalDetails implements UserDetails{

	private Member member; //콤포지션(다른객체의 인스턴스를 자신의 인스턴스 변수로 포함해서 메서드를 호출)
	
	//생성자
	public PrincipalDetails(Member member) {
		this.member = member;
	}
	
	//해당 유저의 권한정보를 리턴하는 곳
	//getAuthorities() 메서드는 사용자의 권한 정보를 스프링 시큐리티가 인식할 수 있는 형태로 변환하여 반환하는 역할
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//getAuthorities메서드의 리턴 타입은 Collection 따라서 Collection 타입의 collect를 리턴하면 됨
		//( Collection은 데이터 그룹을 다루기 위한 인터페이스 및 클래스의 모임 -> Collection 프레임워크에는 다양한 유형의 컬렉션 인터페이스와 클래스가 포함되어 있음 )
		//따라서 컬렉션에 권한 정보 담기 위해 GrantedAuthority를 구현한 객체를 담는 Collection 타입의 collect을 선언하고 
		//GrantedAuthority를 생성해서 add하면 됨
		//원래 시큐리티에서 권한 정보(GrantedAuthority)를 문자열로 다루기 때문에 GrantedAuthority의 타입은 String
		//근데 member.getCategory(); 이게 사실상 유저의 권한인데 현재 도메인에서 category의 타입을 int로 잡아서 이대로 리턴할 수가 없음
		//따라서 int -> String으로 바꿈
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(new SimpleGrantedAuthority("ROLE_" + member.getRole().name()));
		
		
		
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

	//계정이 활성화 되었는가?
	@Override
	public boolean isEnabled() {
		//우리사이트 1년동안 회원이 로그인 안하면 휴면계정으로 전환할 때 사용(로그인 데이트 필요) -> 여기선 구현 안함
		//현재시간 - 로그인 시간 = 1년을 초과하면 return false; 이런 식으로 하면 됨
		return true;
	}

}
