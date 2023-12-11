package com.choongang.gb2023501.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터(SecurityConfig)가 스프링 기본 필터 체인에 등록됨
public class SecurityConfig {
	
	// 해쉬 암호화 방식을 사용하겠다.
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	
	// Security 모듈을 비활성화 하겠다.
//	WebSecurityConfigurerAdapter는  Deprecated 되었으므로 사용하지 않고 아래와 같이 SecurityFilterChain을 Bean으로 등록하여 사용 
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests() //인증, 인가가 필요한 URL 지정
			.antMatchers("/user/**").authenticated() //"/user/**"이런 주소로 들어오면 인증 필요
			.antMatchers("/admin/**").hasRole(Role.USER.getValue()) //"/admin/**" 이런 주소로 들어오면 ROLE_ADMIN권한 필요 
			.anyRequest().permitAll() //위 url 외엔 어떤 요청이든 권한 허가
			.and() 
			.formLogin() //권한 없는 페이지에 요청 들어갈 때 로그인 페이지로 이동하기 위한 것
			.loginPage("/login");
		return http.build();
	}
	


}
