package com.choongang.gb2023501.configuration;

public enum Role {
	USER(1, "USER");
	
	private final int 	 key;
	private final String value;
	
	Role(int key, String value) {
		this.key 	= key;
		this.value	= value;
	}
	
	public String getValue() {
		return value;
		
	}
	
	public int getKey() {
		return key;
	}
	
	public static String getValueByKey(int key) {
		for( Role role : Role.values()) {
			if(role.getKey() == key) {
				return role.getValue();
			}
		}
		throw new IllegalArgumentException("Invalid role key: " + key);
		
	}
	
}
