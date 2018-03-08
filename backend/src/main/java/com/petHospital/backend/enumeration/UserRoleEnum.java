package com.petHospital.backend.enumeration;

public enum UserRoleEnum {
	ADMIN(0), RECEPTION(1),VETERINARIAN(2),ASSISTANT(3);
	private int index;  
    // 构造方法  
    private UserRoleEnum(int index) {  
        this.index = index;  
    }
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}  
}
