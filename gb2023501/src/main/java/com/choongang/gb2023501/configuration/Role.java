package com.choongang.gb2023501.configuration;

public enum Role {
    EDUCATOR(1),  // 교육자
    STUDENT(2),   // 학생
    USER(3),      // 일반 사용자
    ADMIN(4);     // 관리자

    private final int category;  // 각 역할에 대응하는 숫자 값

    Role(int category) {
        this.category = category;
    }

    public int getCategory() {
        return category;
    }
    

    public static Role getRoleByCategory(int category) {
        for (Role role : Role.values()) {
            if (role.getCategory() == category) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid category: " + category);
    }
}

//public enum Role {
//	//권한 부여 3355조 코드 참고
//	EDUCATOR(1, "EDUCATOR"),
//	STUDENT(2,"STUDENT"),
//	USER(3,"USER"),
//	ADMIN(4,"ADMIN");
//	
//	private final int 	 key;
//	private final String value;
//	
//	Role(int key, String value) {
//		this.key 	= key;
//		this.value	= value;
//	}
//	
//	public String getValue() {
//		return value;
//		
//	}
//	
//	public int getKey() {
//		return key;
//	}
//	
//	public static String getValueByKey(int key) {
//		for( Role role : Role.values()) {
//			if(role.getKey() == key) {
//				return role.getValue();
//			}
//		}
//		throw new IllegalArgumentException("Invalid role key: " + key);
//		
//	}
//	
//}

