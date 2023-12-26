package com.choongang.gb2023501.configuration;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.choongang.gb2023501.domain.LgJoin;
import com.choongang.gb2023501.domain.Member;
import com.choongang.gb2023501.jhRepository.MemberRepository;
import com.choongang.gb2023501.jhService.MemberService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
//@AllArgsConstructor
@Slf4j
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

//	private final MemberRepository mr;
	private final MemberService	ms;
	
    // 명시적으로 디폴트 생성자 추가
    // @Autowired 어노테이션을 사용하여 의존성 주입

    
    
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

//	학습자 로그인 시 학습그룹 가입 안되어 있으면 학습그룹 가입 페이지로 이동

      // 여기에 특정 조건을 확인하는 로직을 추가
      // 예를 들어, ROLE_STUDENT인 경우 특정 페이지로 리다이렉트
//      if (authentication.getAuthorities().stream()
//              .anyMatch(r -> r.getAuthority().equals("ROLE_STUDENT"))) {
//          response.sendRedirect("/student/dashboard");
//      } else {
//          // 다른 역할에 대한 처리나 조건이 있다면 여기에 추가
//          // 예: response.sendRedirect("/other/dashboard");
//      }
		System.out.println("로그인 성공 핸들러");
		if(authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_STUDENT"))){
//			String mmId = authentication.getName();
			
			Optional<Member> memberOptional = ms.selectUserById();
			Member member = memberOptional.get();
			
//			int mmNum = ms.selectMmNumById();
			
			List<LgJoin> joinedLearnGroup = ms.selectJoinedLearnGroupList(member);
			
			int joinedLgCount = joinedLearnGroup.size();
			
			if(joinedLgCount > 0) {
				//가입 되어있으면 메인으로
				response.sendRedirect("/");
				
			} else {
				//가입 안돼있으면 그룹가입신청 페이지로
				response.sendRedirect("/learning/learnGrpJoinForm");
				
			}
			
//			System.out.println("유저 아이디 -> " + authentication.getName() );
		} else {
			response.sendRedirect("/");
			
		}
		
	}

}
