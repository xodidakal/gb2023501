package com.choongang.gb2023501.configuration;


import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.choongang.gb2023501.configuration.auth.CustomAuthenticationProvider;
//import com.choongang.gb2023501.configuration.auth.PrincipalDetailsService;



@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터(SecurityConfig)가 스프링 기본 필터 체인에 등록됨
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	
	//접근 권한 없는 경우 예외 처리 할 클래스 
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
    	//아래는 AccessDeniedHandler 상속받아서 직접 생성
        return new CustomAccessDeniedHandler();
    }
	
	
	// 해쉬 암호화 방식을 사용하겠다. -> password 암호화
	//@Bean 사용하면 해당 메서드의 리턴되는 오브젝트를 IoC로 등록해줌
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	

	@Autowired
	private CustomAuthenticationSuccessHandler successHandler;
	 
//	@Autowired
//	private  CustomAuthenticationProvider authProvider;
	
//	WebSecurityConfigurerAdapter는  Deprecated 되었으므로 사용하지 않고 아래와 같이 SecurityFilterChain을 Bean으로 등록하여 사용 
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		//접근 권한 없는 경우 예외처리할 핸들러 필터체인에 등록
		http.exceptionHandling().accessDeniedHandler(accessDeniedHandler());
		
		http.authorizeHttpRequests((requests) -> requests
				//접근 제한 권한 설정은 마지막에
				//.antMatchers("/learning/**").hasAnyRole("STUDENT", "EDUCATOR", "ADMIN")
				//.antMatchers("/educator/**").hasAnyRole( "EDUCATOR", "ADMIN")

				
				
				//				.antMatchers("/educator/**").hasRole("EDUCATOR")
				/* 개발단계에서는 역할에 따른 접근제한 해제.
				.antMatchers("/admin/**").hasRole(Role.ADMIN.getValue())
				.antMatchers("/user/myPage/**").hasRole(Role.USER.getValue())
				.antMatchers("/user/bizPage").hasRole(Role.BIZ.getValue())
				*/
				.anyRequest().permitAll()
			);		
		
		http.formLogin((form) -> form
				.loginPage("/info/loginForm")
				.permitAll()
				.usernameParameter("mmId")		// login에 필요한 id값 설정 (default는 username)
                .passwordParameter("mmPswd")	// login에 필요한 password 값  (default password)
                .loginProcessingUrl("/login")	// login주소가 호출 되면 시큐리티가 낚아채서 대신 로그인 진행해줌
                .failureUrl("/info/loginForm?error=true")
                .successHandler(successHandler)
//                .failureUrl("/loginFailure")
				.defaultSuccessUrl("/")			// 로그인 성공시 이동할 URL (메이페이지로 이동)
			);
		
		// Logout 설정.
		http.logout((logout) -> logout
				.permitAll()
				.logoutSuccessUrl("/info/loginForm")
				.invalidateHttpSession(true) //세션 무효화 -현재 세션을 끝내고 새로운 세션을 시작
			);	
		//세션을 날렸는데도 헤더에서 계속 로그아웃만 표시 되어서 바꿈 
		//근데도 문제 지속되어서 헤더에서 <%= SecurityContextHolder.getContext().getAuthentication() %>
		//에서 지금으로 바꿈
//				.logoutSuccessHandler((request, response, authentication) -> {
//			        SecurityContextHolder.clearContext(); // 세션을 무효화하고 SecurityContextHolder를 비웁니다.
//			        response.sendRedirect("/info/loginForm");
//				})
		
		// Authentication Provider 등록. -> CustomAuthenticationProvider에서 실제 로그인 처리
//		http.authenticationProvider(authProvider);
		
//		http.authorizeRequests() //인증, 인가가 필요한 URL 지정
////			.antMatchers("/user/**").authenticated() //authenticated() : 해당 URL에 진입하기 위해서 Authentication(인증, 로그인)이 필요 -> "/user/**"이런 주소로 들어오면 인증 필요
////			.antMatchers("/admin/**").hasRole("ADMIN") //hasAuthority() : 해당 URL에 진입하기 위해서 Authorization(인가, ex)권한이 ADMIN인 유저만 진입 가능)이 필요 -> "/admin/**" 이런 주소로 들어오면 ROLE_ADMIN권한 필요 
////			.antMatchers("/admin/**").hasRole(Role.USER.getValue()) //hasAuthority() : 해당 URL에 진입하기 위해서 Authorization(인가, ex)권한이 ADMIN인 유저만 진입 가능)이 필요 -> "/admin/**" 이런 주소로 들어오면 ROLE_ADMIN권한 필요 
//			.anyRequest().permitAll() //위 url 외엔 어떤 요청이든 권한 허가
//			.and() 
//			.formLogin() //Form Login 방식 적용 (권한 없는 페이지에 요청 들어갈 때 로그인 페이지로 이동하기 위한 것)
//			.loginPage("/info/loginForm") // 로그인 페이지 URL
//			.usernameParameter("username") 
//			.passwordParameter("password")
//			.loginProcessingUrl("/login") //login주소가 호출 되면 시큐리티가 낚아채서 대신 로그인 진행해줌
//			.defaultSuccessUrl("/") //로그인 성공시 이동할 URL (메이페이지로 이동)
//			.failureUrl("/loginFailure")
//			.and()
//			.logout()
//			.logoutSuccessUrl("/") //로그 아웃 성공시 이동할 URL (메이페이지로 이동)
//			
//			;
//		// Authentication Provider 등록.
//		http.authenticationProvider(authProvider);
		
		return http.build();
	}
	

	// 인증요청으로 들어온 토큰이 올바른 유저인지 인증 수행하므로 빈 등록 필수!
	@Bean
	AuthenticationManager authenticationManager(
		AuthenticationConfiguration authenticationConfiguration
	) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

}
