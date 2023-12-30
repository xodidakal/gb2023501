package com.choongang.gb2023501.jhService;
import com.choongang.gb2023501.domain.Member;

import com.choongang.gb2023501.model.MemberSearchCriteriaDTO;

import lombok.RequiredArgsConstructor;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import java.text.ParseException;
//import java.sql.Date;
import java.text.SimpleDateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
//import java.util.List;

@RequiredArgsConstructor
public class MemberSpecification {
    

   // private final MemberSearchCriteriaDTO searchCriteria = new MemberSearchCriteriaDTO();

	public static Specification<Member> searchByPeriod(MemberSearchCriteriaDTO criteria) {
	    return (root, query, criteriaBuilder) -> {
	                //Date startDate = new SimpleDateFormat("yy/MM/dd").parse(criteria.getStartDate());
	                //Date endDate = new SimpleDateFormat("yy/MM/dd").parse(criteria.getEndDate());
	    			//디비에 저장된 형식은 YY/MM/DD인데 문자열은 yyyy-MM-dd형식이라 비교가 제대로 안됐었음
	    			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    			
	    			Date endDate = null;
	    			Date startDate = null;
					try {
						endDate = sdf.parse(criteria.getEndDate());
						startDate = sdf.parse(criteria.getStartDate());
					} catch (ParseException e) {
						e.printStackTrace();
					}
	    	
	    		    // endDate에 1일을 더하기
	    	        Calendar calendar = Calendar.getInstance();
	    	        calendar.setTime(endDate);
	    	        calendar.add(Calendar.DATE, 1);
	    	        Date endDatePlus = calendar.getTime();
	    	        
//		            Date startDate =java.sql.Date.valueOf(criteria.getStartDate()); 
//		            Date endDate =java.sql.Date.valueOf(criteria.getEndDate()); 
		            
	                System.out.println("MemberSpecification 시작 날짜 " + startDate);
	                System.out.println("MemberSpecification 끝 날짜 " + endDate);
	                Predicate predicate = criteriaBuilder.between(root.get("regiDate"), startDate, endDatePlus);
	                return predicate;
	    };
	}

    public static Specification<Member> searchById(MemberSearchCriteriaDTO criteria) {
    	System.out.println("아이디로 찾기"+criteria);
    	return (root, query, criteriaBuilder) ->
        		criteriaBuilder.like(root.get("mmId"), "%" + criteria.getSearchValue()+"%");
	}
    
    public static Specification<Member> searchByName(MemberSearchCriteriaDTO criteria) {
    	System.out.println("이름으로 찾기"+criteria);
    	return (root, query, criteriaBuilder) ->
    	criteriaBuilder.like(root.get("mmName"), "%" + criteria.getSearchValue()+"%");
    }
    
    public static Specification<Member> searchByPhon(MemberSearchCriteriaDTO criteria) {
    	System.out.println("폰으로 찾기"+criteria);
    	return (root, query, criteriaBuilder) ->
    	criteriaBuilder.like(root.get("phone"), "%" + criteria.getSearchValue()+"%");
    }
    
    public static Specification<Member> searchByCategory(MemberSearchCriteriaDTO criteria) {
    	return (root, query, criteriaBuilder) ->
    	criteriaBuilder.equal(root.get("category"), criteria.getCategory());
    }
    
    public static Specification<Member> searchByMshipType(MemberSearchCriteriaDTO criteria) {
    	return (root, query, criteriaBuilder) ->
    	criteriaBuilder.equal(root.get("mshipType"), criteria.getMshipType());
    }
}
//@RequiredArgsConstructor
//public class MemberSpecification implements Specification<Member> {
//
//    private final MemberSearchCriteriaDTO searchCriteria;
//
//    @Override
//    public Predicate toPredicate(javax.persistence.criteria.Root<Member> root, javax.persistence.criteria.CriteriaQuery<?> query, javax.persistence.criteria.CriteriaBuilder criteriaBuilder) {
//        List<Predicate> predicates = new ArrayList<>();
//
//        // 검색 조건이 있는 경우에만 추가
//        if (searchCriteria.getStartDate() != null && searchCriteria.getEndDate() != null) {
//            predicates.add(criteriaBuilder.between(root.get("regiDate"), searchCriteria.getStartDate(), searchCriteria.getEndDate()));
//        }
//
//        if (searchCriteria.getSearchType() != null && searchCriteria.getSearchCriteria() != null) {
//            if ("mmId".equals(searchCriteria.getSearchType())) {
//                predicates.add(criteriaBuilder.equal(root.get("mmId"), searchCriteria.getSearchCriteria()));
//            } else if ("mmName".equals(searchCriteria.getSearchType())) {
//                predicates.add(criteriaBuilder.equal(root.get("mmName"), searchCriteria.getSearchCriteria()));
//            } else if ("phone".equals(searchCriteria.getSearchType())) {
//                predicates.add(criteriaBuilder.equal(root.get("phone"), searchCriteria.getSearchCriteria()));
//            }
//        }
//
//        if (searchCriteria.getCategory() != 0) {
//            predicates.add(criteriaBuilder.equal(root.get("category"), searchCriteria.getCategory()));
//        }
//
//        if (searchCriteria.getMshipType() != 0) {
//            predicates.add(criteriaBuilder.equal(root.get("mshipType"), searchCriteria.getMshipType()));
//        }
//
//        // null 체크 추가
//        if (!predicates.isEmpty()) {
//            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//        } else {
//            // 검색 조건이 없는 경우에는 true를 반환하여 모든 결과를 검색
//            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
//        }
//    }
