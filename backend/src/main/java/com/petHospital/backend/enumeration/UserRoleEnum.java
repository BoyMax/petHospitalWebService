package com.petHospital.backend.enumeration;

public enum UserRoleEnum {
	ADMIN(0), RECEPTION(1),VETERINARIAN(2),ASSISTANT(3);
	private int index;  
    // 构造方法  
    private UserRoleEnum(int index) {  
        this.index = index;  
    }
	public UserRoleEnum getIndex(int index) {
		UserRoleEnum role;
		switch (index){
		case 0:
			role = UserRoleEnum.ADMIN;
			break;
		case 1:
			role = UserRoleEnum.RECEPTION;
			break;
		case 2:
			role = UserRoleEnum.VETERINARIAN;
			break;
		case 3:
			role = UserRoleEnum.ASSISTANT;
			break;
		default:
			role = UserRoleEnum.ASSISTANT;
		}
		return role;
	}
	public void setIndex(int index) {
		this.index = index;
	}  
}
