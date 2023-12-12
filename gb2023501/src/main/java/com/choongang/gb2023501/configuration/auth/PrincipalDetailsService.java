package com.choongang.gb2023501.configuration.auth;

import java.util.Optional;

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
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

	private final MemberRepository mr;

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		//nullPoint 방지 위해 Optional사용
//		Optional<Member> op = mr.findById(username);
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
//	
	
	
	
	
	//첫번 째 영상보고 한 것 -> 실패
	//시큐리티 session = Autentication = UserDetails
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println("username : " + username);
		//주의!! 
		//loadUserByUsername의 파라미터 username는 반드시 로그인폼 input태그의 name속성이 name="username"이어야만 매칭이 됨
		//만약 name을 username이 아닌 다른 것을 쓰고 싶다면 
		//SecurityConfig에서 .usernameParameter("m_id")로 따로 설정해줘야 함 안그러면 name속성이 username와 다를경우 매칭 안되는 문제 생김
		
		//로그인 폼에서 로그인 버튼을 누르면 /login이 발동되고 스프링은 IoC컨테이너에서 UserDetailsService 타입으로 등록된 것을 찾게 됨
		//따라서 PrincipalDetailsService를 찾고 loadUserByUsername함수를 찾고 username로 넘어온 파라미터를 가지고 옴(여기선 m_id로 따로 설정을 해줌)
		
		//해당 파라미터(여기선 m_id)를 가진 사용자가 있는지 확인
		//mr엔 기본적인 crud에 관한 메소드만 있으므로 따라서 username으로 검색하려면 findByUsername는 새로 만듦
		Member memberEntity = mr.findByMmId(username); 
		
		//검색해서 가져온 유저네임이 null이 아니면 사용자가 있다는 것이고 그 사용자를 PrincipalDetails에 넣음 없으면 null을 리턴
		if(memberEntity != null) {
			return new PrincipalDetails(memberEntity);
			//사용자의 m_id를 PrincipalDetails에 담아서 리턴하면 (PrincipalDetails의 맨 위 주석내용 참고) 
			//PrincipalDetails은 UserDetails을 상속 받았으므로 Authentication에 넣을 수 있고 Authentication은 Security Session에 담을 수 있음
			//즉 Security Session(Authentication(PrincipalDetails))이 되는 것
		}

		return null;
	}
	

}
