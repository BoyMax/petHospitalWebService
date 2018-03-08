package com.petHospital.backend.service;

import com.petHospital.backend.model.User;

public interface UserService {
	public User retreiveUser(String userName);
	public User createUser(String name,String password);
}
