package com.choongang.gb2023501.configuration.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.choongang.gb2023501.domain.Member;
import com.choongang.gb2023501.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//시큐리티 설정에서 loginProcessingUrl("/login")으로 설정되어
// /login요청이 오면 자동으로 UserDetailsService타입으로 IoC되어 있는 loadUserByUsername함수가 실행
//(정해진 규칙임 따라서 UserDetailsService 이 타입으로 꼭 만들어야 loadUserByUsername 이 함수가 호출됨)
//@Service라고 하면 PrincipalDetailsService가 IoC로 등록됨 따라서 자동으로 loadUserByUsername가 자동으로 호출 됨
@Service
@Slf4j
//@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

	@Autowired
	private MemberRepository mr;

//	두번째방법
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		//nullPoint 방지 위해 Optional사용
//		Optional<Member> op = mr.findByMmId(username);
//		if(!op.isPresent()) {
//			throw new UsernameNotFoundException("사용자 없음");
//		}
//		//패스워드는 알아서 검증해줌
//		//op.get();으로 member가져옴
//		Member member = op.get();
//		
//		//첫번째 영상에선 UserDetails상속받은 PrincipalDetails로 리턴했는데 
//		//지금은 User상속받은 SecurityUser로 리턴함
//		return new SecurityUser(member);
//	}
////	
	
//	//시큐리티 session = Autentication = UserDetails
//	//시큐리티가 로그인 가로챌 떄 id랑 비번가로채는데 비번은 알아서 처리함 -> username만 db에 있는지 확인하면 됨 
//	//loadUserByUsername를 오버라이딩해야 PrincipalDetails에 내 Member정보 담을 수 있음
	@Override
	public UserDetails loadUserByUsername(String mmId) throws UsernameNotFoundException {
		
		System.out.println("username(mmId) : " + mmId);
		
		//로그인 폼에서 로그인 버튼을 누르면 /login이 발동되고 스프링은 IoC컨테이너에서 UserDetailsService 타입으로 등록된 것을 찾게 됨
		//따라서 PrincipalDetailsService를 찾고 loadUserByUsername함수를 찾고 username로 넘어온 파라미터를 가지고 옴(여기선 m_id로 따로 설정을 해줌)
		
		//해당 파라미터(여기선 m_id)를 가진 사용자가 있는지 확인
		Member memberEntity = mr.findByMmId(mmId)
								.orElseThrow(()-> {
													return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. : " + mmId);
								}); 
		
//		//검색해서 가져온 유저네임이 null이 아니면 사용자가 있다는 것이고 그 사용자를 PrincipalDetails에 넣음 없으면 null을 리턴
//		if(memberEntity != null) {
//			return new PrincipalDetails(memberEntity);
//			//사용자의 m_id를 PrincipalDetails에 담아서 리턴하면 (PrincipalDetails의 맨 위 주석내용 참고) 
//			//즉 Security Session(Authentication(PrincipalDetails))이 되는 것
//		}

		//시큐리티 세션에 유저 정보 저장됨
		return new PrincipalDetails(memberEntity);
	}
	

//	//첫번 째 영상보고 한 것 -> 실패
//	//시큐리티 session = Autentication = UserDetails
//	// login url 캐치 -> 로그인 인증 작업 수행 -> 유저정보 조회 기능 호출
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		
//		System.out.println("username : " + username);
//		
//		// username 으로 사용자 정보 조회
//		Member memberEntity = mr.findByMmId(username); 
//		
//		// 사용자 정보가 잇으면 권한 세팅 
//		if(memberEntity == null) {
//			throw new UsernameNotFoundException("User don't exist1");
//		}
//
//		return new PrincipalDetails(memberEntity);
//	}
	
}
