package com.choongang.gb2023501.model;

import java.util.Date;

import lombok.Data;

@Data
public class Member {
	private int 	m_num;
	private String 	m_name;
	private String 	m_id;
	private String 	m_pswd;
	private int 	m_category;
	private int 	m_mship_type;
	private String 	m_phone;
	private String 	m_tel;
	private String 	m_birth;
	private int 	m_gender;
	private String 	m_email;
	private String 	m_address;
	private Date 	m_regi_date;
	private Date 	m_modi_date;
	private int 	m_econsent;
	private int 	m_sconsent;
	private int 	m_dele_status;

}
