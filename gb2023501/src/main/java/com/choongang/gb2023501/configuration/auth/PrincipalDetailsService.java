package com.choongang.gb2023501.configuration.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//시큐리티 설정에서 loginProcessingUrl("/login")으로 설정되어
// /login요청이 오면 자동으로 UserDetailsService타입으로 IoC되어 있는 loadUserByUsername함수가 실행
//(정해진 규칙임 따라서 UserDetailsService 이 타입으로 꼭 만들어야 loadUserByUsername 이 함수가 호출됨)
//@Service라고 하면 PrincipalDetailsService가 IoC로 등록됨 따라서 자동으로 loadUserByUsername가 자동으로 호출 됨
@Service
public class PrincipalDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//주의!! 
		//loadUserByUsername의 파라미터 username는 반드시 로그인폼 input태그의 name속성이 name="username"이어야만 매칭이 됨
		//만약 name을 username이 아닌 다른 것을 쓰고 싶다면 
		//SecurityConfig에서 .usernameParameter("m_id")로 따로 설정해줘야 함 안그러면 name속성이 username와 다를경우 매칭 안되는 문제 생김
		
		//로그인 폼에서 로그인 버튼을 누르면 /login이 발동되고 스프링은 IoC컨테이너에서 UserDetailsService 타입으로 등록된 것을 찾게 됨
		//따라서 PrincipalDetailsService를 찾고 loadUserByUsername함수를 찾고 username로 넘어온 파라미터를 가지고 옴(여기선 m_id로 따로 설정을 해줌)
		
		

		return null;
	}
	

}
