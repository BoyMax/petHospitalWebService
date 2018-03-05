package com.petHospital.backend.service;

import com.petHospital.backend.dao.UserRepository;
import com.petHospital.backend.dao.UserRepositoryImpl;
import com.petHospital.backend.model.User;

public class UserServiceImpl implements UserService {

	public User retreiveUser(String userName) {
		UserRepository ur = new UserRepositoryImpl();
		return ur.getUserByName(userName);
	}

}
