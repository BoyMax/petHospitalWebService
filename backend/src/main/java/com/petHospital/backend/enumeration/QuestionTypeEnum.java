package com.petHospital.backend.enumeration;

public enum QuestionTypeEnum {
	CHOICE(0), Filling(1),JUDGEMENT(2),DESCRIPTION(3);
	private int index;  
    // 构造方法  
    private QuestionTypeEnum(int index) {  
        this.index = index;  
    }
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}  
}
