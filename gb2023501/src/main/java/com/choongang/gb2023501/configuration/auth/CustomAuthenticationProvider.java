package com.choongang.gb2023501.configuration.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

//import com.oracle.s202350104.model.Users;
//import com.oracle.s202350104.service.user.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//인증 수행 처리 단계
//UserDetailService로 유저 정보 요청
//가져온 UserDetails(사용자정보)와 토큰 비교해서 인증 수행 
//인증이 성공하면, Authenticaiton.isAuthenticated = true 하여 Authentication을 반환
//Authentication Token 을 반환
//SecurityContext에 Authetication (인증 정보) 저장
@RequiredArgsConstructor
@Component
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

//	private final UserService userService;
	
	//실제 인증 로직 이뤄짐
	@Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UUID transactionId = UUID.randomUUID();
		String mmId = "";
		String mmPswd = "";
//		List<GrantedAuthority> authorities = new ArrayList<>();
		try {
			log.info("[{}]{}:{}",transactionId, "AuthenticationProvider", "start");
			mmId = authentication.getName();//아이디
			mmPswd = authentication.getCredentials().toString();//패스워드
	        log.info("mmId:{}",mmId);
	        log.info("mmPswd:{}",mmPswd);
//	        Optional<Users> user = userService.getUserByEmail(username);
//	        log.info("user:{}",user);
////	        Optional<Users> user = userService.getUserById(Integer.parseInt(username));
//	        if (user == null) {
//	            throw new BadCredentialsException("username is not found. username=" + username);
//	        }
//	        if (!password.equals(user.get().getPassword())) {
//	        	throw new BadCredentialsException("password is not matched");        	
//	        }
//	        /* 개발 단계에서는 패스워드 인코딩 생략.
//			if (!this.passwordEncoder.matches(password, user.getPassword())) {
//				throw new BadCredentialsException("password is not matched");
//			}
//			*/
////	        userService.updateUserPoint(user.getId(), 9);
//	        authorities.add(new SimpleGrantedAuthority("ROLE_"+Role.getValueByKey(user.get().getSmall_code())));	
		} catch (Exception e) {
			log.error("[{}]{}:{}",transactionId, "AuthenticationProvider", e.getMessage());
			 throw e;  // 추가된 부분
		} finally {
			log.info("[{}]{}:{}",transactionId, "AuthenticationProvider", "end");
		}	        
    	return new UsernamePasswordAuthenticationToken(mmId, mmPswd);
    }

	@Override
	public boolean supports(Class<?> authentication) {
		 return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}
	

}