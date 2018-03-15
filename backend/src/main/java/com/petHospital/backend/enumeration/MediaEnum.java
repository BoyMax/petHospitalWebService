package com.petHospital.backend.enumeration;

public enum MediaEnum {
	PICTURE(0), VIDEO(1);
	private int index;  
    // 构造方法  
    private MediaEnum(int index) {  
        this.index = index;  
    }
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}  
}
