package com.petHospital.backend.enumeration;

public enum UserStatusEnum {
	ENABLE(0), DISABLE(1),DELETE(2);
	
	private int index;  
    // 构造方法  
    private UserStatusEnum(int index) {  
        this.index = index;  
    }
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}  
    
}
