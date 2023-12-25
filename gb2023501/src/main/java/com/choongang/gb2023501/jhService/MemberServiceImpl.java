package com.choongang.gb2023501.jhService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.choongang.gb2023501.domain.Member;
import com.choongang.gb2023501.jhRepository.CustomMemberRepository;
import com.choongang.gb2023501.jhRepository.MemberRepository;
import com.choongang.gb2023501.model.MemberSearchCriteriaDTO;

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
		System.out.println("MemberServiceImpl getLoggedInId Start...");
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
		System.out.println("MemberServiceImpl selectMmNumById Start...");
		Member member = new Member();
		member.setMmNum(0);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication != null && !authentication.getName().equals("anonymousUser") ){
			
				member = mr.findByMmId(authentication.getName());
				
				log.info("로그인된 회원 번호: {} " , member.getMmNum());
				
				return member.getMmNum();
		}
		return member.getMmNum();
	}
	
	
	//로그인된 id로 모든 사용자 정보 가져옴
	//null포인트에러 방지 위해 Optional타입사용
	@Override
	public Optional<Member> selectUserById() {
		System.out.println("MemberServiceImpl selectUserById Start...");
		
		//SecurityContextHolder에서 로그인된 아이디 가져오기
		String mmId = getLoggedInId();
		
		Member member = mr.findByMmId(mmId);
		
		//로그인 안 한 상태에서 컨트롤러에 해당코드 있으면
		//리턴 값 null나올 경우 강제 로그인 화면 이동 / 무한 리디렉션(로그인된 권한 필요하다는 뜻) 문제 발생
		//따라서 아래방법 쓰면 null이아니면 해당 객체 member / null이면  빈 Optional객체 반환 
		//로그인 안해도 강제 로그인화면 이동, 무한 리디렉션 문제 해결
		return  Optional.ofNullable(member);
	}

	//휴대폰과 이름으로 기존 사용자인지 찾기
	@Override
	public Optional<Member> findByNameAndPhone(String name, String phone) {
		System.out.println("MemberServiceImpl findByNameAndPhone Start...");
		
		Optional<Member> currentUser = mr.findBymmNameAndPhone(name, phone);
		
		//System.out.println("MemberServiceImpl currentUser -> " + currentUser);
		return currentUser;
	}

	//이메일과 이름으로 기존 사용자인지 찾기
	@Override
	public Optional<Member> findByNameAndEmail(String name, String email) {
		System.out.println("MemberServiceImpl findByNameAndEmail Start...");
		
		Optional<Member> currentUser = mr.findBymmNameAndEmail(name, email);
		return currentUser;
	}

	@Override
	public boolean existsByMmId(String id) {
		System.out.println("MemberServiceImpl existsByMmId Start...");
		boolean existsByMmId = mr.existsByMmId(id);
		
		return existsByMmId;
	}

	@Override
	public Member join(@Valid Member member) {
		System.out.println("MemberServiceImpl join Start...");
		//.save() : 엔티티를 영속화(추가)하거나 업데이트하는 데 사용
		//Spring Data JPA가 자동으로 영속화 여부 확인 후 영속화 x면(식별자가 없는 경우) insert, o면 update 따라서 persist따로 안 써도 됨
		Member savedMember = mr.save(member);
		//String result = savedMember.getMmId(); // 예시로 mmId 필드를 반환하도록 함
		System.out.println("result -> " + savedMember);
		return savedMember;
	}

	//아이디, 이름,폰으로 유저 찾기
	@Override
	public Optional<Member> findByMmIdAndPhoneAndName(String id, String phone, String name) {
		System.out.println("MemberServiceImpl findByMmIdAndPhoneAndName Start...");
		Optional<Member> currentUser = mr.findByMmIdAndPhoneAndMmName(id, phone, name);
		
		
		return currentUser;
	}

	//아이디, 이름,이메일로 유저 찾기
	@Override
	public Optional<Member> findByMmIdAndEmailAndName(String id, String email, String name) {
		System.out.println("MemberServiceImpl findByMmIdAndEmailAndName Start...");
		Optional<Member> currentUser = mr.findByMmIdAndEmailAndMmName(id, email, name);
		return currentUser;
	}

	//회원목록 전체 조회 -> 삭제 예정
	@Override
	public List<Member> findAll() {
		List<Member> memberList = mr.findAll();
		return memberList;
	}
	
	
	//회원목록 전체 조회(페이지네이션)
	@Override
	public Page<Member> findAll(Pageable pageable) {
		System.out.println("MemberServiceImpl findAll Start...");
		Page<Member> memberList = mr.findAll(pageable);
		return memberList;
	}

	//검색조건 있는 회원 목록 조회
	@Override
	public Page<Member> SearchMemberList(MemberSearchCriteriaDTO criteria, Pageable pageable) {
		System.out.println("MemberServiceImpl SearchMemberList Start...");
//		Map<String, Object> searchKeys = new HashMap<>();
//		
		// 회원 폰번호
//		String phone = criteria.getPhone();
		//회원 구분(교육자/학습자/일반인/운영자)
//		Integer category = criteria.getCategory();	
//		//회원 자격(유/무료)
//		Integer mshipType = criteria.getMshipType();	
//		 // 회원명 검색 조건
////		String mmName = criteria.getMmName();
//	    // 회원 아이디 검색 조건
////	    String mmId = criteria.getMmId();
//	    // 검색 시작일
//	    String startDate = criteria.getStartDate();
//	    // 검색 종료일
//	    String endDate = criteria.getEndDate();
//	    
//	    String searchCriteria = criteria.getSearchValue();
//	    
//	    String	searchType = criteria.getSearchType();
	    
	    Specification<Member> spec = Specification.where(null);

	    //!=만으로는 null체크를 못함 isEmpty까지 해야함
        if (criteria.getStartDate() != null && !criteria.getStartDate().isEmpty() && criteria.getEndDate() != null && !criteria.getEndDate().isEmpty()) {
        	System.out.println("날짜 검색 " + criteria.getSearchValue());
            spec = spec.and(MemberSpecification.searchByPeriod(criteria));
        }

        if (criteria.getSearchType() != null && criteria.getSearchValue() != null) {
            switch (criteria.getSearchType()) {
                case "mmId":
                	System.out.println("아이디 검색 " + criteria.getSearchValue());
                    spec = spec.and(MemberSpecification.searchById(criteria));
                    break;
                case "mmName":
                	System.out.println("이름 검색 " + criteria.getSearchValue());
                    spec = spec.and(MemberSpecification.searchByName(criteria));
                    break;
                case "phone":
                	System.out.println("폰 검색 " + criteria.getSearchValue());
                    spec = spec.and(MemberSpecification.searchByPhon(criteria));
                    break;
                // Add other cases as needed
            }
        }

        if (criteria.getCategory() != 0 && criteria.getCategory() != null) {
        	System.out.println("회원구분 검색 " + criteria.getCategory());
            spec = spec.and(MemberSpecification.searchByCategory(criteria));
        }

        if (criteria.getMshipType() != 0 && criteria.getMshipType() != null) {
        	System.out.println("회원자격 검색 " + criteria.getMshipType());
            spec = spec.and(MemberSpecification.searchByMshipType(criteria));
        }

        return mr.findAll(spec, pageable);
	}
	//여기서 null 조건 확인해서 로직짜기 
//	    
//	    if(phone != null) searchKeys.put("phone", phone);
//	    if(category != null) searchKeys.put("category", category);
//	    if(mshipType != null) searchKeys.put("mshipType", mshipType);
//	    if(mmId != null) searchKeys.put("mmId", mmId);
//	    if(endDate != null) searchKeys.put("endDate", endDate);
//		
//		return mr.findAll(MemberSpecification.searchMemberList(searchKey), pageable)
//				 .stream().map()
//				 .collect(Collectors.toList())
//				;
		
		// MemberSpecification을 이용하여 검색 조건을 생성
        //MemberSpecification spec = new MemberSpecification(searchCriteria);
		//Page<Member> memberList = mr.findAll(spec, pageable);

	//회원목록 조회 (검색 조건 포함)
//	@Override
//	public List<Member> selectMemberList(Member member) {
//		System.out.println("MemberServiceImpl findByMmIdAndEmailAndName Start...");
//		List<Member> memberList = mr.selectMemberList(member);
//		
//		return memberList;
//	}





}
