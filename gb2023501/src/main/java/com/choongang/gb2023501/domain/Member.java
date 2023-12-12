package com.choongang.gb2023501.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.choongang.gb2023501.configuration.Role;

import lombok.Data;

@Entity
@Data
@SequenceGenerator(name = "member_seq", // 객체기준
				   sequenceName = "member_seq",	// DB 기준
				   initialValue = 21,	// 시작 번호
				   allocationSize = 1   // 증가 값
				  )
@Table(name = "member")
public class Member {
	
	//회원번호
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "member_seq")
	@Column(name = "m_num")
	private int mmNum;			
	
	
	//회원명
	@Column(name = "m_name")
	private String mmName;		
	
	
	//아이디
	@Column(name = "m_id")
	private String mmId;			
	
	
	//비밀번호
	@Column(name = "m_pswd")
	private String mmPswd;		
	
	
	//회원 구분
	@Column(name = "m_category")
	private int category;		
	
	
	//자격 유형
	@Column(name = "m_mship_type")
	private int mshipType;		
	
	
	//휴대폰 번호
	@Column(name = "m_phone")
	private String phone;		
	
	
	//연락처
	@Column(name = "m_tel")
	private String tel;		
	
	
	//생년월일
	@Column(name = "m_birth")
	private String birth;		
	
	
	//성별
	@Column(name = "m_gender")
	private int gender;		
	
	
	//이메일
	@Column(name = "m_email")
	private String email;		
	
	
	//주소
	@Column(name = "m_address")
	private String address;	
	
	
	//가입일자
	//Spring Boot의 spring-boot-starter-data-jpa 의존성을 사용하고 있기 때문에, Spring Data JPA의 편리한 설정을 활용
	//@CreationTimestamp와 @CreatedDate 둘 다 엔티티의 특정 필드에 자동으로 현재 시간이 기록
	//일반적으로 CreationTimestamp보단 CreatedDate를 선호 
	//후자가  Spring Data JPA의 Auditing 기능과 연결해 생성일과 수정일을 자동으로 관리하고
	//수정일(@LastModifiedDate)이나 생성자(@CreatedBy)와 같은 추가적인 기능을 쉽게 확장하고 유지보수할 수 있기 때문
	//전자는 Hibernate 프레임워크에서 제공 후자는 Spring Data JPA에서 제공하는 어노테이션
	//Spring Data JPA와 Hibernate은 모두 자바 기반의 ORM(Object-Relational Mapping) 프레임워크
	//주로 간단한 CRUD 작업을 위한 편의성을 제공하고자 하는 경우에는 Spring Data JPA를 사용
	//더 세부적이고 복잡한 ORM 기능이 필요한 경우에는 Hibernate를 직접 사용
	//BUT 둘이 다른 것이 아니라  Spring Data JPA는 Hibernate를 포함한 여러 JPA(Java Persistence API) 구현체와 통합되는 Spring의 일종의 프로젝트
	//둘 다 사용하려면 , 프로젝트의 의존성 관리 도구(예: Maven, Gradle)를 통해 Spring Data JPA와 JPA 구현체(Hibernate 등)를 추가해야 함
	//여기선 gradle에 spring-boot-starter-data-jpa를 추가해서 사용중
	@CreatedDate
	@Column(name = "m_regi_date")
	private Date regiDate;		
	
	
	//수정일자
	//엔티티의 마지막 수정 일자를 자동으로 관리하기 위해 사용
	//엔티티의 특정 필드에 마지막으로 수정된 일자가 자동으로 업데이트되게 할 수 있음
	//@Entity + LocalDateTime나 Date 타입이어야함
	@LastModifiedDate
	@Column(name = "m_modi_date")
	private Date modiDate;		
	
	
	//이메일 수신동의
	@Column(name = "m_econsent")
	private int econsent;		
	
	
	//SMS 수신동의
	@Column(name = "m_sconsent")
	private int sconsent;		
	
	
	//삭제여부
	@Column(name = "m_dele_status")
	private int deleStatus;	
	
	
	// 권한
    @Transient // 데이터베이스에 저장하지 않음
    private Role role;  

	
	
	

}
