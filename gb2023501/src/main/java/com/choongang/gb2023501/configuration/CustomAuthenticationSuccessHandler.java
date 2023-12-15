package com.choongang.gb2023501.configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

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
	}

}
