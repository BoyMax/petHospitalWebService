package com.petHospital.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petHospital.backend.dao.UserRepository;
import com.petHospital.backend.model.User;

@Service
public class UserServiceImpl implements UserService {

	 @Autowired
	 UserRepository userRepository;
	 
	public User retreiveUser(String userName) {
		return userRepository.getUserByName(userName);
	}

	public User createUser(String name, String password) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		return userRepository.save(user);
	}

}
