package com.choongang.gb2023501.jhService;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.choongang.gb2023501.domain.Member;
import com.choongang.gb2023501.model.MemberSearchCriteriaDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberSpecifications implements Specification<Member> {

	private final MemberSearchCriteriaDTO criteria;

	@Override
	public Predicate toPredicate(Root<Member> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		// 동적으로 생성되는 검색 조건을 저장할 Predicate
		
		Predicate predicate = criteriaBuilder.conjunction();
		
		
		return null;
	}

}
