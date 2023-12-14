package com.choongang.gb2023501.jhService;

import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.choongang.gb2023501.domain.Member;
import com.choongang.gb2023501.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberRepository mr;
	
	
	//로그인된 id가져오기 /없을 경우 null반환
	@Override
	public String getLoggedInId() {
		//Authentication (인증) : 증명, 유저가 누구인지 확인하는 과정
		//로그인 성공시 인증된 사용자 정보 Authentication 호출가능
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication != null && !authentication.getName().equals("anonymousUser") ){
			return authentication.getName();
		}
		
		//Security에선 로그인 안하면 리디렉션하는데 null값이 반환될 경우 무한 리디렉션 발생할 수 있음
		//따라서 임시로 사용자가 로그인하지 않은 경우 null 대신 빈 문자열("") 반환
		//컨트롤러에서 getLoggedInId호출했는데 로그인 안한 경우 발생할 수 있는 문제
		return "";
	}

	//로그인된 id로 pk 회원번호 가져오기
	@Override
	public int selectMmNumById() {
		Member member = new Member();
		member.setMmNum(0);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication != null && !authentication.getName().equals("anonymousUser") ){
				member = mr.findByMmId(authentication.getName());
				
				log.info("로그인된 회원 번호 -> " , member.getMmNum());
				
				return member.getMmNum();
		}
		return member.getMmNum();
	}
	
	
	//로그인된 id로 모든 사용자 정보 가져옴
	//null포인트에러 방지 위해 Optional타입사용
	@Override
	public Optional<Member> selectUserById() {
		
		//로그인된 아이디 가져오기
		String mmId = getLoggedInId();
		
		Member member = mr.findByMmId(mmId);
		
		//로그인 안 한 상태에서 컨트롤러에 해당코드 있으면
		//리턴 값 null나올 경우 강제 로그인 화면 이동 / 무한 리디렉션(로그인된 권한 필요하다는 뜻) 문제 발생
		//따라서 아래방법 쓰면 null이아니면 해당 객체 member / null이면  빈 Optional객체 반환 
		//로그인 안해도 강제 로그인화면 이동, 무한 리디렉션 문제 해결
		return  Optional.ofNullable(member);
	}





}
