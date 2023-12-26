package com.choongang.gb2023501.configuration.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.choongang.gb2023501.domain.Member;
import com.choongang.gb2023501.jhRepository.MemberRepository;

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
	private final MemberRepository mr;
	
//	@RequiredArgsConstructor는 @RequiredArgsConstructor에 대한 생성자만 자동 생성
	//비밀번호 암호화
	//순환참조 발생해서 일단 암호화 보류
//	@Autowired -> @RequiredArgsConstructor 있어서 final넣고 @Autowired는 빼버림
//	@Lazy //순환참조 때문에 넣었던 건데 SecurityConfig에서 CustomAuthenticationProvider의존성 주입 삭제해서 순환참조 문제 해결해서 없앰
	private final PasswordEncoder passwordEncoder; 
	
	//실제 인증 로직 이뤄짐
	@Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UUID transactionId = UUID.randomUUID();
		String mmId = "";
		String mmPswd = "";
		List<GrantedAuthority> authorities = new ArrayList<>();
		try {
			log.info("[{}]{}:{}",transactionId, "AuthenticationProvider", "start");
			mmId = authentication.getName();//아이디
			mmPswd = authentication.getCredentials().toString();//패스워드
	        log.info("mmId:{}",mmId);
	        log.info("mmPswd:{}",mmPswd);
	        
	        // 여기서 실제 디비에서 mmId에 해당하는 Member 검색
	        //Optional->  NPE(Null Pointer Exception)을 처리하기 위해 자바에서 제공하는 클래스
	        //반환값이 Null이 발생할 수도 있는 메서드에 사용하면 Optional 의 메서드를 통해 Null이 발생했을 때 문제를 해결
            Member member = mr.findByMmId(mmId);
            System.out.println("CustomAuthenticationProvider 로그인 회원"+member);
            
            if (member == null) {
            	throw new BadCredentialsException("아이디가 일치하지 않습니다." + mmId);
            }
            
        	//Optional 객체에서 값 꺼내서 memeber에 저장
//            Member member = memberOptional.get();
            
            String loggedInPswd = member.getMmPswd();
            System.out.println("CustomAuthenticationProvider 회원 비번 "+loggedInPswd);
            System.out.println("CustomAuthenticationProvider 로그인 하려는 비번 "+mmPswd);
            
         // 패스워드를 암호화된 비밀번호와 비교
            //암호화 안된 비번과 비교하려 하면 에러남
            if (!passwordEncoder.matches(mmPswd, loggedInPswd )) {
                throw new BadCredentialsException("비밀번호가 일치하지 않습니다." + mmPswd);
            }
//            
//            // 파라미터 mmPswd와 디비의 패스워드 비교 ->암호화 x
//            if (!mmPswd.equals(loggedInPswd)) {
//            	throw new BadCredentialsException("비밀번호가 일치하지 않습니다." + mmPswd);
//            }
//            
            	
            //
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
            

            // category에 따라 권한 부여
            switch (member.getCategory()) {
                case 1:
                    authorities.add(new SimpleGrantedAuthority("ROLE_EDUCATOR"));
                    break;
                case 2:
                    authorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
                    break;
                case 3:
                    authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                    break;
                case 4:
                    authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                    break;
                default:
                    throw new BadCredentialsException("유효하지 않은 카테고리 값: " + member.getCategory());
            
            }
            
            log.info("authorities:{}", authorities);
            
		} catch (Exception e) {
			
			log.error("[{}]{}:{}",transactionId, "AuthenticationProvider", e.getMessage());
			 throw e;  // 추가된 부분
		} finally {
			log.info("[{}]{}:{}",transactionId, "AuthenticationProvider", "end");
		}	
		
		// 로그인 성공 -> SecurityContextHolder에 Authentication 객체 저장
		// => SecurityContextHolder(SecurityContext(Authentication))
		//SecurityContextHolder.getContext().getAuthentication()로 Authentication 호출가능
    	return new UsernamePasswordAuthenticationToken(mmId,  mmPswd, authorities);
    }

	// Provider 적용 가능 여부
	@Override
	public boolean supports(Class<?> authentication) {
		 return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}
	

}